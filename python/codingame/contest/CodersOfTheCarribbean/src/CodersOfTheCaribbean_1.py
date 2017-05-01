import sys
import math
import collections

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
FIRE = "FIRE "
#Misc
MAX_SPEED=1
MINE_DISABLED_TIME=6
CANNONBALL_DISABLED_TIME=3
Y_MAX=20
X_MAX=22

mine_timer = 0
cannonball_timer = 0

Point = collections.namedtuple("Point", ["x", "y"])
Hex = collections.namedtuple("Hex", ["q", "r", "s"])

class Hex:

    def __init__(self,q,r,s):
        self.q = q
        self.r = r
        self.s = s

    def __add__(self, b):
        return Hex(self.q + b.q, self.r + b.r, self.s + b.s)

    def __sub(self, b):
        return Hex(self.q - b.q, self.r - b.r, self.s - b.s)

    def scale(self, k):
        return Hex(self.q * k, self.r * k, self.s * k)

    def direction(direction):
        hex_directions = [Hex(1, 0, -1), Hex(1, -1, 0), Hex(0, -1, 1), Hex(-1, 0, 1), Hex(-1, 1, 0), Hex(0, 1, -1)]
        return hex_directions[direction]

    def neighbor(self, direction):
        return self.add(direction(direction))

    def diagonal_neighbor(self, direction):
        hex_diagonals = [Hex(2, -1, -1), Hex(1, -2, 1), Hex(-1, -1, 2), Hex(-2, 1, 1), Hex(-1, 2, -1), Hex(1, 1, -2)]
        return self.add(hex_diagonals[direction])

    def length(self):
        return (abs(self.q) + abs(self.r) + abs(self.s)) // 2

    def distance(self, b):
        return self.subtract(b).length()

    def round(self):
        q = int(round(self.q))
        r = int(round(self.r))
        s = int(round(self.s))
        q_diff = abs(q - self.q)
        r_diff = abs(r - self.r)
        s_diff = abs(s - self.s)
        if q_diff > r_diff and q_diff > s_diff:
            q = -r - s
        else:
            if r_diff > s_diff:
                r = -q - s
            else:
                s = -q - r
        return Hex(q, r, s)


    def lerp(self, b, t):
        return Hex(self.q * (1 - t) + b.q * t, self.r * (1 - t) + b.r * t, self.s * (1 - t) + b.s * t)

    def linedraw(self, b):
        N = self.distance(b)
        a_nudge = Hex(self.q + 0.000001, self.r + 0.000001, self.s - 0.000002)
        b_nudge = Hex(b.q + 0.000001, b.r + 0.000001, b.s - 0.000002)
        results = []
        step = 1.0 / max(N, 1)
        for i in range(0, N + 1):
            results.append(a_nudge.lerp(b_nudge, step * i).round())
        return results

OffsetCoord = collections.namedtuple("OffsetCoord", ["col", "row"])

EVEN = 1
ODD = -1
def qoffset_from_cube(offset, h):
    col = h.q
    row = h.r + (h.q + offset * (h.q & 1)) // 2
    return OffsetCoord(col, row)

def qoffset_to_cube(offset, h):
    q = h.col
    r = h.row - (h.col + offset * (h.col & 1)) // 2
    s = -q - r
    return Hex(q, r, s)

def roffset_from_cube(offset, h):
    col = h.q + (h.r + offset * (h.r & 1)) // 2
    row = h.r
    return OffsetCoord(col, row)

def roffset_to_cube(offset, h):
    q = h.col - (h.row + offset * (h.row & 1)) // 2
    r = h.row
    s = -q - r
    return Hex(q, r, s)

class Coordinate:
    """Class representing a Coordinate or a position"""
    def __init__(self,x,y):
        self.x = x
        self.y = y

    def distance(self, b):
        """Calculate the distance between Coordinate a and b on a cartesian plane"""
        d = math.sqrt(float((b.x - self.x)**2) + float((b.y - self.y)**2))
        return int(round(d))

    def byX(self):
        return self.x

    def byY(self):
        return self.y

    def __str__(self):
        return "(" + str(x) + "," + str(y) + ")"

    def __repr__(self):
        return "(" + str(x) + "," + str(y) + ")"


