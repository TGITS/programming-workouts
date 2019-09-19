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

final class FlatArea {
    private final Point rightMostPoint;
    private Point middlePoint;
    private final Point leftMostPoint;

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

    boolean isLanderAboveFlatArea(MarsLander marsLander) {
        return marsLander.getPosition().isOnLeftOf(this.getFlatArea().getLeftMostPoint()) && marsLander.getPosition().isOnRightOf(this.getFlatArea().getRightMostPoint());
    }

    boolean isLanderOnLeftOfFlatArea(MarsLander marsLander) {
        return marsLander.getPosition().isOnLeftOf(this.getFlatArea().getLeftMostPoint());
    }

    boolean isLanderOnRightOfFlatArea(MarsLander marsLander) {
        return marsLander.getPosition().isOnRightOf(this.getFlatArea().getRightMostPoint());
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

    String outputAnswer() {
        return String.format("%d %d", this.rotationAngle, this.thrustPower);
    }

    void incrementRotationAngle() {
        if (this.rotationAngle >= 75) {
            this.rotationAngle = 90;
        } else {
            this.rotationAngle = this.rotationAngle + 15;
        }
    }

    void decrementRotationAngle() {
        if (this.rotationAngle <= -75) {
            this.rotationAngle = -90;
        } else {
            this.rotationAngle = this.rotationAngle - 15;
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
        //Quelle est notre vitesse horizontale ?
        //Est-ce que cette vitesse horizontale est dans la bonne direction ? La vitesse horizontale doit être négative pour qu'on aille à droite
        //Quelle est notre angle de rotation ? Est-ce que c'est angle est dans le bon sens ?
        //Si on va dans la bonne direction, est-ce que notre vitesse est suffisante ?
        //Comment déterminer que la vitesse est suffisante ? En approchant il faut ralentir
        if(this.horizontalSpeed > 0) {
            //Vitesse horizontale dans la bonne direction
        }
        else {
            //vitesse horizontale dans la mauvaise direction
            if(this.rotationAngle > 0) {
                //Angle de rotation dans la bonne direction
            } else {
                //Angle de rotation dans la mauvaise direction
                //Il faut corriger l'angle qui actuellement est négatif ou nul, on veut le rentre positif
                this.incrementRotationAngle();
            }
        }
    }

    String computeRotationAngleAndThrustPower(Surface marsSurface) {
        String thrustPower = "";

        if (marsSurface.isLanderOnLeftOfFlatArea(this)) {
            //we need to go to the right
        } else if (marsSurface.isLanderOnRightOfFlatArea(this)) {
            //we need to go to the left
        } else if (marsSurface.isLanderAboveFlatArea(this)) {
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
        return outputAnswer();
    }

    String computeRotationAngle(Surface marsSurface) {
        FlatArea flatGround = marsSurface.getFlatArea();
        return "0";
    }

    String computeThrustPower(Surface marsSurface) {
        String result = "";
        FlatArea flatGround = marsSurface.getFlatArea();
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