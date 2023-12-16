from turtle import *


def hexagone(cote, couleur, remplissage):
    angle = 60

    begin_fill()
    pencolor(couleur)
    fillcolor(remplissage)

    right(angle)

    for i in range(0, 5):
        forward(cote)
        left(angle)

    forward(cote)

    end_fill()


couleurs = ["black","grey","purple","blue","light blue", "green","light green", "yellow", "orange", "red"]
cote = 200
pas = 10
for i in range(0, 10):
    hexagone(cote - pas * i, "black", couleurs[i])
    home()


hideturtle()
done()
