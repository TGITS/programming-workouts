import turtle

# initialisation
turtle.mode("standard")
turtle.home()
turtle.showturtle()
turtle.speed(1)
turtle.pencolor("red")
turtle.pensize(2)
turtle.pendown()

# dessin du triangle
side = 40
angle = 120

for _ in range(3):
    turtle.forward(side)
    turtle.left(angle)


# finalisation
turtle.hideturtle()
turtle.done()
