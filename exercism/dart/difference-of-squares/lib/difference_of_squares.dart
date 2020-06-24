import 'dart:math';

class DifferenceOfSquares {
  int _square(int number) => pow(number, 2).toInt();
  
  int _sum(int a, int b) => a + b;
  
  List<int> _generateList(int max) => List<int>.generate(max, (i) => i + 1);
  
  int squareOfSum(int max) {
    return _square(_generateList(max).reduce(_sum));
  }

  int sumOfSquares(int max) {
    return _generateList(max).map(_square).reduce(_sum);
  }

  int differenceOfSquares(int max) {
    return squareOfSum(max) - sumOfSquares(max);
  }
}
