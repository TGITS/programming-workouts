import networkx as nx


def get_data(input_name: str) -> list[str]:
    data = []
    with open(input_name, "r") as input:
        for line in input:
            data.append(line.strip())
    return data


def extract_edges(data: list[str]) -> list[tuple[str,str]]:
    return [line.split("-") for line in data]




if __name__ == "__main__":
    # data = get_data("input_test.txt")
    data = get_data("input.txt")
    print("data:", data)
    print()
    edges = extract_edges(data)
    graph = nx.Graph(edges)
    print(graph)
    # Returns all cliques in an undirected graph.
    # This function returns an iterator over cliques, each of which is a list of nodes. 
    # The iteration is ordered by cardinality of the cliques: first all cliques of size one, then all cliques of size two, etc.
    cliques = list(nx.enumerate_all_cliques(graph))
    print(cliques)
    # The largest clique is the last one
    largest_clique = cliques[-1]
    print(largest_clique)
    print(','.join(sorted(largest_clique)))