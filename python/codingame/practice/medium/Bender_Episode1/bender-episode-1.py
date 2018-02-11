import sys
import math

# Créer une classe pour représenter la ville/le plan
# Création des objets de cette classe à partir d'une liste de liste ?
# Il faut une fonction pour trouver les coordonnées du point de départ
# Créer une classe Case représentant une case du plan ?
# Créer une classe pour représenter Bender/l'automate

# What are the abstractions needed to express the problem ?

class Coordinate:
    """Class to represent a Coordinate x,y. We can add two coordinates."""

    def __init__(self, x, y):
        """Initialisation of the class Coordinate which needs an 'x' and a 'y' coordinate"""
        self.x = x
        self.y = y

    def __str__(self):
        """String representation of a Coordinate"""
        return "({0},{1})".format(self.x, self.y)

    def __add__(self, other_coordinate):
        """Addition of 2 coordinates - Return a new object"""
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

    def is_blank(self):
        """Return True if the cell is a blank cell"""
        return self.content == " "

    def is_obstacle(self):
        """Return True if the cell is an obstacle (breakable or not)"""
        return self.content == "X" or self.content == "#"

    def is_breakable_obstacle(self):
        """Return True if the cell is a breakable obstacle """
        return self.content == "X"

    def break_obstacle(self):
        """Delete the breakable obstacle"""
        if self.is_breakable_obstacle():
            self.content = " "

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

    def get_content(self):
        """Get the content of the cell"""
        return self.content

    def get_coordinate(self):
        """Get the coordinate of the cell"""
        return self.coordinate

    def __str__(self):
        """String representation of a Cell"""
        return "{0} at {1}".format(self.content, str(self.coordinate))

