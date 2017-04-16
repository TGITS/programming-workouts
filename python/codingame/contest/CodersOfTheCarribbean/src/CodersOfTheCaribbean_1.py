import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

#constants
SHIP = "SHIP"
BARREL = "BARREL"
MOVE = "MOVE "
SLOWER = "SLOWER"
WAIT = "WAIT"


class Point:
    def __init__(self,x,y):
        self.x = x
        self.y = y


# class for representing a ship
class Ship:
    def __init__(self,x,y,arg1,arg2,arg3,arg4):
        self.position = Point(x, y)
        self.rotation = arg1
        self.speed = arg2
        self.rum = arg3
        self.controller = arg4


# class for representing a barrel of rum
class Barrel:
    def __init__(self,x,y,arg1):
        self.position = Point(x, y)
        self.quantity = arg1


# Function to compute magnitude
def distance(a, b):
    """Calculate the distance between point a and b on a cartesian plane"""
    d = math.sqrt(float((b.x - a.x)**2) + float((b.y - a.y)**2))


# game loop
while True:
    my_ship_count = int(input())  # the number of remaining ships
    entity_count = int(input())  # the number of entities (e.g. ships, mines or cannonballs)
    my_ships = []
    opponent_ships = []
    for i in range(entity_count):
        entity_id, entity_type, x, y, arg_1, arg_2, arg_3, arg_4 = input().split()
        entity_id = int(entity_id)
        x = int(x)
        y = int(y)
        arg_1 = int(arg_1)
        arg_2 = int(arg_2)
        arg_3 = int(arg_3)
        arg_4 = int(arg_4)
    for i in range(my_ship_count):

        # Write an action using print
        # To debug: print("Debug messages...", file=sys.stderr)

        # Any valid action, such as "WAIT" or "MOVE x y"
        print("MOVE 11 10")