class GameEntity:
    """Superclass of all the entities of the game, that are all characterize by a position, an id and a size of one"""
    def __init__(self,entity_id,x,y):
        self.entity_id = entity_id
        self.position = Coordinate(x, y)
        self.size = 1

    def hasSameX(self, entity):
        return self.position.x == entity.position.x

    def hasSameY(self, entity):
        return self.position.y == entity.position.y

    def onBoardEdge(self):
        return self.position.x == 0 or self.position.x == X_MAX or self.position.y == 0 or self.position.y == Y_MAX

    def isNeighbor(self,entity):
        """Return true is this game entity is in a cell next to another entity"""

    def __str__(self):
        return "(id:" + str(entity_id) + ")::" + str(self.position)

    def __repr__(self):
        return "(id:" + str(entity_id) + ")::" + str(self.position)


class Barrel(GameEntity):
    """Class representing a barrel of rum with its quantity of rum"""
    def __init__(self,entity_id,x,y,arg1):
        GameEntity.__init__(self, entity_id,x,y)
        self.quantity = arg1

    def __str__(self):
        return "Barrel:" + super().__str__() + "::(rum:" + str(self.quantity) + ")"

    def __repr__(self):
        return "Barrel:" + super().__repr__() + "::(rum:" + str(self.quantity) + ")"

class Cannonball(GameEntity):
    """Class representing a Cannonbal in the Game"""
    def __init__(self,entity_id,x,y,arg1,arg2):
        GameEntity.__init__(self, entity_id,x,y)
        self.shipEntityId = arg1
        self.turnsBeforeImpact = arg2

    def __str__(self):
        return "Cannonball :" + super().__str__() + "::(id owner:" + str(self.shipEntityId) + ")::(turns before impact:" + str(self.turnsBeforeImpact)+")"

    def __repr__(self):
        return "Cannonball :" + super().__repr__() + "::(id owner:" + str(self.shipEntityId) + ")::(turns before impact:" + str(self.turnsBeforeImpact)+")"

class Mine(GameEntity):
    """Class representing the Mine in the Game"""
    def __init__(self,entity_id,x,y):
        GameEntity.__init__(self, entity_id,x,y)

    def __str__(self):
        return "Mine :" + super().__str__()

    def __repr__(self):
        return "Mine :" + super().__repr__()

