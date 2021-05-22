import turtle

# initialisation
turtle.mode("standard")
turtle.home()
turtle.showturtle()
turtle.speed(1)
turtle.pencolor("red")
turtle.pensize(2)
turtle.pendown()

# dessin du carré
side = 100
angle = 90

for i in range(4):
    turtle.forward(side)
    turtle.right(angle)


# finalisation
turtle.hideturtle()
turtle.done()
