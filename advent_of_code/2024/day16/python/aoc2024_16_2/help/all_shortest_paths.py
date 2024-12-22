# Adapted from https://www.geeksforgeeks.org/print-all-shortest-paths-between-given-source-and-destination-in-an-undirected-graph/

# The apporach is to do a Breadth First Traversal (BFS) for a graph. Below are the steps:
# 1.Start BFS traversal from source vertex.
# 2.While doing BFS, store the shortest distance to each of the other nodes and also maintain a parent vector for each of the nodes.
# 3.Make the parent of source node as “-1”. For each node, it will store all the parents for which it has the shortest distance from the source node.
# 4.Recover all the paths using parent array. At any instant, we will push one vertex in the path array and then call for all its parents.
# 5.If we encounter “-1” in the above steps, then it means a path has been found and can be stored in the paths array.

# On the initial code contributed by sanjeev2552

from sys import maxsize
from collections import deque


# Function to form edge between
# two vertices src and dest
def add_edge(adj: list[list[int]], src: int, dest: int) -> None:
    adj[src].append(dest)
    adj[dest].append(src)


# Function which finds all the paths
# and stores it in paths array
def find_paths(
    paths: list[list[int]], path: list[int], parent: list[list[int]], n: int, u: int
) -> None:
    # Base Case
    if u == -1:
        paths.append(path.copy())
        return

    # Loop for all the parents
    # of the given vertex
    for par in parent[u]:
        # Insert the current
        # vertex in path
        path.append(u)

        # Recursive call for its parent
        find_paths(paths, path, parent, n, par)

        # Remove the current vertex
        path.pop()


# Function which performs bfs
# from the given source vertex
def bfs(adj: list[list[int]], parent: list[list[int]], n: int, start: int) -> None:
    # dist will contain shortest distance
    # from start to every other vertex
    dist = [maxsize for _ in range(n)]
    q = deque()

    # Insert source vertex in queue and make
    # its parent -1 and distance 0
    q.append(start)
    parent[start] = [-1]
    dist[start] = 0

    # Until Queue is empty
    while q:
        u = q[0]
        q.popleft()
        for v in adj[u]:
            if dist[v] > dist[u] + 1:
                # A shorter distance is found
                # So erase all the previous parents
                # and insert new parent u in parent[v]
                dist[v] = dist[u] + 1
                q.append(v)
                parent[v].clear()
                parent[v].append(u)

            elif dist[v] == dist[u] + 1:
                # Another candidate parent for
                # shortest path found
                parent[v].append(u)


# Function which prints all the paths
# from start to end
def print_paths(adj: list[list[int]], n: int, start: int, end: int) -> None:
    paths = []
    path = []
    parent = [[] for _ in range(n)]

    # Function call to bfs
    bfs(adj, parent, n, start)

    # Function call to find_paths
    find_paths(paths, path, parent, n, end)
    for v in paths:
        # Since paths contain each
        # path in reverse order,
        # so reverse it
        v = reversed(v)

        # Print node for the current path
        for u in v:
            print(u, end=" ")
        print()


# Driver Code
if __name__ == "__main__":
    # Number of vertices
    n = 6

    # array of vectors is used
    # to store the graph
    # in the form of an adjacency list
    adj = [[] for _ in range(n)]

    # Given Graph
    add_edge(adj, 0, 1)
    add_edge(adj, 0, 2)
    add_edge(adj, 1, 3)
    add_edge(adj, 1, 4)
    add_edge(adj, 2, 3)
    add_edge(adj, 3, 5)
    add_edge(adj, 4, 5)

    # Given source and destination
    src = 0
    dest = n - 1

    # Function Call
    print_paths(adj, n, src, dest)
