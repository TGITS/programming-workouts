import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Anagram {

    private String word;

    public Anagram(String word) {
        this.word = word;
    }

    public List<String> match(List<String> possibleAnagrams) {
        return possibleAnagrams
                .stream()
                .filter(possibleAnagram -> !possibleAnagram.equalsIgnoreCase(word))
                .filter(possibleAnagram -> stringToCharacterList(possibleAnagram.toLowerCase()).equals(stringToCharacterList(word.toLowerCase())))
                .collect(Collectors.toList());
    }

    private List<String> stringToCharacterList(String word) {
        return Arrays.stream(word.split("")).sorted().collect(Collectors.toList());
    }
}
