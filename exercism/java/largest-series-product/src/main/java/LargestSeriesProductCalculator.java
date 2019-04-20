import java.util.Arrays;

class LargestSeriesProductCalculator {

    private int[] numbers;

    LargestSeriesProductCalculator(String inputNumber) {
        if (!inputNumber.isBlank() && !inputNumber.matches("\\d+")) {
            throw new IllegalArgumentException("String to search may only contain digits.");
        }

        numbers = new int[inputNumber.length()];
        for (int i = 0; i < inputNumber.length(); i++) {
            numbers[i] = Integer.parseInt(String.valueOf(inputNumber.charAt(i)));
        }
    }

    long calculateLargestProductForSeriesLength(int numberOfDigits) {

        if (numberOfDigits > numbers.length) {
            throw new IllegalArgumentException("Series length must be less than or equal to the length of the string to search.");
        }

        if (numberOfDigits < 0) {
            throw new IllegalArgumentException("Series length must be non-negative.");
        }

        if (numberOfDigits == numbers.length) {
            return numberOfDigits == 0 ? 1 : Arrays.stream(numbers).reduce(1, (a, b) -> a * b);
        } else {
            int max = 0;
            for (int i = 0; i + numberOfDigits <= numbers.length; i++) {
                int product = 1;
                for (int j = i; j < i + numberOfDigits; j++) {
                    product = product * numbers[j];
                }
                if (max < product) {
                    max = product;
                }
            }
            return max;
        }
    }
}
