class Diamond {
  int getLineSizeFromLineIndex(int index) => 1 + index * 2;
  static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

  List<String> rows(String initialLetter) {
    int index = LETTERS.indexOf(initialLetter);
    int lengthMax = getLineSizeFromLineIndex(index);
    List<String> lines = List();
    String letter = LETTERS[index];

    if (index == 0) {
      lines.add(letter);
    } else {
      lines.add(letter + " " * (lengthMax - 2) + letter);
    }

    index--;

    while (index >= 0) {
      letter = LETTERS[index];
      int lineSize = getLineSizeFromLineIndex(index);
      int paddingSize = (lengthMax - lineSize) ~/ 2;
      String padding = " " * paddingSize;
      String line;
      if (index == 0) {
        line = padding + letter + padding;
      } else {
        String spaces = " " * (lineSize - 2);
        line = padding + letter + spaces + letter + padding;
      }
      lines.add(line);
      lines.insert(0, line);
      index--;
    }
    return lines;
  }
}
