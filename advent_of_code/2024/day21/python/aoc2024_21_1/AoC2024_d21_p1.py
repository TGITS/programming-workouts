from functools import cache

NUMERIC_KEYPAD_GAP = (0, 4)
DIRECTIONAL_KEYPAD_GAP = (0, 0)

GAP = "X"
KEYPAD_NUMERIC = ["789", "456", "123", "X0A"]
KEYPAD_DIRECTIONAL = ["X^A", "<v>"]


def get_data(input_name: str) -> list[str]:
    data = []
    with open(input_name, "r") as input:
        for line in input:
            data.append(line.strip())
    return data


def find_position(key, keypad):
    for y, row in enumerate(keypad):
        for x, char in enumerate(row):
            if char == key:
                return x, y


def shortest_paths_between_keys(start_key, end_key, keypad):
    x_start, y_start = find_position(start_key, keypad)
    x_end, y_end = find_position(end_key, keypad)
    dx, dy = x_end - x_start, y_end - y_start

    y_moves = "v" * dy if dy >= 0 else "^" * (-dy)
    x_moves = ">" * dx if dx >= 0 else "<" * (-dx)

    if dx == dy == 0:  # We already are on the right key
        return [""]
    elif dy == 0:  # Only horizontal movement
        return [x_moves]
    elif dx == 0:  # Only vertical movement
        return [y_moves]
    elif keypad[y_start][x_end] == GAP:
        return [y_moves + x_moves]
    elif keypad[y_end][x_start] == GAP:
        return [x_moves + y_moves]
    else:
        return [y_moves + x_moves, x_moves + y_moves]


@cache
def sequence_length(sequence: str, depth: int) -> int:
    if depth == 1:
        return len(sequence)

    if any(elt in sequence for elt in "012345679"):
        keypad = KEYPAD_NUMERIC
    else:
        keypad = KEYPAD_DIRECTIONAL

    result = 0
    for start_key, end_key in zip("A" + sequence, sequence):
        shortest_paths = shortest_paths_between_keys(start_key, end_key, keypad)
        result += min(
            sequence_length(shortest_path + "A", depth - 1)
            for shortest_path in shortest_paths
        )
    return result


def compute_complexity(code: str, number_of_keypads: int) -> int:
    return int(code.removeprefix("0").removesuffix("A")) * sequence_length(
        code, number_of_keypads
    )


if __name__ == "__main__":
    # data = get_data("input_test.txt")
    data = get_data("input.txt")
    print("data:", data)
    # number of keypads : 1 numeric keypad, 2 Directional Keypad operated by robots, 1 directional keypad operated by a human
    print(sum(compute_complexity(code, 1 + 2 + 1) for code in data))

# for input_test.txt expected 126384
