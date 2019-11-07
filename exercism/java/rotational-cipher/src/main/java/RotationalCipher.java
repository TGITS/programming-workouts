class RotationalCipher {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static final String NOT_ROTATED_CHARACTERS = " .,;:!?'0123456789";
    private int shiftKey;

    RotationalCipher(int shiftKey) {
        this.shiftKey = shiftKey;
    }

    String rotate(String data) {
        StringBuilder rotatedData = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            if(isNotRotatedCharacter(c)){
                rotatedData.append(c);
            } else {
                rotatedData.append(rotateCharacter(c));
            }
        }
        return rotatedData.toString();
    }

    private boolean isNotRotatedCharacter(char c){
        return NOT_ROTATED_CHARACTERS.indexOf(c) >= 0;
    }

    private char rotateCharacter(char c) {
        boolean isUpperCase = Character.isUpperCase(c);
        if(isUpperCase) {
            c = Character.toLowerCase(c);
        }
        int originalPositionInAlphabet = ALPHABET.indexOf(c);
        int rotatedPosition = (originalPositionInAlphabet + shiftKey) % ALPHABET.length();
        return isUpperCase ? Character.toUpperCase(ALPHABET.charAt(rotatedPosition)): ALPHABET.charAt(rotatedPosition);
    }
}
