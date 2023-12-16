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

# Tracé des vecteurs vitesse
N=6 # Nombre de points
dt=0.40
for k in range(0,N-1):
    Vx=(x[k+1]-x[k])/dt

    echelle=0.3 # coefficient pour ajuster la longeur des vecteurs
    Vx=Vx*echelle

    plt.quiver(x[k],y[k],Vx,0,color="red", scale_units='xy',scale=1)

# Affichage
plt.show()
