def est_parfait(nb:int) -> bool:
    liste_diviseurs=[]
    for i in range(1,nb):
        if(nb%i==0):
            liste_diviseurs.append(i)

    return sum(liste_diviseurs)==nb

nb=int(input("Entrez un nombre entier strictement positif : "))

for i in range(1,nb+1):
    if(est_parfait(i)):
        print(i)
        