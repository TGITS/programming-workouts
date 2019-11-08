import java.util.stream.Collectors;

class RotationalCipher {
    private static final int LOWER_CASE_Z_CODE_POINT = "z".codePointAt(0);
    private static final int LOWER_CASE_A_CODE_POINT = "a".codePointAt(0);

    private int shiftKey;

    RotationalCipher(int shiftKey) {
        this.shiftKey = shiftKey;
    }

    String rotate(String data) {
        return data.codePoints().mapToObj(this::processCodePoint).collect(Collectors.joining());
    }

    private String processCodePoint(int codePoint) {
        if (Character.isLetter(codePoint)) {
            return rotateCodePoint(codePoint);
        } else {
            return new String(Character.toChars(codePoint));
        }
    }

    private String rotateCodePoint(int codePoint) {
        boolean isUpperCase = Character.isUpperCase(codePoint);
        if (isUpperCase) {
            codePoint = Character.toLowerCase(codePoint);
        }
        int rotatedCodePoint = codePoint + shiftKey;
        if (rotatedCodePoint > LOWER_CASE_Z_CODE_POINT) {
            rotatedCodePoint = rotatedCodePoint - (LOWER_CASE_Z_CODE_POINT + 1) + LOWER_CASE_A_CODE_POINT;
        }
        return isUpperCase ? new String(Character.toChars(rotatedCodePoint)).toUpperCase() : new String(Character.toChars(rotatedCodePoint));
    }


}
