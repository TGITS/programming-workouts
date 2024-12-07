import operator
import itertools


def extract_data(input_name: str) -> list[tuple[int, list[int]]]:
    first_separator = ": "
    second_separator = " "
    equations = []
    with open(input_name, "r") as input:
        for line in input:
            raw_line = line.strip().split(first_separator)
            expected_result = int(raw_line[0])
            numbers = [int(n) for n in raw_line[1].split(second_separator)]
            equations.append((expected_result, numbers))

    return equations


def equation_can_be_combined(equation: tuple[int, list[int]]) -> bool:
    expected_result = equation[0]
    computation = 0
    numbers = equation[1]
    operators_combinations = itertools.product(
        [operator.add, operator.mul, operator.concat], repeat=len(numbers) - 1
    )

    for operators_combination in operators_combinations:
        computation = numbers[0]
        for i, number in enumerate(numbers[1:]):
            if operators_combination[i] == operator.concat:
                computation = int(
                    operators_combination[i](str(computation), str(number))
                )
            else:
                computation = operators_combination[i](computation, number)
        if computation == expected_result:
            return True

    return False


def find_valid_equations(
    equations: list[tuple[int, list[int]]],
) -> list[tuple[int, list[int]]]:
    return [equation for equation in equations if equation_can_be_combined(equation)]


if __name__ == "__main__":
    equations = extract_data("input.txt")
    # print(equations)
    valid_equations = find_valid_equations(equations)
    print(valid_equations)
    print(sum(equation[0] for equation in valid_equations))
