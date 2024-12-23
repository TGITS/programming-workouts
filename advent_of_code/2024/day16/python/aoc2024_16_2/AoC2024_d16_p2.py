from collections import defaultdict
import math
import heapq
import sys

sys.setrecursionlimit(1000000)

WALL = "#"
START = "S"
END = "E"
STANDARD = "."
MOVE_FORWARD_POINT = 1
ROTATE_NINETY_DEGREE_POINT = 1000

#         N (-1,0)
# W (0,-1)        E (0,1)
#         S (1,0)

directions = {(-1, 0), (0, -1), (0, 1), (1, 0)}


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


def get_starting_and_ending_position(
    maze: list[str],
) -> tuple[tuple[int, int], tuple[int, int]]:
    start = None
    end = None
    for y, line in enumerate(maze):
        for x, c in enumerate(line):
            if c == START:
                start = (x, y)
            if c == END:
                end = (x, y)
    return start, end


def get_walls(maze: list[str]) -> set[tuple[int, int]]:
    return {
        (i, j)
        for i, line in enumerate(maze)
        for j, char in enumerate(line)
        if char == WALL
    }


def distance_function(location):
    # A location is the x and y coordinate and the "direction" along the x and y axis
    x, y, dx, dy = location
    neighbours = []
    if (x + dx, y + dy) not in walls:
        neighbours.append([(x + dx, y + dy, dx, dy), 1])

    for newdx, newdy in directions:
        # If not facing the current direction, the cost is more important
        # Furtehermore we do not add the location facing the current direction
        if (newdx, newdy) != (dx, dy):
            neighbours.append([(x, y, newdx, newdy), 1000])
    return neighbours


def find_shortest_path(
    start: tuple[int, int, int, int], end_locations: list[tuple[int, int, int, int]]
) -> int:
    visited = set()
    queue = [(0, start)]
    # math.inf constant returns a floating-point positive infinity
    distance_by_coordinates = defaultdict(lambda: math.inf, {start: 0})
    while True:
        current_distance, current_location = heapq.heappop(queue)
        if current_location in visited:
            continue
        visited.add(current_location)
        if current_location in end_locations:
            return current_distance
        neighbours = distance_function(current_location)
        for coord, distance in neighbours:
            new_distance = current_distance + distance
            if coord not in visited:
                if new_distance < distance_by_coordinates[coord]:
                    distance_by_coordinates[coord] = new_distance
                    heapq.heappush(queue, (new_distance, coord))


def compute_distances(
    start_locations: list[tuple[int, int, int, int]],
) -> dict[tuple[int, int, int, int], int]:
    visited = set()
    queue = [(0, start) for start in start_locations]
    # math.inf constant returns a floating-point positive infinity
    distance_by_coordinates = defaultdict(
        lambda: math.inf, {start: 0 for start in start_locations}
    )
    distance_by_location = {}
    while True:
        if queue:
            current_distance, current_location = heapq.heappop(queue)
            if current_location in visited:
                continue
            else:
                distance_by_location[current_location] = current_distance
            visited.add(current_location)
            neighbours = distance_function(current_location)
            for coord, distance in neighbours:
                new_distance = current_distance + distance
                if coord not in visited:
                    if new_distance < distance_by_coordinates[coord]:
                        distance_by_coordinates[coord] = new_distance
                        heapq.heappush(queue, (new_distance, coord))
        else:
            return distance_by_location


if __name__ == "__main__":
    # maze = get_maze("input_test.txt")
    # maze = get_maze("input_test1.txt")
    maze = get_maze("input.txt")
    display_maze(maze)
    print()
    walls = get_walls(maze)
    start, end = get_starting_and_ending_position(maze)
    print("start:", start)
    print("end:", end)
    print()
    # list of possible start locations
    start_locations = [start + (0, 1)]  # We always start facing the EAST
    # list of possible end locations
    end_locations = [end + direction for direction in directions]
    print("Start locations:", start_locations)
    print("End locations:", end_locations)

    answer = find_shortest_path(start_locations[0], end_locations)
    print(answer)

    distance_from_start = compute_distances(start_locations)
    distance_from_end = compute_distances(end_locations)

    coordinates = set()
    for x, y, dx, dy in distance_from_start:
        if (
            distance_from_start[(x, y, dx, dy)] + distance_from_end[(x, y, -dx, -dy)]
            == answer
        ):
            coordinates.add((x, y))
    print(len(coordinates))


# For part 1 :
# input_test.txt => 7036
# input_test0.txt => 11048
# input.txt => 106512 ? 107512 ?

# For part 2
# input_test.txt => 45
# input_test1.txt => 64
# input.txt => 563
