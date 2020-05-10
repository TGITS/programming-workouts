import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordCount {

    public Map<String, Integer> phrase(String words) {
        return Arrays
                .stream(words.toLowerCase().split("[\\s,;:.!?&@$%^]"))
                .filter(s -> s.length() > 0)
                .map(this::removeQuotationMarks)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(e -> 1)));
    }

    private boolean hasQuotationMarks(String s) {
        return s.startsWith("'") && s.endsWith("'");
    }

    private String removeQuotationMarks(String s) {
        if (hasQuotationMarks(s)) {
            return s.substring(1, s.length() - 1);
        }
        return s;
    }
}