class CityMap:
    """The class CityMap represent the map of the city in which Bender is moving."""

    def __init__(self, height, width, city_map, start_cell, teleporters):
        """The initialisation method of the class CityMap which needs the height and the width of the map, 
        and the map of the city (a list of list of cells)"""
        self.height = height
        self.width = width
        self.map = city_map
        self.start_cell = start_cell
        self.teleporters = teleporters

    def cell_at(self, coordinate):
        """Return the cell which is at the coordinate given in parameter"""
        return self.map[coordinate.x][coordinate.y]

    def get_other_teleporter(self, teleporter):
        """Return the other teleporter"""
        teleporter_index = self.teleporters.index(teleporter)
        if teleporter_index == 0:
            return self.teleporters[1]
        else:
            return self.teleporters[0]

    def get_number_of_cells(self):
        """Give the number of cells on this map"""
        return self.height * self.width
        
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

    def __init__(self, coordinate, city_map):
        """Initialisation of Bender object"""
        self.coordinate = coordinate
        self.city_map = city_map
        self.direction = "SOUTH"
        self.direction_priorities = ["SOUTH", "EAST", "NORTH", "WEST"]
        self.next_direction_index = 1
        self.selected_direction_index = self.direction_priorities.index(self.direction)
        self.in_breaker_mode = False
        self.dead = False
        self.moves = []
        self.direction_to_coordinate_mapping = { 
            "SOUTH" : Coordinate(0,1),
            "NORTH" : Coordinate(0,-1),
            "EAST"  : Coordinate(1,0),
            "WEST"  : Coordinate(-1,0)
        }
        self.times_blocked = 0
    
    def __update_direction(self, direction):
        """Private method to update the direction of Bender"""
        self.direction = direction

    def __change_direction(self):
        """To change the direction of Bender - The next direction in the possible direction is taken"""
        self.next_direction_index = (self.next_direction_index + 1) % 4

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
    
    def __is_in_normal_mode(self):
        """Return true if Bender is in Normal mode"""
        return not self.in_breaker_mode   

    def __commit_suicide(self):
        self.dead = True

    def __is_dead(self):
        """Return true if Bender is Dead"""
        return self.dead
    
    def __inc_times_blocked(self):
        """Increment the number of times Bender has been blocked"""
        self.times_blocked += 1

    def __reset_times_blocked(self):
        """Reset the value of times_blocked to 0"""
        self.times_blocked = 0

    def __is_blocked(self):
        """Return true if Bender is blocked, this is if the times_blocked is greater than 4"""    

    def __compute_next_move(self):
        """Compute the next moves and position of Bender. The list contains only the value 'LOOP' if bender cannot attain the suicide booth"""
        print("Entering __compute_next_move", file=sys.stderr)
        current_direction = self.direction
        next_position = self.__next_position()
        next_cell = self.city_map.cell_at(next_position)
        print("current direction : {}".format(str(current_direction)), file=sys.stderr)
        print("next position : {}".format(str(next_position)), file=sys.stderr)
        print("next cell : {}".format(str(next_cell)), file=sys.stderr)

        if next_cell.is_suicide_booth():
            self.__reset_times_blocked()
            self.__update_coordinate(next_position)
            self.__commit_suicide()
            return current_direction

        if next_cell.is_blank():
            self.__reset_times_blocked()
            self.__update_coordinate(next_position)
            return current_direction

        if next_cell.is_beer():
            self.__reset_times_blocked()
            self.__toogle_breaker_mode()
            self.__update_coordinate(next_position)
            return current_direction

        if next_cell.is_inverter():
            self.__reverse_direction_priorities()
            self.__reset_times_blocked()
            self.__update_coordinate(next_position)
            return current_direction   

        if next_cell.is_teleporter():
            self.__reset_times_blocked()
            current_cell = self.city_map.get_other_teleporter()
            self.__update_coordinate(current_cell.get_position())
            return current_direction

        if next_cell.is_unbreakable_obstacle():
            self.__change_direction()
            self.__inc_times_blocked()
            return self.__compute_next_move()  

        if next_cell.is_unbreakable_obstacle() and self.__is_blocked():
            return "LOOP"    

        if next_cell.is_path_modifier():
            self.__update_direction(next_cell.get_content())
            self.__reset_times_blocked()
            self.__update_coordinate(next_position)
            return current_direction

        if self.__is_in_normal_mode():
            #Obstacle in normal mode Bender change direction and will try to move in another cell
            if next_cell.is_breakable_obstacle() and self.__is_blocked():
               return "LOOP"

            if next_cell.is_breakable_obstacle():
               self.__change_direction()
               self.__inc_times_blocked()
               return self.__compute_next_move()   
            
        if self.__is_in_breaker_mode() and next_cell.is_breakable_obstacle():
            next_cell.break_obstacle()
            self.__reset_times_blocked()
            return self.__compute_next_move()

        print("You have forgotten something!", file=sys.stderr)
        
    def get_computed_moves(self):
        """Print the list of computed moves"""
        print("Entering get_computed_moves", file=sys.stderr)
        moves = []
        max_iterations = self.city_map.get_number_of_cells() * 2
        number_iterations = 0
        print("number_iterations : {}".format(str(number_iterations)), file=sys.stderr)
        print("max_iterations : {}".format(str(max_iterations)), file=sys.stderr)
        print("not self.__is_dead() : {}".format(str(not self.__is_dead())), file=sys.stderr)
        while not self.__is_dead() and number_iterations < max_iterations :
            moves.append(self.__compute_next_move())
            number_iterations += 1

        print("About to exit get_computed_moves", file=sys.stderr)
        print("Moves : {}".format(" ".join(moves)), file=sys.stderr)
        if number_iterations >= max_iterations or "LOOP" in moves :
            return "LOOP"
        else:
           return " ".join(moves)

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
        if current_cell.is_teleporter():
            teleporters.append(current_cell)
    city_map.append(row_of_cells)

futurama = CityMap(l, c, city_map, start_cell, teleporters)
print(str(futurama), file=sys.stderr)
bender = Bender(start_cell.coordinate, futurama)
print(str(bender), file=sys.stderr)
print(str(start_cell), file=sys.stderr)
result = bender.get_computed_moves()
print(result, file=sys.stderr)
print(result)
# Write an action using print
# To debug: print("Debug messages...", file=sys.stderr)

#print("answer")