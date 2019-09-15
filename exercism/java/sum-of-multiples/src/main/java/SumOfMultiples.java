import java.util.stream.IntStream;

class SumOfMultiples {

    private int number;
    private int[] multiples;

    SumOfMultiples(int number, int[] set) {
        this.number = number;
        this.multiples = set;
    }

    int getSum() {
        if (number < 2) {
            return 0;
        }
        return IntStream.range(1, this.number).filter(i -> IntStream.of(this.multiples).filter(j -> j != 0).anyMatch(j -> i % j == 0)).sum();
    }

}
