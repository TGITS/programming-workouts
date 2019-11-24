import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Matrix {

    private Set<MatrixCoordinate> saddlePoints;

    Matrix(List<List<Integer>> values) {
        if (values.isEmpty()) {
            this.saddlePoints = Set.of();
        } else {
            int numberOfRows = values.size();
            int numberOfColumns = values.get(0).size();
            int[] rowMax = initializeIntArray(numberOfRows, Integer.MIN_VALUE);
            int[] columnMin = initializeIntArray(numberOfColumns, Integer.MAX_VALUE);
            computeRowMaxAndColumnMin(values, rowMax, columnMin, numberOfRows, numberOfColumns);
            computeNonEmptySaddlePointsSet(rowMax, columnMin, numberOfRows, numberOfColumns);
        }
    }

    private int[] initializeIntArray(int numberOfElements, int initialValue) {
        int[] intArray = new int[numberOfElements];
        for (int i = 0; i < numberOfElements; i++) {
            intArray[i] = initialValue;
        }
        return intArray;
    }

    private void computeRowMaxAndColumnMin(List<List<Integer>> values, int[] rowMax, int[] columnMin, int numberOfRows, int numberOfColumns) {
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                rowMax[i] = Math.max(rowMax[i], values.get(i).get(j));
                columnMin[j] = Math.min(columnMin[j], values.get(i).get(j));
            }
        }
    }

    private void computeNonEmptySaddlePointsSet(int[] rowMax, int[] columnMin, int numberOfRows, int numberOfColumns) {
        Set<MatrixCoordinate> saddlePoints = new HashSet<>();
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                if (rowMax[i] == columnMin[j]) {
                    saddlePoints.add(new MatrixCoordinate(i + 1, j + 1));
                }
            }
        }
        this.saddlePoints = saddlePoints;
    }


    Set<MatrixCoordinate> getSaddlePoints() {
        return this.saddlePoints;
    }
}
