import java.util.*;
import java.util.stream.Collectors;

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

final class LandingArea {

    public static final int NEARING_WIDTH_LIMIT = 1000;
    public static final int NEARING_HEIGHT_LIMIT = 500;
    private final Point leftMostPoint;
    private final Point rightMostPoint;
    private Point middlePoint;


    LandingArea(Point leftMostPoint, Point rightMostPoint) {
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

    Point getRightMostPointWithNearingLimit() {
        return new Point(this.rightMostPoint.getX() + LandingArea.NEARING_WIDTH_LIMIT, this.leftMostPoint.getY());
    }

    Point getLeftMostPoint() {
        return this.leftMostPoint;
    }

    Point getLeftMostPointWithNearingLimit() {
        return new Point(this.leftMostPoint.getX() - LandingArea.NEARING_WIDTH_LIMIT, this.leftMostPoint.getY());
    }

    int getLandingAreaAltitude() {
        return this.middlePoint.getY();
    }

    int getLandingAreaAltitudeWithNearingLimit() {
        return this.getLandingAreaAltitude() + LandingArea.NEARING_HEIGHT_LIMIT;
    }
}

final class Surface {
    private final List<Point> points = new ArrayList<>();
    private LandingArea landingArea;

    Surface() {
        super();
    }

    boolean addPoint(Point p) {
        return points.add(p);
    }

    int getNumberOfDefiningPoints() {
        return points.size();
    }

    LandingArea getLandingArea() {
        if (landingArea == null) {
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

            this.landingArea = new LandingArea(leftMostPoint, rightMostPoint);
        }
        return this.landingArea;
    }

    public Point getPoint(int index) {
        return points.get(index);
    }

    List<Point> getPointsOnRightOfReferencePoint(Point referencePoint) {
        return points.stream().filter(point -> point.isOnRightOf(referencePoint)).collect(Collectors.toList());
    }

    List<Point> getPointsOnLeftOfReferencePoint(Point referencePoint) {
        return points.stream().filter(point -> point.isOnLeftOf(referencePoint)).collect(Collectors.toList());
    }

    Optional<Point> getPeakOnTheRight(Point referencePoint)  {
        return points.stream().filter(point -> point.isOnRightOf(referencePoint)).filter(point -> point.isAboveOf(referencePoint)).max(Comparator.comparingInt(Point::getY));
    }

    Optional<Point> getPeakOnTheLeft(Point referencePoint)  {
        return points.stream().filter(point -> point.isOnLeftOf(referencePoint)).filter(point -> point.isAboveOf(referencePoint)).max(Comparator.comparingInt(Point::getY));
    }
}

/**
 * From the MarsLander point of view, 2 questions must be answered, the second depending on the ansewer to the first :
 * - What should I do considering my present situation ? Should I go left, right, down, up, prepare to land ?
 * - How I do what I should do considering my present situation and state ? What is my current state and what is the state I want to be in next round ?
 * The idea is to modify my current state to be in a state that allow me to change my situation in a correct way with the objectives.
 */
final class MarsLander {

    private static final int ABSOLUTE_MAX_HORIZONTAL_SPEED = 20;
    private static final int ABSOLUTE_MAX_VERTICAL_SPEED = 40;

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

    boolean isInAbscissaLimitOfLandingArea() {
        return this.getPosition().isOnRightOf(this.marsSurface.getLandingArea().getLeftMostPoint()) && this.getPosition().isOnLeftOf(this.marsSurface.getLandingArea().getRightMostPoint());
    }

    boolean isOnLeftOfLandingArea() {
        return this.getPosition().isOnLeftOf(this.marsSurface.getLandingArea().getLeftMostPoint());
    }

    boolean isNearOnLeftOfLandingArea() {
        return isOnLeftOfLandingArea() && this.getPosition().isOnRightOf(this.marsSurface.getLandingArea().getLeftMostPointWithNearingLimit());
    }

    boolean isOnRightOfLandingArea() {
        return this.getPosition().isOnRightOf(this.marsSurface.getLandingArea().getRightMostPoint());
    }

