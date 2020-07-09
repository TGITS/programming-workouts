class WordCount {
  Map<String, int> _collectAsMap(Map<String, int> accumulator, String key) =>
      accumulator..update(key, (int value) => ++value, ifAbsent: () => 1);

  String _deleteFrontAndBackQuotesIfAny(String inputString) {
    String processedString = inputString;
    if (processedString.startsWith('\'')) {
      processedString = processedString.substring(1);
    }

    if (processedString.endsWith('\'')) {
      processedString =
          processedString.substring(0, processedString.length - 1);
    }

    return processedString;
  }

  Map<String, int> countWords(String input) {
    return input
        .replaceAll(RegExp(r'[,;:!&@%&\?\$\^\.\*\\\s]+'), ' ')
        .split(' ')
        .where((s) => s.trim().isNotEmpty)
        .map((s) => s.toLowerCase())
        .map(_deleteFrontAndBackQuotesIfAny)
        .fold({}, _collectAsMap);
  }
}
