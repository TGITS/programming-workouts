from turtle import *
from itertools import cycle


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


couleurs = cycle(["black", "grey", "purple", "blue", "light blue",
                  "green", "light green", "yellow", "orange", "red"])
rayon = 200
pas = 10

speed(10)

# init tortue
penup()
right(90)
forward(rayon)
left(90)
pendown()


# dessin des cercles concentriques
while rayon > 0:
    cercle(rayon, next(couleurs))
    positionne_tortue(pas)
    rayon = rayon-pas


hideturtle()
done()
