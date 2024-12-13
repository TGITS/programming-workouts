# Il faut utiliser des résultats d'Algèbre Linéaire pour résoudre un système d"équations
#
# On a le système d'équation
# x_a*A + x_b*B = x_prize
# y_a*A + y_b*B = y_prize
#
# x_prize et y_prize sont les coordonnées du "Prize"
# x_a et y_a sont le déplacement effectué en x et en y pour A
# x_b et y_b sont le déplacement effectué en x et en y pour B
# On recherche A et B
# Si on a plusieurs couples A et B, il faut prendre le couple pour lequel (3*A + B) est minimum
#
# Les calculs à faire sont les suivants
# denominator = x_a*y_b - x_b*y_a
#
# A = (X*y_b - x_b*Y)/denominator
# B = (x_a*Y - X*y_a)/denominator
#
# numerator_a = X*y_b - x_b*Y
# numerator_b = x_a*Y - X*y_a
#
# On ne cherche que les solutions positives et entières


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


def minimum_tokens_to_prize(entry: Entry) -> tuple[int, int]:
    (a_x, a_y) = entry.a
    (b_x, b_y) = entry.b
    (X, Y) = entry.prize
    denominator = a_x * b_y - a_y * b_x
    # print("denominator:", denominator)
    if denominator == 0:
        return (0, 0)

    numerator_a = b_y * X - b_x * Y
    numerator_b = a_x * Y - a_y * X

    # print("numerator_a:", numerator_a)
    # print("numerator_b:", numerator_b)

    A = numerator_a / denominator
    B = numerator_b / denominator

    # print("A:", A)
    # print("B:", B)

    if A < 0 or B < 0 or not (A.is_integer() and B.is_integer()):
        return (0, 0)
    return (A, B)


def compute_minimal_cost(entries: list[Entry]) -> int:
    minimal_cost = 0
    for entry in entries:
        (a, b) = minimum_tokens_to_prize(entry)
        minimal_cost += int(a) * 3 + int(b)
    return minimal_cost


if __name__ == "__main__":
    # data = extract_data("input_test.txt")
    data = extract_data("input.txt")
    print(data)
    # factors_combinations = compute_factors_combinations()
    # print(factors_combinations)
    print(compute_minimal_cost(data))
