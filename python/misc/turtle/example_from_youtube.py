from turtle import *

speed(0)
bgcolor("black")
c=['cyan','white']
pensize(2)

for x in range(450):
    color(c[x%2])
    fd(x)
    lt(91)
done()