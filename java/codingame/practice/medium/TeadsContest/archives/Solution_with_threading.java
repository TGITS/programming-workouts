import java.util.*;

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

    class GraphWorker implements Runnable {

        final private int start;
        final private int end;
        private int min;

        public GraphWorker(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            min = Graph.this.computeMinimalTime(start, end);
        }

        public int getMin() {
            return this.min;
        }
    }

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
        int numberOfNodes = nodes.length;
        if (numberOfNodes < 40) {
            return computeMinimalTime(0, numberOfNodes);
        } else {
            int steps = numberOfNodes < 100 ? 10 : 20;
            int division = numberOfNodes / steps;
            int remainder = numberOfNodes % steps;
            int start = 0;
            GraphWorker[] graphWorkers = new GraphWorker[steps];
            Thread[] threads = new Thread[steps];
            int[] results = new int[steps];
            for (int i = 0; i < steps; i++) {
                System.err.println("Creating thread n°" + i);
                if (i == steps - 1) {
                    graphWorkers[i] = new GraphWorker(start, start + division + remainder);
                } else {
                    graphWorkers[i] = new GraphWorker(start, start + division);
                }
                start += division;
                threads[i] = new Thread(graphWorkers[i]);
                System.err.println("Starting thread n°" + i);
                threads[i].start();
            }

            for (int i = 0; i < steps; i++) {
                System.err.println("Joining on thread n°" + i);
                try {
                    threads[i].join();
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
            }

            System.err.println("Collecting the result");
            for (int i = 0; i < steps; i++) {
                results[i] = graphWorkers[i].getMin();
            }
            System.err.println("Returning the result");
            return Arrays.stream(results).min().getAsInt();
        }
    }

    public int computeMinimalTime(int start, int end) {
        Node node;
        int result = Integer.MAX_VALUE;
        for (int i = start; i < end; i++) {
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