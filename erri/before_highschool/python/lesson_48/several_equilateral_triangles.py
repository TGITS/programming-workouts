import turtle

# initialisation
turtle.mode("standard")
turtle.home()
turtle.showturtle()
turtle.speed(0)
turtle.pencolor("orange")
turtle.fillcolor("yellow")
turtle.pensize(2)
turtle.pendown()

# dessin du triangle
side = 10
angle = 120

for _ in range(22):
    # dessin d'un triangle rempli
    turtle.begin_fill()
    for _ in range(3):
        turtle.forward(side)
        turtle.left(angle)
    turtle.end_fill()    
    side = side + 10

turtle.home()
side = 10

for _ in range(22):
    # dessin d'un triangle
    for _ in range(3):
        turtle.forward(side)
        turtle.left(angle)
    side = side + 10

# finalisation
turtle.hideturtle()
turtle.done()
