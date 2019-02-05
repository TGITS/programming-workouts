import java.util.function.Function;
import java.util.stream.Collectors;

class IsogramChecker {

    boolean isIsogram(String phrase) {

        if(phrase == null) { return false; }

        if(phrase.isEmpty() || phrase.isBlank()) { return true; }

        return  phrase
                .toLowerCase()
                .chars()
                .mapToObj(c -> (char) c)
                .filter(c -> c != '-' && c != ' ')
                .collect(Collectors.groupingBy(
                        Function.identity(), Collectors.counting()
                ))
                .values()
                .stream()
                .noneMatch( i -> i > 1);
    }
}
