import java.util.Arrays;
import java.util.Collections;
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
            case FOUR_OF_A_KIND:
                return this.scoreForFourOfAKind();
            case LITTLE_STRAIGHT:
                return this.scoreForLittleStraight();
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
        if (new HashSet<>(groupByCount.values()).equals(new HashSet<>(Arrays.asList(2L, 3L)))) {
            return Arrays.stream(dices).sum();
        }
        return 0;
    }

    private int scoreForFourOfAKind() {
        Map<Integer, Long> groupByCount = Arrays.stream(dices).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        if (new HashSet<>(groupByCount.values()).equals(new HashSet<>(Arrays.asList(1L, 4L))) || new HashSet<>(groupByCount.values()).equals(new HashSet<>(Collections.singletonList(5L)))) {
            return groupByCount.entrySet().stream().filter(entry -> entry.getValue() == 4L || entry.getValue() == 5L).map(entry -> entry.getKey() * 4).findFirst().get();
        }
        return 0;
    }

    private int scoreForLittleStraight() {
        //if (new HashSet<Integer>(Arrays.asList(dices)).equals(new HashSet<>(Arrays.asList(1L, 4L)))
        return 0;
    }
}
