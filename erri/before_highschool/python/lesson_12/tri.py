def tri(tableau):
    indice_depart = 0
    taille_tableau = len(tableau)

    while indice_depart < taille_tableau:
        indice_minimum, _ = minimum_avec_indice(
            tableau, indice_depart)
        tableau[indice_depart], tableau[indice_minimum] = tableau[indice_minimum], tableau[indice_depart]
        indice_depart = indice_depart + 1
    return tableau


def minimum(tableau):
    valeur_minimum = tableau[0]
    for valeur in tableau:
        if valeur_minimum > valeur:
            valeur_minimum = valeur
    return valeur_minimum


def minimum_avec_indice(tableau, indice_depart=0):
    indice_minimum = indice_depart
    valeur_minimum = tableau[indice_minimum]
    for indice, valeur in enumerate(tableau):
        if indice >= indice_depart and valeur_minimum > valeur:
            valeur_minimum = valeur
            indice_minimum = indice
    return indice_minimum, valeur_minimum


if __name__ == "__main__":
    mon_tableau = [5,4,1,2,10,8,3,9,7,6]
    print(mon_tableau)
    print(minimum(mon_tableau))
    print(minimum_avec_indice(mon_tableau))
    print(tri(mon_tableau))
    print(mon_tableau)
