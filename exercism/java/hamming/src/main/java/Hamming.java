import java.util.ArrayList;
import java.util.List;

class Hamming {

    private String leftStrand;
    private String rightStrand;

    Hamming(String leftStrand, String rightStrand) {
        if(leftStrand.length() != rightStrand.length()) {
            throw new IllegalArgumentException("leftStrand and rightStrand must be of equal length.");
        }

        this.leftStrand = leftStrand;
        this.rightStrand = rightStrand;
    }

    int getHammingDistance() {

        if(this.leftStrand == this.rightStrand) {
            return 0;
        }

        return Long.valueOf(zip(this.leftStrand,this.rightStrand).stream().filter(PairOfStrings::isPairDifferent).count()).intValue();
    }

    private List<PairOfStrings> zip(String left, String right) {
        List<PairOfStrings> pairs = new ArrayList<>();
        for(int i = 0; i < left.length(); i++) {
            pairs.add(new PairOfStrings(Character.toString(left.charAt(i)),Character.toString(right.charAt(i))));
        }
        return pairs;
    }

    private static class PairOfStrings {
        private final String first;
        private final String second;

        public PairOfStrings(String first, String second) {
            this.first = first;
            this.second = second;
        }

        public boolean isPairIdentical() {
            return this.first.equals(this.second);
        }

        public boolean isPairDifferent() {
            return !isPairIdentical();
        }
    }

}
