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
    antenna_1: tuple[int, int], antenna_2: tuple[int, int]
) -> tuple[tuple[int, int], tuple[int, int]]:
    x1 = antenna_1[0]
    y1 = antenna_1[1]
    x2 = antenna_2[0]
    y2 = antenna_2[1]
    dx = x2 - x1
    dy = y2 - y1

    return ((x1 - dx, y1 - dy), (x2 + dx, y2 + dy))


def is_inbound(coordinate: tuple[int, int], x_max: int, y_max: int) -> bool:
    return (coordinate[0] >= 0 and coordinate[0] < x_max) and (
        coordinate[1] >= 0 and coordinate[1] < y_max
    )


def find_unfiltered_antinodes_locations(
    antennas_coordinates_by_frequency: dict[str, list[tuple[int, int]]],
) -> set[tuple[int, int]]:
    antinodes_locations = set()
    for v in antennas_coordinates_by_frequency.values():
        antennas_combinations = itertools.combinations(v, 2)
        for combination in antennas_combinations:
            for pair in get_antinodes_for_pair(combination[0], combination[1]):
                antinodes_locations.add(pair)

    return antinodes_locations


def filter_antinodes_locations(
    unfiltered_antinodes_locations: set[tuple[int, int]],
    x_max: int,
    y_max: int,
) -> list[tuple[int, int]]:
    return [t for t in unfiltered_antinodes_locations if is_inbound(t, x_max, y_max)]


if __name__ == "__main__":
    for file in [
        "input_test0.txt",
        "input_test1.txt",
        "input_test2.txt",
        "input_test3.txt",
        "input_test.txt",
        "input.txt",
    ]:  # ,
        map_of_antennas = extract_data(file)
        print(map_of_antennas)
        y_max = len(map_of_antennas)
        x_max = len(map_of_antennas[0])
        print("x_max =", x_max, "y_max =", y_max)
        antennas_coordinates_by_frequency = extract_antennas_coordinates_by_frequency(
            map_of_antennas
        )
        # print(antennas_coordinates)
        unfiltered_antinodes_locations = find_unfiltered_antinodes_locations(
            antennas_coordinates_by_frequency
        )
        # print(unfiltered_antinodes_locations)
        filtered_antinodes_locations = filter_antinodes_locations(
            unfiltered_antinodes_locations, x_max, y_max
        )
        print(filtered_antinodes_locations)
        print(len(filtered_antinodes_locations))

