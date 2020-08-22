import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;

public class Robot {

    private static Set<String> existingNames = new HashSet<>();
    private static Random random = new Random();

    private String name = null;

    public String getName() {
        if (name == null) {
            name = generateName();
            while (existingNames.contains(name)) {
                name = generateName();
            }
            existingNames.add(name);
        }
        return name;

    }

    public void reset() {
        this.name = null;
    }

    private String generateName() {
        return String.valueOf(generateRandomUpperCaseLetter()) +
                generateRandomUpperCaseLetter() +
                generateRandomDigit() +
                generateRandomDigit() +
                generateRandomDigit();
    }

    private char generateRandomUpperCaseLetter() {
        String UPPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return UPPPERCASE_LETTERS.charAt(random.nextInt(UPPPERCASE_LETTERS.length()));
    }

    private char generateRandomDigit() {
        String DIGITS = "0123456789";
        return DIGITS.charAt(random.nextInt(DIGITS.length()));
    }

}