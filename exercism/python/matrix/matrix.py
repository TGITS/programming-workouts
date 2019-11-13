class Matrix:

    def __init__(self, matrix_string):
        rows = matrix_string.splitlines()
        self._matrix = [[int(cell) for cell in row.split()] for row in rows]

    def row(self, index):
        internal_index = index - 1
        return self._matrix[internal_index]

    def column(self, index):
        internal_index = index - 1
        return [row[internal_index] for row in self._matrix]
