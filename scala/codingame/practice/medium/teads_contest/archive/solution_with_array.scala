
//import scala.collection.mutable.Set
import scala.collection.immutable.Set
import scala.io.StdIn

/**
  * Auto-generated code below aims at helping you parse
  * the standard input according to the problem statement.
  **/
object Solution extends App {
  val n = StdIn.readInt // the number of adjacency relations
  val time0 = System.currentTimeMillis()
  //Console.err.println(time0)
  val graph = new Graph(n)
  for (i <- 0 until n) {
    // xi: the ID of a person which is adjacent to yi
    // yi: the ID of a person which is adjacent to xi
    val Array(id1, id2) = for (id <- StdIn.readLine split " ") yield id.toInt
    graph.addEdges(id1, id2)
  }
  val time1 = System.currentTimeMillis()
  //Console.err.println(time1)
  Console.err.println(time1 - time0)
  //Console.err.println(graph)

  // Write an action using println
  // To debug: Console.err.println("Debug messages...")

  // The minimal amount of steps required to completely propagate the advertisement
  val result = graph.computeMinimalTime()
  val time2 = System.currentTimeMillis()
  //Console.err.println(time2)
  Console.err.println(time2 - time1)
  println(result.toString)
}

class Graph(val adjacencyRelations: Int) {

  //private val nodesById: mutable.OpenHashMap[Int, Set[Int]] = new mutable.OpenHashMap[Int, Set[Int]](adjacencyRelations + 50)
  private val nodesById: Array[Set[Int]] = new Array[Set[Int]](adjacencyRelations + 3)
  private var nodes: Set[Int] = Set.empty[Int]

  def addEdges(source: Int, destination: Int): Unit = {
    def addOrUpdateNode(source: Int, destination: Int): Unit = {
      val adjacencySet = nodesById(source)
      if (adjacencySet == null) {
        nodesById(source) = Set[Int](destination)
      } else {
        nodesById(source) = adjacencySet + destination
      }
    }

    addOrUpdateNode(source, destination)
    addOrUpdateNode(destination, source)
    nodes = nodes + source + destination
  }

  def computeAdjacencyReachRec(n: Int): Int = {
    @annotation.tailrec
    def compute(n: Int, alreadyVisitedNodes: Set[Int], currentSetToProcess: Set[Int]): Int = {
      if (currentSetToProcess.isEmpty) {
        n
      }
      else {
        compute(n + 1, alreadyVisitedNodes ++ currentSetToProcess, currentSetToProcess.flatMap(n => this.nodesById(n)) -- (alreadyVisitedNodes ++ currentSetToProcess))
      }
    }

    compute(0, Set[Int](n), nodesById(n))
  }

  def computeMinimalTime(): Int = {
    //this.nodesById.keySet.par.map(computeAdjacencyReachRec(_)).min
    (for (n <- nodes if (nodesById(n) != null)) yield (computeAdjacencyReachRec(n))).min
  }
}