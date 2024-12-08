import itertools


def extract_data(input_name: str) -> list[str]:
    map_of_antennas = []
    with open(input_name, "r") as input:
        for line in input:
            map_of_antennas.append(line.strip())
    return map_of_antennas


def extract_antennas_coordinates_by_frequency(
    map_of_antennas: list[str],
) -> dict[str, list[tuple[int, int]]]:
    y_max = len(map_of_antennas)
    x_max = len(map_of_antennas[0])
    antennas_coordinates_by_frequency = {}
    for y in range(y_max):
        for x in range(x_max):
            current_cell = map_of_antennas[y][x]
            if current_cell != ".":
                antennas_coordinates = antennas_coordinates_by_frequency.get(
                    current_cell, []
                )
                antennas_coordinates.append((x, y))
                antennas_coordinates_by_frequency[current_cell] = antennas_coordinates
    return antennas_coordinates_by_frequency


def get_antennas_coordinates(
    antennas_coordinates_by_frequency: dict[str, list[tuple[int, int]]],
) -> set[tuple[int, int]]:
    antennas_coordinates = set()
    for v in antennas_coordinates_by_frequency.values():
        for t in v:
            antennas_coordinates.add(t)
    return antennas_coordinates


def get_antinodes_for_pair(
    antenna_1: tuple[int, int], antenna_2: tuple[int, int], x_max: int, y_max: int
) -> set[tuple[int, int], tuple[int, int]]:
    x1 = antenna_1[0]
    y1 = antenna_1[1]
    x2 = antenna_2[0]
    y2 = antenna_2[1]
    dx = x2 - x1
    dy = y2 - y1
    antinodes = set()
    antinodes.add((x1, y1))
    antinodes.add((x2, y2))

    factor = 1
    current_antinode = (x1 - factor * dx, y1 - factor * dy)
    while is_inbound(current_antinode, x_max, y_max):
        antinodes.add(current_antinode)
        factor += 1
        current_antinode = (x1 - factor * dx, y1 - factor * dy)

    factor = 1
    current_antinode = (x2 + factor * dx, y2 + factor * dy)
    while is_inbound(current_antinode, x_max, y_max):
        antinodes.add(current_antinode)
        factor += 1
        current_antinode = (x2 + factor * dx, y2 + factor * dy)

    return antinodes


def is_inbound(coordinate: tuple[int, int], x_max: int, y_max: int) -> bool:
    return (coordinate[0] >= 0 and coordinate[0] < x_max) and (
        coordinate[1] >= 0 and coordinate[1] < y_max
    )


def find_antinodes_locations(
    antennas_coordinates_by_frequency: dict[str, list[tuple[int, int]]],
    x_max: int,
    y_max: int,
) -> set[tuple[int, int]]:
    antinodes_locations = set()
    for v in antennas_coordinates_by_frequency.values():
        antennas_combinations = itertools.combinations(v, 2)
        for combination in antennas_combinations:
            for pair in get_antinodes_for_pair(
                combination[0], combination[1], x_max, y_max
            ):
                antinodes_locations.add(pair)

    return antinodes_locations


if __name__ == "__main__":
    for file in [
        "input_test1.txt",
        "input_test.txt",
        "input.txt"
    ]:
        map_of_antennas = extract_data(file)
        print(map_of_antennas)
        y_max = len(map_of_antennas)
        x_max = len(map_of_antennas[0])
        print("x_max =", x_max, "y_max =", y_max)
        antennas_coordinates_by_frequency = extract_antennas_coordinates_by_frequency(
            map_of_antennas
        )
        antinodes_locations = find_antinodes_locations(
            antennas_coordinates_by_frequency, x_max, y_max
        )
        print(antinodes_locations)
        print(len(antinodes_locations))
