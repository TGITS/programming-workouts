def séparation_positif_negatif(tableau):
    t_positif = []
    t_negatif = []

    for elt in tableau:
        if elt < 0:
            t_negatif.append(elt)
        else:
            t_positif.append(elt)

    return t_positif, t_negatif
