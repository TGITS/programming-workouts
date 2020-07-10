class MatchingBrackets {
  bool isPaired(String s) {
    int length = s.length;
    List<String> stack = List();
    for (int i = 0; i < length; i++) {
      switch (s[i]) {
        case "[":
          stack.add("[");
          break;
        case "{":
          stack.add("{");
          break;
        case "(":
          stack.add("(");
          break;
        case "]":
          if (stack.isEmpty) return false;
          String last = stack.removeLast();
          if (last != "[") return false;
          break;
        case "}":
          if (stack.isEmpty) return false;
          String last = stack.removeLast();
          if (last != "{") return false;
          break;
        case ")":
          if (stack.isEmpty) return false;
          String last = stack.removeLast();
          if (last != "(") return false;
          break;
        default:
          break;
      }
    }
    return stack.isEmpty;
  }
}
