import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class Yacht {

    private int[] dices;
    private final YachtCategory yachtCategory;

    Yacht(int[] dices, YachtCategory yachtCategory) {
        this.dices = Arrays.copyOf(dices, dices.length);
        this.yachtCategory = yachtCategory;
    }

    int score() {
        switch (this.yachtCategory) {
            case YACHT:
                return this.scoreForYacht();
            case ONES:
                return this.scoreForNumbers(1);
            case TWOS:
                return this.scoreForNumbers(2);
            case THREES:
                return this.scoreForNumbers(3);
            case FOURS:
                return this.scoreForNumbers(4);
            case FIVES:
                return this.scoreForNumbers(5);
            case SIXES:
                return this.scoreForNumbers(6);
            case FULL_HOUSE:
                return this.scoreForFullHouse();
            default:
                return -1;
        }

    }

    private int scoreForYacht() {
        return Arrays.stream(dices).distinct().count() == 1 ? 50 : 0;
    }

    private int scoreForNumbers(int number) {
        return (int) (Arrays.stream(dices).filter(i -> i == number).count() * number);
    }

    private int scoreForFullHouse() {
        Map<Integer, Long> groupByCount = Arrays.stream(dices).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        groupByCount.values();
        if (groupByCount.size() == 2 /*&& new HashSet<>(groupByCount.values()).equals(new HashSet<Long>(2L,3L))*/) {
            return Arrays.stream(dices).sum();
        }
        return 0;
    }
}
