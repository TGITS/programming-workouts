# Ouverture d'un fichier en écriture => 'w' (write)
fichier = open('mangas.txt', 'w')
# Ecriture dans le fichier le caractère '\n' indique un retour à la ligne
fichier.write("Spy x Family\n")
fichier.write("One Piece\n")
fichier.write("Radiant\n")
fichier.write("Dragon Ball\n")
# On ferme le fichier qu'on a ouvert
fichier.close()

# Ouverture d'un fichier en lecture => 'r' (read)
fichier = open('mangas.txt', 'r')
for line in fichier:
    # La fonction strip() sur les str supprime 
    # les caractères blancs et retours à la ligne 
    # en début et fin de ligne
    print(line.strip())
# On ferme le fichier qu'on a ouvert
fichier.close()

# Pour avoir une séparation dans l'affichage
print("-------------")

# Ouverture d'un fichier en lecture => 'r' (read)
fichier = open('mangas.txt', 'r')
# On lit le fichier en 1 seule fois
print(fichier.read())