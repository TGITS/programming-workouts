def pouce_vers_centimètre(valeur_en_pouce):
    return 2.54 * valeur_en_pouce


def centimètre_vers_pouce(valeur_en_centimètre):
    return valeur_en_centimètre / 2.54


if __name__ == "__main__":
    nombre = float(input("entrez un nombre de pouce : "))
    print("le nombre converti en centimètre :", pouce_vers_centimètre(nombre))
