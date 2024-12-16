from collections import namedtuple, deque
from dataclasses import dataclass
from operator import itemgetter
import sys

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
    east: type["Cell"] = None
    north: type["Cell"] = None
    west: type["Cell"] = None
    south: type["Cell"] = None
    distance: Distance = None

    def __str__(self):
        return "Cell(kind={}, position={}, distance={}, east={}, north={}, west={}, south={}".format(
            self.kind,
            self.position,
            self.distance,
            self.east.position if not self.east is None else "None",
            self.north.position if not self.north is None else "None",
            self.west.position if not self.west is None else "None",
            self.south.position if not self.south is None else "None",
        )

    def __repr__(self):
        return "Cell(kind={}, position={}, distance={}, east={}, north={}, west={}, south={}".format(
            self.kind,
            self.position,
            self.distance,
            self.east.position if not self.east is None else "None",
            self.north.position if not self.north is None else "None",
            self.west.position if not self.west is None else "None",
            self.south.position if not self.south is None else "None",
        )


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
        cell.east = east
    if north.kind != WALL:
        cell.north = north
    if west.kind != WALL:
        cell.west = west
    if south.kind != south:
        cell.south = south


def calculate_rotation(current_direction, neighbour_direction):
    diff = int(abs(current_direction - neighbour_direction))
    if diff == 0:
        return 0
    if diff == 90 or diff == 270:
        return 1
    if diff == 180:
        return 2


def compute_distances(
    current_cell: Cell,
    current_direction: int,
    current_distance: Distance,
    already_visited: list[Position],
):
    already_visited.append(current_cell.position)
    if current_cell.distance is None:
        # If distance is not None, the cell hase already been visited
        current_cell.distance = current_distance

    data_by_rotations = {}

    if (
        not current_cell.east is None
        and not current_cell.east.position in already_visited
    ):
        rotation = current_distance.rotation + calculate_rotation(
            current_direction, EAST
        )
        distance = Distance(current_distance.step + 1, rotation)
        data = data_by_rotations.get(rotation, [])
        data.append((current_cell.east, EAST, distance))
        data_by_rotations[rotation] = data
    if (
        not current_cell.north is None
        and not current_cell.north.position in already_visited
    ):
        rotation = current_distance.rotation + calculate_rotation(
            current_direction, NORTH
        )
        distance = Distance(current_distance.step + 1, rotation)
        data = data_by_rotations.get(rotation, [])
        data.append((current_cell.north, NORTH, distance))
        data_by_rotations[rotation] = data
    if (
        not current_cell.west is None
        and not current_cell.west.position in already_visited
    ):
        rotation = current_distance.rotation + calculate_rotation(
            current_direction, WEST
        )
        distance = Distance(current_distance.step + 1, rotation)
        data = data_by_rotations.get(rotation, [])
        data.append((current_cell.west, WEST, distance))
        data_by_rotations[rotation] = data
    if (
        not current_cell.south is None
        and not current_cell.south.position in already_visited
    ):
        rotation = current_distance.rotation + calculate_rotation(
            current_direction, SOUTH
        )
        distance = Distance(current_distance.step + 1, rotation)
        data = data_by_rotations.get(rotation, [])
        data.append((current_cell.south, SOUTH, distance))
        data_by_rotations[rotation] = data

    for k in sorted(data_by_rotations.keys()):
        v = data_by_rotations[k]
        for data in sorted(v, key=itemgetter(2)):
            compute_distances(data[0], data[1], data[2], already_visited)


def walk_the_maze(start_cell: Cell):
    compute_distances(start_cell, EAST, Distance(0, 0), [])


if __name__ == "__main__":
    # maze = get_maze("input_test.txt")
    maze = get_maze("input_test1.txt")
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

    compute_distances(start_cell, EAST, Distance(0, 0), [])

    print("start_cell:", start_cell)
    print("end_cell:", end_cell)
    print("lowest_score:", end_cell.distance.evaluate())

# input_test.txt => 7036
# input_test0.txt => 11048
