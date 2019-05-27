import java.util.HashMap;
import java.util.Map;

public class RomanNumeral {

    private final static Map<Integer, String> DIGIT_TO_ROMAN_NUMERAL = new HashMap<>();

    static {
        DIGIT_TO_ROMAN_NUMERAL.put(1, "I");
        DIGIT_TO_ROMAN_NUMERAL.put(4, "IV");
        DIGIT_TO_ROMAN_NUMERAL.put(5, "V");
        DIGIT_TO_ROMAN_NUMERAL.put(9, "IX");
        DIGIT_TO_ROMAN_NUMERAL.put(10, "X");
        DIGIT_TO_ROMAN_NUMERAL.put(40, "XL");
        DIGIT_TO_ROMAN_NUMERAL.put(50, "L");
        DIGIT_TO_ROMAN_NUMERAL.put(90, "XC");
        DIGIT_TO_ROMAN_NUMERAL.put(100, "C");
        DIGIT_TO_ROMAN_NUMERAL.put(400, "CD");
        DIGIT_TO_ROMAN_NUMERAL.put(500, "D");
        DIGIT_TO_ROMAN_NUMERAL.put(900, "CM");
        DIGIT_TO_ROMAN_NUMERAL.put(1000, "M");
    }

    private final static int[] BASE_VALUES = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

    private final String romanNumeral;

    public RomanNumeral(int i) {
        this.romanNumeral = computeRomanNumeralFromNumber(i);
    }

    public String getRomanNumeral() {
        return this.romanNumeral;
    }

    private String computeRomanNumeralFromNumber(int number) {
        for (int value : BASE_VALUES) {
            if (number >= value) {
                return DIGIT_TO_ROMAN_NUMERAL.get(value) + computeRomanNumeralFromNumber(number - value);
            }
        }
        return "";
    }
}
