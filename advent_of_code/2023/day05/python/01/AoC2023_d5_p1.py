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
        ranges_by_map_name = {}
        for name in names_of_map:
            while not lines[current_line_index].startswith(name):
                current_line_index += 1
            # Reading next map
            current_line_index += 1  # We does not need the line with the map name
            while (
                current_line_index < max_lines
                and len(lines[current_line_index].strip()) != 0
            ):  # While there is line to process and we are not on a blank line
                # destination_range_start source_range_start range_length
                values = [int(n) for n in lines[current_line_index].split(" ")]
                current_content = ranges_by_map_name.get(name, [])
                # destination_range_start, source_range_start, range_length
                current_content.append((values[0], values[1], values[2]))
                ranges_by_map_name[name] = current_content
                current_line_index += 1
        return (seeds, ranges_by_map_name)

def get_destination_from_source(
    source: int, map_name: str, ranges_by_map_name: dict[str, list[tuple[int, int, int]]]
) -> int:
    map_ranges = ranges_by_map_name[map_name]
    for map_range in map_ranges:
        destination_range_start = map_range[0]
        source_range_start = map_range[1]
        range_length = map_range[2]
        if destination_range_start <= source < destination_range_start + range_length:
            return source_range_start + (source - destination_range_start)
    return source


def get_location_from_seed(
    seed: int, names_of_map: list[str], ranges_by_map_name: dict[str, list[tuple[int, int, int]]]
):
    index = seed
    for name in names_of_map:
        index = get_destination_from_source(index, name, ranges_by_map_name)

    return index


def get_locations_from_seeds(
    seeds: list[int], names_of_map: list[str], ranges_by_map_name: dict[str, dict[int, int]]
) -> list[int]:
    return [get_location_from_seed(seed, names_of_map, ranges_by_map_name) for seed in seeds]


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
    input_file_name ="input_test.txt"
    # input_file_name ="input.txt"
    (seeds, ranges_by_map_name) = extract_data(input_file_name, names_of_map)
    # print(seeds)
    # print(map_of_maps)
    locations = get_locations_from_seeds(seeds, names_of_map, ranges_by_map_name)
    # print(locations)
    print(min(locations))
