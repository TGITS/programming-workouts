import operator
import re
from typing import Callable
from collections import deque

operator_by_name = {"OR": operator.or_, "AND": operator.and_, "XOR": operator.xor}


def get_data(
    input_name: str,
) -> tuple[
    dict[str, int],
    dict[str, int],
    dict[str, int],
    deque[tuple[str, str, str, Callable[[bool, bool], bool], str]],
]:
    initial_values = {}
    x_entries = {}
    y_entries = {}
    computations = deque()

    with open(input_name, "r") as input:
        lines = input.readlines()
        nb_of_lines = len(lines)
        i = 0
        current_line = lines[i].strip()
        while len(current_line) != 0:
            var, val = current_line.split(": ")
            value = bool(int(val))
            initial_values[var] = value
            if re.match(r"x\d\d", var):
                x_entries[var] = value
            if re.match(r"y\d\d", var):
                y_entries[var] = value
            i += 1
            current_line = lines[i].strip()

        i += 1

        while i < nb_of_lines:
            line = lines[i].strip()
            operation, result = line.split(" -> ")
            op1, logical_operator, op2 = operation.split(" ")
            computations.append(
                (op1, op2, result, operator_by_name[logical_operator], logical_operator)
            )
            i += 1

    return initial_values, x_entries, y_entries, computations


def construct_graph(
    computations: deque[tuple[str, str, str, Callable[[bool, bool], bool], str]],
    graph_output_name: str,
):
    with open(graph_output_name, "w") as output:
        output.write("digraph {\n")
        output.write("\tAND [color = red]\n")
        output.write("\tOR [color = blue]\n")
        output.write("\tXOR [color = orange]\n")
        for computation in reversed(sorted(computations)):
            output.write(f"\t{computation[0]} -> {computation[4]}\n")
            output.write(f"\t{computation[1]} -> {computation[4]}\n")
            output.write(f"\t{computation[4]} -> {computation[2]}\n")
        output.write("}\n")


def find_output_wire(
    left: str,
    right: str,
    operation: str,
    computations: deque[tuple[str, str, str, Callable[[bool, bool], bool], str]],
) -> str | None:
    for computation in computations:
        if (
            computation[0] == left
            and computation[1] == right
            and computation[4] == operation
        ):
            return computation[2]

        if (
            computation[0] == right
            and computation[1] == left
            and computation[4] == operation
        ):
            return computation[2]

    return None


def full_adder_logic(
    x: str,
    y: str,
    c0: str | None,
    computations: deque[tuple[str, str, str, Callable[[bool, bool], bool], str]],
    swapped: list[str],
) -> tuple[str | None, str | None]:
    """
    Full Adder Logic:
    A full adder adds three one-bit numbers (X1, Y1, and carry-in C0) and outputs a sum bit (Z1) and a carry-out bit (C1).
    The logic for a full adder is as follows:
    - X1 XOR Y1 -> M1 (intermediate sum)
    - X1 AND Y1 -> N1 (intermediate carry)
    - C0 AND M1 -> R1 (carry for intermediate sum)
    - C0 XOR M1 -> Z1 (final sum)
    - R1 OR N1 -> C1 (final carry)

    Args:
    - x: input wire x
    - y: input wire y
    - c0: input carry
    - computations: list of the computations
    - swapped: list of swapped entries/wires

    Returns:
    - z1: final sum
    - c1: final carry

    References:
    - https://www.geeksforgeeks.org/full-adder/
    - https://www.geeksforgeeks.org/carry-look-ahead-adder/
    - https://en.wikipedia.org/wiki/Adder_(electronics)#Full_adder
    """

    # X1 XOR Y1 -> M1 (intermediate sum)
    m1 = find_output_wire(x, y, "XOR", computations)

    # X1 AND Y1 -> N1 (intermediate carry)
    n1 = find_output_wire(x, y, "AND", computations)

    assert m1 is not None, f"m1 is None for {x}, {y}"
    assert n1 is not None, f"n1 is None for {x}, {y}"

    if c0 is not None:
        # C0 AND M1 -> R1 (carry for intermediate sum)
        r1 = find_output_wire(c0, m1, "AND", computations)
        if not r1:
            n1, m1 = m1, n1
            swapped.append(m1)
            swapped.append(n1)
            r1 = find_output_wire(c0, m1, "AND", computations)

        # C0 XOR M1 -> Z1 (final sum)
        z1 = find_output_wire(c0, m1, "XOR", computations)

        if m1 and m1.startswith("z"):
            m1, z1 = z1, m1
            swapped.append(m1)
            swapped.append(z1)

        if n1 and n1.startswith("z"):
            n1, z1 = z1, n1
            swapped.append(n1)
            swapped.append(z1)

        if r1 and r1.startswith("z"):
            r1, z1 = z1, r1
            swapped.append(r1)
            swapped.append(z1)

        assert r1 is not None, f"r1 is None for {c0}, {m1}"
        assert n1 is not None, f"n1 is None for {c0}, {m1}"

        # R1 OR N1 -> C1 (final carry)
        c1 = find_output_wire(r1, n1, "OR", computations)
    else:
        z1 = m1
        c1 = n1

    return z1, c1


def find_swapped_wires(
    x_y_entries: tuple[str, str],
    computations: deque[tuple[str, str, str, Callable[[bool, bool], bool], str]],
) -> str:
    c0 = None  # carry
    swapped = []  # list of swapped wires
    for x, y in x_y_entries:
        z1, c1 = full_adder_logic(x, y, c0, computations, swapped)

        if c1 and c1.startswith("z") and c1 != "z45":  # z45 is for the final carry
            c1, z1 = z1, c1
            swapped.append(c1)
            swapped.append(z1)

        # update carry
        c0 = c1 if c1 else find_output_wire(x, y, "AND", computations)

    return ",".join(sorted(swapped))


if __name__ == "__main__":
    computations_values, x_entries, y_entries, computations = get_data("input.txt")

    x_y_entries = zip(sorted(x_entries.keys()), sorted(y_entries.keys()))

    print(find_swapped_wires(x_y_entries, computations))
    # construct_graph(computations, "graph_of_computations.dot")
