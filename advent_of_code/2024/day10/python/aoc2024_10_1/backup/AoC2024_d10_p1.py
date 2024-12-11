def extract_data(input_name: str) -> list[str]:
    lines = None
    with open(input_name, "r") as input:
        lines = [line.strip() for line in input.readlines()]

    return lines


def find_trailhead(map: list[str]) -> list[tuple[int, int]]:
    trailheads = []
    for y, line in enumerate(map):
        for x, cell in enumerate(line):
            if cell == "0":
                trailheads.append((x, y))
    return trailheads


def find_eligible_neighbours(
    node: tuple[int, int], map: list[str], x_max: int, y_max: int
) -> list[tuple[int, int]]:
    x = node[0]
    y = node[1]
    neighbours_list = []
    current_value = int(map[y][x])
    # UP -- x, y-1
    if y - 1 >= 0:
        if int(map[y - 1][x]) == current_value + 1:
            neighbours_list.append((x, y - 1))
    # DOWN -- x, y +1
    if y + 1 < y_max:
        if int(map[y + 1][x]) == current_value + 1:
            neighbours_list.append((x, y + 1))
    # LEFT -- x-1, y
    if x - 1 >= 0:
        if int(map[y][x - 1]) == current_value + 1:
            neighbours_list.append((x - 1, y))
    # RIGHT -- x+1, y
    if x + 1 < x_max:
        if int(map[y][x + 1]) == current_value + 1:
            neighbours_list.append((x + 1, y))

    return neighbours_list


def score_trailhead(
    node: tuple[int, int],
    map: list[str],
    x_max: int,
    y_max: int,
    explored_nodes: set[tuple[int, int]],
) -> int:
    """
    Find all the possible trails from a trailhead to deduce the score
    """
    x = node[0]
    y = node[1]
    if map[y][x] == "9":
        return 1
    sum = 0
    eligible_neighbours = find_eligible_neighbours(node, map, x_max, y_max)
    for neighbour in eligible_neighbours:
        # print("neighbour :", neighbour, " - ", map[neighbour[1]][neighbour[0]])
        if not neighbour in explored_nodes:
            sum += score_trailhead(neighbour, map, x_max, y_max, explored_nodes)
            explored_nodes.add(neighbour)

    return sum


def compute_total_score(
    trailheads: list[tuple[int, int]], map: list[str], x_max: int, y_max: int
) -> int:
    total_score = 0
    explored_nodes = set()
    for trailhead in trailheads:
        score = score_trailhead(trailhead, map, x_max, y_max, explored_nodes)
        print("score for ", trailhead, ":", score)
        total_score += score
    return total_score


if __name__ == "__main__":
    map = extract_data("input_test.txt")
    print(map)
    y_max = len(map)  # maximum numbers of lines
    x_max = len(map[0])  # maximum length of a line
    print("x_max:", x_max, "y_max:", y_max)
    trailheads = find_trailhead(map)
    print(trailheads)
    print(compute_total_score(trailheads, map, x_max, y_max))
