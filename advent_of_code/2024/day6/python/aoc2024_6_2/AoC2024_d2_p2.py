def extract_data(input_name: str) -> list[list[str]]:
    lines = None
    with open(input_name, "r") as input:
        lines = [list(line.strip()) for line in input.readlines()]

    return lines

def find_guard_initial_position(data: list[list[str]]) -> tuple[int, int, int]:
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


def is_position_an_obstacle(data: list[list[str]], position: tuple[int, int, int]) -> bool:
    return data[position[1]][position[0]] == "#"


def is_stuck_in_loop(
    data: list[list[str]], max_x: int, max_y: int
) -> bool:
    positions_set = set()
    current_guard_position = find_guard_initial_position(data)
    positions_set.add(current_guard_position)
    next_guard_position = compute_forward_move(current_guard_position)
    while not is_position_out_of_bound(
        next_guard_position, max_x, max_y
    ):  # We are not out of the map
        if next_guard_position in positions_set: # If the next position is in the set of position we are in a loop
            return True
        if is_position_an_obstacle(data, next_guard_position):
            next_guard_position = turn_right(current_guard_position)
        current_guard_position = next_guard_position
        positions_set.add(current_guard_position)
        next_guard_position = compute_forward_move(current_guard_position)

    # If we are here, this is because we are not in a loop
    return False

def count_possible_obstruction_position(data: list[list[str]]) -> int:
    max_y = len(data)  # maximum numbers of lines
    max_x = len(data[0])  # maximum length of a line
    data_working_copy = [line[:] for line in data]
    count = 0
    for y in range(max_y):
        for x in range(max_x):
            current_cell = data_working_copy[y][x]
            if current_cell == '.':
                data_working_copy[y][x] = "#"
                if is_stuck_in_loop(data_working_copy, max_x, max_y):
                    count += 1 # We have a loop, we increment the counter
                data_working_copy[y][x] = current_cell # We reset the cell to its original value
    
    return count

if __name__ == "__main__":
    data = extract_data("input.txt")
    print(count_possible_obstruction_position(data))
