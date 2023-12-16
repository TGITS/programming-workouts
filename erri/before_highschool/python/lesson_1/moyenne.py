nombre_de_notes = int(input("combien de notes voulez-vous rentrer ?  "))
somme = 0 
compteur = 0
while compteur < nombre_de_notes:
    compteur = compteur + 1
    note = input("entrez une note : ")
    print("vous avez saisi la note numéro " + str(compteur) + " : " + note)
    somme = somme + int(note)

moyenne = somme / nombre_de_notes
print("moyenne : " + str(moyenne))    
