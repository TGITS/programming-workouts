from turtle import *

cote = 150
angle = 60

begin_fill()
pencolor("black")
fillcolor("light blue")

left(angle)
forward(cote)
right(180 - angle)
forward(cote)
right(180 - angle)
forward(cote)

end_fill()

hideturtle()
done()