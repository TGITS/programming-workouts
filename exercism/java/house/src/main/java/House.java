public class House {

    private static final String SENTENCE_BEGINNING_THIS = "This";
    private static final String SENTENCE_BEGINNING_THAT = "that";
    private static final String FIRST_VERB = " is ";
    private static final String[] SENTENCE_COMPONENT = new String[]{"the house that Jack built.", "the malt", "the rat", "the cat", "the dog",
            "the cow with the crumpled horn", "the maiden all forlorn", "the man all tattered and torn", "the priest all shaven and shorn",
            "the rooster that crowed in the morn", "the farmer sowing his corn", "the horse and the hound and the horn"};
    private static final String[] SENTENCE_VERB = new String[]{" lay in ", " ate ", " killed ", " worried ", " tossed ", " milked ",
            " kissed ", " married ", " woke ", " kept ", " belonged to "};

    public String verse(int verse) {
        int initialVerseNumber = verse-1;

        StringBuilder sb = new StringBuilder(SENTENCE_BEGINNING_THIS).append(FIRST_VERB).append(SENTENCE_COMPONENT[initialVerseNumber]);
        for(int i = initialVerseNumber-1; i >= 0; i--) {
            sb.append(" ").append(SENTENCE_BEGINNING_THAT).append(SENTENCE_VERB[i]).append(SENTENCE_COMPONENT[i]);
        }
        return sb.toString();
    }

    public String verses(int startVerse, int endVerse) {
        StringBuilder sb = new StringBuilder();
        for (int i = startVerse; i <= endVerse; i++) {
            sb.append(this.verse(i));
            if(i < endVerse) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public String sing() {
        return verses(1,12);
    }
}
