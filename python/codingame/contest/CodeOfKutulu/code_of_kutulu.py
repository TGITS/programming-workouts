import sys
import math

# Survive the wrath of Kutulu
# Coded fearlessly by JohnnyYuge & nmahoude (ok we might have been a bit scared by the old god...but don't say anything)


class Cell:
    '''Class representing a cell of the map
       - the character '#' represents a wall
       - the character 'w' represents a spawn for wanderers
       - the character '.' represents an empty cell 
    '''

    def __init__(self, representation, x, y):
        '''representation is the character representation of the cell
           x and y represent the coordinate of the cell in the table'''
        self._representation = representation
        self._x = x
        self._y = y

    def __str__(self):
        return self._representation

    def __repr__(self):
        return "Cell({0}, {1}, {2})".format(self._representation, self._x,
                                            self._y)

    @property
    def x(self):
        return self._x

    @x.setter
    def x(self, x):
        self._x = x

    @property
    def y(self):
        return self._y

    @y.setter
    def y(self, y):
        self._y = y

    @property
    def representation(self):
        return self._representation

    def is_wall(self):
        return self._representation == '#'

    def is_spawn(self):
        return self._representation == 'w'

    def is_empty_cell(self):
        return self._representation == '.'

    def coordinate(self):
        '''Returns a tuple of the coordinate x and y of the cell'''
        return self._x, self._y


class Map:
    '''The Class Map represent the Map of the game Code of Kutulu.
    A Map is characterized by a width and a height and a table of cells'''

    def __init__(self, width, height, lines):
        '''The initialization of the object of the class takes the width and the height of the map.
           A list of lines is also to be provided'''
        self._width = width
        self._height = height
        self._table = []
        for y, line in enumerate(lines):
            temp = []
            for x, character in enumerate(line):
                temp.append(Cell(character, x, y))
            self._table.append(temp)

    def get_cell(self, x, y):
        return self._table[y][x]

    def __str__(self):
        display = ""
        for lines in self._table:
            for char in lines:
                display += str(char)
            display += "\n"
        return display

    def __repr__(self):
        return "Map({0}, {1}, {2})".format(
            self._width, self._height, ["".join(line) for line in self._table])


class Entity:
    '''An abstract class that represent an entity of the game.
       An entity can be an explorer or a wanderer
    '''

    def __init__(self, type, id, x, y, *args):
        super().__init__()
        self._type = type
        self._id = id
        self._x = x
        self._y = y

    def __str__(self):
        return "type:{0} id:{1} x:{2} y:{3}".format(self._type, self._id,
                                                    self._x, self._y)

    def __repr__(self):
        return "Entity(type='{0}',id={1},x={2}, y={3})".format(
            self._type, self._id, self._x, self._y)

    #Equality on the id for the entities
    def __eq__(self, other):
        return self._id == other._id

    def __neq__(self, other):
        return self._id != other._id

    def __lt__(self, other):
        return self._id < other._id

    def __gt__(self, other):
        return self._id > other._id

    @property
    def id(self):
        return self._id

    @property
    def type(self):
        return self._type

    @property
    def x(self):
        return self._x

    @x.setter
    def x(self, x):
        self._x = x

    @property
    def y(self):
        return self._y

    @y.setter
    def y(self, y):
        self._y = y

    def coordinate(self):
        return self.x, self.y

    def distance(self, other_entity):
        '''Taxicab distance between the entity and an other entity'''
        return abs(self._x - other_entity._x) + abs(self._y - other_entity._y)

    def is_explorer(self):
        return "EXPLORER" == self.type

    def is_wanderer(self):
        return "WANDERER" == self.type

    def compute_other_entities_sorted_by_distances(self, entities):
        if entities:
            return None
        else:
            return sorted(entities,key=lambda e:self.distance(e))

    def compute_action(self, explorers, wanderers):
        explorers_sorted_by_distance = self.compute_other_entities_sorted_by_distances(explorers)
        wanderers_which_target_me = [wanderer for wanderer in wanderers if wanderers and explorers_sorted_by_distance and (wanderer.target == self.id or wanderer.target == explorers_sorted_by_distance[0].id)]
        wanderers_target = [wanderer.target for wanderer in wanderers]
        explorers_not_targeted = [explorer for explorer in explorers if explorers and wanderers_target and explorer.id not in wanderers_target]
        if not wanderers_which_target_me:  
            if explorers_sorted_by_distance: 
                return "MOVE {0} {1}".format(explorers_sorted_by_distance[0].x, explorers_sorted_by_distance[0].y)
            else:
                return "WAIT"
        else:
            if explorers_not_targeted:
                return "MOVE {0} {1}".format(explorers_not_targeted.x, explorers_not_targeted.y)
            else:
                return "WAIT"


