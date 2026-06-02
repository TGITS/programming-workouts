///usr/bin/env jbang "$0" "$@" ; exit $?
//JAVA 17+
//DEPS info.picocli:picocli:4.7.5
//DEPS com.github.javaparser:javaparser-core:3.26.0

import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.ParserConfiguration.LanguageLevel;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.EnumDeclaration;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Command(
    name = "enum-extractor",
    mixinStandardHelpOptions = true,
    version = "1.0",
    description = "Scans a Java codebase and extracts all enum declarations with their constants and values."
)
class EnumExtractor implements Callable<Integer> {

    @Parameters(index = "0", paramLabel = "SOURCE_DIR", description = "Root directory of the Java source code to scan.")
    private Path sourceDir;

    @Option(names = {"-o", "--output"}, paramLabel = "FILE", description = "Write output to FILE instead of stdout.")
    private Path outputFile;

    @Option(
        names = {"-f", "--format"},
        paramLabel = "FORMAT",
        description = "Output format: ${COMPLETION-CANDIDATES} (default: ${DEFAULT-VALUE}).",
        defaultValue = "text"
    )
    private Format format;

    @Option(
        names = {"-j", "--java-version"},
        paramLabel = "VERSION",
        description = "Java language version to use when parsing (e.g. 8, 11, 17, 21). " +
                      "Auto-detected from pom.xml, build.gradle or .java-version if not set."
    )
    private Integer javaVersion;

    enum Format { text, markdown }

    // -------------------------------------------------------------------------

    public static void main(String... args) {
        System.exit(new CommandLine(new EnumExtractor()).execute(args));
    }

    @Override
    public Integer call() throws Exception {
        if (!Files.isDirectory(sourceDir)) {
            System.err.println("Error: '" + sourceDir + "' is not a valid directory.");
            return 1;
        }

        LanguageLevelResult levelResult = resolveLanguageLevel(sourceDir);
        configureParser(levelResult.level());

        List<EnumInfo> enums = extractEnums(sourceDir);

        String output = switch (format) {
            case markdown -> renderMarkdown(enums, sourceDir, levelResult);
            case text     -> renderText(enums, sourceDir, levelResult);
        };

        if (outputFile != null) {
            Files.writeString(outputFile, output);
            System.out.printf("Extracted %d enum(s) → %s%n", enums.size(), outputFile.toAbsolutePath());
        } else {
            System.out.print(output);
        }

        return 0;
    }

    // -------------------------------------------------------------------------
    // Language level resolution
    // -------------------------------------------------------------------------

    /** Default Java version used when none is specified and none can be auto-detected. */
    static final int DEFAULT_JAVA_VERSION = 11;

    record LanguageLevelResult(int version, LanguageLevel level, String source) {}

    private LanguageLevelResult resolveLanguageLevel(Path sourceDir) {
        // 1. User-supplied flag wins
        if (javaVersion != null) {
            return new LanguageLevelResult(javaVersion, toLanguageLevel(javaVersion), "CLI option --java-version");
        }

        // 2. Auto-detect: walk up from sourceDir looking for build/config files
        Path dir = sourceDir.toAbsolutePath();
        while (dir != null) {
            Optional<LanguageLevelResult> found = detectInDirectory(dir);
            if (found.isPresent()) return found.get();
            dir = dir.getParent();
        }

        // 3. Fallback: built-in default version
        return new LanguageLevelResult(
                DEFAULT_JAVA_VERSION,
                toLanguageLevel(DEFAULT_JAVA_VERSION),
                "built-in default (Java " + DEFAULT_JAVA_VERSION + ")");
    }

    private Optional<LanguageLevelResult> detectInDirectory(Path dir) {
        // .java-version  (e.g. "17" or "17.0.2" or "openjdk-21")
        Path javaVersionFile = dir.resolve(".java-version");
        if (Files.isRegularFile(javaVersionFile)) {
            Optional<Integer> v = readFirstLine(javaVersionFile).flatMap(this::parseVersionString);
            if (v.isPresent())
                return Optional.of(new LanguageLevelResult(v.get(), toLanguageLevel(v.get()), ".java-version"));
        }

        // pom.xml  — maven.compiler.release / maven.compiler.source / java.version property
        Path pom = dir.resolve("pom.xml");
        if (Files.isRegularFile(pom)) {
            Optional<Integer> v = detectFromPom(pom);
            if (v.isPresent())
                return Optional.of(new LanguageLevelResult(v.get(), toLanguageLevel(v.get()), "pom.xml"));
        }

        // build.gradle  (Groovy DSL)
        Path gradleGroovy = dir.resolve("build.gradle");
        if (Files.isRegularFile(gradleGroovy)) {
            Optional<Integer> v = detectFromGradle(gradleGroovy);
            if (v.isPresent())
                return Optional.of(new LanguageLevelResult(v.get(), toLanguageLevel(v.get()), "build.gradle"));
        }

        // build.gradle.kts  (Kotlin DSL)
        Path gradleKts = dir.resolve("build.gradle.kts");
        if (Files.isRegularFile(gradleKts)) {
            Optional<Integer> v = detectFromGradle(gradleKts);
            if (v.isPresent())
                return Optional.of(new LanguageLevelResult(v.get(), toLanguageLevel(v.get()), "build.gradle.kts"));
        }

        return Optional.empty();
    }

