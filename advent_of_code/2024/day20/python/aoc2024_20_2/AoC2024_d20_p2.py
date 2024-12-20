from itertools import combinations
import heapq
import sys

sys.setrecursionlimit(1000000)

TRACK = "."
WALL = "#"
START = "S"
END = "E"


def get_maze(input_name: str) -> list[list[str]]:
    maze = []
    with open(input_name, "r") as input:
        for line in input:
            maze.append(list(line.strip()))
    return maze


def display_maze(maze: list[list[str]]):
    for line in maze:
        for c in line:
            print(c, end="")
        print()


def copy_maze(maze: list[list[str]]) -> list[list[str]]:
    copy = []
    for line in maze:
        copy.append(line[:])
    return copy


def get_starting_and_ending_position(
    maze: list[list[str]],
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


def find_possible_neighbours(
    node: tuple[int, int], maze: list[list[str]]
) -> list[tuple[int, int]]:
    x, y = node
    y_max = len(maze)
    x_max = len(maze[0])
    possible_neighbours = []

    if 0 <= y < y_max and 0 <= x < x_max - 1 and maze[y][x + 1] != WALL:
        possible_neighbours.append((x + 1, y))
    if 0 < y < y_max and 0 <= x < x_max and maze[y - 1][x] != WALL:
        possible_neighbours.append((x, y - 1))
    if 0 <= y < y_max and 0 < x < x_max and maze[y][x - 1] != WALL:
        possible_neighbours.append((x - 1, y))
    if 0 <= y < y_max - 1 and 0 <= x < x_max and maze[y + 1][x] != WALL:
        possible_neighbours.append((x, y + 1))

    return possible_neighbours


def compute_time_and_path_to_exit(
    start: tuple[int, int], end: tuple[int, int], maze: list[list[str]]
) -> tuple[int, list[tuple[int, int]], dict[tuple[int, int], int]]:
    distance = 0
    to_explore = [(distance, start)]
    visited = []
    current_node = None
    distances_by_node = {}

    while to_explore and current_node != end:
        current_distance, current_node = heapq.heappop(to_explore)
        # print(current_node, current_distance, end)
        if not current_node in visited:
            visited.append(current_node)
            distances_by_node[current_node] = current_distance

            neighbours = find_possible_neighbours(current_node, maze)
            for n in neighbours:
                if not n in visited:
                    heapq.heappush(to_explore, (current_distance + 1, n))

    return current_distance, visited, distances_by_node


def compute_taxicab_distance(a: tuple[int, int], b: tuple[int, int]) -> int:
    return abs(b[0] - a[0]) + abs(b[1] - a[1])


def find_possible_shortcuts(
    distances_by_node: dict[tuple[int, int]],
    nodes: list[tuple[int, int]],
    minimum_gain: int,
) -> set[tuple[int, int]]:
    sum_of_shortcuts = 0
    pairs = combinations(nodes, 2)
    for n1, n2 in pairs:
        taxicab_distance = compute_taxicab_distance(n1, n2)
        dijkstra_distance = abs(distances_by_node[n1] - distances_by_node[n2])

        if taxicab_distance >= dijkstra_distance or taxicab_distance > 20:
            continue

        if (dijkstra_distance - taxicab_distance) >= minimum_gain:
            # print(n1, n2, dijkstra_distance, taxicab_distance, (dijkstra_distance - taxicab_distance))
            sum_of_shortcuts += 1

    return sum_of_shortcuts


if __name__ == "__main__":
    maze, minimum_gain = get_maze("input_test.txt"), 50  # Expected 285
    # maze, minimum_gain = get_maze("input.txt"), 100
    # display_maze(maze)
    start, end = get_starting_and_ending_position(maze)
    time_to_exit, visited, distances_by_node = compute_time_and_path_to_exit(
        start, end, maze
    )
    print("Original time to exit:", time_to_exit)
    print()
    sum_of_shortcuts = find_possible_shortcuts(distances_by_node, visited, minimum_gain)
    print(sum_of_shortcuts)
    # Expected output for part 2 : 985737
