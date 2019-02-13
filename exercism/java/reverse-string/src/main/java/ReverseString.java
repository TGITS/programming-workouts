class ReverseString {

    public String reverse(String inputString) {
        return inputString.isBlank() ? inputString : classicReverseVariant(inputString);//A variant of classical solution with only String and a for loop
        //return inputString.isBlank() ? inputString : classicReverse(inputString);//A classical solution with only String and a for loop from the end of the string
        //return inputString.isBlank() ? inputString : explicitReverseWithString(inputString);//A solution with only String
        //return inputString.isBlank() ? inputString : explicitReverseWithStringBuilder(inputString);//A solution with StringBuilder and an explicit method to compute the reverse
        //return inputString.isBlank() ? inputString : new StringBuilder(inputString).reverse().toString();//A solution using StringBuilder reverse
    }

    private String explicitReverseWithStringBuilder(String inputString) {
        var characters = inputString.split("");
        var sb = new StringBuilder();
        for (String character:characters){
            sb.insert(0,character);
        }
        return sb.toString();
    }

    private String explicitReverseWithString(String inputString){
        var characters = inputString.split("");
        String computedString = "";
        for (String character:characters){
            computedString = String.format("%s%s", character, computedString);
        }
        return computedString;
    }

    private String classicReverse(String inputString){
        var inputStringLength = inputString.length();
        String computedString = "";
        for(var i = inputStringLength-1; i >= 0; i--) {
            computedString += Character.toString(inputString.charAt(i));
        }
        return computedString;
    }

    private String classicReverseVariant(String inputString){
        var inputStringLength = inputString.length();
        String computedString = "";
        for(var i = 0; i < inputStringLength; i++) {
            computedString = inputString.charAt(i) + computedString;
        }
        return computedString;
    }
}