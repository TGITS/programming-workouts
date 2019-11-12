import java.util.Arrays;

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

}
