# Calcul de la somme des nombres de 1 à 10 (inclus)
somme = 0
nombre = 1

# Tant que la variable nombre est inférieure ou égale à 10
# je répète les instructions dans le bloc
while nombre <= 10:
    # J'ajoute le nombre à ma somme
    somme = somme + nombre
    # Je passe au nombre suivant
    nombre = nombre + 1

# J'ai calculé ma somme, je l'affiche
print('somme :', somme)