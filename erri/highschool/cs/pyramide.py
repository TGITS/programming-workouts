########################################
# Pyramide à base carrée avec des billes
#
# On voudrait 7 étages
# On a 1000 billes : écrire un programme qui détermine combien d'étage pourra avoir la pyramide
#
# définir :
# nb_billes_initial le nombre de billes initial
# nb_billes le nombre de billes utilisés pour construire la pyramide
# nb_niveau le nombre de niveau (ou d'étages) de la pyramide
# cote la taille du côté du carré (en nombre de billes)
#
# Bien poser le problème : le premier niveau (niveau 0) est un carré de 7 billes de côté,
# le second niveau (niveau 1) un carré de 6 billes de côté, ...,
# le dernier niveau (niveau 6 puisqu'on a commencé à compter à partir de 0) à une bille de côté.
########################################

nb_billes_initial=1000

nb_billes=0

nb_niveau=0

cote=1

while nb_billes<=1000:
    nb_billes+=cote**2
    nb_niveau+=1
    cote+=1
    
print(nb_billes)
print(nb_niveau)