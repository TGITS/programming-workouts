import operator
import re
from typing import Callable
from collections import deque

operator_by_name = {"OR": operator.or_, "AND": operator.and_, "XOR": operator.xor}


def get_data(
    input_name: str,
) -> tuple[dict[str, int], deque[tuple[str, str, str, Callable[[bool, bool], bool]]]]:
    initial_values = {}
    computations = deque()

    with open(input_name, "r") as input:
        lines = input.readlines()
        nb_of_lines = len(lines)
        i = 0
        current_line = lines[i].strip()
        while len(current_line) != 0:
            var, val = current_line.split(": ")
            initial_values[var] = bool(int(val))
            i += 1
            current_line = lines[i].strip()

        i += 1

        while i < nb_of_lines:
            line = lines[i].strip()
            operation, result = line.split(" -> ")
            op1, logical_operator, op2 = operation.split(" ")
            computations.append((op1, op2, result, operator_by_name[logical_operator]))
            i += 1

    return initial_values, computations


def compute(
    computations_values: dict[str, bool],
    computations: deque[tuple[str, str, str, Callable[[bool, bool], bool]]],
) -> dict[str, bool]:
    result_values = {}
    while computations:
        current_computation = computations.popleft()
        if (
            current_computation[0] in computations_values.keys()
            and current_computation[1] in computations_values.keys()
        ):
            computation_result = current_computation[3](
                computations_values[current_computation[0]],
                computations_values[current_computation[1]],
            )
            computations_values[current_computation[2]] = computation_result
            if re.match(r"z\d\d", current_computation[2]):
                result_values[current_computation[2]] = computation_result
        else:
            # We cannot perform the operation this time, we replace the tuple in the queue
            computations.append(current_computation)

    return result_values


def get_number(result_values: dict[str, bool]) -> str:
    vars = reversed(sorted(result_values.keys()))
    binary_number = ""
    for var in vars:
        binary_number += str(int(result_values[var]))
    print(binary_number)
    return int(binary_number, 2)


if __name__ == "__main__":
    # computations_values,computations = get_data("input_test0.txt")
    # computations_values,computations = get_data("input_test.txt")
    computations_values, computations = get_data("input.txt")

    # print("Initial values:", computations_values)
    # print("Computations:", computations)

    result_values = compute(computations_values, computations)
    print(get_number(result_values))

# input_test0.txt => 4
# input_test.txt => 2024
# input.txt => 52038112429798 (1011110101010000010001001101001100011011100110)
