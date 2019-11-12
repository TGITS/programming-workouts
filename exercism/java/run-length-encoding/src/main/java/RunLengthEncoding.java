import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class RunLengthEncoding {

    private static final String NULL_INPUT_ARE_NOT_POSSIBLE = "Null input values are not accepted";

    public String encode(String s) {
        if (s == null) {
            throw new IllegalArgumentException(NULL_INPUT_ARE_NOT_POSSIBLE);
        }

        if (s.isEmpty() || s.length() == 1) {
            return s;
        }

        Deque<CharacterCount> characterCounts = new LinkedList<>();
        characterCounts.addLast(new CharacterCount(s.charAt(0)));
        for (int i = 1; i < s.length(); i++) {
            char currentCharacter = s.charAt(i);
            CharacterCount lastlyAddedCharacterCount = characterCounts.getLast();
            if (lastlyAddedCharacterCount.isSameCharacter(currentCharacter)) {
                lastlyAddedCharacterCount.incrementCharacterCount();
            } else {
                characterCounts.addLast(new CharacterCount(currentCharacter));
            }
        }

        return characterCounts.stream().map(CharacterCount::toString).collect(Collectors.joining());
    }

    public String decode(String s) {
        if (s == null) {
            throw new IllegalArgumentException(NULL_INPUT_ARE_NOT_POSSIBLE);
        }

        if (s.isEmpty() || s.length() == 1) {
            return s;
        }

        String decodedString = "";
        String currentNumber = "";
        int stringLength = s.length();
        for (int i = 0; i < stringLength; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                currentNumber += c;
            } else {
                int number = currentNumber.isEmpty() ? 1 : Integer.parseInt(currentNumber);
                for (int j = 0; j < number; j++) {
                    decodedString += c;
                }
                currentNumber = "";
            }
        }
        return decodedString;
    }

}

final class CharacterCount {
    private final char character;
    private int count;

    CharacterCount(char character) {
        this.character = character;
        count = 1;
    }

    void incrementCharacterCount() {
        count++;
    }

    boolean isSameCharacter(char c) {
        return this.character == c;
    }

    public String toString() {
        if (count == 1) {
            return Character.toString(this.character);
        }
        return "" + count + this.character;
    }


}