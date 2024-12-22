from collections import namedtuple, deque
from dataclasses import dataclass, field
from operator import itemgetter
import heapq
import sys
from queue import PriorityQueue
from enum import Enum
import math

sys.setrecursionlimit(1000000)

UP = "^"
DOWN = "v"
LEFT = "<"
RIGHT = ">"
WALL = "#"
START = "S"
END = "E"
STANDARD = "."
MOVE_FORWARD_POINT = 1
ROTATE_NINETY_DEGREE_POINT = 1000
EAST = 0
NORTH = 90
WEST = 180
SOUTH = 270

#      N (90)
# W (180)   E (0)
#     S (270)

Position = namedtuple("Position", ["x", "y"])

class Direction(Enum):
    NORTH = 0
    EAST = 1
    SOUTH = 2
    WEST = 3


# Unless otherwise specified, all directions are represented by a tuple (row, col) ie y, x.
# This to make it easier to reference lists.
NORTH = (-1, 0)
EAST = (0, 1)
SOUTH = (1, 0)
WEST = (0, -1)
NORTHEAST = (-1, 1)
SOUTHEAST = (1, 1)
SOUTHWEST = (1, -1)
NORTHWEST = (-1, -1)

# Clockwise 90 degrees
ROTATE = {
    EAST: SOUTH, SOUTH: WEST, WEST: NORTH, NORTH: EAST
}


DIRECTIONS_ALL = [
    NORTH,
    EAST,
    SOUTH,
    WEST,
    NORTHEAST,
    SOUTHEAST,
    SOUTHWEST,
    NORTHWEST,
]

DIRECTION_DELTAS = {
    # (row, col)
    Direction.EAST: EAST,
    Direction.NORTH: NORTH,
    Direction.WEST: WEST,
    Direction.SOUTH: SOUTH
}

ARROWS_TO_DIRECTION = {
    '>': Direction.EAST,
    'v': Direction.SOUTH,
    '<': Direction.WEST,
    '^': Direction.NORTH,
}

ARROWS_TO_DIRECTION2 = {
    '>': EAST,
    'v': SOUTH,
    '<': WEST,
    '^': NORTH,
}

DIRECTION2_TO_ARROWS = {
}


def into_range(x, n, m):
    # for x returns value in range n-m (inclusive)
    return ((x-n) % (m-n+1))+n


def next_neighbour(position, direction):
    return (position[0] + DIRECTION_DELTAS[direction][0], position[1] + DIRECTION_DELTAS[direction][1])


def next_neighbour2(position, direction):
    # a faster version if direction is stored as tuple instead of enum
    return (position[0] + direction[0], position[1] + direction[1])


def in_grid(position, grid):
    return 0 <= position[0] < len(grid) and 0 <= position[1] < len(grid[0])


def tile(grid, p):
    return grid[p[0]][p[1]]


def add_pos(p1, p2):
    return (p1[0] + p2[0], p1[1] + p2[1])


def subtract_pos(p1, p2):
    return (p1[0] - p2[0], p1[1] - p2[1])


direction = subtract_pos


def print_grid(grid, axis=False):
    if axis:
        print('  ' + ''.join([str(i % 10) for i in range(len(grid[0]))]))

    for r, row in enumerate(grid):
        if axis:
            print(r % 10, end=' ')
        print(''.join(row))


def make_grid(rows, cols, value=0):
    #  return np.array([[point(x, y) for x in range(cols)] for y in range(rows)])
    return [[value] * cols for _ in range(rows)]


# return all positions of value in grid
def find_in_grid(grid, value):
    result = []
    for i in range(len(grid)):
        for j in range(len(grid[0])):
            if grid[i][j] == value:
                result.append((i, j))

    return result


def neighbours(position, grid, check_in_bounds=True, condition=lambda g, x: True):
    def in_grid_bound(p):
        if check_in_bounds:
            return in_grid(p, grid)
        else:
            return True

    result = []
    for d in DIRECTION_DELTAS.keys():
        new_position = next_neighbour(position, d)
        if in_grid_bound(new_position) and condition(grid, new_position):
            result.append(new_position)

    return result


def reverse_direction(direction):
    if direction == Direction.EAST:
        return Direction.WEST
    if direction == Direction.WEST:
        return Direction.EAST
    if direction == Direction.NORTH:
        return Direction.SOUTH
    if direction == Direction.SOUTH:
        return Direction.NORTH


# rotate 90 degrees clockwise or anticlockwise
def rotate_direction(current_direction, anticlockwise=False):
    directions = list(Direction)

    # Determine the step: +1 for clockwise, -1 for anticlockwise
    step = -1 if anticlockwise else 1

    # Calculate the next index using modular arithmetic
    next_index = (current_direction.value + step) % len(directions)
    return directions[next_index]

