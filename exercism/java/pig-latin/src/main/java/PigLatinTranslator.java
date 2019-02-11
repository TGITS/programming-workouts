import java.util.Set;

public class PigLatinTranslator {
    public String translate(String sentence) {

        var words = sentence.split("\\s");

        var sb = new StringBuilder();
        for(var word:words) {
            sb.append(translateWord(word)).append(" ");
        }
        sb.deleteCharAt(sb.lastIndexOf(" "));
        return sb.toString();
    }

    private String translateWord(String word) {
        if (startsByTripleConsonantLetters(word) || startsByYAtTheEndOfAConsonant3LettersCluster(word) || startsByQuAndAPrecedingConsonantLetters(word)) {
            return String.format("%s%say", word.substring(3), word.substring(0, 3));
        }

        if (startsByDoubleConsonantLetters(word) || startsByYAtTheEndOfAConsonant2LettersCluster(word)) {
            return String.format("%s%say", word.substring(2), word.substring(0, 2));
        }

        if (startsBySimpleWowelLetter(word) || startsByDoubleWowelSound(word)) {
            return String.format("%say", word);
        }

        if (startsBySimpleConsonantLetter(word) || startsByYAfter1ConsonantLetter(word)) {
            return String.format("%s%say", word.substring(1), word.substring(0, 1));
        }

        return word;
    }

    private boolean startsBySimpleWowelLetter(String word) {
        return "aeiou".contains(word.substring(0,1));
    }
    private boolean startsBySimpleConsonantLetter(String word){
        return "bcdfghjklmnpqrstvwxyz".contains(word.substring(0,1));
    }

    private boolean startsByDoubleConsonantLetters(String word) {
        if (word.length() <= 1) { return false; }
        return Set.of(new String[]{"ch","qu","th","rh"}).contains(word.substring(0,2));
    }

    private boolean startsByQuAndAPrecedingConsonantLetters(String word) {
        if (word.length() <= 2) { return false; }
        return Set.of(new String[]{"squ"}).contains(word.substring(0,3));
    }

    private boolean startsByTripleConsonantLetters(String word) {
        if (word.length() <= 2) { return false; }
        return Set.of(new String[]{"thr", "sch"}).contains(word.substring(0,3));
    }

    private boolean startsByDoubleWowelSound(String word) {
        if (word.length() <= 1) { return false; }
        return Set.of(new String[]{"xr","yt"}).contains(word.substring(0,2));
    }

    private boolean startsByYAfter1ConsonantLetter(String word) {
        if (word.length() <= 2) { return false; }
        return startsByDoubleConsonantLetters(word) && word.substring(1,2).equalsIgnoreCase("y");
    }

    private boolean startsByYAtTheEndOfAConsonant2LettersCluster(String word) {
        if (word.length() <= 3) { return false; }
        return startsByDoubleConsonantLetters(word) && word.substring(3,4).equalsIgnoreCase("y");
    }

    private boolean startsByYAtTheEndOfAConsonant3LettersCluster(String word) {
        if (word.length() <= 4) { return false; }
        return startsByTripleConsonantLetters(word) && word.substring(4,5).equalsIgnoreCase("y");
    }
}
