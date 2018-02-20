import sys
import math

class Coordinate:
    """Class to represent a Coordinate in the grid. We can add two coordinates. 
    The r attribute represents the row and c the column"""

    def __init__(self, r, c):
        """Initialisation of the class Coordinate which needs an 'r' and a 'c' coordinate"""
        self.r = r
        self.c = c

    def __str__(self):
        """String representation of a Coordinate"""
        return "({0},{1})".format(self.r, self.c)

    def __add__(self, other_coordinate):
        """Addition of 2 coordinates - Return a new object"""
        return Coordinate(self.r + other_coordinate.r, self.c + other_coordinate.c)

    def __eq__(self,other):
        """Override of the equality for coordinate object"""
        if isinstance(other, self.__class__):
            return self.r == other.r and self.c == other.c
        return False

    def __ne__(self,other):
        """Override of the != for coordinate object"""
        return self.r != other.r or self.c != other.c

    def __hash__(self):
        """Override of the hash for this object"""
        return hash(self.r + self.c) # Not a very good hash for performance purpose


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
        return "'{0}' at {1}".format(self.content, str(self.coordinate))

    def __eq__(self,other):
        """Override of the equality for cell object"""
        if isinstance(other, self.__class__):
            return self.coordinate == other.coordinate and self.content == other.content
        return False

    def __ne__(self,other):
        """Override of the != for coordinate object"""
        return self.coordinate != other.coordinate or self.content != other.content

    def __hash__(self):
        """Override of the hash for this object"""
        return hash(self.content) + hash(self.coordinate) # Not a very good hash for performance purpose
    
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
        return self.map[coordinate.r][coordinate.c]

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

class Move:
    """A class that represents a move of Bender"""

    def __init__(self,next_cell,direction,bender_state):
        self.next_cell = next_cell
        self.direction = direction
        self.bender_state = bender_state

    def __str__(self):
        """String representation of a Move"""
        return "{0} {1} {2}".format(self.next_cell, self.direction, self.bender_state)
    
    def __eq__(self,other):
        """Override of the equality for cell object"""
        if isinstance(other, self.__class__):
            return self.next_cell == other.next_cell and self.direction == other.direction and self.bender_state == other.bender_state
        return False

    def __ne__(self,other):
        """Override of the != for coordinate object"""
        return self.next_cell != other.next_cell or self.direction != other.direction or self.bender_state != other.bender_state

    def __hash__(self):
        """Override of the hash for this object"""
        return hash(self.next_cell) + hash(self.direction) + hash(self.bender_state) # Not a very good hash for performance purpose

class Bender:
    """The class Bender represent the robot of the type of bender"""

    def __init__(self, coordinate, city_map):
        """Initialisation of Bender object"""
        self.coordinate = coordinate
        self.city_map = city_map
        self.direction = "SOUTH"
        self.direction_priorities = ["SOUTH", "EAST", "NORTH", "WEST"]
        self.next_direction_index = 0
        self.selected_direction_index = self.direction_priorities.index(self.direction)
        self.in_breaker_mode = False
        self.dead = False
        self.trap = False
        self.history = []
        self.direction_to_coordinate_mapping = { 
            "SOUTH" : Coordinate(1,0),
            "NORTH" : Coordinate(-1,0),
            "EAST"  : Coordinate(0,1),
            "WEST"  : Coordinate(0,-1)
        }
        self.letter_to_direction = { 
            "S":"SOUTH",
            "N":"NORTH",
            "E":"EAST",
            "W":"WEST"
        }
        self.times_blocked = 0
    
    def get_state(self):
        """Output in a string representation the state of Bender : DEAD, NORMAL, BREAKER"""
        if self.is_dead():
            return "DEAD"
        if self.__is_in_breaker_mode():
            return "BREAKER"
        if self.is_looping():
            return "LOOP"
        return "NORMAL"

    def update_history(self, next_cell, direction):
        """Update the Bender's history of moves"""
        move = Move(next_cell, direction, self.get_state())
        self.history.append(move)
        if self.history.count(move) > 3:
            self.__trap()

    def is_looping(self):
        """Return true if Bender is in a Loop"""
        return self.trap

    def __update_direction(self, direction):
        """Private method to update the direction of Bender"""
        self.direction = self.letter_to_direction[direction]

    def __change_direction(self):
        """To change the direction of Bender - The next direction in the possible directions is taken"""
        if self.direction == self.direction_priorities[self.next_direction_index]:
           self.next_direction_index = (self.next_direction_index + 1) % 4
        self.direction = self.direction_priorities[self.next_direction_index]
    
    def __reset_direction_index(self):
        """Reset the next_direction_index"""
        self.next_direction_index = 0  

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

    def __trap(self):
        """Update the status of Bender when he is trapped in a loop"""
        self.trap = True

    def __commit_suicide(self):
        self.dead = True

    def is_dead(self):
        """Return true if Bender is Dead"""
        return self.dead

    def __compute_next_move(self):
        """Compute the next moves and position of Bender. The list contains only the value 'LOOP' if bender cannot attain the suicide booth"""
        current_direction = self.direction
        next_position = self.__next_position()
        next_cell = self.city_map.cell_at(next_position)

        if not next_cell.is_obstacle():
            self.__reset_direction_index()

        if next_cell.is_suicide_booth():
            self.__update_coordinate(next_position)
            self.__commit_suicide()
            self.update_history(next_cell, current_direction)
            return current_direction

        if next_cell.is_start() or next_cell.is_blank():
            self.__update_coordinate(next_position)
            self.update_history(next_cell, current_direction)
            return current_direction

        if next_cell.is_beer():
            self.__toogle_breaker_mode()
            self.__update_coordinate(next_position)
            self.update_history(next_cell, current_direction)
            return current_direction

        if next_cell.is_inverter():
            self.__reverse_direction_priorities()
            self.__update_coordinate(next_position)
            self.update_history(next_cell, current_direction)
            return current_direction   

        if next_cell.is_teleporter():
            current_cell = self.city_map.get_other_teleporter(next_cell)
            self.__update_coordinate(current_cell.get_coordinate())
            self.update_history(current_cell, current_direction)
            return current_direction

        if next_cell.is_unbreakable_obstacle():
            self.__change_direction()
            return None  

        if next_cell.is_path_modifier():
            self.__update_direction(next_cell.get_content())
            self.__update_coordinate(next_position)
            self.update_history(next_cell, current_direction)
            return current_direction

        if self.__is_in_normal_mode() and next_cell.is_breakable_obstacle():
            self.__change_direction()
            return None   
            
        if self.__is_in_breaker_mode() and next_cell.is_breakable_obstacle():
            next_cell.break_obstacle()
            return None

        print("You have forgotten something!", file=sys.stderr)
        
    def get_computed_moves(self):
        """Print the list of computed moves"""
        moves = []
        while not self.is_dead() and not self.is_looping() :
            move = self.__compute_next_move()
            if move != None:
                moves.append(move)

        print("Moves : {}".format(" ".join(moves)), file=sys.stderr)
        if self.is_looping():
            print("Bender trapped in a LOOP", file=sys.stderr)
            return "LOOP"
        else:
            return "\n".join(moves)
        
    def __str__(self):
        """The String value of a Bender object"""
        return "Position : {0} - Direction : {1}".format(self.coordinate,self.direction)

l, c = [int(i) for i in input().split()]
city_map = []
start = None
suicide_booth = None
teleporters = []

for i in range(l):
    row = list(input())
    row_of_cells = []
    for j in range(c):
        current_cell = Cell(Coordinate(i,j),row[j])
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
print(result)