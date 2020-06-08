import 'dart:math';

class ArmstrongNumbers {
  bool isArmstrongNumber(int number) {
    String numberAsString = number.toString();
    int numberOfDigits = numberAsString.length;
    return number ==
        numberAsString
            .split("")
            .map((digit) => pow(int.parse(digit), numberOfDigits))
            .fold(0, (previousValue, element) => previousValue + element);
  }
}
