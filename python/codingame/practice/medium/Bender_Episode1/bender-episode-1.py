import sys
import math

# Créer une classe pour représenter la ville/le plan
# Création des objets de cette classe à partir d'une liste de liste ?
# Il faut une fonction pour trouver les coordonnées du point de départ
# Créer une classe Case représentant une case du plan ?
# Créer une classe pour représenter Bender/l'automate

# What are the abstractions needed to express the problem ?

class Coordinate:
    """Class to represent a Coordinate x,y. We can add to coordinate."""

    def __init__(self, x, y):
        """Initialisation of the class Coordinate which needs a x and a y coordinate"""
        self.x = x
        self.y = y

    def __str__(self):
        """String representation of a Coordinate"""
        return "({0},{1})".format(self.x, self.y)

    def __add__(self, other_coordinate):
        """Addition of 2 coordinate - Return a new object"""
        return Coordinate(self.x + other_coordinate.x, self.y + other_coordinate.y)

class Cell:
    """The class Cell is a class that represent an element of the map."""

    def __init__(self, coordinate, content):
        """Initialisation of the class Cell which needs a coordinate and a content"""
        self.coordinate = coordinate
        self.content = content

    def is_start(self):
        """Return true if the cell is a start"""
        return self.content == "@"

    def is_suicide_booth(self):
        """Return True if the cell is a suicide booth"""
        return self.content == "$"

    def is_obstacle(self):
        """Return True if the cell is an obstacle (breakable or not)"""
        return self.content == "X" or self.content == "#"

    def is_breakable_obstacle(self):
        """Return True if the cell is a breakable obstacle """
        return self.content == "X"

    def is_unbreakable_obstacle(self):
        """Return True if the cell is an unbreakable obstacle"""
        return self.content == "#"

    def is_teleporter(self):
        """Return True if the cell is a Teleporter"""
        return self.content == "T"

    def is_beer(self):
        """Return True if the cell is a Beer"""
        return self.content == "B"

    def is_inverter(self):
        """Return True if the cell is an Inverter"""
        return self.content == "I"

    def is_path_modifier(self):
        """Return True if the cell is a path modifier"""
        return self.content == "S" or self.content == "N" or self.content == "W" or self.content == "E"    

    def __str__(self):
        """String representation of a Cell"""
        return "{0} at {1}".format(self.content, str(self.coordinate))

class CityMap:
    """The class CityMap represent the map of the city in which Bender is moving."""

    def __init__(self, height, width, city_map):
        """The initialisation method of the class CityMap which needs the height and the width of the map, 
        and the map of the city (a list of list of cells)"""
        self.height = height
        self.width = width
        self.map = city_map

    def cell_at(self, coordinate):
        """Return the cell which is at the coordinate given in parameter"""
        return self.map[coordinate.x][coordinate.y]

    def __str__(self):
        """String representation of the map"""
        str = ""
        for row in self.map :
            for cell in row :
                str += "{0}".format(cell.content)
            str += "\n"
        return str

class Bender:
    """The class Bender represent the robot of the type of bender"""

    def __init__(self, coordinate, city_map, direction="SOUTH"):
        """Initialisation of Bender object"""
        self.coordinate = coordinate
        self.city_map = city_map
        self.direction = direction
        self.direction_priorities = ["SOUTH", "EAST", "NORTH", "WEST"]
        self.selected_direction_index = self.direction_priorities.index(direction)
        self.in_breaker_mode = False
        self.moves = []
        self.direction_to_coordinate_mapping = { 
            "SOUTH" : Coordinate(0,1),
            "NORTH" : Coordinate(0,-1),
            "EAST"  : Coordinate(1,0),
            "WEST"  : Coordinate(-1,0)
        }
    
    def __update_direction(self, direction):
        """Private method to update the direction of Bender"""
        self.direction = direction

    def __update_coordinate(self,coordinate):
        """Private method to update the coordinate of a bender object"""
        self.coordinate = coordinate

    def __next_position(self):
        """Determine the next position of Bender"""
        position_offset = self.direction_to_coordinate_mapping[self.direction]
        return self.coordinate + position_offset

    def __reverse_direction_priorities(self):
        """Reverser the list of direction priorities"""
        self.direction_priorities.reverse()

    def __toogle_breaker_mode(self):
        """Toggle in and out of Breaker Mode"""
        self.in_breaker_mode = not self.in_breaker_mode

    def __is_in_breaker_mode(self):
        """Return true if Bender is in Breaker mode"""
        return self.in_breaker_mode   

    def compute_moves(self):
        """Compute the list of moves of Bender. The list contains only the value 'LOOP' if bender cannot attain the suicide booth"""
        next_position = self.__next_position()
        next_cell = self.city_map.cell_at(next_position)
        if next_cell.is_obstacle:
            if self.in_breaker_mode and next_cell.is_breakable_obstacle:
                pass
            else:
                pass

    def get_computed_moves(self):
        """Print the list of computed moves"""
        pass

    def __str__(self):
        """The String value of a Bender object"""
        return "Position : {0} - Direction : {1}".format(self.coordinate,self.direction)

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

l, c = [int(i) for i in input().split()]
city_map = []
start = None
suicide_booth = None
teleporters = []

for i in range(l):
    row = list(input())
    row_of_cells = []
    for j in range(c):
        current_cell = Cell(Coordinate(j,i),row[j])
        row_of_cells.append(current_cell)
        if current_cell.is_start():
            start_cell = current_cell
    city_map.append(row_of_cells)

futurama = CityMap(l, c, city_map)
print(str(futurama), file=sys.stderr)
bender = Bender(start_cell.coordinate, city_map)
print(str(bender), file=sys.stderr)
print(str(start_cell), file=sys.stderr)

# Write an action using print
# To debug: print("Debug messages...", file=sys.stderr)

print("answer")