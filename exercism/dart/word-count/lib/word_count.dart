class WordCount {
  Map<String, int> _collectAsMap(Map<String, int> accumulator, String key) =>
      accumulator..update(key, (int value) => ++value, ifAbsent: () => 1);

  Map<String, int> countWords(String input) {
    return input
        .replaceAll(RegExp(r"(?!'\w)([\W']+|['\W])"), ' ')
        .split(' ')
        .where((s) => s.trim().isNotEmpty)
        .map((s) => s.toLowerCase())
        .fold({}, _collectAsMap);
  }
}
