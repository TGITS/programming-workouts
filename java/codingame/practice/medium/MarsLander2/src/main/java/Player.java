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

    public double distance(Point p) {
        return Math.sqrt(Math.pow((p.getX() - this.getX()), 2) + Math.pow((p.getY() - this.getY()), 2));
    }

    public int horizontalDistance(Point p) {
        return Math.abs(p.getX() - this.getX());
    }

    public int verticalDistance(Point p) {
        return Math.abs(p.getY() - this.getY());
    }

    public boolean hasSameAltitude(Point p) {
        return this.getY() == p.getY();
    }

    public boolean isOnLeftOf(Point p) {
        return this.x < p.x;
    }

    public boolean isOnRightOf(Point p) {
        return this.x > p.x;
    }

    public boolean hasSameAbsciss(Point p) {
        return this.x == p.x;
    }
}

final class Surface {
    private final List<Point> points = new ArrayList<>();

    public Surface() {
        super();
    }

    public boolean addPoint(Point p) {
        return points.add(p);
    }

    public int getNumberOfDefiningPoints() {
        return points.size();
    }

    public Surface getBiggestFlatGround() {
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

    public MarsLander() {
        super();
    }

    public void update(int x, int y, int horizontalSpeed, int verticalSpeed, int fuel, int rotationAngle, int thrustPower) {
        this.updatePosition(new Point(x, y));
        this.updateHorizontalSpeed(horizontalSpeed);
        this.updateVerticalSpeed(verticalSpeed);
        this.updateFuel(fuel);
        this.updateRotationAngle(rotationAngle);
        this.updateThrustPower(thrustPower);
    }

    public Point getPosition() {
        return position;
    }

    public int getHorizontalSpeed() {
        return horizontalSpeed;
    }

    public int getVerticalSpeed() {
        return verticalSpeed;
    }

    public int getFuel() {
        return fuel;
    }

    public int getRotationAngle() {
        return rotationAngle;
    }

    public int getThrustPower() {
        return thrustPower;
    }

    public void updatePosition(Point position) {
        this.position = position;
    }

    public void updatePosition(int x, int y) {
        this.updatePosition(new Point(x, y));
    }

    public void updateHorizontalSpeed(int horizontalSpeed) {
        this.horizontalSpeed = horizontalSpeed;
    }

    public void updateVerticalSpeed(int verticalSpeed) {
        this.verticalSpeed = verticalSpeed;
    }

    public void updateFuel(int fuel) {
        this.fuel = fuel;
    }

    public void updateRotationAngle(int rotationAngle) {
        this.rotationAngle = rotationAngle;
    }

    public void updateThrustPower(int thrustPower) {
        this.thrustPower = thrustPower;
    }

    public String computeRotationAngleAndThrustPower(Surface marsSurface) {
        return computeRotationAngle(marsSurface) + " " + computeThrustPower(marsSurface);
    }

    public String computeRotationAngle(Surface marsSurface) {
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