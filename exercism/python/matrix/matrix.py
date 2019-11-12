class Matrix:

    def __init__(self, matrix_string):
        rows = matrix_string.split("\n")
        self._row_size = len(rows)
        self._matrix = [[int(cell) for cell in row.split()] for row in rows]

    def row(self, index):
        internal_index = index - 1
        return list(self._matrix[internal_index])

    def column(self, index):
        internal_index = index - 1
        return [self._matrix[i][internal_index] for i in range(self._row_size)]
