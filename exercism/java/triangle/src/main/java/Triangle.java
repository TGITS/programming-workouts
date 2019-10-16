import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class Triangle {
    private Set<Double> sides = new HashSet<>();

    Triangle(double side1, double side2, double side3) throws TriangleException {
        if ((side1 == 0 || side2 == 0 || side3 == 0)
                || (side1 + side2 < side3 || side1 + side3 < side2 || side2 + side3 < side1)) {
            throw new TriangleException();
        }

        sides.add(side1);
        sides.add(side2);
        sides.add(side3);
    }

    boolean isEquilateral() {
        return sides.size() == 1;
    }

    boolean isIsosceles() {
        return sides.size() <= 2;
    }

    boolean isScalene() {
        return sides.size() == 3;
    }

    boolean isDegenerate() {
        if (sides.size() == 2) {
            Iterator<Double> iterator = sides.iterator();
            double side1 = iterator.next();
            double side2 = iterator.next();
            return side1 < side2 ? (2 * side1) == side2 : (2 * side2) == side1;
        } else if (sides.size() == 3) {
            double[] sidesAsArray = new double[3];
            int i = 0;
            for (double side : sides) {
                sidesAsArray[i] = side;
                i++;
            }
            return sidesAsArray[0] + sidesAsArray[1] == sidesAsArray[2] || sidesAsArray[0] + sidesAsArray[2] == sidesAsArray[1] || sidesAsArray[1] + sidesAsArray[2] == sidesAsArray[0];
        }
        return false;
    }

}
