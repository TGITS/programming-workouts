import sys
import math

# Créer une classe pour représenter la ville/le plan
# Création des objets de cette classe à partir d'une liste de liste ?
# Il faut une fonction pour trouver les coordonnées du point de départ
# Créer une classe Case représentant une case du plan ?
# Créer une classe pour représenter Bender/l'automate

# What are the abstractions needed to express the problem ?

class Cell:
    """The class Cell represent an element of the map."""

    def __init__(self,x,y,content):
        self.x = x
        self.y = y
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
        return self.content == "S" or self.content == "N" or self.content == "W" of self.content == "E"    

    def __str__(self):
        """String representation of a Cell"""
        return "{0} at ({1},{2})".format(self.content, self.x, self.y)

class CityMap:
    """The class CityMap represent the map of the city in which Bender is moving."""

    def __init__(self, height, width, city_map):
        self.height = height
        self.width = width
        self.map = city_map

    def display(self, output=sys.stderr):
        """Print the map"""
        for row in self.map :
            for cell in row :
                print("{0}".format(cell.content), file=output, end='', flush=True)
            print(flush=True)

    def get_cell(x,y):
        return self.map[x][y]

class Bender:
    """The class Bender represent the robot of the type of bender"""

    def __init__(self, x, y, city_map, direction="SOUTH"):
        """Initialisation of Bender object"""
        self.x = x
        self.y = y
        self.city_map = city_map
        self.direction = direction
        self.direction_priorities = ["SOUTH", "EAST", "NORTH", "WEST"]
        self.selected_direction_index = self.direction_priorities.index(direction)
        self.in_breaker_mode = False
        self.moves = []
        self.direction_to_coordinate_mapping = { 
            "SOUTH" : (0,1),
            "NORTH" : (0,-1),
            "EAST"  : (1,0),
            "WEST"  : (-1,0)
        }
        
    
    def __update_direction(self, direction):
        """Private method to update the direction of Bender"""
        self.direction = direction

    def __update_position(self,x,y):
        self.x = x
        self.y = y

    def __next_position(self):
        """Determine the next position of Bender"""
        x_offset, y_offset = self.direction_to_coordinate_mapping[self.direction]
        return self.x + x_offset, self.y + y_offset

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
        x,y = self.__next_position()
        next_cell = self.city_map.get_cell(x,y)
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
        return "Position : ({0},{1}) - Direction : {2}".format(self.x,self.y,self.direction)

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
        current_cell = Cell(j,i,row[j])
        row_of_cells.append(current_cell)
        if current_cell.is_start():
            start_cell = current_cell
    city_map.append(row_of_cells)

futurama = CityMap(l, c, city_map)
futurama.display()
bender = Bender(start_cell.x,start_cell.y, city_map)
print(str(bender), file=sys.stderr)
print(str(start_cell), file=sys.stderr)
# print("map : {0}".format(str(city_map)), file=sys.stderr)


# Write an action using print
# To debug: print("Debug messages...", file=sys.stderr)

print("answer")