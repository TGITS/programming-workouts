print('Calcul de la somme des nombres de 1 au nombre donné.')
nombre = int(input("rentrez un nombre : "))
somme = 0
for i in range(1,nombre + 1):
    somme = somme + i

print(somme)