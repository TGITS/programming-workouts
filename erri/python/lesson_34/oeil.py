from turtle import *

cote = 222
print(position())
pencolor("orange")
fillcolor("orange")
pendown()
begin_fill()
# Premier carré
right(45)
forward(cote)
print(position())
for i in range(0, 3):
    left(90)
    forward(cote)
    print(position())

# 2ème carré
forward(222)
print(position())
for i in range(0, 3):
    right(90)
    forward(cote)
    print(position())
end_fill()

# Dessin 1er Cercle
begin_fill()
pencolor("red")
fillcolor("red")
right(90)
penup()
forward(cote)
left(90)
pendown()
circle(cote)
end_fill()

# Dessin 2eme Cercle
begin_fill()
pencolor("yellow")
fillcolor("black")
left(90)
penup()
forward(cote * 2 / 3)
right(90)
pendown()
circle(cote / 3)
end_fill()

hideturtle()

done()
