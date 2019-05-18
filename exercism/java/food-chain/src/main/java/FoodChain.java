import java.util.HashMap;
import java.util.Map;

public class FoodChain {

    public static final String I_KNOW_AN_OLD_LADY_WHO_SWALLOWED_A = "I know an old lady who swallowed a %s.";
    public static final String I_DONT_KNOW_WHY_SHE_SWALLOWED_A = "I don't know why she swallowed the %s. Perhaps she'll die.";
    public static final String[] ANIMAL_BY_VERSE = new String[]{"fly","spider","bird","cat","dog","goat","cow","horse"};
    public static final String SHE_S_DEAD = "She's dead, of course!";
    public static final String WRIGGLED = "%s wriggled and jiggled and tickled inside her%s";
    public static final String IT = "It";
    public static final String THAT = " that";
    public static final String SHE_SWALLOWED_TO = "She swallowed the %s to catch the %s.";
    public static final Map<String,String> SENTENCE_BY_ANIMAL = new HashMap<>();
    static {
        SENTENCE_BY_ANIMAL.put("spider",String.format(WRIGGLED,IT,".")+"\n");
        SENTENCE_BY_ANIMAL.put("bird","How absurd to swallow a bird!\n");
        SENTENCE_BY_ANIMAL.put("cat", "Imagine that, to swallow a cat!\n");
        SENTENCE_BY_ANIMAL.put("dog", "What a hog, to swallow a dog!\n");
        SENTENCE_BY_ANIMAL.put("goat", "Just opened her throat and swallowed a goat!\n");
        SENTENCE_BY_ANIMAL.put("cow", "I don't know how she swallowed a cow!\n");
        SENTENCE_BY_ANIMAL.put("horse", "She's dead, of course!");
    }

    public String verse(int verse) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(I_KNOW_AN_OLD_LADY_WHO_SWALLOWED_A, ANIMAL_BY_VERSE[verse-1]));
        sb.append("\n");

        if(verse == 1) {
             sb.append(String.format(I_DONT_KNOW_WHY_SHE_SWALLOWED_A,ANIMAL_BY_VERSE[0]));
        }

        if(verse == 2) {
            sb.append(SENTENCE_BY_ANIMAL.get(ANIMAL_BY_VERSE[verse-1]));
            sb.append(String.format(SHE_SWALLOWED_TO, ANIMAL_BY_VERSE[verse-1], ANIMAL_BY_VERSE[verse-2]));
            sb.append("\n");
            sb.append(String.format(I_DONT_KNOW_WHY_SHE_SWALLOWED_A,ANIMAL_BY_VERSE[0]));
        }

        if(verse >= 3 && verse < 8) {
            sb.append(SENTENCE_BY_ANIMAL.get(ANIMAL_BY_VERSE[verse-1]));
            for(int i = verse-1; i > 0 ; i--) {
                if(ANIMAL_BY_VERSE[i-1].equals("spider")) {
                    sb.append(String.format(SHE_SWALLOWED_TO, ANIMAL_BY_VERSE[i], ANIMAL_BY_VERSE[i-1] + String.format(WRIGGLED, THAT,"")));
                } else {
                    sb.append(String.format(SHE_SWALLOWED_TO, ANIMAL_BY_VERSE[i], ANIMAL_BY_VERSE[i-1]));
                }
                sb.append("\n");
            }
            sb.append(String.format(I_DONT_KNOW_WHY_SHE_SWALLOWED_A,ANIMAL_BY_VERSE[0]));
        }

        if (verse == 8) {
            sb.append(SHE_S_DEAD);
        }
        return sb.toString();
    }

    public String verses(int startVerse, int endVerse) {
        StringBuilder sb = new StringBuilder();
        for(int i = startVerse; i <= endVerse; i++) {
            sb.append(verse(i));
            if (i != endVerse) {
                sb.append("\n\n");
            }
        }
        return sb.toString();
    }
}
