import 'dart:math';

class DifferenceOfSquares {
  int _square(int number) => pow(number, 2) as int;
  int squareOfSum(int max) {
    return _square(new List<int>.generate(max, (i) => i + 1)
        .fold(0, (previousValue, value) => previousValue + value));
  }

  int sumOfSquares(int max) {
    return new List<int>.generate(max, (i) => i + 1)
        .map(_square)
        .fold(0, (previousValue, value) => previousValue + value);
  }

  int differenceOfSquares(int max) {
    return squareOfSum(max) - sumOfSquares(max);
  }
}
