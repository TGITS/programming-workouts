import java.util.stream.Stream;

class DifferenceOfSquaresCalculator {

    int computeSquareOfSumTo(int input) {
        int sum = Stream.iterate(1, n -> n + 1).limit(input).mapToInt(Integer::intValue).sum();
        return sum * sum;
    }

    int computeSumOfSquaresTo(int input) {
        return Stream.iterate(1, n -> n + 1).limit(input).mapToInt(i -> i * i).sum();
    }

    int computeDifferenceOfSquares(int input) {
        return computeSquareOfSumTo(input) - computeSumOfSquaresTo(input);
    }

}
