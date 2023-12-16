from turtle import *

print(position())
pencolor("black")
fillcolor("red")
pendown()
begin_fill()
# Premier carré
forward(222)
print(position())
for i in range(0, 3):
    left(90)
    forward(222)
    print(position())

# 2ème carré
forward(222)
print(position())
for i in range(0, 3):
    right(90)
    forward(222)
    print(position())
end_fill()

begin_fill()
pencolor("grey")
fillcolor("purple")
right(90)
penup()
forward(222)
left(90)
pendown()
circle(222)

end_fill()
hideturtle()
done()
