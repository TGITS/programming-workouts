import networkx as nx
from functools import cache


def extract_data(filename):
    """Read raw data and transform them in a easily processable form"""
    with open(filename) as file:
        input = file.read().splitlines()

    nodes = dict()
    for line in input:
        node = line[0:3]
        edges = line[5:].split(" ")
        nodes[node] = edges

    graph = nx.DiGraph()
    graph.add_nodes_from(nodes.keys())
    for node, edges in nodes.items():
        for edge in edges:
            graph.add_edge(node, edge)

    return graph


def solve_part1(filename):
    graph = extract_data(filename)
    paths = list(nx.all_simple_paths(graph, "you", "out"))
    return len(paths)


def count_paths_between(graph, start_node, end_node):
    """
    Count unique paths from start_node to end_node using recursion and memoization.
    """

    @cache
    def _count(current):
        if (
            current == end_node
        ):  # On est arrivé à destination, cela doit être un chemin valide, on peut retourner la valeur
            return 1

        count = 0
        # Les arcs sortant doivent soit mener au noeud final soit mené à un noeud qui n'a pas de voisins
        for neighbor in graph.neighbors(current):
            count += _count(neighbor)

        return count

    return _count(start_node)


def count_paths_for_sequence(graph, sequence):
    """
    Count unique paths for a given sequence of nodes,
    i.e. a list of nodes where there must be paths between each pair.
    """
    total = 1
    for i in range(
        len(sequence) - 1
    ):  # On compte les chemins entre 2 pairs dans la séquence
        c = count_paths_between(graph, sequence[i], sequence[i + 1])
        total *= c
        if total == 0:  # Si le produit intermédiare vaut 0, pas la peine de continuer
            break
    return total


def solve_part2(filename):
    graph = extract_data(filename)

    sequences = [("svr", "dac", "fft", "out"), ("svr", "fft", "dac", "out")]

    total = 0
    for seq in sequences:
        total += count_paths_for_sequence(graph, seq)

    return total


def main():
    filename = "./data/input.txt"

    # Part 1
    print()
    print("Part 1:", solve_part1(filename))  # 466 on my input data

    # Part 2
    print()
    print(
        "Part 2:",
        solve_part2(filename),
    )  #  549705036748518on my input data


if __name__ == "__main__":
    main()
