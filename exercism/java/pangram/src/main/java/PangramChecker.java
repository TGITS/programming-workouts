import java.util.Set;
import java.util.stream.Collectors;

public class PangramChecker {

    private final static Set<String> alphabet = Set.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z");

    public boolean isPangram(String input) {
        return input.chars()
                .mapToObj(c -> Character.toString((char) c))
                .map(String::toLowerCase)
                .collect(Collectors.toSet())
                .containsAll(alphabet);
    }

}
