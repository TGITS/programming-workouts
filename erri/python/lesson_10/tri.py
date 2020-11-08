def tri(tableau):
    return tableau


def minimum(tableau):
    valeur_minimum=tableau[0]
    for element in tableau:
        if valeur_minimum > element:
            valeur_minimum = element
    return valeur_minimum


if __name__ == "__main__":
    mon_tableau = [9, 7, 8, 1, 10]
    print(minimum(mon_tableau))
