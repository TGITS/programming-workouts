import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class Yacht {

    private static final int LOSING_SCORE = 0;
    private static final int YACHT_SCORE = 50;
    private static final int STRAIGHT_SCORE = 30;

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
                return this.scoreForStraight(Arrays.asList(1,2,3,4,5));
            case BIG_STRAIGHT:
                return this.scoreForStraight(Arrays.asList(2,3,4,5,6));
            case CHOICE:
                return this.scoreForChoice();
            default:
                return -1;
        }
    }

    private int scoreForYacht() {
        return Arrays.stream(dices).distinct().count() == 1 ? YACHT_SCORE : LOSING_SCORE;
    }

    private int scoreForNumbers(int number) {
        return (int) (Arrays.stream(dices).filter(i -> i == number).count() * number);
    }

    private int scoreForFullHouse() {
        Map<Integer, Long> groupByCount = Arrays.stream(dices).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        if (new HashSet<>(groupByCount.values()).equals(new HashSet<>(Arrays.asList(2L, 3L)))) {
            return Arrays.stream(dices).sum();
        }
        return LOSING_SCORE;
    }

    private int scoreForFourOfAKind() {
        Map<Integer, Long> groupByCount = Arrays.stream(dices).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        if (new HashSet<>(groupByCount.values()).equals(new HashSet<>(Arrays.asList(1L, 4L))) || new HashSet<>(groupByCount.values()).equals(new HashSet<>(Collections.singletonList(5L)))) {
            return groupByCount.entrySet().stream().filter(entry -> entry.getValue() == 4L || entry.getValue() == 5L).map(entry -> entry.getKey() * 4).findFirst().get();
        }
        return LOSING_SCORE;
    }

    private int scoreForStraight(List<Integer> expectedResult) {
        Set<Integer> littleStraight = new HashSet<>(expectedResult);
        Set<Integer> givenRoll = Arrays.stream(dices).boxed().collect(Collectors.toSet());
        return givenRoll.equals(littleStraight) ? STRAIGHT_SCORE : LOSING_SCORE;
    }

    private int scoreForChoice() {
        return Arrays.stream(dices).sum();
    }
}
