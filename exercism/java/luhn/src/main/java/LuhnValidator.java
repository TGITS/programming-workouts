class LuhnValidator {

    boolean isValid(String candidate) {
        return candidate != null && candidate.trim().length() > 1 && candidate.matches("(\\d|\\s)+") && processStringOfDigits(candidate.trim()) % 10 == 0;
    }

    private int processStringOfDigits(String candidate) {
        int result = 0;
        boolean toggle = false;
        for (int i = candidate.length() - 1; i >= 0; i--) {
            String currentString = candidate.substring(i, i + 1);
            if (!currentString.isBlank()) {
                int currentNumber = Integer.parseInt(currentString);
                if (toggle) {
                    currentNumber = currentNumber * 2;
                    if (currentNumber > 9) {
                        currentNumber -= 9;
                    }
                }
                result += currentNumber;
                toggle = !toggle;
            }
        }
        return result;
    }
}
