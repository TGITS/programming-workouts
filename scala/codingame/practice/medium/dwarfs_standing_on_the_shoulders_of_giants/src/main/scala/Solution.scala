import scala.io._

/**
  * Auto-generated code below aims at helping you parse
  * the standard input according to the problem statement.
  **/
object Solution extends App {
  val n = StdIn.readInt // the number of relationships of influence
  var vector: Vector[Tuple2[Int, Int]] = Vector()
  for (i <- 0 until n) {
    // x: a relationship of influence between two people (x influences y)
    val Array(x, y) = for (i <- StdIn.readLine() split " ") yield i.toInt
    vector = vector :+ (x, y)
  }
  Console.err.println(vector.mkString(" ; "))
  val set = vector.flatMap(item => Vector(item._1, item._2)).toSet
  val setOfPersons = set.map(id => Person(id, Vector(), None))
  for (person <- setOfPersons) {
    val influenced = vector.filter(p => p._1 == person.id).map(p => p._2).toSet
    person.addInfluenced((setOfPersons.filter(p => influenced contains p.id)).toVector)
  }

  Console.err.println(setOfPersons.mkString(" ; "))

  // The number of people involved in the longest succession of influences
  println(setOfPersons.map(p => p.computeLongestInfluencedChainSize).max.toString)
}

case class Person(val id: Int, var influenced: Vector[Person], var longestInfluencedChainSize: Option[Int]) {
  def addInfluenced(vector: Vector[Person]): Unit = {
    influenced = influenced ++ vector
  }

  def computeLongestInfluencedChainSize(): Int = {
    if (!longestInfluencedChainSize.isEmpty) {
      longestInfluencedChainSize.get
    }
    else {
      if (influenced.isEmpty) {
        longestInfluencedChainSize = Some(1)
      } else {
        longestInfluencedChainSize = Some(1 + influenced.map(p => p.computeLongestInfluencedChainSize).max)
      }
      longestInfluencedChainSize.get
    }
  }
}
