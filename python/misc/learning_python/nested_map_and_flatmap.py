import itertools
import functools
import operator


print("Pour effectuer l'équivalent d'une compréhension imbriquée")
columns = ('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h')
rows = (1, 2, 3, 4, 5, 6, 7, 8)
chessboard = functools.reduce(operator.iconcat, map(
    lambda x: map(lambda y: (x, y), rows), columns), [])
print("Liste des cases de l'échiquier avec map, reduce et iconcat :", chessboard)
chessboard = itertools.chain(
    *map(lambda x: map(lambda y: (x, y), rows), columns))
print("\nListe des cases de l'échiquier avec map et chain :", list(chessboard))
print("\nPour effectuer l'équivalent d'une compréhension imbriquée on préféra utiliser product")
chessboard = itertools.product(columns, rows)
print("Liste des cases de l'échiquier avec product:", list(chessboard))


def functional_flatmap(fun, iterator):
    return functools.reduce(operator.iconcat, map(fun, iterator))


chessboard = functional_flatmap(
    lambda x: list(map(lambda y: (x, y), rows)), columns)
print("\nListe des cases de l'échiquier avec functional_flatmap :", chessboard)


print('\n##############\n')
