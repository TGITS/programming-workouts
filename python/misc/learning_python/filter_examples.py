import itertools

houses = ["tyrell", "stark", "lannister", "tarly", "baratheon", "targaryen"]
print('Houses  :', houses)

print('\n##############\n')

houses_starting_with_t = filter(lambda s: s.startswith("t"), houses)
print("La fonction filter retourne un objet iterator :", houses_starting_with_t)
print("L'objet iterator converti en liste :", list(houses_starting_with_t))
print("L'équivalent avec une compréhension :", [
      house for house in houses if house.startswith("t")])

print('\n##############\n')

houses_starting_with_short_name = filter(lambda s: len(s) <= 5, houses)
print("Liste des maisons dont le nom fait au plus 5 caractères : ", end='')
print(*houses_starting_with_short_name)
print("L'équivalent avec une compréhension :", [
      house for house in houses if len(house) <= 5])

print('\n##############\n')

print("Itérateurs 'infinis' à partir d'une fonction filter")
what_are_the_odds = filter(lambda x: x % 2 != 0, itertools.count(1))
print("Premier nombre entier impair :", next(what_are_the_odds))
print("Nombre impair suivant :", next(what_are_the_odds))
print("Les nombres impairs suivants plus petit que 100 : ", end='')
print(*itertools.takewhile(lambda x: x < 100, what_are_the_odds))
print("Le nombre impair suivant :", next(what_are_the_odds))
print("L'équivalent avec une compréhension :", end='')
print(*itertools.takewhile(lambda x: x < 100, (x for x in itertools.count(1) if x % 2 != 0)))