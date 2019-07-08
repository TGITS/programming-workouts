class ResistorColor {
  static List<String> _colors = [
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
  ];

  int colorCode(String color) {
    if (color == null) {
      throw ArgumentError.notNull(color);
    }

    String colorToLowerCase = color.trim().toLowerCase();

    if (!_colors.contains(colorToLowerCase)) {
      throw new ArgumentError("The provided value for the color is not available.");
    }

    return _colors.indexOf(colorToLowerCase);
  }

  List<String> get colors => _colors;
}
