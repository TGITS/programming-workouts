import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import scala.io.StdIn

/**
  * Auto-generated code below aims at helping you parse
  * the standard input according to the problem statement.
  **/
object Solution extends App {
  val n = StdIn.readInt // the number of adjacency relations
  val edges = ArrayBuffer[Edge]()
  for (i <- 0 until n) {
    // xi: the ID of a person which is adjacent to yi
    // yi: the ID of a person which is adjacent to xi
    val Array(id1, id2) = for (id <- StdIn.readLine split " ") yield id.toInt
    edges += Edge(id1, id2) += Edge(id2, id1)
  }

  //Console.err.println(edges)

  val edgesSet = edges.toSet
  val nodesSet = edgesSet.flatMap(e => List(e.source, e.destination))

  val graph = Graph(Node.constructNodesList(nodesSet, edgesSet), edgesSet)

  //Console.err.println(graph)

  // Write an action using println
  // To debug: Console.err.println("Debug messages...")

  // The minimal amount of steps required to completely propagate the advertisement
  println(Graph.computeMinimalTime(graph).toString)
}

case class Edge(source: Int, destination: Int)

case class Node(content: Int, adjacency: Set[Int])

case class Graph(nodes: Set[Node], edges: Set[Edge]) {
  def numberOfNodes: Int = nodes.size

  def getNodeFromId(id: Int): Node = {
    this.nodes.filter(_.content == id).head
  }
}

object Node {
  def constructNodesList(nodes: Set[Int], edges: Set[Edge]): Set[Node] = {
    nodes.map(n => Node(n, edges.filter(e => e.source == n).map(e => e.destination)))
  }
}

object Graph {
  def computeAdjacencySets(graph: Graph, node: Node): List[Set[Node]] = {
    val result = ListBuffer[Set[Node]]()
    var currentSet = node.adjacency.map(graph.getNodeFromId(_))
    while (!currentSet.isEmpty) {
      result += currentSet
      currentSet = currentSet.flatMap(n => n.adjacency.map(graph.getNodeFromId(_))) -- (result.reduce(_ ++ _) + node)
    }
    result.toList
  }

  def computeAdjacencyReach(graph: Graph, node: Node): Int = {
    var setsOfAllNode = Set[Node](node)
    var number = 0
    var currentSet = node.adjacency.map(graph.getNodeFromId(_))
    while (!currentSet.isEmpty) {
      setsOfAllNode ++= currentSet
      number += 1
      currentSet = currentSet.flatMap(n => n.adjacency.map(graph.getNodeFromId(_))) -- setsOfAllNode
    }
    number
  }

  def computeMinimalTime(graph: Graph): Int = {
    graph.nodes.map(computeAdjacencyReach(graph, _)).min
  }
}