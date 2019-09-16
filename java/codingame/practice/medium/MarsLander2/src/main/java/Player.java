import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

final class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int surfaceN = in.nextInt(); // the number of points used to draw the surface of Mars.
        Surface marsSurface = new Surface();
        for (int i = 0; i < surfaceN; i++) {
            // First nextInt  -> X coordinate of a surface point. (0 to 6999)
            // Second nextInt -> Y coordinate of a surface point. By linking all the points together in a sequential fashion, you form the surface of Mars.
            marsSurface.addPoint(new Point(in.nextInt(), in.nextInt()));
        }

        MarsLander marsLander = new MarsLander();

        // game loop
        while (true) {
            marsLander.updatePosition(in.nextInt(), in.nextInt());//X & Y
            marsLander.updateHorizontalSpeed(in.nextInt());// the horizontal speed (in m/s), can be negative.
            marsLander.updateVerticalSpeed(in.nextInt()); // the vertical speed (in m/s), can be negative.
            marsLander.updateFuel(in.nextInt()); // the quantity of remaining fuel in liters.
            marsLander.updateRotationAngle(in.nextInt()); // the rotation angle in degrees (-90 to 90).
            marsLander.updateThrustPower(in.nextInt()); // the thrust power (0 to 4).

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            // rotate power. rotate is the desired rotation angle. power is the desired thrust power.
            // 2 integers: rotate power. rotate is the desired rotation angle (should be 0 for level 1), power is the desired thrust power (0 to 4).
            System.out.println(marsLander.computeRotationAngleAndThrustPower(marsSurface));
        }
    }

}

final class Point {
    private final int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    double distance(Point p) {
        return Math.sqrt(Math.pow((p.getX() - this.getX()), 2) + Math.pow((p.getY() - this.getY()), 2));
    }

    int horizontalDistance(Point p) {
        return Math.abs(p.getX() - this.getX());
    }

    int verticalDistance(Point p) {
        return Math.abs(p.getY() - this.getY());
    }

    boolean hasSameAltitude(Point p) {
        return this.getY() == p.getY();
    }

    boolean isOnLeftOf(Point p) {
        return this.x < p.x;
    }

    boolean isOnRightOf(Point p) {
        return this.x > p.x;
    }

    boolean hasSameAbscissa(Point p) {
        return this.x == p.x;
    }
}

final class Surface {
    private final List<Point> points = new ArrayList<>();

    Surface() {
        super();
    }

    boolean addPoint(Point p) {
        return points.add(p);
    }

    int getNumberOfDefiningPoints() {
        return points.size();
    }

    Surface getBiggestFlatGround() {
        Point previousPoint = points.get(0);
        double biggestDistance = 0.0;
        Point startOfFlatGround = previousPoint;
        Point endOfFlatGround = previousPoint;
        for (Point currentPoint : points.subList(1, points.size())) {
            double currentDistance = currentPoint.distance(previousPoint);
            if (currentPoint.hasSameAltitude(previousPoint) && currentDistance > biggestDistance) {
                biggestDistance = currentDistance;
                startOfFlatGround = previousPoint;
                endOfFlatGround = currentPoint;
            }
            previousPoint = currentPoint;
        }

        Surface flatGround = new Surface();
        flatGround.addPoint(startOfFlatGround);
        flatGround.addPoint(endOfFlatGround);
        return flatGround;
    }

    public Point getPoint(int index) {
        return points.get(index);
    }
}

final class MarsLander {

    private Point position;
    private int horizontalSpeed; // the horizontal speed (in m/s), can be negative.
    private int verticalSpeed;   // the vertical speed (in m/s), can be negative.
    private int fuel;            // the quantity of remaining fuel in liters.
    private int rotationAngle;   // the rotation angle in degrees (-90 to 90).
    private int thrustPower;     // the thrust power (0 to 4).

    MarsLander() {
        super();
    }

    void update(int x, int y, int horizontalSpeed, int verticalSpeed, int fuel, int rotationAngle, int thrustPower) {
        this.updatePosition(new Point(x, y));
        this.updateHorizontalSpeed(horizontalSpeed);
        this.updateVerticalSpeed(verticalSpeed);
        this.updateFuel(fuel);
        this.updateRotationAngle(rotationAngle);
        this.updateThrustPower(thrustPower);
    }

    Point getPosition() {
        return position;
    }

    int getHorizontalSpeed() {
        return horizontalSpeed;
    }

    int getVerticalSpeed() {
        return verticalSpeed;
    }

    int getFuel() {
        return fuel;
    }

    int getRotationAngle() {
        return rotationAngle;
    }

    int getThrustPower() {
        return thrustPower;
    }

    void updatePosition(Point position) {
        this.position = position;
    }

    void updatePosition(int x, int y) {
        this.updatePosition(new Point(x, y));
    }

    void updateHorizontalSpeed(int horizontalSpeed) {
        this.horizontalSpeed = horizontalSpeed;
    }

    void updateVerticalSpeed(int verticalSpeed) {
        this.verticalSpeed = verticalSpeed;
    }

    void updateFuel(int fuel) {
        this.fuel = fuel;
    }

    void updateRotationAngle(int rotationAngle) {
        this.rotationAngle = rotationAngle;
    }

    void updateThrustPower(int thrustPower) {
        this.thrustPower = thrustPower;
    }

    String computeRotationAngleAndThrustPower(Surface marsSurface) {
        Surface flatGround = marsSurface.getBiggestFlatGround();
        Point flatGroundStartOnLeft = flatGround.getPoint(0);
        Point flatGroundEndOnRight = flatGround.getPoint(1);
        String thrustPower = "";

        if(this.position.isOnLeftOf(flatGroundStartOnLeft)) {
            //we need to go to the right
        } else if (this.position.isOnRightOf(flatGroundEndOnRight)) {
            //we need to go to the left
        } else if(this.position.isOnRightOf(flatGroundStartOnLeft) && this.position.isOnLeftOf(flatGroundEndOnRight)) {
            //we need to stop going right or left
            //we have to stabilize our rotation angle to 0
            //we have to reduce our horizontal speed below 20ms (absolute value)
            //we have to reduce our vertical speed below 40ms (absolute value)
            if (verticalSpeed > 39) {
                thrustPower = Integer.toString(Math.max(0, this.thrustPower - 1));
            } else if (verticalSpeed < -39) {
                thrustPower = Integer.toString(Math.min(4, this.thrustPower + 1));
            } else {
                thrustPower = "0";
            }
        }
        return computeRotationAngle(marsSurface) + " " + thrustPower;
    }

    String computeRotationAngle(Surface marsSurface) {
        Surface flatGround = marsSurface.getBiggestFlatGround();
        return "0";
    }

    public String computeThrustPower(Surface marsSurface) {
        String result = "";
        Surface flatGround = marsSurface.getBiggestFlatGround();
        if (verticalSpeed > 39) {
            result = Integer.toString(Math.max(0, thrustPower - 1));
        } else if (verticalSpeed < -39) {
            result = Integer.toString(Math.min(4, thrustPower + 1));
        } else {
            result = "0";
        }
        return result;
    }

}