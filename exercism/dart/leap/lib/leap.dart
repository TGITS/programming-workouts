class Leap {
  bool leapYear(num year) => year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
}