class Explorer(Entity):
    '''Class representing an explorer'''

    def __init__(self, type, id, x, y, *args):
        super().__init__(type, id, x, y)
        self._sanity = args[0]

    def __str__(self):
        return super().__str__() + " sanity:{}".format(self._sanity)

    def __repr__(self):
        return "Entity('{0}',{1},{2},{3},{4})".format(
            self._type, self._id, self._x, self._y, self._sanity)

    @property
    def sanity(self):
        return self._sanity

    @sanity.setter
    def sanity(self, sanity):
        self._sanity = sanity


class Wanderer(Entity):
    '''Class representing a wanderer'''

    def __init__(self, type, id, x, y, *args):
        super().__init__(type, id, x, y)
        self._lifetime = args[0]
        self._state = args[1]
        #id of the target of the wanderer
        self._target = args[2]

    def __str__(self):
        return super().__str__() + " lifetime:{0} state:{1} target:{2}".format(
            self._lifetime, self._state, self._target)

    def __repr__(self):
        return "Entity('{0}',{1},{2},{3},{4},{5},{6})".format(
            self._type, self._id, self._x, self._y, self._lifetime,
            self._state, self._target)

    @property
    def lifetime(self):
        return self._lifetime

    @lifetime.setter
    def time(self, time):
        self._lifetime = time

    @property
    def state(self):
        return self._state

    @state.setter
    def state(self, state):
        self._state = state

    @property
    def target(self):
        return self._target

    @target.setter
    def target(self, target):
        self._target = target


width = int(input())
height = int(input())
lines = []
for i in range(height):
    lines.append(input())

map = Map(width, height, lines)

# sanity_loss_lonely: how much sanity you lose every turn when alone, always 3 until wood 1
# sanity_loss_group: how much sanity you lose every turn when near another player, always 1 until wood 1
# wanderer_spawn_time: how many turns the wanderer take to spawn, always 3 until wood 1
# wanderer_life_time: how many turns the wanderer is on map after spawning, always 40 until wood 1
sanity_loss_lonely, sanity_loss_group, wanderer_spawn_time, wanderer_life_time = [
    int(i) for i in input().split()
]

# game loop
while True:
    # the first given entity corresponds to your explorer
    entity_count = int(input())  
    other_explorers = []
    wanderers = []
    for i in range(entity_count):
        entity_type, id, x, y, param_0, param_1, param_2 = input().split()
        id = int(id)
        x = int(x)
        y = int(y)
        param_0 = int(param_0)
        param_1 = int(param_1)
        param_2 = int(param_2)
        if i == 0:
            my_explorer = Explorer(entity_type, id, x, y, param_0)
        elif entity_type == "EXPLORER":
            other_explorers.append(Explorer(entity_type, id, x, y, param_0))
        else:
            wanderers.append(
                Wanderer(entity_type, id, x, y, param_0, param_1, param_2))

    #print("{}".format(str(map)), file=sys.stderr)
    print("My explorer : {}".format(str(my_explorer)), file=sys.stderr)
    print(
        "Other explorers : {}".format(" ".join(
            [str(e) for e in other_explorers])),
        file=sys.stderr)
    print(
        "Wanderers : {}".format(" ".join([str(w) for w in wanderers])),
        file=sys.stderr)

    #entities = other_explorers + wanderers
    #entities.append(my_explorer)
    # Write an action using print
    # To debug: print("Debug messages...", file=sys.stderr)

    # MOVE <x> <y> | WAIT
    #print("WAIT")
    print(my_explorer.compute_action(other_explorers,wanderers))