from turtle import *
from itertools import cycle
from math import sqrt, sin


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

# penup()
# left(90)
# forward(200)
# right(150)
# pendown()
# pencolor(blanc_etoile)
# forward(90)

phi = (1 + sqrt(5)) / 2
coefficient = sqrt(3 - phi)
cote_pentagone = coefficient * rayon
cote_oppose = cote_pentagone / 2
hypothenus = cote_oppose / sin(54)

penup()
left(90)
forward(2 * rayon)
right(126)
pendown()
pencolor(blanc_etoile)
fillcolor(blanc_etoile)
sommets = dict()
sommets[1] = position()
forward(coefficient * rayon)
right(180 - 108)
sommets[2] = position()
forward(coefficient * rayon)
right(180 - 108)
sommets[3] = position()
forward(coefficient * rayon)
right(180 - 108)
sommets[4] = position()
forward(coefficient * rayon)
right(180 - 108)
sommets[5] = position()
forward(coefficient * rayon)

hideturtle()
done()
