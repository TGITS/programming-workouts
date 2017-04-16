import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

#constants
#Type of entity
SHIP = "SHIP"
BARREL = "BARREL"
CANNONBALL = "CANNONBALL"
MINE = "MINE"
#Commands
MOVE = "MOVE "
SLOWER = "SLOWER"
WAIT = "WAIT"


class Point:
    """Class representing a point or a position"""
    def __init__(self,x,y):
        self.x = x
        self.y = y

    def distance(self, b):
        """Calculate the distance between point a and b on a cartesian plane"""
        d = math.sqrt(float((b.x - self.x)**2) + float((b.y - self.y)**2))


class GameEntity:
    """Superclass of all the entities of the game, that are all characterize by a position and an id"""
    def __init__(self,entity_id,x,y):
        self.entity_id = entity_id
        self.position = Point(x, y)


class Barrel(GameEntity):
    """Class representing a barrel of rum with its quantity of rum"""
    def __init__(self,entity_id,x,y,arg1):
        GameEntity.__init__(self, entity_id,x,y)
        self.quantity = arg1


class Cannonbal(GameEntity):
    """Class representing a Cannonbal in the Game"""
    def __init__(self,entity_id,x,y,arg1,arg2):
        GameEntity.__init__(self, entity_id,x,y)
        self.shipEntityId = arg1
        self.turnsBeforeImpact = arg2


class Mine(GameEntity):
    """Class representing the Mine in the Game"""
    def __init__(self,entity_id,x,y):
        GameEntity.__init__(self, entity_id,x,y)


class Ship(GameEntity):
    """Class representing a ship"""
    def __init__(self,entity_id, x,y,arg1,arg2,arg3,arg4):
        GameEntity.__init__(self, entity_id,x,y)
        self.rotation = arg1
        self.speed = arg2
        self.rum = arg3
        self.owner = arg4

    def computeAction(self,barrels):
        """Take in parameter the list of the barrels and compute for each the distance to the ship"""
        barrelsByDistance = {}
        for b in barrels:
            d = self.position.distance(b.position)
            barrelsByDistance[d] = b

        sorted_distances = sorted(barrelsByDistance.keys())
        barrel_to_go = barrelsByDistance[sorted_distances[0]]
        if barrel_to_go != None:
            return MOVE + str(barrel_to_go.position.x) + " " + str(barrel_to_go.position.y)
        else:
            return WAIT


# Game loop
while True:
    my_ship_count = int(input()) # the number of remaining ships
    print("Le nombre de bateau qu'il me reste : {}".format(my_ship_count), file=sys.stderr)
    entity_count = int(input())  # the number of entities (e.g. ships, mines or cannonballs)
    print("Le nombre d'entités présentes sur le terrain : {}".format(entity_count), file=sys.stderr)
    my_ships = []
    opponent_ships = []
    barrels = []
    mines = []
    cannonbals = []
    for i in range(entity_count):
        entity_id, entity_type, x, y, arg_1, arg_2, arg_3, arg_4 = input().split()
        if entity_type == SHIP:
            owner = int(arg_4)
            ship = Ship(int(entity_id),int(x),int(y),int(arg_1),int(arg_2),int(arg_3),owner)
            if owner == 1:
                my_ships.append(ship)
            else:
                opponent_ships.append(ship)
        elif entity_type == BARREL:
            barrels.append(Barrel(int(entity_id),int(x),int(y),int(arg_1)))
        elif entity_type == MINE:
            mines.append(Mine(int(entity_id),int(x),int(y)))
        elif entity_type == CANNONBALL:
            cannonbals.append(Cannonbal(int(entity_id),int(x),int(y),int(arg_1),int(arg_2)))


    for s in my_ships:

        # Write an action using print
        # To debug: print("Debug messages...", file=sys.stderr)

        # Any valid action, such as "WAIT" or "MOVE x y"
        print(s.computeAction(barrels))
