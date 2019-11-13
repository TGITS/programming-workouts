import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Matrix {

    private int[][] matrix = null;
    private int[][] transposedMatrix = null;
    private int numberOfRows;
    private int numberOfColumns;

    Matrix(List<List<Integer>> values) {
        if (!values.isEmpty()) {
            this.numberOfRows = values.size();
            this.numberOfColumns = values.get(0).size();
            matrix = new int[numberOfRows][numberOfColumns];
            transposedMatrix = new int[numberOfColumns][numberOfRows];
            for (int i = 0; i < numberOfRows; i++) {
                for (int j = 0; j < numberOfColumns; j++) {
                    matrix[i][j] = values.get(i).get(j);
                    transposedMatrix[j][i] = values.get(i).get(j);
                }
            }
        }
    }

    Set<MatrixCoordinate> getSaddlePoints() {
        if (matrix == null) {
            return Set.of();
        }
        Set<MatrixCoordinate> saddlePoints = new HashSet<>();
        List<Integer> rowMaxList = Arrays.stream(this.matrix).map(array -> Arrays.stream(array).max().getAsInt()).collect(Collectors.toList());
        List<Integer> rowMinList = Arrays.stream(this.transposedMatrix).map(array -> Arrays.stream(array).min().getAsInt()).collect(Collectors.toList());
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                if (rowMaxList.get(i) == rowMinList.get(j)) {
                    saddlePoints.add(new MatrixCoordinate(i + 1, j + 1));
                }
            }
        }
        return saddlePoints;
    }
}
