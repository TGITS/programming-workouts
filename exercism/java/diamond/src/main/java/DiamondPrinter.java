import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class DiamondPrinter {

    private static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public List<String> printToList(char targetChar) {
        int targetCharIndex = UPPERCASE_LETTERS.indexOf(targetChar);
        int currentCharIndex = 0;
        char currentChar;
        int lineSize = 2 * targetCharIndex + 1;
        LinkedList<String> lines = new LinkedList<>();

        do {
            currentChar = UPPERCASE_LETTERS.charAt(currentCharIndex);
            lines.add(centerString(computeCentralString(currentChar), lineSize));
            currentCharIndex++;
        } while (targetChar != currentChar);

        if (targetCharIndex != 0) {
            LinkedList<String> secondPartOfTheList = new LinkedList<>();
            for(Iterator<String> it = lines.descendingIterator();it.hasNext();){
                secondPartOfTheList.add(it.next());
            }
            secondPartOfTheList.poll();
            lines.addAll(secondPartOfTheList);
        }

        return lines;
    }

    private String computeCentralString(char c) {
        StringBuilder sb = new StringBuilder();
        switch (c) {
            case 'A':
                sb.append("A");
                break;
            default:
                sb.append(c);
                int spacesBetween = 2 * UPPERCASE_LETTERS.indexOf(c) - 1;
                appendSpaces(sb,spacesBetween);
                sb.append(c);
                break;
        }
        return sb.toString();
    }

    private String centerString(String toBeCentered, int lineSize) {
        StringBuilder sb = new StringBuilder();
        int numberOfSpaces = (lineSize - toBeCentered.length()) / 2;
        appendSpaces(sb,numberOfSpaces);
        sb.append(toBeCentered);
        appendSpaces(sb,numberOfSpaces);
        return sb.toString();
    }

    private void appendSpaces(StringBuilder sb, int numberOfSpaces) {
        for (int i = 0; i < numberOfSpaces; i++) {
            sb.append(" ");
        }
    }
}
