def est_parfait(nb:int) -> bool:
    liste_diviseurs=[]
    for i in range(1,nb):
        if(nb%i==0):
            liste_diviseurs.append(i)

    return sum(liste_diviseurs)==nb

nb=int(input("Entrez un nombre entier strictement positif : "))

if(est_parfait(nb)):
    print(nb,"est parfait")
else:
    print(nb,"n'est pas parfait")