@dataclass
class Distance:
    step: int
    rotation: int

    def evaluate(self) -> int:
        return (
            MOVE_FORWARD_POINT * self.step + ROTATE_NINETY_DEGREE_POINT * self.rotation
        )

    def __eq__(self, other):
        return self.step == other.step and self.rotation == other.rotation

    def __lt__(self, other):
        return self.evaluate() < other.evaluate()

    def __le__(self, other):
        return self.evaluate() <= other.evaluate()


@dataclass
class Cell:
    position: Position
    kind: str
    neighbours: dict[int, type["Cell"]] = field(default_factory=dict)
    distance: Distance = None
    parents: list[type["Cell"]] = field(default_factory=list)

    def __str__(self):
        return "Cell(kind={}, position={}, distance={}, east={}, north={}, west={}, south={}".format(
            self.kind,
            self.position,
            self.distance,
            self.neighbours.get(EAST).position
            if not self.neighbours.get(EAST) is None
            else "None",
            self.neighbours.get(NORTH).position
            if not self.neighbours.get(NORTH) is None
            else "None",
            self.neighbours.get(WEST).position
            if not self.neighbours.get(WEST) is None
            else "None",
            self.neighbours.get(SOUTH).position
            if not self.neighbours.get(SOUTH) is None
            else "None",
        )

    def __repr__(self):
        "Cell(kind={}, position={}, distance={}, east={}, north={}, west={}, south={}".format(
            self.kind,
            self.position,
            self.distance,
            self.neighbours.get(EAST).position
            if not self.neighbours.get(EAST) is None
            else "None",
            self.neighbours.get(NORTH).position
            if not self.neighbours.get(NORTH) is None
            else "None",
            self.neighbours.get(WEST).position
            if not self.neighbours.get(WEST) is None
            else "None",
            self.neighbours.get(SOUTH).position
            if not self.neighbours.get(SOUTH) is None
            else "None",
        )

    def __eq__(self, other):
        return self.distance == other.distance

    def __lt__(self, other):
        return self.distance < other.distance

    def __le__(self, other):
        return self.distance <= other.distance

@dataclass
class Reindeer:
    position: tuple
    cost: int
    direction: tuple
    visited: set

    def __hash__(self):
        return hash((self.position, self.cost, self.direction))

    def __repr__(self):
        return f'Reindeer({self.position}, {self.cost}, {self.direction})'

    # this allows PriorityQueue to compare two items in the queue and pick the one
    # with the lower cost
    def __lt__(self, other):
        return self.cost < other.cost

def get_maze(input_name: str) -> list[str]:
    maze = []
    with open(input_name, "r") as input:
        for line in input:
            maze.append(line.strip())
    return maze


def display_maze(maze: list[str]):
    for line in maze:
        for c in line:
            print(c, end="")
        print()


def get_starting_and_ending_position(maze: list[str]) -> tuple[Position, Position]:
    start = None
    end = None
    for y, line in enumerate(maze):
        for x, c in enumerate(line):
            if c == START:
                start = Position(x=x, y=y)
            if c == END:
                end = Position(x=x, y=y)
    return start, end


def maze_as_cells_matrix(maze: list[str]) -> tuple[list[list[Cell]], Cell, Cell]:
    cells_matrix = []
    start_cell = None
    end_cell = None
    for y, line in enumerate(maze):
        cells_line = []
        for x, c in enumerate(line):
            cell = Cell(kind=c, position=Position(x=x, y=y))
            cells_line.append(cell)
            if c == START:
                start_cell = cell
            elif c == END:
                end_cell = cell
        cells_matrix.append(cells_line)
    return cells_matrix, start_cell, end_cell

def new_cost(reindeer, p2):
    result = reindeer.cost
    if direction(p2, reindeer.pos) != reindeer.direction:
        result += 1000
    return result+1


def find_neighbours(maze: list[list[Cell]]):
    for line in maze:
        for cell in line:
            if cell.kind != WALL:
                find_cell_neighbours(cell, maze)


def find_cell_neighbours(cell: Cell, maze: list[list[Cell]]):
    east = maze[cell.position.y][cell.position.x + 1]
    north = maze[cell.position.y - 1][cell.position.x]
    west = maze[cell.position.y][cell.position.x - 1]
    south = maze[cell.position.y + 1][cell.position.x]
    if east.kind != WALL:
        cell.neighbours[EAST] = east
    if north.kind != WALL:
        cell.neighbours[NORTH] = north
    if west.kind != WALL:
        cell.neighbours[WEST] = west
    if south.kind != WALL:
        cell.neighbours[SOUTH] = south


def calculate_rotation(current_direction, neighbour_direction):
    diff = int(abs(current_direction - neighbour_direction))
    if diff == 0:
        return 0
    if diff == 90 or diff == 270:
        return 1
    if diff == 180:
        return 2


