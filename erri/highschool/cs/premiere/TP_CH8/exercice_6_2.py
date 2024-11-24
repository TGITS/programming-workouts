# Écrire le script d’une fonction qui permet d’obtenir un tableau de n lignes et p colonnes 
# contenant des nombres entiers aléatoirement choisit entre 0 et 100.

from random import *

def tableau_aleatoirement_rempli(n,p):
    '''Retourne un tableau de n lignes et p colonnes
    
    n nombre de lignes
    p nombres de colonnnes
    '''
    # Le tableau que nous llons construire
    tableau = []
    # Pour chaque ligne
    for i in range(0,n):
        # On initialise la ligne a créée
        ligne = []
        for j in range(0,p):
            # On ajoute à la ligne un entier aléatoirement choisi entre 0 et 100
            # On utilise randint du package random
            # On pourrait utiliser randrange également
            ligne.append(randint(0,100))
        # On ajoute la ligne au tableau
        tableau.append(ligne)
    
    return tableau

print(tableau_aleatoirement_rempli(3,4))