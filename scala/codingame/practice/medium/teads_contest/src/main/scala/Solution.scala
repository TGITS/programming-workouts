import scala.collection.mutable.{Set,Map}
import scala.io.StdIn

/**
  * Auto-generated code below aims at helping you parse
  * the standard input according to the problem statement.
  **/
object Solution extends App {
  val n = StdIn.readInt // the number of adjacency relations
  val graph = Graph()
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

case class Graph() {

  private val nodes = Set[Int]()
  private val nodesById:Map[Int,Set[Int]] = Map[Int,Set[Int]]()
  def addEdges(source:Int,destination:Int):Unit = {
    def addOrUpdateNode(source:Int,destination:Int):Unit = {
      if(!nodesById.contains(source)) {
        nodesById.put(source,Set[Int](destination))
      }
      else {
        nodesById.get(source).get += destination
      }
    }

    addOrUpdateNode(source,destination)
    addOrUpdateNode(destination, source)
    nodes += source += destination
  }

  def computeAdjacencyReach(n:Int): Int = {
    var setOfAllNodes = Set[Int](n)
    var number = 0
    var currentSet = nodesById.get(n).getOrElse(Set[Int]())
    while (!currentSet.isEmpty) {
      setOfAllNodes ++= currentSet
      number += 1
      currentSet = currentSet.flatMap(n => this.nodesById.get(n).getOrElse(Set[Int]())) -- setOfAllNodes
    }
    number
  }

  def computeMinimalTime(): Int = {
    this.nodes.map(computeAdjacencyReach(_)).min
  }
}