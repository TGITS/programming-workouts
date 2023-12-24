prix_chalet=800

forfait_ski_max4= 220
forfait_ski_min5= 180

taille_groupe= int(input("Entrez un nombre de personnes: "))

cout_total=prix_chalet

if taille_groupe > 0 and taille_groupe <= 4:
    cout_total+=forfait_ski_max4*taille_groupe  
elif taille_groupe >= 5:
    cout_total+=forfait_ski_min5*taille_groupe
else:
    print("TU t'es bien fait avoir petit voyou!! Cheh!!!")
    exit(-1)

print("coût total:",cout_total)
print("coût par personne:",cout_total/taille_groupe)


    
