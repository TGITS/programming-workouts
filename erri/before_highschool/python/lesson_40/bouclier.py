from turtle import *
from itertools import cycle
from math import sqrt, sin, radians


def cercle(rayon, couleur):
    fillcolor(couleur)
    pencolor(couleur)
    begin_fill()
    circle(rayon)
    end_fill()


def positionne_tortue(pas):
    penup()
    left(90)
    forward(pas)
    right(90)
    pendown()


colormode(255)
blanc_etoile = (240, 234, 236)
couleurs = cycle([(230, 50, 53), (244, 244, 244), (230, 50, 53), (107, 138, 195)])
rayon = 240
pas = 40

speed(10)

# init tortue
penup()
right(90)
forward(rayon)
left(90)
pendown()


# dessin des cercles concentriques
while rayon > 120:
    cercle(rayon, next(couleurs))
    positionne_tortue(pas)
    rayon = rayon - pas

cercle(rayon, next(couleurs))

# calculating somme values
# Based on some maths about Pentagone and Golden Ratio :
# - https://debart.pagesperso-orange.fr/1s/pentagone.mobile.html
# - http://villemin.gerard.free.fr/Wwwgvmm/Geometri/NbOrEtoi.htm
phi = (1 + sqrt(5)) / 2
coefficient = sqrt(3 - phi)
cote_pentagone = coefficient * rayon
cote_oppose = cote_pentagone / 2
hypothenus = cote_oppose / sin(radians(54))

# Placing the turtle to the initial spot
penup()
left(90)
forward(2 * rayon)
right(90)

# Calculating the different points of the star
sommets = []
sommets.append(position())

right(72)

for i in range(0, 4):
    forward(hypothenus)
    sommets.append(position())
    left(72)
    forward(hypothenus)
    sommets.append(position())
    right(144)

forward(hypothenus)
sommets.append(position())
left(72)
forward(hypothenus)
sommets.append(position())

# Drawing the star
fillcolor(blanc_etoile)
pencolor(blanc_etoile)
begin_fill()
begin_poly()

for sommet in sommets:
    setposition(sommet)

setposition(sommets[0])

end_poly()
end_fill()

hideturtle()
done()
