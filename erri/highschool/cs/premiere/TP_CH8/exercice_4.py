# Écrire la fonction COMPTE(X,L) qui compte et renvoie le nombre d'éléments égaux à x dans la liste l.
# Proposer deux versions :
# * un parcours par valeur ;
# * un parcours par indice.

def compte_0(x,l):
    # Fondamentalement nous sommes en train de réécrire la méthode count des listes sous forme de fonction
    return l.count(x)

# version avec un parcours de valeurs
def compte_1(x,l):
    # La variable qui nous sert à compter les occurences de x dans la liste l
    compteur = 0
    # On parcours les valeurs de la liste l
    # quand une de ces valeurs e est égale à la valeur x, on incrémente le compteur
    for e in l:
        if e == x:
            compteur += 1
    
    return compteur

# version avec un parcours par indice
# pas la solution la plus naturelle
def compte_2(x,l):
    # La variable qui nous sert à compter les occurences de x dans la liste l
    compteur = 0
    # Notre variable de boucle va prendre successivement pour valeurs les différentes valeurs d'indice de la liste
    # quand une valeur à un indice i est égale à la valeur x, on incrémente le compteur
    for i in range(len(l)):
        if l[i] == x:
            compteur += 1
    
    return compteur

x1 = 0
x2 = 9
liste1 = [0,1,2,3,4,0,0,5,6,0]

print(compte_0(x1, liste1))
print(compte_1(x1, liste1))
print(compte_2(x1, liste1))
print()
print(compte_0(x2, liste1))
print(compte_1(x2, liste1))
print(compte_2(x2, liste1))