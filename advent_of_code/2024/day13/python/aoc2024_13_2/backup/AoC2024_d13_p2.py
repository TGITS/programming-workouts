from dataclasses import dataclass

BUTTON_A = "Button A: "
BUTTON_B = "Button B: "
PRIZE = "Prize: "
BUTTON_A_TOKEN_COST = 3
BUTTON_B_TOKEN_COST = 1
OFFSET = 10000000000000


@dataclass
class Entry:
    a: tuple[int, int]
    b: tuple[int, int]
    prize: tuple[int, int]


def extract_data(input_name: str) -> list[type[Entry]]:
    data = []
    with open(input_name, "r") as input:
        lines = input.readlines()
        nb_of_lines = len(lines)

        i = 0
        while i < nb_of_lines:
            line = lines[i].strip()

            tmp = line.removeprefix(BUTTON_A)
            coord = tmp.split(", ")
            a_x = int(coord[0].split("+")[1])
            a_y = int(coord[1].split("+")[1])
            a = (a_x, a_y)
            i += 1

            line = lines[i].strip()
            tmp = line.removeprefix(BUTTON_B)
            coord = tmp.split(", ")
            b_x = int(coord[0].split("+")[1])
            b_y = int(coord[1].split("+")[1])
            b = (b_x, b_y)
            i += 1

            line = lines[i].strip()
            tmp = line.removeprefix(PRIZE)
            coord = tmp.split(", ")
            prize_x = int(coord[0].split("=")[1]) + OFFSET
            prize_y = int(coord[1].split("=")[1]) + OFFSET
            prize = (prize_x, prize_y)
            i += 2

            data.append(Entry(a=a, b=b, prize=prize))

    return data


def compute_factors_combinations():
    for i in range(0, 101 * OFFSET):
        for j in range(0, 101 * OFFSET):
            yield (i, j)


def compute_possible_winning_combinations_minimal_cost(entry: Entry) -> int:
    winning_combinations_cost = None
    for factors_combination in compute_factors_combinations():
        computed_x = (
            entry.a[0] * factors_combination[0] + entry.b[0] * factors_combination[1]
        )
        computed_y = (
            entry.a[1] * factors_combination[0] + entry.b[1] * factors_combination[1]
        )
        if computed_x == entry.prize[0] and computed_y == entry.prize[1]:
            cost = (
                factors_combination[0] * BUTTON_A_TOKEN_COST
                + factors_combination[1] * BUTTON_B_TOKEN_COST
            )
            if winning_combinations_cost is None or winning_combinations_cost > cost:
                winning_combinations_cost = cost
    if winning_combinations_cost is None:
        return 0
    else:
        return winning_combinations_cost


def compute_minimal_token_cost(data: list[Entry]) -> int:
    return sum(
        [compute_possible_winning_combinations_minimal_cost(entry) for entry in data]
    )


if __name__ == "__main__":
    data = extract_data("input_test.txt")
    # data = extract_data("input.txt")
    print(data)
    # factors_combinations = compute_factors_combinations()
    # print(factors_combinations)
    print(compute_minimal_token_cost(data))
