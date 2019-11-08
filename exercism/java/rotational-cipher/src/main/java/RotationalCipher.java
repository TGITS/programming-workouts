class RotationalCipher {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private int shiftKey;

    RotationalCipher(int shiftKey) {
        this.shiftKey = shiftKey;
    }

    String rotate(String data) {
        StringBuilder rotatedData = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            if (Character.isLetter(c)) {
                rotatedData.append(rotateCharacter(c));
            } else {
                rotatedData.append(c);
            }
        }
        return rotatedData.toString();
    }

    private char rotateCharacter(char c) {
        boolean isUpperCase = Character.isUpperCase(c);
        if (isUpperCase) {
            c = Character.toLowerCase(c);
        }
        int originalPositionInAlphabet = ALPHABET.indexOf(c);
        int rotatedPosition = (originalPositionInAlphabet + shiftKey) % ALPHABET.length();
        return isUpperCase ? Character.toUpperCase(ALPHABET.charAt(rotatedPosition)) : ALPHABET.charAt(rotatedPosition);
    }
}
