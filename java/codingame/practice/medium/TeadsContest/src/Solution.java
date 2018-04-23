import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

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
        System.err.println(time1 - time0);
        for (int i = 0; i < n; i++) {
            int xi = in.nextInt(); // the ID of a person which is adjacent to yi
            int yi = in.nextInt(); // the ID of a person which is adjacent to xi
            Node n1 = graph.addNode(xi);
            Node n2 = graph.addNode(yi);
            n1.addAdjacencyNode(n2);
            n2.addAdjacencyNode(n1);
        }
        final long time2 = System.currentTimeMillis();
        System.err.println(time2 - time1);
        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");
        int result = graph.computeMinimalTime();
        final long time3 = System.currentTimeMillis();
        System.err.println(time3 - time2);
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

    public int computeAdjacencyReach(Node n) {
        Set<Node> allVisitedNodes = new HashSet<>();
        int reach = 0;
        allVisitedNodes.add(n);
        Set<Node> nodesToProcess = n.getAdjacencySet();
        if (nodesToProcess != null && !nodesToProcess.isEmpty()) {
            Set<Node> temp;
            while (!nodesToProcess.isEmpty()) {
                reach++;
                allVisitedNodes.addAll(nodesToProcess);
                temp = new HashSet<>();
                for (Node subNode : nodesToProcess) {
                    temp.addAll(subNode.getAdjacencySet());
                }
                temp.removeAll(allVisitedNodes);
                nodesToProcess = temp;
            }
        }
        return reach;
    }

    public int computeMinimalTime() {
        Node node = null;
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < nodes.length; i++) {
            node = nodes[i];
            if (node != null && node.adjacencySetSize() > 1) {
                int value = computeAdjacencyReach(node);
                if (value < result) {
                    result = value;
                }
            }
        }
        return result;
    }
}