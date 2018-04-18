import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, HashMap, Map, Set}
import scala.io.StdIn

/**
  * Auto-generated code below aims at helping you parse
  * the standard input according to the problem statement.
  **/
object Solution extends App {
  val n = StdIn.readInt // the number of adjacency relations
  val graph = Graph(n)
  for (i <- 0 until n) {
    // xi: the ID of a person which is adjacent to yi
    // yi: the ID of a person which is adjacent to xi
    val Array(id1, id2) = for (id <- StdIn.readLine split " ") yield id.toInt
    graph.addEdges(id1,id2)
  }

  //Console.err.println(graph)

  // Write an action using println
  // To debug: Console.err.println("Debug messages...")

  // The minimal amount of steps required to completely propagate the advertisement
  println(graph.computeMinimalTime().toString)
}

case class Graph(adjacencyRelations:Int) {

  private val nodes = Set[Int]()
  private val nodesById:mutable.LongMap[Set[Int]] = new mutable.LongMap[Set[Int]](adjacencyRelations+1)
  def addEdges(source:Int,destination:Int):Unit = {
    def addOrUpdateNode(source:Int,destination:Int):Unit = {
      if(!nodesById.contains(source)) {
        nodesById += (source.toLong -> Set[Int](destination))
      }
      else {
        nodesById.getOrNull(source) += destination
      }
    }

    addOrUpdateNode(source,destination)
    addOrUpdateNode(destination, source)
    nodes += source += destination
    //addNodes(source)
    //addNodes(destination)
  }

//  def addNodes(n:Int):Unit = {
//    if(!nodes.contains(n)) nodes += n
//  }

  def computeAdjacencyReach(n:Int): Int = {
    val setOfAllNodes = Set[Int](n)
    var number = 0
    var currentSet = nodesById.get(n).get
    while (!currentSet.isEmpty) {
      setOfAllNodes ++= currentSet
      number += 1
      currentSet = currentSet.flatMap(n => this.nodesById.get(n).get) -- setOfAllNodes
    }
    number
  }

  def computeMinimalTime(): Int = {
    this.nodes.map(computeAdjacencyReach(_)).min
  }
}