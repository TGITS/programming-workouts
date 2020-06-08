String reverse(String input) {
  if(input.isEmpty) {
    return "";
  }
  
  StringBuffer result = new StringBuffer();
  for (int i = input.length - 1; i >= 0; i--) {
    result.write(input[i]);
  }
  return result.toString();
}
