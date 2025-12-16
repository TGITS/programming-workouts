from collections import deque
from scipy.optimize import linprog


def extract_data(filename):
    """Read raw data and transform them in a easily processable form"""
    with open(filename) as file:
        lines = file.read().splitlines()

    indicator_lights = None
    buttons = None
    joltage_requirements = None
    input = []

    for line in lines:
        split_line_part = line.split(" ")
        indicator_lights = split_line_part[0].strip("[]")
        joltage_requirements = tuple(
            int(x) for x in split_line_part[-1].strip("{}").split(",")
        )
        buttons = [
            [int(x) for x in button.strip("()").split(",")]
            for button in split_line_part[1:-1]
        ]
        input.append((indicator_lights, buttons, joltage_requirements))

    return input


def solve_part1(filename):
    input = extract_data(filename)
    total = 0
    for line in input:
        indicator_lights = line[0]
        buttons = line[1]
        final_state = [char == "#" for char in indicator_lights]
        state = [False] * len(final_state)

        q = deque(((state, 0),))
        visited = set(str(state))
        while q:
            current_state, depth = q.popleft()

            if current_state == final_state:
                total += depth
                break

            for button in buttons:
                new_state = current_state[:]
                for press in button:
                    new_state[press] = not new_state[press]

                state_to_add = new_state

                if str(state_to_add) not in visited:
                    visited.add(str(state_to_add))
                    q.append((state_to_add, depth + 1))

    return total


def solve_part2(filename):
    """
    solve AX = B where
    - A : effect of each button
    - X : how many times we press each button (what we search)
    - B : goal state (joltage requirements)
    We want to minimize the sum of X
    """
    input = extract_data(filename)

    total = 0
    for line in input:
        joltage_requirements = line[-1]
        buttons = line[1]
        X = [1] * len(buttons)
        A = [[i in m for m in buttons] for i in range(len(joltage_requirements))]
        total += linprog(X, A_eq=A, b_eq=joltage_requirements, integrality=True).fun

    return int(total)


def main():
    filename = "./data/input.txt"

    # Part 1
    print()
    print("Part 1 :", solve_part1(filename))  #  417 on my input data

    # Part 2
    print()
    print(
        "Part 2 :",
        solve_part2(filename),
    )  # 16765 on my input data


if __name__ == "__main__":
    main()
