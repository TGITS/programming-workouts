class Proverb {

    private static final String AND_ALL_FOR_THE_WANT_OF = "And all for the want of a %s.";
    private static final String FOR_THE_WANT_OF = "For want of a %s the %s was lost.\n";


    private String[] words;

    public Proverb(String[] words) {
        this.words = words;
    }

    public String recite() {
        if (words == null || words.length == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        if (words.length > 1) {
            for (int i = 0; i < words.length - 1; i++) {
                sb.append(String.format(FOR_THE_WANT_OF, words[i], words[i + 1]));
            }
        }
        sb.append(String.format(AND_ALL_FOR_THE_WANT_OF, words[0]));
        return sb.toString();
    }

}
