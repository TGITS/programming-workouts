class Matrix {
    private int[][] matrix;

    Matrix(String matrixAsString) {
        String[] rows = matrixAsString.split("\n");
        matrix = new int[rows.length][];
        for (int rowIndex = 0; rowIndex < rows.length; rowIndex++) {
            String[] line = rows[rowIndex].split(" ");
            matrix[rowIndex] = new int[line.length];
            for (int columnIndex = 0; columnIndex < line.length; columnIndex++) {
                matrix[rowIndex][columnIndex] = Integer.parseInt(line[columnIndex]);
            }
        }
    }

    int[] getRow(int rowNumber) {
        return matrix[rowNumber - 1];
    }

    int[] getColumn(int columnNumber) {
        int[] column = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            column[i] = matrix[i][columnNumber - 1];
        }
        return column;
    }
}
