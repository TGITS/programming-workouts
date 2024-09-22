# Script d’un programme qui demande les coordonnées de deux points A et B et qui calcule la distance AB.
from math import sqrt

print("Veuillez saisir les coordonnées du point A")
xa = float(input("Saisissez l'abscisse du point A: "))
ya = float(input("Saisissez l'ordonnée du point A: "))

print("Veuillez saisir les coordonnées du point B")
xb = float(input("Saisissez l'abscisse du point B: "))
yb = float(input("Saisissez l'ordonnée du point B: "))

d = sqrt((xb - xa)**2 + (yb - ya)**2)

print("la distance entre les points A et B est :", str(d))
