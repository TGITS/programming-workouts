"""
Dessin d'un échiquier 8x8 dans la console
Les cases blanches sont représentées par des "o"
Les cases noires sont représentées par des "x".
La première colonne affichée est une case blanche, il y a ensuite une alternance de case noire et blanche
"""

# Le programme ci-après est à compléter, là-où il y a des ...

def display_chessboard():
    for ... in range(...):
        for ... in range(...):
            if (i + j) % ... == 0:
                print("o", end="")
            else:
                ...
        ...


display_chessboard()
