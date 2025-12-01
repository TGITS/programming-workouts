def extract_data(
    input_name: str, names_of_map: list[str]
) -> tuple[list[int], dict[str, dict[int, int]]]:
    with open(input_name, "r") as input:
        lines = input.readlines()
        max_lines = len(lines)
        current_line_index = 0
        # Reading seeds
        seeds = [
            int(n)
            for n in lines[current_line_index][len("seeds: ") - 1 :].strip().split(" ")
        ]
        # print(seeds)
        current_line_index += 1
        map_of_maps = {}
        for name in names_of_map:
            while not lines[current_line_index].startswith(name):
                current_line_index += 1
            # Reading next map
            current_line_index += 1  # We does not need the line with the map name
            while (
                current_line_index < max_lines
                and len(lines[current_line_index].strip()) != 0
            ):  # While there is line to process et we are not on a blank line
                # destination_range_start source_range_start range_length
                values = [int(n) for n in lines[current_line_index].split(" ")]
                current_content = map_of_maps.get(name, {})
                map_of_maps[name] = {
                    **current_content,
                    **create_source_to_destination_map(values[0], values[1], values[2]),
                }
                current_line_index += 1
        return (seeds, map_of_maps)


def create_source_to_destination_map(
    destination_range_start: int, source_range_start: int, range_length: int
) -> dict[int, int]:
    destination_range = range(
        destination_range_start, destination_range_start + range_length
    )
    source_range = range(source_range_start, source_range_start + range_length)
    source_to_destination = {}
    for i in range(0, range_length):
        source_to_destination[source_range[i]] = destination_range[i]
    return source_to_destination


def get_destination_from_source(
    source: int, map_name: str, map_of_maps: dict[str, dict[int, int]]
) -> int:
    source_to_destination_map = map_of_maps[map_name]
    return source_to_destination_map.get(source, source)


def get_location_from_seed(
    seed: int, names_of_map: list[str], map_of_maps: dict[str, dict[int, int]]
):
    index = seed
    for name in names_of_map:
        index = get_destination_from_source(index, name, map_of_maps)

    return index


def get_locations_from_seeds(
    seeds: list[int], names_of_map: list[str], map_of_maps: dict[str, dict[int, int]]
) -> list[int]:
    return [get_location_from_seed(seed, names_of_map, map_of_maps) for seed in seeds]


if __name__ == "__main__":
    names_of_map = [
        "seed-to-soil",
        "soil-to-fertilizer",
        "fertilizer-to-water",
        "water-to-light",
        "light-to-temperature",
        "temperature-to-humidity",
        "humidity-to-location",
    ]
    (seeds, map_of_maps) = extract_data("input.txt", names_of_map)
    print(seeds)
    # print(map_of_maps)
    locations = get_locations_from_seeds(seeds, names_of_map, map_of_maps)
    # print(locations)
    print(min(locations))
