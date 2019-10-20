import sys
import math
from queue import Queue


class Graph:
    def __init__(self, vertex_list=[]):
        self.vertex_list = vertex_list

    def _create_node(self, index_node_1, index_node_2):
        if not self.vertex_list[index_node_1]:
            self.vertex_list[index_node_1] = Node(index_node_1)
        self.vertex_list[index_node_1].add_edge(index_node_2)

    def add_edge(self, v1, v2):
        self._create_node(v1, v2)
        self._create_node(v2, v1)

    def get_adjacent_vertices(self, v):
        return self.vertex_list[v].get_adjacent_vertices()

    def get_indegree(self, v):
        return self.vertex_list[v].get_indegree()

    def mark_node_as_gateway(self, v):
        self.vertex_list[v].mark_as_gateway()

    def build_distance_table(self, source_node_index):
        distance_table = {}

        for i in range(len(self.vertex_list)):
            distance_table[i] = (None, None)

        distance_table[source_node_index] = (0, source_node_index)

        queue = Queue()
        queue.put(source_node_index)

        while not queue.empty():
            current_node_index = queue.get()
            current_distance = distance_table[current_node_index][0]

            for neighbor_index in self.get_adjacent_vertices(current_node_index):
                if distance_table[neighbor_index][0] is None:
                    distance_table[neighbor_index] = (
                        current_distance + 1, current_node_index)

                    if len(self.get_adjacent_vertices(neighbor_index)) > 0:
                        queue.put(neighbor_index)

        return distance_table

    def compute_shortest_path(self, source_node_index, destination_node_index):
        distance_table = self.build_distance_table(source_node_index)

        path = [destination_node_index]

        previous_vertex = distance_table[destination_node_index][1]

        while previous_vertex is not None and previous_vertex is not source_node_index:
            path.insert(0, previous_vertex)
            previous_vertex = distance_table[previous_vertex][1]

        if previous_vertex is None:
            print('No shortest path from {} to {}'.format(
                source_node_index, destination_node_index), file=sys.stderr)
        else:
            path.insert(0, source_node_index)
            print('Shortest path from {} to {} is : {}'.format(
                source_node_index, destination_node_index, ' '.join([str(n) for n in path])), file=sys.stderr)

        return path

    def compute_edge_to_severe(self, skynet_agent_node_index):
        pathes = sorted([self.compute_shortest_path(
            skynet_agent_node_index, gateway.get_index()) for gateway in self.get_gateways()], key=len)
        if len(pathes) >= 1:
            selected_shortest_path = pathes[0]
            if len(selected_shortest_path) > 1:
                return "{} {}".format(selected_shortest_path[-2], selected_shortest_path[-1])

        return " "

    def get_gateways(self):
        return [v for v in self.vertex_list if v.is_gateway]

    def __str__(self):
        graph_as_string = ""
        for v in self.vertex_list:
            graph_as_string = graph_as_string + str(v) + "\n"
        return graph_as_string

    def __repr__(self):
        graph_as_string = ""
        for v in self.vertex_list:
            graph_as_string = graph_as_string + str(v) + "\n"
        return graph_as_string


class Node:
    def __init__(self, index):
        self.index = index
        self.adjacency_set = set()
        self.is_gateway = False

    def get_index(self):
        return self.index

    def add_edge(self, adjacent_node_index):
        self.adjacency_set.add(adjacent_node_index)

    def mark_as_gateway(self):
        self.is_gateway = True

    def get_adjacent_vertices(self):
        return self.adjacency_set

    def get_indegree(self, v):
        return len(self.adjacency_set)

    def __str__(self):
        return 'Node(index={}, adjacency set=[{}], isGateway={})'.format(self.index, ' '.join(map(str, self.adjacency_set)), self.is_gateway)

    def __repr__(self):
        return 'Node(index={}, adjacency set=[{}], isGateway={})'.format(self.index, ' '.join(map(str, self.adjacency_set)), self.is_gateway)


if __name__ == "__main__":
    # Auto-generated code below aims at helping you parse
    # the standard input according to the problem statement.

    # n: the total number of nodes in the level, including the gateways
    # l: the number of links
    # e: the number of exit gateways
    number_of_nodes, number_of_links, number_of_exits = [
        int(i) for i in input().split()]
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
        skynet_agent_node_index = int(input())

        print(graph.compute_edge_to_severe(skynet_agent_node_index))
