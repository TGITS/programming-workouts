from turtle import *

print(position())
pencolor("black")
fillcolor("white")
pendown()
begin_fill()
# Premier carré
right(45)
forward(200)
print(position())
for i in range(0, 3):
    left(90)
    forward(200)
    print(position())

# 2ème carré
forward(200)
print(position())
for i in range(0, 3):
    right(90)
    forward(200)
    print(position())
end_fill()

#External circle
begin_fill()
pencolor("grey")
fillcolor("blue")
right(90)
penup()
forward(200)
left(90)
pendown()
circle(200)
end_fill()

#External circle
penup()
goto(0,-50)
left(45)
pendown()
begin_fill()
pencolor("black")
fillcolor("black")
circle(50)
end_fill()


hideturtle()
done()
