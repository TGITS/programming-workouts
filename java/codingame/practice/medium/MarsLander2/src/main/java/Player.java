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

        MarsLander marsLander = new MarsLander(marsSurface);

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
            System.out.println(marsLander.computeRotationAngleAndThrustPower());
        }
    }

}

final class Point {
    private final int x;
    private final int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return x;
    }

    int getY() {
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
        return Math.sqrt(Math.pow((p.x - this.x), 2) + Math.pow((p.y - this.y), 2));
    }

    int horizontalDistance(Point p) {
        return Math.abs(p.x - this.x);
    }

    int verticalDistance(Point p) {
        return Math.abs(p.y - this.y);
    }

    boolean hasSameAltitude(Point p) {
        return this.y == p.y;
    }

    boolean hasSameAbscissa(Point p) {
        return this.x == p.x;
    }

    boolean isOnLeftOf(Point p) {
        return this.x < p.x;
    }

    boolean isOnRightOf(Point p) {
        return this.x > p.x;
    }

    boolean isAboveOf(Point p) {
        return this.y > p.y;
    }

    boolean isBelowOf(Point p) {
        return this.y < p.y;
    }
}

final class FlatArea {
    private final Point leftMostPoint;
    private final Point rightMostPoint;
    private Point middlePoint;


    FlatArea(Point leftMostPoint, Point rightMostPoint) {
        super();
        this.leftMostPoint = leftMostPoint;
        this.rightMostPoint = rightMostPoint;
    }

    Point getMiddlePoint() {
        if (middlePoint == null) {
            middlePoint = new Point((rightMostPoint.getX() - leftMostPoint.getX()) / 2, leftMostPoint.getY());
        }
        return middlePoint;
    }

    Point getRightMostPoint() {
        return this.rightMostPoint;
    }

    Point getLeftMostPoint() {
        return this.leftMostPoint;
    }
}

final class Surface {
    private final List<Point> points = new ArrayList<>();
    private FlatArea flatArea;

    Surface() {
        super();
    }

    boolean addPoint(Point p) {
        return points.add(p);
    }

    int getNumberOfDefiningPoints() {
        return points.size();
    }

    FlatArea getFlatArea() {
        if (flatArea == null) {
            Point previousPoint = points.get(0);
            double biggestDistance = 0.0;
            Point leftMostPoint = previousPoint;
            Point rightMostPoint = previousPoint;
            for (Point currentPoint : points.subList(1, points.size())) {
                double currentDistance = currentPoint.distance(previousPoint);
                if (currentPoint.hasSameAltitude(previousPoint) && currentDistance > biggestDistance) {
                    biggestDistance = currentDistance;
                    leftMostPoint = previousPoint;
                    rightMostPoint = currentPoint;
                }
                previousPoint = currentPoint;
            }

            this.flatArea = new FlatArea(leftMostPoint, rightMostPoint);
        }
        return this.flatArea;
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
    private final Surface marsSurface;

    MarsLander(Surface marsSurface) {
        super();
        this.marsSurface = marsSurface;
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

    Surface getMarsSurface() {
        return this.marsSurface;
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

    String outputAnswer() {
        return String.format("%d %d", this.rotationAngle, this.thrustPower);
    }

    boolean isLanderAboveFlatArea() {
        return this.getPosition().isOnRightOf(this.marsSurface.getFlatArea().getLeftMostPoint()) && this.getPosition().isOnLeftOf(this.marsSurface.getFlatArea().getRightMostPoint());
    }

    boolean isOnLeftOfFlatArea() {
        return this.getPosition().isOnLeftOf(this.marsSurface.getFlatArea().getLeftMostPoint());
    }

    boolean isOnRightOfFlatArea() {
        return this.getPosition().isOnRightOf(this.marsSurface.getFlatArea().getRightMostPoint());
    }

    void incrementRotationAngle(int angle) {
        this.rotationAngle = this.rotationAngle + angle;
        if (this.rotationAngle >= 75) {
            this.rotationAngle = 90;
        }
    }

    void decrementRotationAngle(int angle) {
        this.rotationAngle = this.rotationAngle - angle;
        if (this.rotationAngle <= -75) {
            this.rotationAngle = -90;
        }
    }

    void incrementThrustPower() {
        if (this.thrustPower >= 3) {
            this.thrustPower = 4;
        } else {
            this.thrustPower = this.thrustPower + 1;
        }
    }

    void decrementThrustPower() {
        if (this.thrustPower <= 1) {
            this.thrustPower = 0;
        } else {
            this.thrustPower = this.thrustPower - 1;
        }
    }

    void goOnRight() {
        System.err.println("Going On Right");
        if (this.horizontalSpeed >= 18 && this.horizontalSpeed <= 20) {
            if (this.rotationAngle < 0) {
                this.incrementRotationAngle(15);
            } else if (this.rotationAngle > 0) {
                this.decrementRotationAngle(15);
            }
            this.incrementThrustPower();
        } else if (this.horizontalSpeed < 18) {
            this.incrementThrustPower();
            if (this.rotationAngle > -45) {
                this.decrementRotationAngle(15);
            }
        } else {
            if (this.rotationAngle <= 0) {
                this.incrementRotationAngle(15);
                this.decrementThrustPower();
            } else {
                this.incrementRotationAngle(15);
                this.incrementThrustPower();
            }
        }
    }

    void goOnLeft() {
        System.err.println("Going On Left");
        if (this.horizontalSpeed >= -20 && this.horizontalSpeed < -18) {
            if (this.rotationAngle > 0) {
                this.decrementRotationAngle(15);
            } else if (this.rotationAngle < 0) {
                this.incrementRotationAngle(15);
            }
            this.incrementThrustPower();
        } else if (this.horizontalSpeed > -18) {
            this.incrementThrustPower();
            if (this.rotationAngle < 45) {
                this.incrementRotationAngle(15);
            }
        } else {
            if (this.rotationAngle >= 0) {
                this.decrementRotationAngle(15);
                this.decrementThrustPower();
            } else {
                this.decrementRotationAngle(15);
                this.incrementThrustPower();
            }
        }
    }

    void prepareToLand() {
        if (this.verticalSpeed > 39) {
            this.decrementThrustPower();
        } else if (verticalSpeed < -39) {
            this.incrementThrustPower();
        } else {
            this.updateThrustPower(0);
        }
    }

    String computeRotationAngleAndThrustPower() {
        if (isOnLeftOfFlatArea()) {
            System.err.println("Lander on left of Flat Area");
            goOnRight();
        } else if (isOnRightOfFlatArea()) {
            System.err.println("Lander on right of Flat Area");
            goOnLeft();
        } else {
            System.err.println("Lander above Flat Area");
            if (this.rotationAngle > 0) {
                this.decrementRotationAngle(15);
            } else if (this.rotationAngle < 0) {
                this.incrementRotationAngle(15);
            } else {
                prepareToLand();
            }
        }
        return outputAnswer();
    }

}