def breadth_first_search_traversal(start_cell: Cell, start_direction: int, start_distance: Distance):
    start_cell.distance = start_distance
    start_cell.parents = []
    to_explore = []
    to_explore.append((start_cell, start_direction, start_distance))

    # Until the queue to_explore is empty
    while to_explore:
        current_cell, current_direction, current_distance = heapq.heappop(to_explore)

        cell_in_direction = current_cell.neighbours.get(current_direction)

        if not cell_in_direction is None:
            distance = Distance(current_distance.step + 1, current_distance.rotation)
            if not cell_in_direction.distance is None:
                if cell_in_direction.distance > distance:
                    cell_in_direction.distance = distance
                    heapq.heappush(to_explore,(cell_in_direction, current_direction, distance))
                    cell_in_direction.parents.clear()
                    cell_in_direction.parents.append(current_cell)
                elif cell_in_direction.distance == distance:
                    cell_in_direction.parents.append(current_cell)
            else: # Distance None, this has not been initialized
                cell_in_direction.distance = distance
                heapq.heappush(to_explore,(cell_in_direction, current_direction, distance))
                cell_in_direction.parents.append(current_cell)
                

        for key in filter(
            lambda k: k != current_direction, current_cell.neighbours.keys()
        ):
            cell = current_cell.neighbours.get(key)
            if not cell is None:
                rotation = current_distance.rotation + calculate_rotation(
                    current_direction, key
                )
                distance = Distance(current_distance.step + 1, rotation)
                if not cell.distance is None:
                    if cell.distance > distance:
                        cell.distance = distance
                        heapq.heappush(to_explore,(cell, current_direction, distance))
                        cell.parents.clear()
                        cell.parents.append(current_cell)
                    elif cell.distance == distance:
                        cell.parents.append(current_cell)
                else:
                    cell.distance = distance
                    heapq.heappush(to_explore,(cell, current_direction, distance))
                    cell.parents.append(current_cell)


def walk_the_maze(start_cell: Cell):
    breadth_first_search_traversal(start_cell, EAST, Distance(0, 0))


def find_best_positions(end_cell: Cell, start_cell: Cell) -> set[Cell]:
    best_positions = set()

    to_explore = []
    to_explore.append(end_cell)
    current_cell = None
    current_position = None
    # current_minimal_distance = end_cell.distance
    while to_explore and current_position != start_cell.position:
        current_cell = heapq.heappop(to_explore)
        current_position = current_cell.position
        best_positions.add(current_position)
        neighbours = [c for c in current_cell.neighbours.values()]
        # distances = [c.distance for c in neighbours]
        # smallest_distance = min(distances)
        # smallest_distance_neighbours = [ n for n in neighbours if n.distance == smallest_distance ]

        # for cell in smallest_distance_neighbours:
        for cell in neighbours:
            if not cell.position in best_positions:
                heapq.heappush(to_explore, cell)
                # best_positions.add(cell.position)

    to_explore = []
    to_explore.append(start_cell)
    current_cell = None
    current_position = None
    while to_explore and current_position != end_cell.position:
        current_cell = heapq.heappop(to_explore)
        current_position = current_cell.position
        best_positions.add(current_position)
        neighbours = [c for c in current_cell.neighbours.values()]
        # distances = [c.distance for c in neighbours]
        # smallest_distance = min(distances)
        # smallest_distance_neighbours = [ n for n in neighbours if n.distance == smallest_distance ]

        # for cell in smallest_distance_neighbours:
        for cell in neighbours:
            if not cell.position in best_positions:
                heapq.heappush(to_explore, cell)

    # to_explore = deque()
    # to_explore.append(start_cell)
    # current_cell = None
    # current_position = None
    # while to_explore and current_position != end_cell.position:
    #     current_cell = to_explore.popleft()
    #     current_position = current_cell.position
    #     best_positions.add(current_position)
    #     neighbours = [c for c in current_cell.neighbours.values()]
    #     distances = [c.distance for c in neighbours]
    #     smallest_distance = min(distances)
    #     smallest_distance_neighbours = [ n for n in neighbours if n.distance == smallest_distance ]

    #     for cell in smallest_distance_neighbours:
    #         if not cell.position in best_positions:
    #             to_explore.append(cell)
    #             best_positions.add(cell.position)

    return best_positions


if __name__ == "__main__":
    maze = get_maze("input_test.txt")
    # maze = get_maze("input_test1.txt")
    # maze = get_maze("input.txt")
    display_maze(maze)
    print()

    start, end = get_starting_and_ending_position(maze)
    print("start:", start)
    print("end:", end)
    print()

    cells_matrix, start_cell, end_cell = maze_as_cells_matrix(maze)
    find_neighbours(cells_matrix)
    print("start_cell:", start_cell)
    print("end_cell:", end_cell)
    print()

    walk_the_maze(start_cell)

    print("start_cell:", start_cell)
    print("end_cell:", end_cell)
    print("lowest_score:", end_cell.distance.evaluate())
    # best_positions = find_best_positions(end_cell, start_cell)
    # print(sorted(best_positions))
    # print(len(best_positions))

# input_test.txt => 45
# input_test1.txt => 64

# For part 1 :
# input_test.txt => 7036
# input_test0.txt => 11048