    boolean isNearOnRightOfLandingArea() {
        return isOnRightOfLandingArea() && this.getPosition().isOnLeftOf(this.marsSurface.getLandingArea().getRightMostPointWithNearingLimit());
    }

    boolean isBelowLandingArea() {
        return this.getPosition().isBelowOf(this.marsSurface.getLandingArea().getMiddlePoint());
    }

    boolean isAboveLandingArea() {
        return this.getPosition().isAboveOf(this.marsSurface.getLandingArea().getMiddlePoint());
    }

    boolean isBelowLandingAreaWithNearingLimit() {
        return this.getPosition().isBelowOf(this.marsSurface.getLandingArea().getMiddlePoint());
    }

    boolean isAboveLandingAreaWithNearingLimit() {
        return this.getPosition().isAboveOf(this.marsSurface.getLandingArea().getMiddlePoint());
    }

    void incrementRotationAngle(int angle) {
        this.rotationAngle = this.rotationAngle + angle;
        if (this.rotationAngle > 90) {
            this.rotationAngle = 90;
        }
    }

    void decrementRotationAngle(int angle) {
        this.rotationAngle = this.rotationAngle - angle;
        if (this.rotationAngle < -90) {
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

    /**
     * The HS is positive oriented to the Right
     * The HS is negative oriented to the Left
     * Tilt Angle is positive toward the Left (trigonometric sense/anti-clockwise)
     * Tilt Angle is negative toward the Right (clockwise)
     * <p>
     * To increment HorizontalSpeed, the tilt angle must be negative and the thrust power must
     */

    void goOnRight() {
        System.err.println("Going on right");
        if (this.horizontalSpeed <= ABSOLUTE_MAX_HORIZONTAL_SPEED * 2) {
            this.updateThrustPower(4);
            if (this.isAboveLandingArea() && this.getMarsSurface().getPointsOnRightOfReferencePoint(this.getPosition()).stream().allMatch(point -> this.getPosition().isAboveOf(point))) {
                this.updateRotationAngle(-45);
            } else {
                this.updateRotationAngle(0);
            }
        } else {
            this.updateThrustPower(4);
            if (this.isAboveLandingArea() && this.getMarsSurface().getPointsOnRightOfReferencePoint(this.getPosition()).stream().allMatch(point -> this.getPosition().isAboveOf(point))) {
                //Going too fast, we try to slow down
                this.updateRotationAngle(45);
            } else {
                this.updateRotationAngle(0);
            }
        }
    }

    void goOnLeft() {
        System.err.println("Going on left");
        if (this.horizontalSpeed >= -ABSOLUTE_MAX_HORIZONTAL_SPEED * 2) {
            this.updateThrustPower(4);
            if (this.isAboveLandingArea() && this.getMarsSurface().getPointsOnLeftOfReferencePoint(this.getPosition()).stream().allMatch(point -> this.getPosition().isAboveOf(point))) {
                this.updateRotationAngle(45);
            } else {
                this.updateRotationAngle(0);
            }
        } else {
            this.updateThrustPower(4);
            if (this.isAboveLandingArea() && this.getMarsSurface().getPointsOnLeftOfReferencePoint(this.getPosition()).stream().allMatch(point -> this.getPosition().isAboveOf(point))) {
                //Going too fast, we try to slow down
                this.updateRotationAngle(-45);
            } else {
                this.updateRotationAngle(0);
            }
        }
    }

    void prepareLandingApproachingOnRight() {
        System.err.println("Prepare landing approaching on right");
        if (this.horizontalSpeed > ABSOLUTE_MAX_HORIZONTAL_SPEED - 5 && this.horizontalSpeed <= ABSOLUTE_MAX_HORIZONTAL_SPEED) {
            this.updateRotationAngle(0);
            if (this.isBelowLandingArea() || this.getMarsSurface().getPointsOnRightOfReferencePoint(this.getPosition()).stream().anyMatch(point -> this.getPosition().isBelowOf(point))) {
                this.updateThrustPower(4);
            } else {
                regulateVerticalSpeed();
            }
        } else if (this.horizontalSpeed > ABSOLUTE_MAX_HORIZONTAL_SPEED) {
            this.updateThrustPower(4);
            if (this.isAboveLandingArea() && this.getMarsSurface().getPointsOnRightOfReferencePoint(this.getPosition()).stream().allMatch(point -> this.getPosition().isAboveOf(point))) {
                //Going too fast, we try to slow down
                this.updateRotationAngle(45);
            } else {
                this.updateRotationAngle(0);
            }
        } else {
            this.updateThrustPower(4);
            if (this.isAboveLandingArea() && this.getMarsSurface().getPointsOnRightOfReferencePoint(this.getPosition()).stream().allMatch(point -> this.getPosition().isAboveOf(point))) {
                this.updateRotationAngle(-45);
            } else {
                this.updateRotationAngle(0);
            }
        }
    }

    void prepareLandingApproachingOnLeft() {
        System.err.println("Prepare landing approaching on left");
        if (this.horizontalSpeed >= -ABSOLUTE_MAX_HORIZONTAL_SPEED && this.horizontalSpeed < -ABSOLUTE_MAX_HORIZONTAL_SPEED + 5) {
            this.updateRotationAngle(0);
            if (this.isBelowLandingArea() || this.getMarsSurface().getPointsOnLeftOfReferencePoint(this.getPosition()).stream().anyMatch(point -> this.getPosition().isBelowOf(point))) {
                this.updateThrustPower(4);
            } else {
                regulateVerticalSpeed();
            }
        } else if (this.horizontalSpeed < -ABSOLUTE_MAX_HORIZONTAL_SPEED) {
            this.updateThrustPower(4);
            if (this.isAboveLandingArea() && this.getMarsSurface().getPointsOnLeftOfReferencePoint(this.getPosition()).stream().allMatch(point -> this.getPosition().isAboveOf(point))) {
                //Going too fast, we try to slow down
                this.updateRotationAngle(-45);
            } else {
                this.updateRotationAngle(0);
            }
        } else {
            this.updateThrustPower(4);
            if (this.isAboveLandingArea() && this.getMarsSurface().getPointsOnLeftOfReferencePoint(this.getPosition()).stream().allMatch(point -> this.getPosition().isAboveOf(point))) {
                this.updateRotationAngle(45);
            } else {
                this.updateRotationAngle(0);
            }
        }
    }

    void prepareFinalLanding() {
        if ((this.horizontalSpeed > -ABSOLUTE_MAX_HORIZONTAL_SPEED && this.horizontalSpeed < ABSOLUTE_MAX_HORIZONTAL_SPEED)) {
            this.updateRotationAngle(0);
            regulateVerticalSpeed();
        } else if (this.horizontalSpeed >= ABSOLUTE_MAX_HORIZONTAL_SPEED) {
            //We slow down
            this.updateRotationAngle(45);
            this.updateThrustPower(4);
        } else {
            //we slow down
            this.updateRotationAngle(-45);
            this.updateThrustPower(4);
        }
    }

    void regulateVerticalSpeed() {
        if (this.verticalSpeed > ABSOLUTE_MAX_VERTICAL_SPEED - 10) {
            this.updateThrustPower(0);
        } else if (verticalSpeed < -ABSOLUTE_MAX_VERTICAL_SPEED + 10) {
            this.updateThrustPower(4);
        } else {
            this.updateThrustPower(0);
        }
    }

    String computeRotationAngleAndThrustPower() {
        if (isNearOnLeftOfLandingArea()) {
            System.err.println("Lander nearing on left of Flat Area");
            prepareLandingApproachingOnRight();
        } else if (isOnLeftOfLandingArea()) {
            System.err.println("Lander on left of Flat Area");
            goOnRight();
        } else if (isNearOnRightOfLandingArea()) {
            System.err.println("Lander nearing on right of Flat Area");
            prepareLandingApproachingOnLeft();
        } else if (isOnRightOfLandingArea()) {
            System.err.println("Lander on right of Flat Area");
            goOnLeft();
        } else {
            System.err.println("Lander above Flat Area");
            prepareFinalLanding();
        }
        return outputAnswer();
    }

}