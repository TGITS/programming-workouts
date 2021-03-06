# https://www.codingame.com/training/hard/skynet-revolution-episode-2
import sys
from queue import Queue


class Node:
    def __init__(self, index):
        self.index = index
        self.adjacency_set = set()
        self.is_gateway = False

    def get_index(self):
        return self.index

    def add_edge(self, adjacent_vertex_index):
        self.adjacency_set.add(adjacent_vertex_index)

    def severe_edge(self, adjacent_vertex_index):
        self.adjacency_set.discard(adjacent_vertex_index)

    def mark_as_gateway(self):
        self.is_gateway = True

    def get_adjacent_vertices(self):
        return self.adjacency_set

    def get_indegree(self):
        return len(self.adjacency_set)

    def __str__(self):
        return "Node(index={}, adjacency set=[{}], isGateway={})".format(
            self.index, " ".join(map(str, self.adjacency_set)), self.is_gateway
        )


class Graph:
    def __init__(self, vertices=None):
        self.vertices = vertices if vertices else []

    def _create_vertex_from_index(self, index_vertex_1, index_vertex_2):
        if not self.vertices[index_vertex_1]:
            self.vertices[index_vertex_1] = Node(index_vertex_1)
        self.vertices[index_vertex_1].add_edge(index_vertex_2)

    def add_edge(self, v1, v2):
        self._create_vertex_from_index(v1, v2)
        self._create_vertex_from_index(v2, v1)

    def severe_edge(self, v1, v2):
        self.vertices[v1].severe_edge(v2)
        self.vertices[v2].severe_edge(v1)

    def get_adjacent_vertices(self, v):
        return self.vertices[v].get_adjacent_vertices()

    def is_gateway(self, v):
        return self.vertices[v].is_gateway

    def is_near_gateway(self, v):
        return (
            self.is_gateway(v)
            or len(
                [
                    node
                    for node in self.get_adjacent_vertices(v)
                    if self.is_gateway(node)
                ]
            )
            > 0
        )

    def get_gateway_indegree(self, v):
        return len(
            [
                node
                for node in self.get_adjacent_vertices(v)
                if self.is_gateway(node) or self.is_near_gateway(node)
            ]
        )

    def get_indegree(self, v):
        return self.vertices[v].get_indegree()

    def mark_node_as_gateway(self, v):
        self.vertices[v].mark_as_gateway()

    def build_distance_table(self, source_index):
        distance_table = {}

        for i in range(len(self.vertices)):
            distance_table[i] = (None, None)

        distance_table[source_index] = (0, source_index)

        queue = Queue()
        queue.put(source_index)

        while not queue.empty():
            current_vertex_index = queue.get()
            current_distance = distance_table[current_vertex_index][0]

            for neighbor_index in self.get_adjacent_vertices(current_vertex_index):
                if distance_table[neighbor_index][0] is None:
                    distance_table[neighbor_index] = (
                        current_distance + 1,
                        current_vertex_index,
                    )

                    if len(self.get_adjacent_vertices(neighbor_index)) > 0:
                        queue.put(neighbor_index)

        return distance_table

    def compute_shortest_path(self, source_index, destination_index):
        distance_table = self.build_distance_table(source_index)

        path = [destination_index]

        previous_vertex = distance_table[destination_index][1]

        while previous_vertex is not None and previous_vertex is not source_index:
            path.insert(0, previous_vertex)
            previous_vertex = distance_table[previous_vertex][1]

        if previous_vertex is None:
            print(
                "No shortest path from {} to {}".format(
                    source_index, destination_index
                ),
                file=sys.stderr,
            )
        else:
            path.insert(0, source_index)
            print(
                "Shortest path from {} to {} is : {}".format(
                    source_index, destination_index, " ".join([str(n) for n in path])
                ),
                file=sys.stderr,
            )

        return path

    def compute_edge_to_severe(self, skynet_agent_vertex_index):
        shortest_paths = filter(
            lambda vertices_list: len(vertices_list) > 1,
            [
                self.compute_shortest_path(
                    skynet_agent_vertex_index, gateway.get_index()
                )
                for gateway in self.get_gateways()
            ],
        )
        shortest_paths_sorted_by_penultimate_vertex_indegree = sorted(
            [shortest_path for shortest_path in shortest_paths],
            key=lambda shortest_path: graph.get_gateway_indegree(shortest_path[-2]),
            reverse=True,
        )
        print(
            "Shortest paths sorted by penultimate vertex indegree : {}".format(
                " ".join(
                    [
                        str(path)
                        for path in shortest_paths_sorted_by_penultimate_vertex_indegree
                    ]
                )
            ),
            file=sys.stderr,
        )
        shortest_paths_sorted_by_length = sorted(
            shortest_paths_sorted_by_penultimate_vertex_indegree, key=len
        )
        print(
            "Shortest paths sorted by length : {}".format(
                " ".join([str(path) for path in shortest_paths_sorted_by_length])
            ),
            file=sys.stderr,
        )
        selected_path = shortest_paths_sorted_by_length[0]
        self.severe_edge(selected_path[-2], selected_path[-1])
        return "{} {}".format(selected_path[-2], selected_path[-1])

    def get_gateways(self):
        return [v for v in self.vertices if v.is_gateway]

    def __str__(self):
        graph_as_string = ""
        for v in self.vertices:
            graph_as_string = graph_as_string + str(v) + "\n"
        return graph_as_string


if __name__ == "__main__":

    # n: the total number of nodes in the level, including the gateways
    # l: the number of links
    # e: the number of exit gateways
    number_of_nodes, number_of_links, number_of_exits = [
        int(i) for i in input().split()
    ]
    gateways = set()
    graph = Graph([None] * number_of_nodes)

    for i in range(number_of_links):
        # n1: N1 and N2 defines a link between these nodes
        index_node_1, index_node_2 = [int(j) for j in input().split()]
        graph.add_edge(index_node_1, index_node_2)

    for i in range(number_of_exits):
        ei = int(input())  # the index of a gateway node
        graph.mark_node_as_gateway(ei)
        gateways.add(ei)

    # game loop
    while True:
        # The index of the node on which the Skynet agent is positioned this turn
        skynet_agent_vertex_index = int(input())

        print(graph.compute_edge_to_severe(skynet_agent_vertex_index))
