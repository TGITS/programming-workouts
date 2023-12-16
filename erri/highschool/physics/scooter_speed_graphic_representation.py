# Importation des bibliothèques numpy et matplotlib
import numpy as np
import matplotlib.pyplot as plt

# Positions du point M
# M(x,y) : (0,0),(8,0),(19,0),(31,0),(45,0),(62,0)
# M ne croit que selon l'axe des abscisses
# A noter la manière de saisir les coordonnées
# On a un tableau des x et un tableau des y, les mêmes indices de chaque tableau correspondent aux coordonnées du point M.
x=np.array([0,8,19,31,45,62])
y=np.array([0,0,0,0,0,0])

# Tracé de la chronophotographie avec l'aide de matplotlib
plt.plot(x,y, 'o', markersize=5)
plt.xlabel("x")
plt.ylabel("y")
plt.title("chronophotographie")

# Affichage
plt.show()
