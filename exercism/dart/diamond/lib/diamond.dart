class Diamond {
  static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  static final List<int> odd = new List<int>.generate(26, (int index) => 1 + index * 2);

  List<String> rows(String letter) {
    int index = UPPERCASE_LETTERS.indexOf(letter);
    int lengthMax = odd.elementAt(index);
    print(lengthMax);
    List<String> lines = List();
    if (index == 0) {
      lines.add(UPPERCASE_LETTERS[index]);
    } else {
      lines.add(UPPERCASE_LETTERS[index] +
          " " * (lengthMax - 2) +
          UPPERCASE_LETTERS[index]);
    }
    index--;
    while (index >= 0) {
      if (index == 0) {
        int padding_size = (lengthMax - 1) ~/ 2;
        String line = " " * padding_size + UPPERCASE_LETTERS[index] + " " * padding_size;
        lines.add(line);
        lines.insert(0,line);
      } else {
        int nbSpaces = odd.elementAt(index);
        int padding_size = (lengthMax - nbSpaces) ~/ 2;
        lines.add(" " * padding_size + UPPERCASE_LETTERS[index] +
            " " * (nbSpaces - 2) +
            UPPERCASE_LETTERS[index] + " " * padding_size);
        lines.insert(
            0,
            " " * padding_size + UPPERCASE_LETTERS[index] +
            " " * (nbSpaces - 2) +
            UPPERCASE_LETTERS[index] + " " * padding_size);
      }
      index --;
    }
    return lines;
  }
}
