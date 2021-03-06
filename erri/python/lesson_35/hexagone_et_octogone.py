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

hexagone(100,"black","red")

# octogone
cote = 80
angle = 45

begin_fill()
pencolor("black")
fillcolor("orange")

right(15)

for i in range(0, 7):
    forward(cote)
    right(angle)

forward(cote)

end_fill()

hideturtle()
done()
