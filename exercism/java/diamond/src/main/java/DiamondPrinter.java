import java.util.ArrayList;
import java.util.List;

class DiamondPrinter {

    private static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public List<String> printToList(char a) {
        List<String> lines = new ArrayList<>();
        lines.add(Character.toString(a));
        return lines;
    }
}
