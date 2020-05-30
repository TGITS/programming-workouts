import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordCount {

    public Map<String, Integer> phrase(String words) {
        return Arrays
                .stream(words.toLowerCase().split("[\\s,;:.!?&@$%^]"))
                .filter(s -> s.length() > 0)
                .map(s -> s.replaceAll("^'|'$",""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(e -> 1)));
    }
}