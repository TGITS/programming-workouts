import turtle
import itertools


def hexagone(side, pen_color, fill_color):
    angle = 60

    turtle.begin_fill()
    turtle.pencolor(pen_color)
    turtle.fillcolor(fill_color)

    turtle.right(angle)

    for _ in range(0, 5):
        turtle.forward(side)
        turtle.left(angle)

    turtle.forward(side)

    turtle.end_fill()


colors = itertools.cycle(["black", "grey", "purple", "blue", "light blue",
                          "green", "light green", "yellow", "orange", "red"])
side = 200
step = 10

while side > 0:
    drawing_color = next(colors)
    hexagone(side, drawing_color, drawing_color)
    side = side - step
    turtle.home()


turtle.hideturtle()
turtle.done()
