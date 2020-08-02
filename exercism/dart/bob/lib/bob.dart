class Bob {
  static String _lookaround_assertion = r'(?=\b[A-Z]+\b)';
  static String _subpattern = r'[A-Z0-9\s,;%\^\*@#\$\(\)\!]+';
  static String _full_yelling_pattern = r'(^(' +
      _lookaround_assertion +
      _subpattern +
      r'|' +
      _subpattern +
      _lookaround_assertion +
      r')+[\!]?$)';

  String response(String s) {
    if (s.trim().isEmpty) {
      return "Fine. Be that way!";
    }

    if (RegExp(_full_yelling_pattern).hasMatch(s.trim())) {
      return "Whoa, chill out!";
    }

    if (RegExp(r"^([A-Z]|\s|')+\?$").hasMatch(s)) {
      return "Calm down, I know what I'm doing!";
    }

    if (s.trim().endsWith("?")) {
      return "Sure.";
    }

    return "Whatever.";
  }
}
