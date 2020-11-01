def calcul(entier):
    resultat = entier * 11
    resultat = resultat+5
    resultat = resultat * 9
    resultat = resultat+entier
    return resultat

if __name__ == "__main__":
    for x in range(0,21):
        print(calcul(x))
