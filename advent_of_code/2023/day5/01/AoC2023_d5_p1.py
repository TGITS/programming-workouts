def extract_data(input_name: str):

    with open(input_name, "r") as input:
        lines = input.readlines()
        current_index = 0
        # Reading seeds
        seeds = [int(n) for n in lines[current_index][len("seeds: ")-1:].split(" ")]
        current_index += 1
        while not lines[current_index].startswith("seed-to-soil map:"):
            current_index += 1
        # Reading seed-to-soil map:
        current_index += 1 # We does not need the line with "seed-to-soil map"
        while len(lines[current_index]) != 0:
            # destination_range_start source_range_start range_length
            values = [int(n) for n in lines[current_index].split(" ")]
            seed_to_soil = create_source_to_destination_map(values[0], values[1], values[2])
        while not lines[current_index].startswith("soil-to-fertilizer map:"):
            current_index += 1   

def create_source_to_destination_map(destination_range_start: int, source_range_start: int, range_length: int) -> dict[int, int]:
    destination_range = range(destination_range_start, destination_range_start+range_length)
    source_range = range(source_range_start, source_range_start+range_length)
    source_to_destination = {}
    for i in range(0, range_length):
        source_to_destination[source_range[i]] = destination_range[i]
    return source_to_destination




if __name__ == "__main__":
    extract_data("input.txt")
    