    /** Patterns for pom.xml: release > source > java.version property. */
    private static final List<Pattern> POM_PATTERNS = List.of(
        Pattern.compile("<maven\\.compiler\\.release>\\s*(\\d+)\\s*</maven\\.compiler\\.release>"),
        Pattern.compile("<maven\\.compiler\\.source>\\s*(\\d[^<]*)\\s*</maven\\.compiler\\.source>"),
        Pattern.compile("<java\\.version>\\s*(\\d[^<]*)\\s*</java\\.version>"),
        Pattern.compile("<release>\\s*(\\d+)\\s*</release>"),
        Pattern.compile("<source>\\s*(\\d[^<]*)\\s*</source>")
    );

    private Optional<Integer> detectFromPom(Path pom) {
        try {
            String content = Files.readString(pom);
            for (Pattern p : POM_PATTERNS) {
                Matcher m = p.matcher(content);
                if (m.find()) {
                    Optional<Integer> v = parseVersionString(m.group(1).trim());
                    if (v.isPresent()) return v;
                }
            }
        } catch (IOException ignored) {}
        return Optional.empty();
    }

    /** Patterns for build.gradle / build.gradle.kts. */
    private static final List<Pattern> GRADLE_PATTERNS = List.of(
        Pattern.compile("(?:sourceCompatibility|javaVersion|release|languageVersion)\\s*[=:]\\s*[\"'`]?(?:JavaVersion\\.VERSION_)?(\\d[^\\s\"'`),]*)"),
        Pattern.compile("JavaVersion\\.VERSION_(\\d+)"),
        Pattern.compile("java\\s*\\{[^}]*sourceCompatibility\\s*=\\s*[\"'`]?(\\d[^\\s\"'`),]*)")
    );

    private Optional<Integer> detectFromGradle(Path gradle) {
        try {
            String content = Files.readString(gradle);
            for (Pattern p : GRADLE_PATTERNS) {
                Matcher m = p.matcher(content);
                if (m.find()) {
                    Optional<Integer> v = parseVersionString(m.group(1).trim());
                    if (v.isPresent()) return v;
                }
            }
        } catch (IOException ignored) {}
        return Optional.empty();
    }

