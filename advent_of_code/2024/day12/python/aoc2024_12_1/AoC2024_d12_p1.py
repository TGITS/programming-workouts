from dataclasses import dataclass
from collections import namedtuple
from collections import deque

GardenPlot = namedtuple("GardenPlot", ["type", "x", "y"])


@dataclass
class Region:
    type: str
    garden_plots: list[GardenPlot]


def get_map(input_name: str) -> list[str]:
    map = []
    with open(input_name, "r") as input:
        for line in input:
            map.append(line.strip())
    return map


def initialize_garden_plots_to_process(map: list[str]) -> list[type[GardenPlot]]:
    y_max = len(map)
    x_max = len(map[0])
    garden_plots = deque()
    for y in range(y_max):
        for x in range(x_max):
            garden_plots.append(GardenPlot(type=map[y][x], x=x, y=y))
    return garden_plots


def find_garden_plot_neighbours(
    map: list[str], garden_plot: type[GardenPlot]
) -> deque[GardenPlot]:
    y_max = len(map)
    x_max = len(map[0])
    x = garden_plot.x
    y = garden_plot.y
    gp_type = garden_plot.type
    neighbours = deque()

    if garden_plot.x - 1 >= 0 and map[y][x - 1] == gp_type:
        neighbours.append(GardenPlot(type=gp_type, x=x - 1, y=y))

    if garden_plot.x + 1 < x_max and map[y][x + 1] == gp_type:
        neighbours.append(GardenPlot(type=gp_type, x=x + 1, y=y))

    if garden_plot.y - 1 >= 0 and map[y - 1][x] == gp_type:
        neighbours.append(GardenPlot(type=gp_type, x=x, y=y - 1))

    if garden_plot.y + 1 < y_max and map[y + 1][x] == gp_type:
        neighbours.append(GardenPlot(type=gp_type, x=x, y=y + 1))

    return neighbours


def count_garden_plot_neighbours(map: list[str], garden_plot: type[GardenPlot]) -> int:
    return len(find_garden_plot_neighbours(map, garden_plot))


def compute_regions(
    map: list[str],
    garden_plots_to_process: deque[type[GardenPlot]],
    processed_garden_plots: set[type[GardenPlot]],
) -> list[type[Region]]:
    regions = []
    while len(garden_plots_to_process):
        current_garden_plot = garden_plots_to_process.popleft()
        processed_garden_plots.add(current_garden_plot)
        current_type = current_garden_plot.type
        current_region = Region(type=current_type, garden_plots=[current_garden_plot])
        neighbours = find_garden_plot_neighbours(map, current_garden_plot)
        while len(neighbours) != 0:
            neighbour = neighbours.popleft()
            if not neighbour in processed_garden_plots:
                current_region.garden_plots.append(neighbour)
                processed_garden_plots.add(neighbour)
                garden_plots_to_process.remove(neighbour)
                neighbours.extend(find_garden_plot_neighbours(map, neighbour))
        regions.append(current_region)

    return regions


def compute_region_price(map: list[str], region: type[Region]) -> int:
    area = len(region.garden_plots)
    perimeter = 0
    for gp in region.garden_plots:
        neighbours_count = count_garden_plot_neighbours(map, gp)
        gp_perimeter = 4 - neighbours_count
        perimeter += gp_perimeter
    return area * perimeter


def compute_total_price(map: list[str], regions: list[type[Region]]) -> int:
    total_price = 0
    for region in regions:
        total_price += compute_region_price(map, region)
    return total_price


if __name__ == "__main__":
    # map = get_map("input_test.txt")
    # map = get_map("input_test0.txt")
    # map = get_map("input_test1.txt")
    map = get_map("input.txt")
    print("map:", map)
    garden_plots_to_process = initialize_garden_plots_to_process(map)
    print(garden_plots_to_process)
    garden_plots_to_process = initialize_garden_plots_to_process(map)
    processed_garden_plots = set()
    regions = compute_regions(map, garden_plots_to_process, processed_garden_plots)
    print(regions)
    print(compute_total_price(map, regions))


# input_test.txt => 140
# input_test0.txt => 772
# input_text1.txt => 1930
