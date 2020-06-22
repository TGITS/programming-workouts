import 'dart:math';

class ArmstrongNumbers {
  num _armstrongNumber(int number) {
    return '$number'
        .split("")
        .map(int.parse)
        .map((digit) => pow(digit, '$number'.length))
        .fold(0, (previousValue, value) => previousValue + value);
  }

  bool isArmstrongNumber(int number) => number == _armstrongNumber(number);
}
