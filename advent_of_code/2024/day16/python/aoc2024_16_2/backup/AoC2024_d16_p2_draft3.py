from collections import namedtuple, deque
from dataclasses import dataclass, field
from operator import itemgetter
import heapq
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
    neighbours: dict[int, type["Cell"]] = field(default_factory=dict)
    distance: Distance = None
    parents: list[type["Cell"]] = field(default_factory=list)

    def __str__(self):
        return "Cell(kind={}, position={}, distance={}, east={}, north={}, west={}, south={}".format(
            self.kind,
            self.position,
            self.distance,
            self.neighbours.get(EAST).position
            if self.neighbours.get(EAST) is not None
            else "None",
            self.neighbours.get(NORTH).position
            if self.neighbours.get(NORTH) is not None
            else "None",
            self.neighbours.get(WEST).position
            if self.neighbours.get(WEST) is not None
            else "None",
            self.neighbours.get(SOUTH).position
            if self.neighbours.get(SOUTH) is not None
            else "None",
            [", ".join(str(parent.position) for parent in self.parents)]
            if self.parents
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
            [", ".join(str(parent.position) for parent in self.parents)]
            if self.parents
            else "None",
        )

    def __eq__(self, other):
        return self.distance == other.distance

    def __lt__(self, other):
        return self.distance < other.distance

    def __le__(self, other):
        return self.distance <= other.distance


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


def breadth_first_search_traversal(
    start_cell: Cell, end_cell: Cell, start_direction: int, start_distance: Distance
):
    start_cell.distance = start_distance
    start_cell.parents = []
    to_explore = []
    to_explore.append((start_cell, start_direction, start_distance))
    current_position = None

    # Until the queue to_explore is empty
    while to_explore and current_position != end_cell.position:
        current_cell, current_direction, current_distance = heapq.heappop(to_explore)
        current_position = current_cell.position

        for direction, cell in current_cell.neighbours.items():
            distance = None
            if direction == current_direction:
                distance = Distance(
                    current_distance.step + 1, current_distance.rotation
                )
            else:
                rotation = current_distance.rotation + calculate_rotation(
                    current_direction, direction
                )
                distance = Distance(current_distance.step + 1, rotation)
            if cell is not None:
                if cell.distance is not None:
                    if cell.distance > distance:
                        cell.distance = distance
                        heapq.heappush(to_explore, (cell, current_direction, distance))
                        cell.parents.clear()
                        cell.parents.append(current_cell)
                    elif cell.distance == distance:
                        cell.parents.append(current_cell)
                else:
                    cell.distance = distance
                    heapq.heappush(to_explore, (cell, current_direction, distance))
                    cell.parents.append(current_cell)


def walk_the_maze(start_cell: Cell, end_cell: Cell):
    breadth_first_search_traversal(start_cell, end_cell, EAST, Distance(0, 0))


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

    walk_the_maze(start_cell, end_cell)

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
