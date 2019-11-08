import java.util.stream.Collectors;

class RotationalCipher {
    private static final int ALPHABET_SIZE = 26;
    private int shiftKey;

    RotationalCipher(int shiftKey) {
        this.shiftKey = shiftKey;
    }

    String rotate(String data) {
        return data.codePoints()
                .mapToObj(this::processCodePoint)
                .collect(Collectors.joining());
    }

    private String processCodePoint(int codePoint) {
        if (Character.isLetter(codePoint)) {
            return rotateCodePoint(codePoint);
        } else {
            return Character.toString(codePoint);
        }
    }

    private String rotateCodePoint(int codePoint) {
        int rotatedCodePoint = codePoint + (shiftKey % ALPHABET_SIZE);
        return Character.getType(codePoint) == Character.getType(rotatedCodePoint)
                ? (Character.toString(rotatedCodePoint))
                : Character.toString(rotatedCodePoint - ALPHABET_SIZE);
    }

}
