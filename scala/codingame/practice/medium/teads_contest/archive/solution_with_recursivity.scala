import scala.collection.mutable.{MultiMap, OpenHashMap, Set}
//import scala.collection.immutable.Set
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

  private val nodesById = new OpenHashMap[Int, Set[Int]](adjacencyRelations + 50) with MultiMap[Int, Int]

  def addEdges(source: Int, destination: Int): Unit = {
    nodesById.addBinding(source, destination)
    nodesById.addBinding(destination, source)
  }

  def computeAdjacencyReachRec(n: Int): Int = {
    @annotation.tailrec
    def compute(n: Int, alreadyVisitedNodes: Set[Int], currentSetToProcess: Set[Int]): Int = {
      if (currentSetToProcess.isEmpty) {
        n
      }
      else {
        val temp = alreadyVisitedNodes ++ currentSetToProcess
        compute(n + 1, temp, currentSetToProcess.flatMap(n => this.nodesById.getOrElse(n, Set.empty[Int])) -- temp)
      }
    }

    compute(0, Set[Int](n), nodesById(n))
  }

  def computeMinimalTime(): Int = {
    this.nodesById.retain((_, v) => v.size > 1).keySet.map(computeAdjacencyReachRec(_)).min
  }
}