import heapq

NUMERIC_KEYPAD_BUTTON_COORDINATE = {
    "7": (0, 0),
    "8": (1, 0),
    "9": (2, 0),
    "4": (0, 1),
    "5": (1, 1),
    "6": (2, 1),
    "1": (0, 2),
    "2": (1, 2),
    "3": (2, 2),
    "0": (1, 3),
    "A": (2, 3),
}

DIRECTIONAL_KEYPAD_BUTTON_COORDINATE = {
    "^": (1, 0),
    "A": (2, 0),
    "<": (0, 1),
    "v": (1, 1),
    ">": (2, 1),
}


def get_data(input_name: str) -> list[str]:
    data = []
    with open(input_name, "r") as input:
        for line in input:
            data.append(line.strip())
    return data


def taxicab_distance(a: tuple[int, int], b: tuple[int, int]) -> int:
    return abs(b[0] - a[0]) + abs(b[1] - a[1])


def get_numeric_keypad_button_neighbour(button: str) -> list[tuple[str, str]]:
    if button == "A":
        return [("0", "<"), ("3", "^")]
    if button == "0":
        return [("2", "^"), ("A", ">")]
    if button == "3":
        return [("2", "<"), ("A", "v"), ("6", "^")]
    if button == "2":
        return [("1", "<"), ("0", "v"), ("5", "^"), ("3", ">")]
    if button == "1":
        return [("4", "^"), ("2", ">")]
    if button == "6":
        return [("5", "<"), ("3", "v"), ("9", "^")]
    if button == "5":
        return [("4", "<"), ("2", "v"), ("8", "^"), ("6", ">")]
    if button == "4":
        return [("7", "^"), ("1", "v"), ("5", ">")]
    if button == "9":
        return [("8", "<"), ("6", "v")]
    if button == "8":
        return [("7", "<"), ("5", "v"), ("9", ">")]
    if button == "7":
        return [("4", "v"), ("8", ">")]


def get_directional_keypad_button_neighbour(button: str) -> tuple[str, str]:
    if button == "A":
        return [("^", "<"), (">", "v")]
    if button == "^":
        return [("v", "v"), ("A", ">")]
    if button == ">":
        return [("v", "<"), ("A", "^")]
    if button == "v":
        return [("<", "<"), ("^", "^"), (">", ">")]
    if button == "<":
        return [("v", ">")]


def find_button_on_numeric_keypad(start_button: str, end_button: str) -> str:
    distance = taxicab_distance(
        NUMERIC_KEYPAD_BUTTON_COORDINATE[start_button],
        NUMERIC_KEYPAD_BUTTON_COORDINATE[end_button],
    )
    path = []
    to_explore = [(distance, (start_button, ""))]
    visited = set()
    current_button = None

    while to_explore and current_button != end_button:
        current_distance, current_tuple = heapq.heappop(to_explore)
        current_button = current_tuple[0]
        current_command = current_tuple[1]
        visited.add(current_button)
        path.append(current_command)
        neighbours = get_numeric_keypad_button_neighbour(current_button)
        for n in neighbours:
            if not n[0] in visited:
                current_distance = taxicab_distance(
                    NUMERIC_KEYPAD_BUTTON_COORDINATE[n[0]],
                    NUMERIC_KEYPAD_BUTTON_COORDINATE[end_button],
                )
                heapq.heappush(to_explore, (current_distance, n))

    return "".join(path)


def find_button_on_directional_keypad(start_button: str, end_button: str) -> str:
    distance = taxicab_distance(
        DIRECTIONAL_KEYPAD_BUTTON_COORDINATE[start_button],
        DIRECTIONAL_KEYPAD_BUTTON_COORDINATE[end_button],
    )
    path = []
    to_explore = [(distance, (start_button, ""))]
    visited = set()
    current_button = None

    while to_explore and current_button != end_button:
        current_distance, current_tuple = heapq.heappop(to_explore)
        current_button = current_tuple[0]
        current_command = current_tuple[1]
        visited.add(current_button)
        path.append(current_command)
        neighbours = get_directional_keypad_button_neighbour(current_button)
        for n in neighbours:
            if not n[0] in visited:
                current_distance = taxicab_distance(
                    DIRECTIONAL_KEYPAD_BUTTON_COORDINATE[n[0]],
                    DIRECTIONAL_KEYPAD_BUTTON_COORDINATE[end_button],
                )
                heapq.heappush(to_explore, (current_distance, n))

    return "".join(path)


def commands_for_numeric_pad(code: str) -> str:
    sequence_of_commands = ""
    start_button = "A"
    for button in code:
        sequence_of_commands += (
            find_button_on_numeric_keypad(start_button, button) + "A"
        )
        start_button = button
    return sequence_of_commands


def commands_for_directional_pad(code: str) -> str:
    sequence_of_commands = ""
    start_button = "A"
    for button in code:
        sequence_of_commands += (
            find_button_on_directional_keypad(start_button, button) + "A"
        )
        start_button = button
    return sequence_of_commands


def get_sequences(data: list[str]) -> dict[str, str]:
    sequences_by_codes = {}
    for code in data:
        first_sequence_of_commands = commands_for_numeric_pad(code)
        print("first sequence of commands for", code, ":", first_sequence_of_commands)

        second_sequence_of_commands = commands_for_directional_pad(
            first_sequence_of_commands
        )
        print("second sequence of commands for", code, ":", second_sequence_of_commands)

        third_sequence_of_commands = commands_for_directional_pad(
            second_sequence_of_commands
        )
        print("third sequence of commands for", code, ":", third_sequence_of_commands)

        sequences_by_codes[code] = third_sequence_of_commands
        print(code, ":", third_sequence_of_commands)

    return sequences_by_codes


def compute_complexity(sequences_by_codes: dict[str, str]) -> int:
    complexity = 0
    for k, v in sequences_by_codes.items():
        complexity += int(k.removeprefix("0").removesuffix("A")) * len(v)
    return complexity


if __name__ == "__main__":
    data = get_data("input_test.txt")
    # data = get_data("input.txt")
    print("data:", data)
    # command = ""
    # start_button = "A"

    # for button in data[0]:
    #     print(start_button, " - ", button)
    #     intermediate_command = find_button_on_numeric_keypad(start_button, button) + "A"
    #     command += intermediate_command
    #     print(intermediate_command)
    #     print(command)
    #     start_button = button

    # print(command)

    sequences_by_codes = get_sequences(data)
    complexity = compute_complexity(sequences_by_codes)
    # print(sequences_by_codes)
    print(complexity)
# input_test.txt => 126384
