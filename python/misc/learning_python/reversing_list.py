numbers = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
print('liste de nombres :', numbers)
numbers.reverse()
print('liste de nombres après reverse() :', numbers)

print('\n##############\n')

numbers = list(range(10))
print('liste de nombres :', numbers)
reversed(numbers)
print('liste de nombres après avoir appliqué reversed dessus :', numbers)
reversed_numbers = reversed(numbers)
print("liste de nombres dans l'ordre inverse obtenue avec reversed :", list(reversed_numbers))

print('\n##############\n')

print('Compte à rebours :')
for i in reversed(range(10)):
    print(i)

print('\n##############\n')

une_tranche_de_nombres_de_3_a_6 = numbers[3:7:1]
une_tranche_de_nombres_pair_de_0_a_8 = numbers[0:9:2]
print("liste de nombres :", numbers)
print("liste de nombres de 3 à 6 :", une_tranche_de_nombres_de_3_a_6)
print("liste de nombres de 0 à 8 :", une_tranche_de_nombres_pair_de_0_a_8)

print('\n##############\n')

copy_of_numbers = numbers[::]

print("liste de nombres :", numbers)
print("copie de la liste de nombre :", copy_of_numbers)
print("La liste et sa copie son identique :", numbers == copy_of_numbers)
print("La liste et la copie sont 2 listes distinctes :", numbers is copy_of_numbers)

print('\n##############\n')

numbers_reversed_with_slice = numbers[::-1]
print("liste de nombres :", numbers)
print("la liste de nombres inversée avec une slice :",numbers_reversed_with_slice)
