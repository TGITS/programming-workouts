import itertools

houses = ["tyrell", "stark", "lannister", "tarly", "baratheon", "targaryen"]
print('Houses  :', houses)

print('\n##############\n')

houses_starting_with_t = filter(lambda s: s.startswith("t"), houses)
print("La fonction filter retourne un objet iterator :", houses_starting_with_t)
print("L'objet iterator converti en liste :", list(houses_starting_with_t))
print("L'équivalent avec une compréhension :", [
      house for house in houses if house.startswith("t")])

print("L'équivalent avec une boucle for : ", end='')
houses_starting_with_t = []
for house in houses:
      if house.startswith("t"):
            houses_starting_with_t.append(house)
print(houses_starting_with_t)

print('\n##############\n')

houses_with_short_name = filter(lambda s: len(s) <= 5, houses)
print("Liste des maisons dont le nom fait au plus 5 caractères : ", end='')
print(*houses_with_short_name)
print("L'équivalent avec une compréhension :", [
      house for house in houses if len(house) <= 5])

print('\n##############\n')

print("Itérateurs 'infinis' à partir d'une fonction filter")
odd_numbers = filter(lambda x: x % 2 != 0, itertools.count(1))
print("Premier nombre entier impair :", next(odd_numbers))
print("Nombre impair suivant :", next(odd_numbers))
print("Les nombres impairs suivants plus petit que 100 : ", end='')
print(*itertools.takewhile(lambda x: x < 100, odd_numbers))
print("Le nombre impair suivant :", next(odd_numbers))
print("L'équivalent avec une compréhension :", end='')
odds_numbers_with_comprehension = (
    x for x in itertools.count(1) if x % 2 != 0)
print(*itertools.takewhile(lambda x: x < 100,
                           odds_numbers_with_comprehension))

print('\n##############\n')

print("Passage de None comme valeur pour le paramètre correspondant à la fonction")
# Si la valeur de la fonction passée en paramètre est None, c'est la fonction identité qui est utilisée
# Cela revient à supprimer toutes valeurs s'évaluant à faux dans un contexte booléen
# Il faut se rappeler qu'en Python il n'y a pas que True ou False qui peuvent être évaluée à vrai ou faux dans un contexte booléen.
# Pour rappel des valeurs 'falsy' en Python sont : False, None, [], (), {}, set(), "", range(0), 0, 0.0, 0j
falsy_values = [False, None, [], (), {}, set(), "", range(0), 0, 0.0, 0j]
print("Exemple de valeurs 'falsy' en Python : ", falsy_values)
truthy_values = [True, "Not falsy", 1, [0, 1, 2]]
print("Exemple de valeurs 'truthy' en Python : ", truthy_values)
falsy_and_truthy_values = falsy_values + truthy_values
print("Exemple d'une liste de valeurs 'falsy' et 'truthy'", falsy_and_truthy_values)
print("Liste précédente filtrée de ces valeurs 'falsy' (avec filter) :", list(filter(None,falsy_and_truthy_values)))
print("Liste précédente filtrée de ces valeurs 'falsy' (avec une compréhension) :", [item for item in falsy_and_truthy_values if item])