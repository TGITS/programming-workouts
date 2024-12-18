from random import randint

"""
programme de création d'une matrice carrée avec des valeurs aléatoires et mises à zéro de sa diagonale \ (coin gauche vers coin droit)
"""


def create_square_matrix(size: int) -> list[list[int]]:
    """
    Création d'une matrice (c'est-à-dire un tableau à 2 entrées) carrée
    """
    matrice = []
    for _ in range(size):
        col = []
        for _ in range(size):
            col.append(randint(1, 9))
        matrice.append(col)
    return matrice


def display_matrix(matrix: list[list[int]]):
    """
    Affichage d'une matrice dans la console
    """
    nb_col = len(matrix)
    nb_ligne = len(matrix[0])
    for j in range(nb_ligne):
        for i in range(nb_col):
            print(matrix[i][j], end=" ")
        print()


def fill_diagonal_with_zeros(matrix: list[list[int]]):
    """
    Remplit la diagonale '\' avec des 0
    """
    nb_col = len(matrix)
    nb_ligne = len(matrix[0])
    for j in range(nb_ligne):
        for i in range(nb_col):
            if i == j:
                matrix[i][j] = 0


matrix = create_square_matrix(4)
print(matrix)

print()
display_matrix(matrix)

fill_diagonal_with_zeros(matrix)

print()
display_matrix(matrix)
