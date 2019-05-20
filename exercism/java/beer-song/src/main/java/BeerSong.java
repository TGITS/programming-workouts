public class BeerSong {

    private static final String X_BOTTLES_OF_BEER = "%s bottle%s of beer";
    private static final String ON_THE_WALL = "%s on the wall";
    private static final String TAKE_ONE_DOWN = "Take %s down and pass it around, %s.";
    private static final String GO_TO_THE_STORE = "Go to the store and buy some more, %s.\n\n";

    private String bottleOfBeer(final int numberOfBottles) {
        switch(numberOfBottles) {
            case 1 : return String.format(X_BOTTLES_OF_BEER, "1","");
            case 0 : return String.format(X_BOTTLES_OF_BEER, "no more","s");
            default : return String.format(X_BOTTLES_OF_BEER, numberOfBottles,"s");
        }
    }

    private String takeXDown(final int numberOfBottles) {
        if(numberOfBottles == 1 ) {
            return "it";
        }

        return "one";
    }

    private String capitalize(String s) {
        return s.substring(0,1).toUpperCase() + s.substring(1);
    }

    public String sing(int initialNumberOfBottles, int numberOfVerses) {
        StringBuilder sb = new StringBuilder();
        int numberOfBottles = initialNumberOfBottles;
        for(int i = numberOfVerses; i > 0; i--) {
            sb.append(String.format("%s, %s.\n", capitalize(String.format(ON_THE_WALL, bottleOfBeer(numberOfBottles))), String.format(bottleOfBeer(numberOfBottles))));
            if(numberOfBottles > 0) {
                sb.append(String.format("%s\n\n", String.format(TAKE_ONE_DOWN, takeXDown(numberOfBottles), String.format(ON_THE_WALL, bottleOfBeer(numberOfBottles - 1)))));
                numberOfBottles -= 1;
            } else {
                sb.append(String.format(GO_TO_THE_STORE, String.format(ON_THE_WALL, bottleOfBeer(99))));
            }
        }

        return sb.toString();
    }

    public String singSong() {
        return sing(99,100);
    }
}
