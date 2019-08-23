import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Matrix {
    private String[] rows;

    Matrix(String matrixAsString) {
        rows = matrixAsString.split("\n");
    }

    int[] getRow(int rowNumber) {
        List<Integer> temp = Arrays.stream(rows[rowNumber-1].split(" ")).map(String::trim).map(Integer::parseInt).collect(Collectors.toList());
        int[] result = new int[temp.size()];
        for(int i =0; i < temp.size() ; i++) {
            result[i] = temp.get(i);
        }
        return result;
    }

    int[] getColumn(int columnNumber) {
        throw new UnsupportedOperationException("Delete this statement and write your own implementation.");
    }
}
