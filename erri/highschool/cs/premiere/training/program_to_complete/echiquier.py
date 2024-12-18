"""
Dessin d'un échiquier 8x8 dans la console
Les cases blanches sont représentées par des "o"
Les cases noires sont représentées par des "x".
La première colonne affichée est une case blanche, il y a ensuite une alternance de case noire et blanche
"""


def display_chessboard():
    for i in range(8):
        for j in range(8):
            if (i + j) % 2 == 0:
                print("o", end="")
            else:
                print("x", end="")
        print()


if __name__ == "__main__":
    display_chessboard()
