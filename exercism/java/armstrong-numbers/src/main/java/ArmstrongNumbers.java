class ArmstrongNumbers {

    boolean isArmstrongNumber(int numberToCheck) {
        String numberAsString = Integer.toString(numberToCheck);
        int numberLength = numberAsString.length();
        int sumOfPower = 0;
        for (int i = 0; i < numberLength; i++) {
            int digit = Integer.parseInt(Character.toString(numberAsString.charAt(i)));
            sumOfPower += (int) Math.round(Math.pow(digit, numberLength));
        }
        return sumOfPower == numberToCheck;
    }
}
