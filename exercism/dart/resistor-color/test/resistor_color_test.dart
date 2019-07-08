import 'package:resistor_color/resistor_color.dart';
import 'package:test/test.dart';

void main() {
  final resistorColor = new ResistorColor();

  group('ResistorColor', () {
    group('Color codes', () {
      test("black", () {
        final int result = resistorColor.colorCode("black");
        expect(result, equals(0));
      }, skip: false);

      test("brown", () {
        final int result = resistorColor.colorCode("brown");
        expect(result, equals(1));
      }, skip: false);

      test("red", () {
        final int result = resistorColor.colorCode("red");
        expect(result, equals(2));
      }, skip: false);

      test("orange", () {
        final int result = resistorColor.colorCode("orange");
        expect(result, equals(3));
      }, skip: false);

      test("yellow", () {
        final int result = resistorColor.colorCode("yellow");
        expect(result, equals(4));
      }, skip: false);

      test("green", () {
        final int result = resistorColor.colorCode("green");
        expect(result, equals(5));
      }, skip: false);

      test("blue", () {
        final int result = resistorColor.colorCode("blue");
        expect(result, equals(6));
      }, skip: false);

      test("violet", () {
        final int result = resistorColor.colorCode("violet");
        expect(result, equals(7));
      }, skip: false);

      test("grey", () {
        final int result = resistorColor.colorCode("grey");
        expect(result, equals(8));
      }, skip: false);

      test("white", () {
        final int result = resistorColor.colorCode("white");
        expect(result, equals(9));
      }, skip: false);

      test("BLACK", () {
        final int result = resistorColor.colorCode("BLACK");
        expect(result, equals(0));
      }, skip: false);

      test("Brown", () {
        final int result = resistorColor.colorCode("Brown");
        expect(result, equals(1));
      }, skip: false);

      test("RED", () {
        final int result = resistorColor.colorCode("RED");
        expect(result, equals(2));
      }, skip: false);

      test("Orange", () {
        final int result = resistorColor.colorCode("Orange");
        expect(result, equals(3));
      }, skip: false);

      test("Yellow", () {
        final int result = resistorColor.colorCode("Yellow");
        expect(result, equals(4));
      }, skip: false);

      test("Green", () {
        final int result = resistorColor.colorCode("Green");
        expect(result, equals(5));
      }, skip: false);

      test("BluE", () {
        final int result = resistorColor.colorCode("BluE");
        expect(result, equals(6));
      }, skip: false);

      test("Violet", () {
        final int result = resistorColor.colorCode("Violet");
        expect(result, equals(7));
      }, skip: false);

      test("GREY", () {
        final int result = resistorColor.colorCode("GREY");
        expect(result, equals(8));
      }, skip: false);

      test("White", () {
        final int result = resistorColor.colorCode("White");
        expect(result, equals(9));
      }, skip: false);

      test("  BLACK  ", () {
        final int result = resistorColor.colorCode("  BLACK  ");
        expect(result, equals(0));
      }, skip: false);

      test("  GREY", () {
        final int result = resistorColor.colorCode("  GREY");
        expect(result, equals(8));
      }, skip: false);

      test("White  ", () {
        final int result = resistorColor.colorCode("White  ");
        expect(result, equals(9));
      }, skip: false);

      test("Wrong color like gray", () {
        expect(() => resistorColor.colorCode("gray"), throwsA(TypeMatcher<ArgumentError>()));
      }, skip: false);

      test("Null argument", () {
        expect(() => resistorColor.colorCode(null), throwsA(TypeMatcher<ArgumentError>()));
      }, skip: false);
    });

    test("Colors", () {
      final List<String> result = resistorColor.colors;
      expect(result, equals([
        "black",
        "brown",
        "red",
        "orange",
        "yellow",
        "green",
        "blue",
        "violet",
        "grey",
        "white"
      ]));
    }, skip: false);
  });
}
