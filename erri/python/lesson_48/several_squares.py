import turtle

# initialisation
turtle.setup(500, 500)
turtle.mode("standard")
turtle.home()
turtle.showturtle()
turtle.speed(10)
turtle.pencolor("red")
turtle.pensize(2)
turtle.pendown()


side = 10
angle = 90
step = 5

for e in range(30):

    # dessin d'un carré
    for i in range(4):
        turtle.forward(side)
        turtle.right(angle)

    side = side + step


# finalisation
turtle.hideturtle()
turtle.done()