    /** Converts a version string like "17", "17.0.2", "1.8" or "openjdk-21" to a major version int. */
    private Optional<Integer> parseVersionString(String raw) {
        // strip non-numeric prefix (e.g. "openjdk-", "temurin-")
        String s = raw.replaceFirst("^[^0-9]*", "").trim();
        if (s.isEmpty()) return Optional.empty();
        try {
            // Handle "1.8" legacy format
            if (s.startsWith("1.")) {
                int minor = Integer.parseInt(s.split("\\.")[1]);
                return Optional.of(minor);
            }
            // Take only the major part
            int major = Integer.parseInt(s.split("[.\\-]")[0]);
            return major > 0 ? Optional.of(major) : Optional.empty();
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    private Optional<String> readFirstLine(Path file) {
        try (Stream<String> lines = Files.lines(file)) {
            return lines.filter(l -> !l.isBlank()).findFirst();
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    /** Maps a Java major version to the closest supported JavaParser LanguageLevel.
     *  JavaParser 3.x starts at JAVA_8; versions below 8 (which have enums since 5)
     *  are silently mapped to JAVA_8 as the minimum available level. */
    private LanguageLevel toLanguageLevel(int version) {
        return switch (version) {
            case 9           -> LanguageLevel.JAVA_9;
            case 10          -> LanguageLevel.JAVA_10;
            case 11          -> LanguageLevel.JAVA_11;
            case 12          -> LanguageLevel.JAVA_12;
            case 13          -> LanguageLevel.JAVA_13;
            case 14          -> LanguageLevel.JAVA_14;
            case 15          -> LanguageLevel.JAVA_15;
            case 16          -> LanguageLevel.JAVA_16;
            case 17          -> LanguageLevel.JAVA_17;
            case 18          -> LanguageLevel.JAVA_18;
            case 19          -> LanguageLevel.JAVA_19;
            case 20          -> LanguageLevel.JAVA_20;
            case 21          -> LanguageLevel.JAVA_21;
            default          -> version > 21 ? LanguageLevel.BLEEDING_EDGE : LanguageLevel.JAVA_8;
        };
    }

    private void configureParser(LanguageLevel level) {
        ParserConfiguration config = new ParserConfiguration()
                .setLanguageLevel(level)
                .setAttributeComments(true);
        StaticJavaParser.setConfiguration(config);
    }

    // -------------------------------------------------------------------------
    // Extraction
    // -------------------------------------------------------------------------

    private List<EnumInfo> extractEnums(Path baseDir) throws IOException {
        List<EnumInfo> result = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(baseDir)) {
            paths.filter(p -> p.toString().endsWith(".java"))
                 .sorted()
                 .forEach(javaFile -> parseFile(javaFile, baseDir, result));
        }
        return result;
    }

    private void parseFile(Path javaFile, Path baseDir, List<EnumInfo> result) {
        try {
            CompilationUnit cu = StaticJavaParser.parse(javaFile);
            String packageName = cu.getPackageDeclaration()
                    .map(pd -> pd.getNameAsString())
                    .orElse("");

            // findAll is recursive: discovers enums nested inside classes too
            cu.findAll(EnumDeclaration.class).forEach(decl -> {
                EnumInfo info = new EnumInfo();
                info.name = decl.getNameAsString();
                info.packageName = packageName;
                info.relativePath = baseDir.relativize(javaFile).toString().replace('\\', '/');
                info.javadoc = decl.getJavadocComment()
                        .map(jd -> jd.parse().toText().strip())
                        .orElse(null);

                // Non-static fields (for metadata display)
                decl.getFields().stream()
                    .filter(f -> !f.isStatic())
                    .flatMap(f -> f.getVariables().stream())
                    .forEach(v -> info.fields.add(v.getTypeAsString() + " " + v.getNameAsString()));

                // Constructor parameter names (used as column headers)
                decl.getConstructors().stream()
                    .findFirst()
                    .ifPresent(ctor -> ctor.getParameters()
                        .forEach(p -> info.paramNames.add(p.getNameAsString())));

                // Enum constants
                decl.getEntries().forEach(entry -> {
                    ConstantInfo c = new ConstantInfo();
                    c.name = entry.getNameAsString();
                    c.javadoc = entry.getJavadocComment()
                            .map(jd -> jd.parse().toText().strip())
                            .orElse(null);
                    entry.getArguments().forEach(arg -> c.arguments.add(arg.toString()));
                    info.constants.add(c);
                });

                result.add(info);
            });
        } catch (Exception e) {
            System.err.println("Warning: skipping " + javaFile + " – " + e.getMessage());
        }
    }

    // -------------------------------------------------------------------------
    // Renderers
    // -------------------------------------------------------------------------

    private String renderText(List<EnumInfo> enums, Path baseDir, LanguageLevelResult level) {
        StringWriter sw = new StringWriter();
        PrintWriter out = new PrintWriter(sw);

        out.println("JAVA ENUM EXTRACTION REPORT");
        out.println("===========================");
        out.println("Source       : " + baseDir.toAbsolutePath());
        out.println("Java version : " + level.version() + "  (detected from: " + level.source() + ")");
        out.println("Enums        : " + enums.size());
        out.println();

        for (EnumInfo e : enums) {
            out.println("┌─────────────────────────────────────────────────────");
            out.println("│ Enum    : " + e.fullName());
            out.println("│ File    : " + e.relativePath);
            if (e.javadoc != null) {
                out.println("│ Comment : " + e.javadoc.replace("\n", "\n│           "));
            }
            if (!e.fields.isEmpty()) {
                out.println("│ Fields  : " + String.join(", ", e.fields));
            }
            out.printf("│ Values (%d):%n", e.constants.size());
            for (ConstantInfo c : e.constants) {
                out.print("│   • " + c.name);
                if (e.hasArgs()) {
                    out.print("(");
                    for (int i = 0; i < e.paramNames.size(); i++) {
                        if (i > 0) out.print(", ");
                        out.print(e.paramNames.get(i) + "=");
                        out.print(i < c.arguments.size() ? c.arguments.get(i) : "?");
                    }
                    out.print(")");
                }
                if (c.javadoc != null) {
                    out.print("  // " + c.javadoc.replaceAll("\\s+", " ").strip());
                }
                out.println();
            }
            out.println("└─────────────────────────────────────────────────────");
            out.println();
        }

        return sw.toString();
    }

    private String renderMarkdown(List<EnumInfo> enums, Path baseDir, LanguageLevelResult level) {
        StringWriter sw = new StringWriter();
        PrintWriter out = new PrintWriter(sw);

        out.println("# Java Enum Extraction Report");
        out.println();
        out.println("**Source:** `" + baseDir.toAbsolutePath() + "`  ");
        out.println("**Java version:** " + level.version() + " *(detected from: " + level.source() + ")*  ");
        out.println("**Enums found:** " + enums.size());
        out.println();
        out.println("---");
        out.println();

        for (EnumInfo e : enums) {
            boolean hasArgs = e.hasArgs();
            boolean hasDesc = e.hasDesc();

            out.println("## `" + e.fullName() + "`");
            out.println();
            out.println("- **File:** `" + e.relativePath + "`");
            if (e.javadoc != null) {
                out.println("- **Description:** " + e.javadoc.replaceAll("\\s+", " ").strip());
            }
            if (!e.fields.isEmpty()) {
                out.println("- **Fields:** `" + String.join("`, `", e.fields) + "`");
            }
            out.println();

            // --- Markdown table header ---
            StringBuilder header = new StringBuilder("| Constant |");
            StringBuilder sep    = new StringBuilder("|----------|");
            if (hasArgs) {
                for (String p : e.paramNames) {
                    header.append(" ").append(p).append(" |");
                    sep.append("---------|");
                }
            }
            if (hasDesc) {
                header.append(" description |");
                sep.append("-------------|");
            }
            out.println(header);
            out.println(sep);

            for (ConstantInfo c : e.constants) {
                StringBuilder row = new StringBuilder("| `").append(c.name).append("` |");
                if (hasArgs) {
                    for (int i = 0; i < e.paramNames.size(); i++) {
                        String val = i < c.arguments.size() ? "`" + c.arguments.get(i) + "`" : "";
                        row.append(" ").append(val).append(" |");
                    }
                }
                if (hasDesc) {
                    String doc = c.javadoc != null ? c.javadoc.replaceAll("\\s+", " ").strip() : "";
                    row.append(" ").append(doc).append(" |");
                }
                out.println(row);
            }
            out.println();

            // --- CSV block ---
            out.println("```csv");
            StringBuilder csvHeader = new StringBuilder("constant");
            if (hasArgs) e.paramNames.forEach(p -> csvHeader.append(",").append(p));
            if (hasDesc) csvHeader.append(",description");
            out.println(csvHeader);

            for (ConstantInfo c : e.constants) {
                StringBuilder row = new StringBuilder(c.name);
                if (hasArgs) {
                    for (int i = 0; i < e.paramNames.size(); i++) {
                        String val = i < c.arguments.size() ? c.arguments.get(i) : "";
                        row.append(",").append(csvEscape(val));
                    }
                }
                if (hasDesc) {
                    String doc = c.javadoc != null ? c.javadoc.replaceAll("\\s+", " ").strip() : "";
                    row.append(",").append(csvEscape(doc));
                }
                out.println(row);
            }
            out.println("```");
            out.println();
        }

        return sw.toString();
    }

    /** Escapes a value for RFC 4180 CSV: wraps in quotes if it contains comma, quote or newline. */
    private static String csvEscape(String value) {
        if (value.isEmpty()) return "";
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }

    // -------------------------------------------------------------------------
    // Data model
    // -------------------------------------------------------------------------

    static class EnumInfo {
        String name;
        String packageName;
        String relativePath;
        String javadoc;
        List<String> fields     = new ArrayList<>(); // "type name" — for metadata display
        List<String> paramNames = new ArrayList<>(); // constructor parameter names — for column headers
        List<ConstantInfo> constants = new ArrayList<>();

        String fullName() {
            return packageName.isEmpty() ? name : packageName + "." + name;
        }

        boolean hasArgs() { return !paramNames.isEmpty(); }
        boolean hasDesc() { return constants.stream().anyMatch(c -> c.javadoc != null); }
    }

    static class ConstantInfo {
        String name;
        String javadoc;
        List<String> arguments = new ArrayList<>();
    }
}
