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

def add_corrupted_memory_block(
    grid: list[list[str]], corrupted_block_coord: tuple[int,int]
):
    x, y = corrupted_block_coord
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


def find_exit(
    start: tuple[int], exit: tuple[int], grid: list[list[str]]
) -> bool:
    distance = 0
    # to_explore = deque()
    # to_explore.append((start, distance))
    to_explore = [(distance, start)]
    visited = set()
    current_cell = None
    exit_found = False

    while to_explore:
        current_distance, current_cell = heapq.heappop(to_explore)
        # print(current_cell, current_distance)
        if current_cell == exit:
            exit_found = True
            break
        if not current_cell in visited:
            visited.add(current_cell)
            neighbours = find_possible_neighbours(current_cell, grid)
            for n in neighbours:
                if not n in visited:
                    heapq.heappush(to_explore, (current_distance + 1, n))

    return exit_found


def find_first_byte_to_cut_off(start: tuple[int], exit: tuple[int], grid: list[list[str]],initial_nb_of_bytes: int, data: list[tuple[int,int]]) -> tuple[int,int]:
    i = initial_nb_of_bytes + 1
    max_loop = len(data)
    exit_found = True
    current_byte_coord = None
    while i < max_loop and exit_found:
        current_byte_coord = data[i]
        add_corrupted_memory_block(grid, current_byte_coord)
        exit_found = find_exit(start, exit, grid)
        i += 1

    return current_byte_coord


if __name__ == "__main__":
    # data, min_range, max_range, initial_nb_bytes = get_data("input_test.txt"), 0, 6, 12
    data, min_range, max_range, initial_nb_bytes = get_data("input.txt"), 0, 70, 1024
    print("data:", data)
    grid = initialize_grid(min_range, max_range)
    display_grid(grid)

    print()
    print()

    place_corrupted_memory_block(grid, initial_nb_bytes, data)
    display_grid(grid)
    print()
    start = (min_range, min_range)
    exit = (max_range, max_range)
    exit_found = find_exit(start, exit, grid)
    print(exit_found)
    first_byte_to_cut_off = find_first_byte_to_cut_off(start, exit, grid, initial_nb_bytes, data)
    print()
    print(",".join([str(i) for i in first_byte_to_cut_off]))
