class CollatzCalculator {

    private static final String errorMessage = "Only natural numbers are allowed";

    int computeStepCount(int start) {
        if(start < 1) {
            throw new IllegalArgumentException(errorMessage);
        }

        int numberOfSteps = 0;
        int temp = start;

        while (temp != 1) {
            if (temp % 2 == 0) {
               temp = temp / 2;
            } else {
                temp = temp * 3 + 1;
            }
            numberOfSteps++;
        }
        return numberOfSteps;
    }

}
