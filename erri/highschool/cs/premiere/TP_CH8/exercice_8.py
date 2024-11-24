# Écrire le script d'une fonction Moyenne d'arguments deux tableaux, un Val et un autre
# Coef, et qui calcule la moyenne des valeurs du tableau Val coefficientée avec les entiers du tableau Coef.

def moyenne(val,coef):
    # les 2 tableaux val et coef doivent avoir la même taille
    assert(len(val), len(coef))
    nombre_valeurs = len(val)
    # J'initialise la somme coefficientée des notes pour pouvoir calculer la moyenne
    somme_notes_coefficientees = 0
    # Pour calculer la moyenne j'ai également besoin de la somme des coefficients
    somme_coefficients = 0
    # On calcule la valeur coefficientée en prenant l'élément au même indice dans les 2 tableaux
    for i in range(nombre_valeurs):
        # Calcul de la somme des notes
        somme_notes_coefficientees += val[i] * coef[i]
        # Calcul de la somme des coefficients
        somme_coefficients += coef[i]
    
    return somme_notes_coefficientees / somme_coefficients

valeurs_1 = [20, 20, 20, 20]
coefficients_1 = [5, 4, 5, 3]
print(moyenne(valeurs_1,coefficients_1))

valeurs_2 = [0, 20, 0, 20]
coefficients_2 = [4, 4, 5, 5]
print(moyenne(valeurs_2,coefficients_2))

valeurs_3 = [0, 20, 0, 20, 10]
coefficients_3 = [4, 4, 5, 5, 3]
print(moyenne(valeurs_3,coefficients_3))