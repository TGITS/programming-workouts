def compte_grains(facteur, max):
    ng = 1
    somme = 0
    for nc in range(1, max+1):
        print("numéro cases :", nc, "- nombre de graines : {:,}".format(ng))
        somme = somme+ng
        print("somme : {:,}".format(somme))
        ng = ng * facteur


if __name__ == "__main__":
    facteur = input(
        "entrer un nombre entier et strictement positif pour le facteur multiplicatif : ")
    max = input(
        "entrer un nombre entier et strictement positif pour le nombre d'itérations : ")
    compte_grains(int(facteur),int(max))
