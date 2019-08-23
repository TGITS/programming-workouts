import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Matrix {
    private String[] rows;

    Matrix(String matrixAsString) {
        rows = matrixAsString.split("\n");
    }

    int[] getRow(int rowNumber) {
        List<Integer> list = Arrays.stream(rows[rowNumber - 1].split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        int[] row = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            row[i] = list.get(i);
        }
        return row;
    }

    int[] getColumn(int columnNumber) {
        int[] column = new int[rows.length];
        for (int i = 0; i < rows.length; i++) {
            column[i] = getRow(i + 1)[columnNumber - 1];
        }
        return column;
    }
}
