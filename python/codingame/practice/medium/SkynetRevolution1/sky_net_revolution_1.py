import sys
import math

class Node:
    def __init__(self, id):
        self.id = id
        self.adjacencies = set()
        self.is_gateway = False

    def addAdjacentNode(self, adjacent_node_index):
        self.adjacencies.add(adjacent_node_index)

    def markAsGateway(self):
        self.is_gateway = True

if __name__ == "__main__":
    # Auto-generated code below aims at helping you parse
    # the standard input according to the problem statement.

    # n: the total number of nodes in the level, including the gateways
    # l: the number of links
    # e: the number of exit gateways
    number_of_nodes, number_of_links, number_of_exits = [int(i) for i in input().split()]
    nodes = [None] * number_of_nodes
    for i in range(number_of_links):
        # n1: N1 and N2 defines a link between these nodes
        index_node_1, index_node_2 = [int(j) for j in input().split()]
        node_1 = nodes[index_node_1] if nodes[index_node_1] else Node(index_node_1)
        node_1.addAdjacentNode(index_node_2)
        nodes[index_node_1] = node_1
        node_2 = nodes[index_node_2] if nodes[index_node_2] else Node(index_node_2)
        node_2.addAdjacentNode(index_node_1);
        nodes[index_node_2] = node_2

    for i in range(number_of_exits):
        ei = int(input())  # the index of a gateway node
        nodes[ei].markAsGateway()

    # game loop
    while True:
        skynet_agent_node_index = int(input())  # The index of the node on which the Skynet agent is positioned this turn
        for node in nodes:
            print("node " + str(node), file=sys.stderr)
        # Write an action using print
        # To debug: print("Debug messages...", file=sys.stderr)


        # Example: 0 1 are the indices of the nodes you wish to sever the link between
        print("0 1")