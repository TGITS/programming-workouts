class WordCount {

  Map<String,int> _accumulate(Map<String,int> previousValue, String element) {
    previousValue.update(element, (int value) => value + 1, ifAbsent: () => 1);
    return previousValue;
  }

  Map<String, int> countWords(String input) {
    return input
        .replaceAll(new RegExp(r'[,;:!&@%&\?\$\^\.\*\\\s]+'), ' ')
        .split(' ')
        .map((s) => s)
        .fold<Map<String,int>>(Map<String,int>(), _accumulate);
  }
}
