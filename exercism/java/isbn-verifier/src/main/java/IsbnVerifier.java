class IsbnVerifier {

    boolean isValid(String stringToVerify) {
        String stringWithoutOutDashes = stringToVerify.trim().replaceAll("-", "");

        if (!stringWithoutOutDashes.matches("(\\d){9}(\\d|X)")) {
            return false;
        }

        int length = stringWithoutOutDashes.length();
        int sum = 0;
        for (int i = 0; i < length - 1; i++) {
            sum += Integer.parseInt(Character.toString(stringWithoutOutDashes.charAt(i))) * (10 - i);
        }

        String lastChar = Character.toString(stringWithoutOutDashes.charAt(length - 1));
        if (lastChar.equals("X")) {
            sum += 10;
        } else {
            sum += Integer.parseInt(lastChar);
        }

        return sum % 11 == 0;
    }

}
