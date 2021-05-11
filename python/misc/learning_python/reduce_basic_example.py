import functools
import operator

# Exemple classique d'utilisation de reduce pour réaliser la somme d'une liste de nombres


# Solution classique avec une boucle for
sum_of_numbers_1 = 0
for number in range(1, 11):
    sum_of_numbers_1 = sum_of_numbers_1 + number
print("Somme des nombres de 1 à 10 avec une boucle for :", sum_of_numbers_1)

sum_of_numbers_2 = functools.reduce(lambda a, b: a + b, range(1, 11))
print("Somme des nombres de 1 à 10 avec reduce :", sum_of_numbers_2)

sum_of_numbers_3 = functools.reduce(lambda a, b: a + b, [1])
print("Somme de la liste à un élément [1] avec reduce :", sum_of_numbers_3)

# essayer de réduire une liste vide sans valeur initiale, par exemple functools.reduce(lambda a, b: a + b, []), 
# provoque la levée d'une exception
sum_of_numbers_4 = functools.reduce(lambda a, b: a + b, [], 0)
print("Somme de la liste vide [] avec reduce et une valeur initiale :", sum_of_numbers_4)

sum_of_numbers_5 = functools.reduce(lambda a, b: a + b, range(1, 11), 0)
print("Somme des nombres de 1 à 10 avec reduce et une valeur initiale de 0 :", sum_of_numbers_5)

sum_of_numbers_6 = functools.reduce(lambda a, b: a + b, range(1, 11), 5)
print("Somme des nombres de 1 à 10 avec reduce et une valeur initiale de 5 :", sum_of_numbers_6)

# On peut utiliser l'opérateur add plutôt qu'une lambda ici
sum_of_numbers_7 = functools.reduce(operator.add, range(1, 11), 5)
print("Somme des nombres de 1 à 10 avec reduce et une valeur initiale de 5 :", sum_of_numbers_7)