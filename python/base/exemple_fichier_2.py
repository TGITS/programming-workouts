# Ouverture d'un fichier en écriture => 'w' (write)
with open('mangas.txt', 'w') as fichier:
    # Ecriture dans le fichier le caractère '\n' indique un retour à la ligne
    fichier.write("Spy x Family\n")
    fichier.write("One Piece\n")
    fichier.write("Radiant\n")
    fichier.write("Dragon Ball\n")

# Ouverture d'un fichier en lecture => 'r' (read)
with open('mangas.txt', 'r') as fichier:
    for line in fichier:
        # La fonction strip() sur les str supprime 
        # les caractères blancs et retours à la ligne 
        # en début et fin de ligne
        print(line.strip())

# Pour avoir une séparation dans l'affichage
print("-------------")

# Ouverture d'un fichier en lecture => 'r' (read)
with open('mangas.txt', 'r') as fichier:
    # On lit le fichier en 1 seule fois
    print(fichier.read())