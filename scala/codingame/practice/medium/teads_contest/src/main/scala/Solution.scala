import scala.collection.mutable
import scala.collection.mutable.Set
import scala.io.StdIn

/**
  * Auto-generated code below aims at helping you parse
  * the standard input according to the problem statement.
  **/
object Solution extends App {
  val n = StdIn.readInt // the number of adjacency relations
  val graph = new Graph(n)
  for (i <- 0 until n) {
    // xi: the ID of a person which is adjacent to yi
    // yi: the ID of a person which is adjacent to xi
    val Array(id1, id2) = for (id <- StdIn.readLine split " ") yield id.toInt
    graph.addEdges(id1, id2)
  }

  //Console.err.println(graph)

  // Write an action using println
  // To debug: Console.err.println("Debug messages...")

  // The minimal amount of steps required to completely propagate the advertisement
  println(graph.computeMinimalTime().toString)
}

class Graph(val adjacencyRelations: Int) {

  private val nodesById: mutable.OpenHashMap[Int, Set[Int]] = new mutable.OpenHashMap[Int, Set[Int]](adjacencyRelations + 50)

  def addEdges(source: Int, destination: Int): Unit = {
    def addOrUpdateNode(source: Int, destination: Int): Unit = {
      nodesById.getOrElseUpdate(source, Set[Int]()) += destination
    }

    addOrUpdateNode(source, destination)
    addOrUpdateNode(destination, source)
  }

  def computeAdjacencyReach(n: Int): Int = {
    val setOfAllNodes = Set[Int](n)
    var number = 0
    var currentSet = nodesById.getOrElse(n, Set.empty[Int])
    if ((!currentSet.isEmpty) || (currentSet.size > 1)) {
      while (!currentSet.isEmpty) {
        setOfAllNodes ++= currentSet
        number += 1
        currentSet = currentSet.flatMap(n => this.nodesById.getOrElse(n, Set.empty[Int])) -- setOfAllNodes
      }
      number
    } else {
      Int.MaxValue
    }
  }

  def computeMinimalTime(): Int = {
    //this.nodesById.keySet.map(computeAdjacencyReach(_)).min
    var min = Int.MaxValue
    //    nodesById.keySet.foreach( n => {
    //      val temp = computeAdjacencyReach(n)
    //      if(temp < min) {
    //        min = temp
    //      }
    //    })
    for (n <- nodesById.keySet) {
      val temp = computeAdjacencyReach(n)
      if (temp < min) {
        min = temp
      }
    }
    min
  }
}