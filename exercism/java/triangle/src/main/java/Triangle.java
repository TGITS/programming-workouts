import java.util.Arrays;

class Triangle {
    private double[] sides = new double[3];

    Triangle(double side1, double side2, double side3) throws TriangleException {
        if ((side1 == 0 || side2 == 0 || side3 == 0)
                || (side1 + side2 < side3 || side1 + side3 < side2 || side2 + side3 < side1)) {
            throw new TriangleException();
        }

        this.sides[0] = side1;
        this.sides[1] = side2;
        this.sides[2] = side3;
    }

    boolean isEquilateral() {
        return Arrays.stream(sides).distinct().count() == 1;
    }

    boolean isIsosceles() {
        return Arrays.stream(sides).distinct().count() == 2 || isEquilateral();
    }

    boolean isScalene() {
        return !isEquilateral() && !isIsosceles();
    }

    boolean isDegenerate() {
        return sides[0] + sides[1] == sides[2] || sides[0] + sides[2] == sides[1] || sides[1] + sides[2] == sides[0];
    }

}
