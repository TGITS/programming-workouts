public class PhoneNumber {

    private static final String WRONG_LENGTH_EXCEPTION_MESSAGE = "incorrect number of digits";

    private String cleansedValue;

    public PhoneNumber(String s) {
        this.cleansedValue= s.replaceAll("[\\.\\(\\)\\s-]","");
        if(cleansedValue.length() < 10) {
            throw new IllegalArgumentException(WRONG_LENGTH_EXCEPTION_MESSAGE);
        }
    }

    public String getNumber() {
        return this.cleansedValue;
    }
}
