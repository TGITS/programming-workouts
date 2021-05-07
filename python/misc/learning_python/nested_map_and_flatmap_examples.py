import itertools
import functools
import operator

columns = ('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h')
rows = (1, 2, 3, 4, 5, 6, 7, 8)

# Approche naïve
print("Approche naïve (1) : ", list(
    map(lambda x: map(lambda y: (x, y), rows), columns)))
print()
print("Approche naïve (2) : ", list(
    map(lambda x: list(map(lambda y: (x, y), rows)), columns)))

# Utilisation de reduce avec operator.iconcat
print('\n##############\n')

chessboard = functools.reduce(operator.iconcat, map(
    lambda x: map(lambda y: (x, y), rows), columns), [])
print("Liste des cases de l'échiquier avec map, reduce et iconcat :", chessboard)

# Implémentation de flatmap avec reduce, iconcat et map
print('\n##############\n')


def functional_flatmap(fun, iterator):
    return functools.reduce(operator.iconcat, map(fun, iterator))


chessboard = functional_flatmap(
    lambda x: list(map(lambda y: (x, y), rows)), columns)
print("\nListe des cases de l'échiquier avec functional_flatmap :", chessboard)

# Implémentation de flatmap impérative
print('\n##############\n')


def imperative_flatmap(fun, iterator):
    resulting_iterator = []
    for elem in iterator:
        resulting_iterator.extend(fun(elem))
    return resulting_iterator


chessboard = imperative_flatmap(
    lambda x: list(map(lambda y: (x, y), rows)), columns)
print("\nListe des cases de l'échiquier avec imperative_flatmap :", chessboard)


# Exemples avec le __splat operator__,
print('\n##############\n')

example_list = ["a", "b", "c", "d"]
example_range = range(0, 10)
example_iterator = itertools.islice(itertools.count(10), 0, 10)
print("Une liste exemple :", example_list)
print("Une liste exemple développée avec * :", *example_list)
print("Un intervalle exemple :", example_range)
print("Un intervalle exemple développée avec * :", *example_range)
print("Un iterateur exemple :", example_iterator)
print("Un iterateur exemple développée avec * :", *example_iterator)

# Exemple avec itertools.chain
print('\n##############\n')

list_0_to_9 = range(0, 10)
list_10_to_19 = itertools.islice(itertools.count(10), 0, 10)
list_20_to_25 = itertools.islice(itertools.count(20), 0, 6)
print("Exemple d'utilisation de chain, nombres de 0 à 25 :", *
      itertools.chain(list_0_to_9, list_10_to_19, list_20_to_25))

# Utilisation de itertools.chain et du __splat operator__
print('\n##############\n')

chessboard = itertools.chain(
    *map(lambda x: map(lambda y: (x, y), rows), columns))
print("\nListe des cases de l'échiquier avec map et chain :", list(chessboard))

# Flatmap avec itertools.chain et le __splat operator__
print('\n##############\n')


def itertools_flatmap(fun, iterator):
    return itertools.chain(*map(fun, iterator))


chessboard = itertools_flatmap(lambda x: map(lambda y: (x, y), rows), columns)
print("\nListe des cases de l'échiquier avec itertools_flatmap :", list(chessboard))

# Utilisation de itertools.product
print('\n##############\n')

print("\nPour effectuer l'équivalent d'une compréhension imbriquée on préféra utiliser itertools.product")
chessboard = itertools.product(columns, rows)
print("Liste des cases de l'échiquier avec product:", list(chessboard))
