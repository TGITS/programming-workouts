import java.util.HashMap;
import java.util.Map;

class ResistorColorDuo {

    private final static Map<String, Integer> COLORS = new HashMap<>();

    static {
        COLORS.put("black", 0);
        COLORS.put("brown", 1);
        COLORS.put("red", 2);
        COLORS.put("orange", 3);
        COLORS.put("yellow", 4);
        COLORS.put("green", 5);
        COLORS.put("blue", 6);
        COLORS.put("violet", 7);
        COLORS.put("grey", 8);
        COLORS.put("white", 9);
    }

    int value(String[] colors) {
        if (colors.length < 2) {
            throw new IllegalArgumentException();
        }
        return COLORS.get(colors[0].toLowerCase()) * 10 + COLORS.get(colors[1].toLowerCase());
    }
}