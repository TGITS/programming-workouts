import java.util.*;
import java.util.stream.Collectors;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        final long time0 = System.currentTimeMillis();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // the number of adjacency relations
        Graph graph = new Graph(n);
        final long time1 = System.currentTimeMillis();
        System.err.println("Duration to create the graph in ms: " + (time1 - time0));
        for (int i = 0; i < n; i++) {
            int xi = in.nextInt(); // the ID of a person which is adjacent to yi
            int yi = in.nextInt(); // the ID of a person which is adjacent to xi
            Node n1 = graph.addNode(xi);
            Node n2 = graph.addNode(yi);
            n1.addAdjacencyNode(n2);
            n2.addAdjacencyNode(n1);
        }
        final long time2 = System.currentTimeMillis();
        System.err.println("Duration to read and add the nodes to the graph in ms: " + (time2 - time1));
        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");
        int result = graph.erodeTheGraph();
        final long time3 = System.currentTimeMillis();
        System.err.println("Duration to compute the minimal time in ms: " + (time3 - time2));
        // The minimal amount of steps required to completely propagate the advertisement
        System.out.println(result);
    }
}

class Node {
    private final int id;
    private final Set<Node> adjacencySet = new HashSet<>();

    public Node(int id) {
        this.id = id;
    }

    public void addAdjacencyNode(Node n) {
        adjacencySet.add(n);
    }

    public int adjacencySetSize() {
        return this.adjacencySet.size();
    }

    public Set<Node> getAdjacencySet() {
        return this.adjacencySet;
    }

    public void removeFromAdjacencySet(Set<Node> toDelete) {
        this.adjacencySet.removeAll(toDelete);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return id == node.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", adjacencySet=" + adjacencySet +
                '}';
    }
}

class Graph {
    private Node[] nodes;

    public Graph(int numberOfNodes) {
        nodes = new Node[numberOfNodes + 3];
    }

    public Node addNode(int id) {
        if (nodes[id] == null) {
            nodes[id] = new Node(id);
        }
        return nodes[id];
    }

    public Set<Node> findLeafs(Set<Node> nodesSet) {
        Set<Node> leafs = new HashSet<>();
        Iterator<Node> iterator = nodesSet.iterator();
        Node node;
        while (iterator.hasNext()) {
            node = iterator.next();
            if (node.adjacencySetSize() == 1) {
                leafs.add(node);
                iterator.remove();
            }
        }
        return leafs;
    }

    public int erodeTheGraph() {
        int result = 0;
        Set<Node> initialSetOfNodes = Arrays.stream(nodes).filter(n -> n != null).collect(Collectors.toSet());
        Set<Node> toDelete;
        Node temp;
        while (!initialSetOfNodes.isEmpty()) {
            toDelete = findLeafs(initialSetOfNodes);
            Iterator<Node> iterator = initialSetOfNodes.iterator();
            while (iterator.hasNext()) {
                temp = iterator.next();
                temp.removeFromAdjacencySet(toDelete);
                if (temp.adjacencySetSize() == 0) {
                    iterator.remove();
                }
            }
            result += 1;
        }
        return result;
    }
}