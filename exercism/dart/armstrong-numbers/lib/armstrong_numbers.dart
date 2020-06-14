import 'dart:math';

class ArmstrongNumbers {
  bool isArmstrongNumber(int number) {
    return number ==
        '$number'
            .split("")
            .map(int.parse)
            .map((digit) => pow(digit, '$number'.length))
            .fold(0, (previousValue, element) => previousValue + element);
  }
}