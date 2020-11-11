from itertools import count, islice, zip_longest

print("zip avec des séquences infinies")
print(list(islice(zip(count(0), count(1), count(2), count(3)), 10)))

print("\n###########\n")

print("zip avec des séquences de tailles différentes")
numbers = [1, 2, 3, 4, 5]
lower_cap_letters = ['a', 'b', 'c', 'd']
upper_cap_letters = ['A', 'B', 'C']
print(numbers)
print(lower_cap_letters)
print(upper_cap_letters)
print("Les 3 listes précédentes 'zippées' avec la fonction zip :",
      list(zip(numbers, lower_cap_letters, upper_cap_letters)))

print("\n###########\n")

print("Utilisation de zip_longest de itertools")
print(numbers)
print(lower_cap_letters)
print(upper_cap_letters)
print("Les 3 listes précédentes 'zippées' avec la fonction itertools.zip_longest :",
      list(zip_longest(numbers, lower_cap_letters, upper_cap_letters)))

print("\n###########\n")

print("Fonction zip avec 0 ou 1 seule séquence")
print(list(zip()))
print(list(zip(range(10))))
print(list(zip_longest(range(10))))