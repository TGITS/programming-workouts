from itertools import combinations
import math
import networkx as nx


def extract_data(filename):
    """Read raw data and transform them in a easily processable form"""
    with open(filename) as file:
        input = file.read().splitlines()

    junction_boxes = [tuple(int(x) for x in line.split(",")) for line in input]

    circuits = nx.Graph()
    circuits.add_nodes_from(junction_boxes)
    return junction_boxes, circuits


def compute_euclidian_distances(junctions):
    """
    Compute the euclidean distance between every pair of junctions.
    The result is stored in a tuple distance, first junction, second junction
    The tuples are stored in a list
    The list is sorted
    """
    distances = []
    for a, b in combinations(junctions, 2):
        distance = math.dist(a, b)
        distances.append((distance, a, b))
    distances.sort()
    return distances


def connect_junction_boxes(distances, circuits, limit):
    for d, a, b in distances[:limit]:
        circuits.add_edge(a, b, weight=d)
    return circuits


def solve_part1(circuits):
    sizes = [len(c) for c in nx.connected_components(circuits)]
    product = math.prod(sorted(sizes, reverse=True)[:3])
    return product


def solve_part2(distances, circuits, start):
    """Keep connecting the different part of the graph after part 1 until all the nodes in the graph are connected"""
    # Just for initializing the loop variables
    d, a, b = distances[start - 1]
    for d, a, b in distances[start:]:
        circuits.add_edge(a, b, weight=d)
        if nx.is_connected(circuits):  # all the nodes in the graph are connected
            break
    last_connect = a[0] * b[0]
    return last_connect


def main():
    filename = "./data/input.txt"
    connection_limit = 1000

    junctions, circuits = extract_data(filename)

    distances = compute_euclidian_distances(junctions)

    circuits = connect_junction_boxes(distances, circuits, connection_limit)

    # Part 1
    print()
    print("Part 1 size:", solve_part1(circuits))  # 135169 on my input data

    # Part 2
    print()
    print(
        "Part 2 last connect product :",
        solve_part2(distances, circuits, connection_limit),
    )  # 302133440 on my input data


if __name__ == "__main__":
    main()
