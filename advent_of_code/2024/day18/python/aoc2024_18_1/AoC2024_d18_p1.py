import heapq
import sys

sys.setrecursionlimit(1000000)

SAFE = "."
CORRUPTED = "#"


def get_data(input_name: str) -> list[str]:
    lines = []
    with open(input_name, "r") as input:
        for line in input:
            lines.append([int(n) for n in line.strip().split(",")])
    return lines


def initialize_grid(min_range: int, max_range: int) -> list[list[str]]:
    grid = []
    for _ in range(min_range, max_range + 1):
        line = []
        for _ in range(min_range, max_range + 1):
            line.append(".")
        grid.append(line)
    return grid


def display_grid(grid: list[list[str]]):
    for line in grid:
        for c in line:
            print(c, end="")
        print()


def place_corrupted_memory_block(
    grid: list[list[str]], nb_of_bytes: int, data: list[list[int]]
):
    for n in range(0, nb_of_bytes):
        x, y = data[n]
        grid[y][x] = CORRUPTED


def find_possible_neighbours(
    cell: tuple[int, int], grid: list[list[str]]
) -> list[tuple[int, int]]:
    x, y = cell
    y_max = len(grid)
    x_max = len(grid[0])
    possible_neighbours = []

    if 0 <= y < y_max and 0 <= x < x_max - 1 and grid[y][x + 1] != CORRUPTED:
        possible_neighbours.append((x + 1, y))
    if 0 < y < y_max and 0 <= x < x_max and grid[y - 1][x] != CORRUPTED:
        possible_neighbours.append((x, y - 1))
    if 0 <= y < y_max and 0 < x < x_max and grid[y][x - 1] != CORRUPTED:
        possible_neighbours.append((x - 1, y))
    if 0 <= y < y_max - 1 and 0 <= x < x_max and grid[y + 1][x] != CORRUPTED:
        possible_neighbours.append((x, y + 1))

    return possible_neighbours


def compute_distance_to_exit(
    start: tuple[int], exit: tuple[int], grid: list[list[str]]
) -> int:
    distance = 0
    # to_explore = deque()
    # to_explore.append((start, distance))
    to_explore = [(distance, start)]
    visited = set()
    current_cell = None

    while to_explore and current_cell != exit:
        current_distance, current_cell = heapq.heappop(to_explore)
        print(current_cell, current_distance)
        if not current_cell in visited:
            visited.add(current_cell)
            neighbours = find_possible_neighbours(current_cell, grid)
            for n in neighbours:
                if not n in visited:
                    heapq.heappush(to_explore, (current_distance + 1, n))

    return current_distance


if __name__ == "__main__":
    # data, min_range, max_range, nb_of_bytes = get_data("input_test.txt"), 0, 6, 12
    data, min_range, max_range, nb_of_bytes = get_data("input.txt"), 0, 70, 1024
    print("data:", data)
    grid = initialize_grid(min_range, max_range)
    display_grid(grid)

    print()
    print()

    place_corrupted_memory_block(grid, nb_of_bytes, data)
    display_grid(grid)
    print()
    start = (min_range, min_range)
    exit = (max_range, max_range)
    distance_to_exit = compute_distance_to_exit(start, exit, grid)
    print(distance_to_exit)
    # print()
    # display_grid(grid)
