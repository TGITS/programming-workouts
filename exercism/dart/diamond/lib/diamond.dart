class Diamond {

  static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

  List<String> rows(String letter) {
    List<String> computedListOfLetters = UPPERCASE_LETTERS.split("").takeWhile((value) => value != letter).toList();
    return computedListOfLetters;
  }
}
