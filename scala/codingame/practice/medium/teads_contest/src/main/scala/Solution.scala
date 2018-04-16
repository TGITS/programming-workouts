import scala.collection.mutable.{ArrayBuffer, ListBuffer, Queue}
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

  Console.err.println(edges)

  val nodesSet = edges.flatMap(e => List(e.source, e.destination)).toSet
  val edgesSet = edges.toSet
  val graph = Graph(Node.constructNodesList(nodesSet, edgesSet), edgesSet.toList)

  Console.err.println(graph)

  // Write an action using println
  // To debug: Console.err.println("Debug messages...")

  // The minimal amount of steps required to completely propagate the advertisement
  println(Graph.computeMinimalTime(graph).toString)
}

case class Edge(source: Int, destination: Int)

case class Node(content: Int, adjacency: List[Int], var marked: Boolean, val circle: ListBuffer[List[Int]])

case class Graph(nodes: List[Node], edges: List[Edge]) {
  var bfsdByNodes = ArrayBuffer[Int]()

  def numberOfNodes: Int = nodes.size

  def getNodeFromId(id: Int): Node = {
    this.nodes.filter(_.content == id).head
  }

  def resetNodesToUnmarked(): Unit = {
    this.nodes.foreach(_.marked = false)
  }
}

object Node {
  def constructNodesList(nodes: Set[Int], edges: Set[Edge]): List[Node] = {
    val temporaryList = ListBuffer[Node]()
    for (n <- nodes) {
      temporaryList += Node(n, edges.filter(e => e.source == n).map(e => e.destination).toList, false, ListBuffer())
    }
    temporaryList.toList
  }
}

object Graph {
  def breadthFirstSearch(graph: Graph, initialNode: Node): List[Node] = {
    val nodes = Queue[Node]()
    var currentNode: Node = null
    val traversedNodes = ListBuffer[Node]()
    nodes.enqueue(initialNode)
    initialNode.marked = true
    while (!nodes.isEmpty) {
      currentNode = nodes.dequeue()
      traversedNodes += currentNode
      initialNode.circle += currentNode.adjacency
      currentNode.adjacency.map(graph.getNodeFromId(_)).foreach(n => {
        if (n.marked == false) {
          nodes.enqueue(n)
          n.marked = true
        }
      })
    }
    traversedNodes.toList
  }

  def computeAdjacencySets(graph: Graph, node: Node): List[Set[Node]] = {
    val result = ListBuffer[Set[Node]]()
    var currentSet = node.adjacency.map(graph.getNodeFromId(_)).toSet
    while (!currentSet.isEmpty) {
      result += currentSet
      currentSet = currentSet.flatMap(n => n.adjacency.map(graph.getNodeFromId(_)).toSet) -- (result.reduce(_ ++ _) + node)
    }
    result.toList
  }

  def computeMinimalTime(graph: Graph): Int = {
    graph.nodes.map(computeAdjacencySets(graph, _)).map(_.size).min
  }
}