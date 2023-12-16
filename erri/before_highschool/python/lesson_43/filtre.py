def filtrer(liste_nombres, nombre_a_filtrer):
    liste_nombres_épurée = []
    for element in liste_nombres:
        if element != nombre_a_filtrer:
            liste_nombres_épurée.append(element)
    return liste_nombres_épurée
