class Darts {

    private static final int POINTS_FOR_DART_OUTSIDE_TARGET = 0;
    private static final int POINTS_FOR_DART_INSIDE_OUTER_CIRCLE = 1;
    private static final int POINTS_FOR_DART_INSIDE_MIDDLE_CIRCLE = 5;
    private static final int POINTS_FOR_DART_INSIDE_INNER_CIRCLE = 10;

    private static final double OUTER_CIRCLE_RADIUS = 10.0;
    private static final double MIDDLE_CIRCLE_RADIUS = 5.0;
    private static final double INNER_CIRCLE_RADIUS = 1.0;

    private final double x, y;

    public Darts(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public int score() {
        if (distanceFromOrigin() <= INNER_CIRCLE_RADIUS) {
            return POINTS_FOR_DART_INSIDE_INNER_CIRCLE;
        }

        if (distanceFromOrigin() <= MIDDLE_CIRCLE_RADIUS) {
            return POINTS_FOR_DART_INSIDE_MIDDLE_CIRCLE;
        }

        if (distanceFromOrigin() <= OUTER_CIRCLE_RADIUS) {
            return POINTS_FOR_DART_INSIDE_OUTER_CIRCLE;
        }

        return POINTS_FOR_DART_OUTSIDE_TARGET;
    }

    private double distanceFromOrigin() {
        return Math.sqrt(square(x) + square(y));
    }

    private double square(double x) {
        return (Math.pow(x, 2.0));
    }

}
