import java.util.stream.IntStream;

class NaturalNumber {

    private final int number;

    public NaturalNumber(int i) {
        if(i <= 0) {
            throw new IllegalArgumentException("You must supply a natural number (positive integer)");
        }
        number = i;
    }

    public Classification getClassification() {
        if (isPerfect()) {
            return Classification.PERFECT;
        }

        if (isAbundant()) {
            return Classification.ABUNDANT;
        }

        return Classification.DEFICIENT;
    }

    private int aliquotSum() {
        return IntStream.range(1, number).filter(i -> number % i == 0).sum();
    }

    private boolean isPerfect() {
        return number == aliquotSum();
    }

    private boolean isAbundant() {
        return aliquotSum() > number;
    }

}
