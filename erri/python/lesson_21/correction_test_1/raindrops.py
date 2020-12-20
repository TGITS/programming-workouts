def est_divisible_par_3(nombre):
    """
    Cette fonction retourne le booléen Vrai (True en Python) si le nombre est divisible par 3 et le booléen Faux (False en Python) sinon
    Pour rappel un nomnbre est divisible par 3 si le reste de la division euclidienne de ce nombre par 3 vaut 0.
    Pour avoir le reste de la division en Python on peut utiliser l'opérateur %
    """
    return nombre % 3 == 0


def est_divisible_par_5(nombre):
    """
    Cette fonction retourne le booléen Vrai (True en Python) si le nombre est divisible par 5 et le booléen Faux (False en Python) sinon
    Pour rappel un nomnbre est divisible par 5 si le reste de la division euclidienne de ce nombre par 5 vaut 0.
    Pour avoir le reste de la division en Python on peut utiliser l'opérateur %
    """
    return nombre % 5 == 0


def est_divisible_par_7(nombre):
    """
    Cette fonction retourne le booléen Vrai (True en Python) si le nombre est divisible par 7 et le booléen Faux (False en Python) sinon
    Pour rappel un nomnbre est divisible par 7 si le reste de la division euclidienne de ce nombre par 7 vaut 0.
    Pour avoir le reste de la division en Python on peut utiliser l'opérateur %
    """
    return nombre % 7 == 0


def convertir(nombre):
    """
    Cette fonction convertit le nombre donné en paramètre selon la règle suivante :
    Si le nombre est divisible par 3, alors il ajoute la chaîne de caractères "Pling" au résultat
    Si le nombre est divisible par 5, alors il ajoute la chaine de caractères "Plang" au résultat
    Si le nombre est divisible par 7, alors il ajoute la chaîne de caractères "Plong" au résultat
    Si le nombre n'est divisible ni par 3, ni par 5 et ni par 7, alors le résultat est ce nombre convertit en chaîne de caractères.
    Le résultat est ce que retourne cette fonction.
    """
    resultat = ""
    if est_divisible_par_3(nombre):
        resultat += "Pling"
    if est_divisible_par_5(nombre):
        resultat += "Plang"
    if est_divisible_par_7(nombre):
        resultat += "Plong"
    if resultat == "":
        resultat = str(nombre)
    return resultat


if __name__ == "__main__":
    liste_de_resultats = []
    for nombre in range(1, 106):
        liste_de_resultats.append(convertir(nombre))

    print(" ; ".join(liste_de_resultats))
