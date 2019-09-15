import java.util.Arrays;
import java.util.stream.Collectors;

class Acronym {

    private String phrase;

    Acronym(String phrase) {
        this.phrase = phrase;
    }

    String get() {
        return Arrays.stream(this.phrase.replaceAll("\\-|\\,|_", " ").split("\\s+")).map(s -> Character.toString(s.charAt(0)).toUpperCase()).collect(Collectors.joining());
    }

}
