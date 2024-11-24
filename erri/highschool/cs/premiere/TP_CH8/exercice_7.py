# On considère la liste suivante : L = [9, 5, 1, -7, -2, 1, -2, 0, -3, 12, 23, 1, -11, -7]
# Écrire le script d’une fonction qui supprime les doublons de la liste.

def supprime_doublon(liste):
    # On définit une liste des valeurs déjà rencontrées, initialement vide
    deja_vu = []
    # On parcourt la liste 'liste' passée en paramètre, et pour chaque élément on regarde s'il est dans la liste deja_vu
    # Si c'est le cas, on le supprime de la liste
    # Sinon, on met l'élément dans la liste deja_vu
    i = 0
    while i < len(liste):
        if liste[i] in deja_vu:
            # Si l'élément est dans deja-vu on le supprime
            # On n'incrémente pas i, car on vient de supprimer l'élément courant
            # Il faut qu'on repasse par l'indice i
            liste.pop(i)
        else:
            # On ajoute l'élément courant dans deja_vu
            deja_vu.append(liste[i])
            # pas de suppression on incrémente i pour passer à l'élément suivant
            i += 1

    # On n'a pas besoin de retourner la liste
    # La liste passée en paramètre a été modifiée dans le coeur du programme


liste = [9, 5, 1, -7, -2, 1, -2, 0, -3, 12, 23, 1, -11, -7]
print(liste)
supprime_doublon(liste)
print(liste)
print()

def fusionne_sans_doublon(liste1, liste2):
    fusion_liste = liste1 + liste2
    supprime_doublon(fusion_liste)
    return fusion_liste

print(fusionne_sans_doublon(liste,liste))

print()

liste_1 = [9, 5, 1, -7, -2, 1, -2, 0, -3, 12, 23, 1, -11, -7]
liste_2 = [9, 5, 1, -7, -2, 1, -2, 0, -3, 12, 23, 1, -11, -7, 8, 8, 6, 6, 4, 4]
print(fusionne_sans_doublon(liste_1,liste_2))