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
    def __init__(self,entity_id, x,y,arg1,arg2,arg3,arg4):
        self.entity_id = entity_id
        self.position = Point(x, y)
        self.rotation = arg1
        self.speed = arg2
        self.rum = arg3
        self.owner = arg4

    def computeAction(self,barrels):
        """Take in parameter the list of the barrels and compute for each the distance to the ship"""
        self.barrelsByDistance = {}
        for b in barrels:
            d = distance(self.position,b.position)
            self.barrelsByDistance[d] = b

        sorted_distances = sorted(self.barrelsByDistance.keys())
        barrel_to_go = self.barrelsByDistance[sorted_distances[0]]
        if barrel_to_go != None:
            return MOVE + str(barrel_to_go.position.x) + " " + str(barrel_to_go.position.y)
        else:
            return WAIT


# class for representing a barrel of rum
class Barrel:
    def __init__(self,entity_id,x,y,arg1):
        self.entity_id = entity_id
        self.position = Point(x, y)
        self.quantity = arg1


# Function to compute magnitude
def distance(a, b):
    """Calculate the distance between point a and b on a cartesian plane"""
    d = math.sqrt(float((b.x - a.x)**2) + float((b.y - a.y)**2))


# game loop
while True:
    my_ship_count = int(input()) # the number of remaining ships
    print("Le nombre de bateau qu'il me reste : {}".format(my_ship_count), file=sys.stderr)
    entity_count = int(input())  # the number of entities (e.g. ships, mines or cannonballs)
    print("Le nombre d'entités présentes sur le terrain : {}".format(entity_count), file=sys.stderr)
    my_ships = []
    opponent_ships = []
    barrels = []
    for i in range(entity_count):
        entity_id, entity_type, x, y, arg_1, arg_2, arg_3, arg_4 = input().split()
        if entity_type == SHIP:
            owner = int(arg_4)
            ship = Ship(int(entity_id),int(x),int(y),int(arg_1),int(arg_2),int(arg_3),owner)
            if owner == 1:
                my_ships.append(ship)
            else:
                opponent_ships.append(ship)

        if entity_type == BARREL:
            barrel = Barrel(int(entity_id),int(x),int(y),int(arg_1))
            barrels.append(barrel)



    for s in my_ships:

        # Write an action using print
        # To debug: print("Debug messages...", file=sys.stderr)

        # Any valid action, such as "WAIT" or "MOVE x y"
        print(s.computeAction(barrels))
