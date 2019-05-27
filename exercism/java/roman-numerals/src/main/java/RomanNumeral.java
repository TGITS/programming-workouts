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
    
    private final int i;
    private final String romanNumeral;

    public RomanNumeral(int i) {
        this.i = i;
        this.romanNumeral = computeRomanNumeralFromNumber(i);
    }

    public String getRomanNumeral() {
        return this.romanNumeral;
    }

    private String computeRomanNumeralFromNumber(int number) {

        if (number >= 1000) {
            return DIGIT_TO_ROMAN_NUMERAL.get(1000) + computeRomanNumeralFromNumber(number - 1000);
        }
        if (number >= 900) {
            return DIGIT_TO_ROMAN_NUMERAL.get(900) + computeRomanNumeralFromNumber(number - 900);
        }
        if (number >= 500) {
            return DIGIT_TO_ROMAN_NUMERAL.get(500) + computeRomanNumeralFromNumber(number - 500);
        }
        if (number >= 400) {
            return DIGIT_TO_ROMAN_NUMERAL.get(400) + computeRomanNumeralFromNumber(number - 400);
        }
        if (number >= 100) {
            return DIGIT_TO_ROMAN_NUMERAL.get(100) + computeRomanNumeralFromNumber(number - 100);
        }
        if (number >= 90) {
            return DIGIT_TO_ROMAN_NUMERAL.get(90) + computeRomanNumeralFromNumber(number - 90);
        }
        if (number >= 50) {
            return DIGIT_TO_ROMAN_NUMERAL.get(50) + computeRomanNumeralFromNumber(number - 50);
        }
        if (number >= 40) {
            return DIGIT_TO_ROMAN_NUMERAL.get(40) + computeRomanNumeralFromNumber(number - 40);
        }
        if (number >= 10) {
            return DIGIT_TO_ROMAN_NUMERAL.get(10) + computeRomanNumeralFromNumber(number - 10);
        }
        if (number >= 9) {
            return DIGIT_TO_ROMAN_NUMERAL.get(9) + computeRomanNumeralFromNumber(number - 9);
        }
        if (number >= 5) {
            return DIGIT_TO_ROMAN_NUMERAL.get(5) + computeRomanNumeralFromNumber(number - 5);
        }
        if (number >= 4) {
            return DIGIT_TO_ROMAN_NUMERAL.get(4) + computeRomanNumeralFromNumber(number - 4);
        }
        if (number >= 1) {
            return DIGIT_TO_ROMAN_NUMERAL.get(1) + computeRomanNumeralFromNumber(number - 1);
        }
        return "";
    }
}
