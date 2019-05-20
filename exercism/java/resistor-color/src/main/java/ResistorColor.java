class ResistorColor {

    private static final String[] colors = {"black", "brown", "red", "orange", "yellow", "green", "blue", "violet", "grey", "white"};

    /* A possible iterative solution can be :

        int colorCode(String color) {
        int index = 0;
        boolean notFound = true;
        while (notFound) {
            if (colors[index].equalsIgnoreCase(color)) {
                notFound = false;
            } else {
                index++;
            }
        }
        return index;
    }*/

    public int colorCode(String color) {
        return findColorCodeIndex(color, 0);
    }

    private int findColorCodeIndex(final String color, final int index) {
        if (index < 0) {
            throw new IllegalArgumentException("The provided index should be greater or equal to 0");
        }

        if (index > colors.length) {
            throw new IllegalArgumentException("The provided color must be correctly spelled or be a legitimate color for a resistor");
        }

        if (colors[index].equalsIgnoreCase(color)) {
            return index;
        }
        return findColorCodeIndex(color, index + 1);
    }

    public String[] colors() {
        return colors;
    }
}
