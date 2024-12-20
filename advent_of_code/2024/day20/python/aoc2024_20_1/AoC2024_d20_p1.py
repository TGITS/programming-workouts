from collections import deque
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


def find_neighbouring_walls(
    node: tuple[int, int], maze: list[list[str]]
) -> list[tuple[int, int]]:
    x, y = node
    y_max = len(maze)
    x_max = len(maze[0])
    neighbouring_walls = []

    if 0 <= y < y_max and 0 <= x < x_max - 1 and maze[y][x + 1] == WALL:
        neighbouring_walls.append((x + 1, y))
    if 0 < y < y_max and 0 <= x < x_max and maze[y - 1][x] == WALL:
        neighbouring_walls.append((x, y - 1))
    if 0 <= y < y_max and 0 < x < x_max and maze[y][x - 1] == WALL:
        neighbouring_walls.append((x - 1, y))
    if 0 <= y < y_max - 1 and 0 <= x < x_max and maze[y + 1][x] == WALL:
        neighbouring_walls.append((x, y + 1))

    return neighbouring_walls


def compute_time_and_path_to_exit(
    start: tuple[int, int], end: tuple[int, int], maze: list[list[str]]
) -> tuple[int, set[tuple[int, int]]]:
    distance = 0
    to_explore = [(distance, start)]
    visited = set()

    current_node = None
    # minimal_distance = None

    while to_explore and current_node != end:
        current_distance, current_node = heapq.heappop(to_explore)
        # print(current_node, current_distance, end)
        if not current_node in visited:
            visited.add(current_node)
            # path.append(current_cell)
            neighbours = find_possible_neighbours(current_node, maze)
            for n in neighbours:
                if not n in visited:
                    heapq.heappush(to_explore, (current_distance + 1, n))

    return current_distance, visited


def find_candidate_walls(
    path: set[tuple[int, int]], maze: list[list[str]]
) -> set[tuple[int, int]]:
    candidate_walls = []
    for node in path:
        candidate_walls.extend(find_neighbouring_walls(node, maze))

    return set(candidate_walls)


def find_possible_shortcuts(
    candidate_walls: set[tuple[int, int]],
    path: set[tuple[int, int]],
    maze: list[list[str]],
) -> set[tuple[int, int]]:
    shortcuts = set()
    for wall in candidate_walls:
        possible_neighbours = find_possible_neighbours(wall, maze)
        intersect = path & set(possible_neighbours)
        if len(intersect) > 1:
            shortcuts.add(wall)

    return shortcuts


def compute_time_to_exit_for_shorcuts(
    candidate_shortcuts: set[tuple[int, int]],
    start: tuple[int],
    exit: tuple[int],
    maze: list[list[str]],
) -> list[int]:
    time_to_exit = []
    for shortcut in candidate_shortcuts:
        maze_copy = copy_maze(maze)
        maze_copy[shortcut[1]][shortcut[0]] = TRACK
        time, _ = compute_time_and_path_to_exit(start, exit, maze_copy)
        time_to_exit.append(time)
    return time_to_exit


if __name__ == "__main__":
    # maze = get_maze("input_test.txt")
    maze = get_maze("input.txt")
    display_maze(maze)
    start, end = get_starting_and_ending_position(maze)
    time_to_exit, visited = compute_time_and_path_to_exit(start, end, maze)
    print(time_to_exit)
    print()
    # print("Nodes in the path to exit",visited)
    candidate_walls = find_candidate_walls(visited, maze)
    # print()
    # print("Shortcut candidate walls:", candidate_walls)
    candidate_shortcuts = find_possible_shortcuts(candidate_walls, visited, maze)
    # print()
    # print("Shortcuts:", sorted(candidate_shortcuts))
    # print()
    # print("Number of possible shorcuts:", len(candidate_shortcuts))
    times_to_exit = compute_time_to_exit_for_shorcuts(
        candidate_shortcuts, start, end, maze
    )
    # print()
    # print(times_to_exit)
    # print()
    # print(sorted(times_to_exit))
    print()
    print("Original time to exit:", time_to_exit)
    print()
    # print("Times with shortcuts (sorted):", sorted(times_to_exit))
    # print()
    print(
        "Number of time gains >= 100 with shortcut:",
        len(
            [
                (time_to_exit - new_time)
                for new_time in times_to_exit
                if (time_to_exit - new_time) >= 100
            ]
        ),
    )
    # Expected output for part 1 : 1327
