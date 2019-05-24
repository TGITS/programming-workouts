public class PhoneNumber {

    private static final String WRONG_LENGTH_EXCEPTION_MESSAGE = "incorrect number of digits";
    private static final String NUMBER_IS_11_DIGITS_BUT_DOES_NOT_START_WITH_1_EXCEPTION_MESSAGE = "11 digits must start with 1";
    private static final String MORE_THAN_11_DIGITS_EXCEPTION_MESSAGE = "more than 11 digits";
    private static final String ILLEGAL_CHARACTER_EXCEPTION_MESSAGE = "letters not permitted";
    private static final String ILLEGAL_PUNCTUATION_EXCEPTION_MESSAGE = "punctuations not permitted";
    private static final String AREA_CODE_STARTS_WITH_ZERO_EXCEPTION_MESSAGE = "area code cannot start with zero";
    private static final String AREA_CODE_STARTS_WITH_ONE_EXCEPTION_MESSAGE = "area code cannot start with one";
    private static final String EXCHANGE_CODE_STARTS_WITH_ZERO_EXCEPTION_MESSAGE = "exchange code cannot start with zero";
    private static final String EXCHANGE_CODE_STARTS_WITH_ONE_EXCEPTION_MESSAGE = "exchange code cannot start with one";

    private String cleansedValue;

    public PhoneNumber(String s) {
        this.cleansedValue = s.replaceAll("[\\.\\(\\)\\s-\\+]", "");
        if (cleansedValue.length() < 10) {
            throw new IllegalArgumentException(WRONG_LENGTH_EXCEPTION_MESSAGE);
        }
        if (cleansedValue.length() == 11 && !cleansedValue.startsWith("1")) {
            throw new IllegalArgumentException(NUMBER_IS_11_DIGITS_BUT_DOES_NOT_START_WITH_1_EXCEPTION_MESSAGE);
        }
        if (cleansedValue.length() > 11) {
            throw new IllegalArgumentException(MORE_THAN_11_DIGITS_EXCEPTION_MESSAGE);
        }
        if (cleansedValue.matches("\\d*[a-zA-Z]+\\d*")) {
            throw new IllegalArgumentException(ILLEGAL_CHARACTER_EXCEPTION_MESSAGE);
        }
        if (cleansedValue.matches("\\d*[@:!]+\\d*")) {
            throw new IllegalArgumentException(ILLEGAL_PUNCTUATION_EXCEPTION_MESSAGE);
        }
        if (this.getNumber().startsWith("0")) {
            throw new IllegalArgumentException(AREA_CODE_STARTS_WITH_ZERO_EXCEPTION_MESSAGE);
        }
        if (this.getNumber().startsWith("1")) {
            throw new IllegalArgumentException(AREA_CODE_STARTS_WITH_ONE_EXCEPTION_MESSAGE);
        }
        if (this.getNumber().substring(3, 4).startsWith("0")) {
            throw new IllegalArgumentException(EXCHANGE_CODE_STARTS_WITH_ZERO_EXCEPTION_MESSAGE);
        }
        if (this.getNumber().substring(3, 4).startsWith("1")) {
            throw new IllegalArgumentException(EXCHANGE_CODE_STARTS_WITH_ONE_EXCEPTION_MESSAGE);
        }
    }

    public String getNumber() {
        return cleansedValue.length() == 11 ? this.cleansedValue.substring(1) : this.cleansedValue;
    }
}
