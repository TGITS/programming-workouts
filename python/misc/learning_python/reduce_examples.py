import functools

# Exemple classique d'utilisation de reduce pour réaliser la somme d'une liste de nombres
numbers_1_to_10 = range(1, 11)
sum_numbers_1_to_10 = functools.reduce(lambda a, b: a + b, numbers_1_to_10)
print("Somme des nombres de 1 à 10 (avec reduce) :", sum_numbers_1_to_10)

print()

# Néanmoins Python propose directement la fonction native sum() pour ce cas d'utilisation
print("Somme des nombres de 1 à 10 (avec sum) :", sum(numbers_1_to_10))

print()

print("Pareil mais avec une valeur de départ de 5 ajoutée à la somme")
sum_numbers_1_to_10_plus_5 = functools.reduce(
    lambda a, b: a + b, numbers_1_to_10, 5)
print("Somme des nombres de 1 à 10 avec une valeur initiale de 5 (avec reduce) :",
      sum_numbers_1_to_10_plus_5)
print("Somme des nombres de 1 à 10 avec une valeur initiale de 5 (avec sum) :",
      sum(numbers_1_to_10, 5))

print('\n##############\n')

# Autre exemple classique d'utilisation de reduce pour retourner le maximum ou le minimum
print("Le plus grand nombre pour l'intervalle de 1 à 10 (avec reduce) :",
      functools.reduce(lambda a, b: a if a > b else b, numbers_1_to_10))
print("Le plus petit nombre pour l'intervalle de 1 à 10 (avec reduce) :",
      functools.reduce(lambda a, b: a if a < b else b, numbers_1_to_10))

print()

# Néanmoins Python propose directement les fonctions native max() et min() pour ce cas d'utilisation
print("Le plus grand nombre pour l'intervalle de 1 à 10 (avec max) :",
      max(numbers_1_to_10))
print("Le plus petit nombre pour l'intervalle de 1 à 10 (avec min) :",
      min(numbers_1_to_10))

print('\n##############\n')

print("On n'est pas obligé de réduire à une valeur scalaire !")
houses = ["tyrell", "stark", "lannister", "tarly", "baratheon", "targaryen"]
print('Houses  :', houses)


def accumulator_function(accumulator, current_item):
    accumulator.update({current_item: len(current_item)})
    return accumulator


length_by_house = functools.reduce(accumulator_function, houses, {})
print("Length of the name by House name (reduce):", length_by_house)
print("Length of the name by House name (compréhension):",
      {house: len(house) for house in houses})