class Ship(GameEntity):
    """Class representing a ship"""
    def __init__(self,entity_id, x,y,arg1,arg2,arg3,arg4):
        GameEntity.__init__(self, entity_id,x,y)
        self.rotation = arg1
        self.speed = arg2
        self.rum = arg3
        self.owner = arg4
        self.size = 3


    def displayOwner(self):
        if self.owner == 1:
            return "Player"
        return "Opponent"

    def isOpponentShipInRange(self,opponent_ship):
        distance_to_opponent_ship = int(round(self.position.distance(opponent_ship.position)))
        return distance_to_opponent_ship <= 10

    def isMineInRange(self,mine):
        distance_to_mine = int(round(self.position.distance(mine.position)))
        #Il faut faire plus compliqué : est-ce que je me dirige dessus ?
        return distance_to_mine <= 10

    def isCannonballInRange(self,cannonball):
        distance_to_cannonball = int(round(self.position.distance(cannonball.position)))
        return distance_to_cannonball <= 3

    def isAhead(self,entities):
        #Si direction 0, même ordonnée mais abscisse entité >= abscisse bateau
        #Si direction 3, même ordonnée mais abscisse entité <= abscisse bateau

    def computeAction(self,barrels,opponent_ships,mines,cannonballs):
        """Take in parameter the list of the barrels and compute for each the distance to the ship"""
        barrelsByDistance = {}
        global mine_timer
        global cannonball_timer
        for b in barrels:
            d = self.position.distance(b.position)
            barrelsByDistance[d] = b

        cannonballs_in_range = [c for c in cannonballs if self.isCannonballInRange(c)]
        print("Boulets de cannons à proximité : {}".format(" , ".join([str(c) for c in cannonballs_in_range])), file=sys.stderr)
        position_mines_in_range = [m.position for m in mines if self.isMineInRange(m)]
        print("Position des mines à proximité : {}".format(" , ".join([str(p) for p in position_mines_in_range])), file=sys.stderr)

        print("Caractéristiques du bateau : {}".format(str(self)),file=sys.stderr)
        action = WAIT # DEFAULT action
        mine_timer += 1
        print("Mine Timer : {}".format(mine_timer),file=sys.stderr)
        cannonball_timer += 1
        print("Cannonball Timer : {}".format(cannonball_timer),file=sys.stderr)
        if barrelsByDistance :
            sorted_distances = sorted(barrelsByDistance.keys())
            print("La liste des distances par ordre ascendant : {}".format(" , ".join([str(i) for i in sorted_distances])), file=sys.stderr)
            print("Les barrils de rhum triés paar distance : {}".format(" , ".join('%s::%s' % (str(key), str(value)) for (key, value) in barrelsByDistance.items())), file=sys.stderr)
            next_barrel = barrelsByDistance[sorted_distances[0]]
            print("Prochain baril cible : {}".format(str(next_barrel)), file=sys.stderr)
            action = MOVE + str(next_barrel.position.x) + " " + str(next_barrel.position.y)
            print("cas 'barrelsByDistance' Action : {}".format(action),file=sys.stderr)
        if cannonball_timer >= CANNONBALL_DISABLED_TIME and self.isOpponentShipInRange(opponent_ships[0]) and self.speed >= 1:
            action = FIRE + str(opponent_ships[0].position.x) + " " + str(opponent_ships[0].position.y)
            cannonball_timer = 0
            print("cas 'cannonball_timer >= 1 and self.isOpponentShipInRange(opponent_ships[0]) and self.speed >= 1' Action : {}".format(action),file=sys.stderr)
        if mine_timer >= MINE_DISABLED_TIME and self.speed >= 1:
            action = MINE
            mine_timer = 0
            print("cas 'mine_timer == 4 and self.speed >= 1' Action : {}".format(action),file=sys.stderr)
        if position_mines_in_range and cannonball_timer >= CANNONBALL_DISABLED_TIME and self.speed >= 1:
            action = FIRE + str(position_mines_in_range[0].x) + " " + str(position_mines_in_range[0].y)
            cannonball_timer = 0
            print("cas 'position_mines_in_range and cannonball_timer >= 1 and self.speed >= 1' Action : {}".format(action),file=sys.stderr)
        if not barrelsByDistance: #Plus de barril de rhum à trouver. Il faut éviter les mines et les boulets de cannons et ne pas rester statique
            #est-ce que j'ai atteint un bord ?
            #est-ce que je fonce vers une mine ?
            if position_mines_in_range:
                if self.rotation == 0:
                    mine_position_sorted_by_x = sorted(position_mines_in_range, key=byX)
                    print("Tri par les abscisses : {}".format(" , ".join([str(p) for p in mine_position_sorted_by_x])), file=sys.stderr)


                #si j'ai la direction 0, est-ce qu'il y a une mine avec le même y que moi mais avec un x plus grand ?
                #si j'ai la direction 3, est-ce qu'il y a une mine avec le même y que moi mais avec un x plus petit ?
                # Si oui, il faut que j'augmente ou réduise mon y
        print("Action : {}".format(action),file=sys.stderr)
        return action

    def __str__(self):
        return "Ship :" + super().__str__() + "::(rotation:" + str(self.rotation) + ")::(speed:" + str(self.speed) + ")::(rum:" + str(self.rum) + ")::(owner:" + self.displayOwner() + ")"

    def __repr__(self):
        return "Ship :" + super().__repr__() + "::(rotation:" + str(self.rotation) + ")::(speed:" + str(self.speed) + ")::(rum:" + str(self.rum) + ")::(owner:" + self.displayOwner() + ")"


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
    cannonballs = []
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
            cannonballs.append(Cannonball(int(entity_id),int(x),int(y),int(arg_1),int(arg_2)))


    for s in my_ships:

        # Write an action using print
        # To debug: print("Debug messages...", file=sys.stderr)

        # Any valid action, such as "WAIT" or "MOVE x y"
        print(s.computeAction(barrels,opponent_ships,mines,cannonballs))
