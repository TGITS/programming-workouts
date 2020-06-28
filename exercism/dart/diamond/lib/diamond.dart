class Diamond {
  static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

  List<String> rows(String letter) {
    int index = UPPERCASE_LETTERS.indexOf(letter);
    List<String> lines = List();
    if (index == 0) {
      lines.add(UPPERCASE_LETTERS[index]);
    } else {
      lines.add(UPPERCASE_LETTERS[index] +
          " " * index +
          UPPERCASE_LETTERS[index]);
    }
    index--;
    while (index >= 0) {
      if (index == 0) {
        lines.add(UPPERCASE_LETTERS[index]);
        lines.insert(0,UPPERCASE_LETTERS[index]);
      } else {
        lines.add(UPPERCASE_LETTERS[index] +
            " " * index +
            UPPERCASE_LETTERS[index]);
        lines.insert(
            0,
            UPPERCASE_LETTERS[index] +
                " " * index +
                UPPERCASE_LETTERS[index]);
      }
      index --;
    }
    return lines;
  }
}
