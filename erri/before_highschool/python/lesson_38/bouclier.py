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


colormode(255)
blanc_etoile = (240, 234, 236)
couleurs = cycle([(230, 50, 53), (244, 244, 244),
                  (230, 50, 53), (107, 138, 195)])
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
while rayon >= 120:
    cercle(rayon, next(couleurs))
    positionne_tortue(pas)
    rayon = rayon-pas


penup()
left(90)
forward(200)
right(150)
pendown()
pencolor(blanc_etoile)
forward(90)


hideturtle()
done()
