def extract_data(input_name: str) -> list[str]:
    lines = None
    with open(input_name, "r") as input:
        lines = [line.strip() for line in input.readlines()]

    return lines


def find_guard_initial_position(data: list[str]) -> tuple[int, int, int]:
    # x position in the line
    # y line number
    direction_by_char = {
        "^": 0,
        ">": 90,
        "v": 180,
        "<": 270,
    }
    for y, line in enumerate(data):
        for x, char in enumerate(line):
            if char in "^>v<":
                return (x, y, direction_by_char[char])


def turn_right(position: tuple[int, int, int]) -> tuple[int, int, int]:
    return (position[0], position[1], (position[2] + 90) % 360)


def compute_forward_move(position: tuple[int, int, int]) -> tuple[int, int, int]:
    x_increment = 0
    y_increment = 0
    direction = position[2]
    if direction == 0:
        y_increment -= 1
    elif direction == 90:
        x_increment += 1
    elif direction == 180:
        y_increment += 1
    elif direction == 270:
        x_increment -= 1

    return (position[0] + x_increment, position[1] + y_increment, direction)


def is_position_out_of_bound(
    position: tuple[int, int, int], max_x: int, max_y: int
) -> bool:
    return (
        position[0] < 0
        or position[0] >= max_x
        or position[1] < 0
        or position[1] >= max_y
    )


def is_position_an_obstacle(data: list[str], position: tuple[int, int, int]) -> bool:
    return data[position[1]][position[0]] == "#"


def compute_positions_set(
    data: list[str], max_x: int, max_y: int
) -> set[tuple[int, int]]:
    positions_set = set()
    current_guard_position = find_guard_initial_position(data)
    positions_set.add((current_guard_position[0], current_guard_position[1]))
    next_guard_position = compute_forward_move(current_guard_position)
    while not is_position_out_of_bound(
        next_guard_position, max_x, max_y
    ):  # tant que nous ne sommes pas sortis de la carte
        if is_position_an_obstacle(data, next_guard_position):
            next_guard_position = turn_right(current_guard_position)
        current_guard_position = next_guard_position
        positions_set.add((current_guard_position[0], current_guard_position[1]))
        next_guard_position = compute_forward_move(current_guard_position)

    return positions_set


if __name__ == "__main__":
    data = extract_data("input.txt")
    print(data)
    max_y = len(data)  # maximum numbers of lines
    max_x = len(data[0])  # maximum length of a line
    print("max_x:", max_x, "max_y:", max_y)
    guard_position = find_guard_initial_position(data)
    print("guard initial_position:", guard_position)
    positions_set = compute_positions_set(data, max_x, max_y)
    print(len(positions_set)) #5212
