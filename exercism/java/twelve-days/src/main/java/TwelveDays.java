class TwelveDays {

    private static final String ON_THE_XTH_DAY = "On the %s day of Christmas my true love gave to me: ";
    private static final String[] XTH = new String[]{"first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eighth", "ninth", "tenth", "eleventh", "twelfth"};
    private static final String[] PRESENTS = new String[]{
            "a Partridge in a Pear Tree.\n",
            "two Turtle Doves, and ",
            "three French Hens, ",
            "four Calling Birds, ",
            "five Gold Rings, ",
            "six Geese-a-Laying, ",
            "seven Swans-a-Swimming, ",
            "eight Maids-a-Milking, ",
            "nine Ladies Dancing, ",
            "ten Lords-a-Leaping, ",
            "eleven Pipers Piping, ",
            "twelve Drummers Drumming, "
    };

    String verse(int verseNumber) {
        StringBuilder sb = new StringBuilder(String.format(ON_THE_XTH_DAY, XTH[verseNumber - 1]));
        for (int i = verseNumber - 1; i >= 0; i--) {
            sb.append(PRESENTS[i]);
        }
        return sb.toString();
    }

    String verses(int startVerse, int endVerse) {
        StringBuilder sb = new StringBuilder();
        for (int i = startVerse; i <= endVerse; i++) {
            sb.append(this.verse(i));
            if (i < endVerse) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    String sing() {
        return this.verses(1, 12);
    }
}
