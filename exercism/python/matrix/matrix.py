class Matrix:

    def __init__(self, matrix_string):
        self._matrix = []
        rows = matrix_string.split("\n")
        self._row_size = len(rows)
        for row_index, row in enumerate(rows):
            self._matrix.append([])
            for cell in row.split():
                self._matrix[row_index].append(int(cell))

    def row(self, index):
        internal_index = index - 1
        return [cell for cell in self._matrix[internal_index]]

    def column(self, index):
        internal_index = index - 1
        return [self._matrix[i][internal_index] for i in range(0, self._row_size)]
