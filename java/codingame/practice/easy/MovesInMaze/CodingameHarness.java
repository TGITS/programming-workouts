///usr/bin/env jbang "$0" "$@" ; exit $?

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

class CodingameHarness {

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            printUsage();
            System.exit(2);
        }

        String command = args[0];
        switch (command) {
            case "run" -> runCommand(args);
            case "judge" -> judgeCommand(args);
            case "judge-all" -> judgeAllCommand(args);
            default -> {
                System.err.println("Unknown command: " + command);
                printUsage();
                System.exit(2);
            }
        }
    }

    private static void runCommand(String[] args) throws Exception {
        if (args.length < 2 || args.length > 3) {
            System.err.println("Usage: jbang CodingameHarness.java run <inputFile> [solutionFile]");
            System.exit(2);
        }

        Path inputFile = Path.of(args[1]);
        Path solutionFile = args.length == 3 ? Path.of(args[2]) : Path.of("MovesInMaze.java");

        String output = runSolution(solutionFile, inputFile);
        System.out.print(output);
    }

    private static void judgeCommand(String[] args) throws Exception {
        if (args.length < 3 || args.length > 4) {
            System.err.println("Usage: jbang CodingameHarness.java judge <inputFile> <expectedFile> [solutionFile]");
            System.exit(2);
        }

        Path inputFile = Path.of(args[1]);
        Path expectedFile = Path.of(args[2]);
        Path solutionFile = args.length == 4 ? Path.of(args[3]) : Path.of("MovesInMaze.java");

        boolean ok = judgeSingle(solutionFile, inputFile, expectedFile, true);
        System.exit(ok ? 0 : 1);
    }

    private static void judgeAllCommand(String[] args) throws Exception {
        if (args.length > 3) {
            System.err.println("Usage: jbang CodingameHarness.java judge-all [testsDir] [solutionFile]");
            System.exit(2);
        }

        Path testsDir = args.length >= 2 ? Path.of(args[1]) : Path.of("tests");
        Path solutionFile = args.length == 3 ? Path.of(args[2]) : Path.of("MovesInMaze.java");

        if (!Files.isDirectory(testsDir)) {
            System.err.println("Tests directory not found: " + testsDir);
            System.exit(2);
        }

        List<Path> inputFiles = new ArrayList<>();
        try (Stream<Path> stream = Files.list(testsDir)) {
            stream
                .filter(path -> path.getFileName().toString().endsWith(".in"))
                .sorted(Comparator.comparing(path -> path.getFileName().toString()))
                .forEach(inputFiles::add);
        }

        if (inputFiles.isEmpty()) {
            System.err.println("No .in files found in " + testsDir);
            System.exit(2);
        }

        int passed = 0;
        int failed = 0;

        for (Path inputFile : inputFiles) {
            String fileName = inputFile.getFileName().toString();
            String expectedName = fileName.substring(0, fileName.length() - 3) + ".out";
            Path expectedFile = inputFile.getParent().resolve(expectedName);

            if (!Files.exists(expectedFile)) {
                System.out.println("FAIL " + fileName + " -> missing expected output: " + expectedName);
                failed++;
                continue;
            }

            boolean ok = judgeSingle(solutionFile, inputFile, expectedFile, false);
            if (ok) {
                System.out.println("PASS " + fileName);
                passed++;
            } else {
                System.out.println("FAIL " + fileName);
                failed++;
            }
        }

        System.out.println();
        System.out.println("Summary: " + passed + " passed, " + failed + " failed");
        System.exit(failed == 0 ? 0 : 1);
    }

    private static boolean judgeSingle(Path solutionFile, Path inputFile, Path expectedFile, boolean verbose) throws Exception {
        String actualOutput = runSolution(solutionFile, inputFile);
        String expectedOutput = Files.readString(expectedFile, StandardCharsets.UTF_8);

        String normalizedActual = normalizeOutput(actualOutput);
        String normalizedExpected = normalizeOutput(expectedOutput);

        boolean ok = normalizedActual.equals(normalizedExpected);

        if (verbose) {
            if (ok) {
                System.out.println("PASS");
            } else {
                System.out.println("FAIL");
                printDiff(normalizedExpected, normalizedActual);
            }
        }

        return ok;
    }

    private static String runSolution(Path solutionFile, Path inputFile) throws Exception {
        if (!Files.exists(solutionFile)) {
            throw new IllegalArgumentException("Solution file not found: " + solutionFile);
        }

        if (!Files.exists(inputFile)) {
            throw new IllegalArgumentException("Input file not found: " + inputFile);
        }

        ProcessBuilder processBuilder = new ProcessBuilder(resolveJbangCommand(), solutionFile.toString());
        processBuilder.redirectInput(inputFile.toFile());

        Process process = processBuilder.start();
        byte[] stdout = process.getInputStream().readAllBytes();
        byte[] stderr = process.getErrorStream().readAllBytes();
        int exitCode = process.waitFor();

        if (exitCode != 0) {
            String stderrText = new String(stderr, StandardCharsets.UTF_8);
            throw new IOException("Solution process failed with exit code " + exitCode + "\n" + stderrText);
        }

        return new String(stdout, StandardCharsets.UTF_8);
    }

    private static String resolveJbangCommand() {
        String osName = System.getProperty("os.name", "").toLowerCase();
        if (osName.contains("win")) {
            return "jbang.cmd";
        }
        return "jbang";
    }

    private static String normalizeOutput(String text) {
        String normalized = text.replace("\r\n", "\n").replace('\r', '\n');
        String[] lines = normalized.split("\n", -1);
        List<String> cleaned = new ArrayList<>(lines.length);

        for (String line : lines) {
            cleaned.add(rstrip(line));
        }

        while (!cleaned.isEmpty() && cleaned.get(cleaned.size() - 1).isEmpty()) {
            cleaned.remove(cleaned.size() - 1);
        }

        return String.join("\n", cleaned);
    }

    private static String rstrip(String line) {
        int end = line.length();
        while (end > 0) {
            char c = line.charAt(end - 1);
            if (c == ' ' || c == '\t') {
                end--;
            } else {
                break;
            }
        }
        return line.substring(0, end);
    }

    private static void printDiff(String expected, String actual) {
        String[] expectedLines = expected.split("\n", -1);
        String[] actualLines = actual.split("\n", -1);
        int maxLines = Math.max(expectedLines.length, actualLines.length);

        for (int i = 0; i < maxLines; i++) {
            String e = i < expectedLines.length ? expectedLines[i] : "<missing>";
            String a = i < actualLines.length ? actualLines[i] : "<missing>";
            if (!e.equals(a)) {
                System.out.println("First difference at line " + (i + 1));
                System.out.println("Expected: " + e);
                System.out.println("Actual  : " + a);
                return;
            }
        }

        System.out.println("Outputs differ, but no line-level difference was found.");
    }

    private static void printUsage() {
        System.out.println("Local CodinGame harness for JBang solutions");
        System.out.println();
        System.out.println("Commands:");
        System.out.println("  run <inputFile> [solutionFile]");
        System.out.println("  judge <inputFile> <expectedFile> [solutionFile]");
        System.out.println("  judge-all [testsDir] [solutionFile]");
    }
}
