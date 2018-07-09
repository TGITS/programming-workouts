import scala.collection.mutable._

/**
  * Auto-generated code below aims at helping you parse
  * the standard input according to the problem statement.
  **/
object Solution extends App {
  val n = scala.io.StdIn.readInt
  var strengthes: Vector[HorseStrength] = Vector()
  val mutableStrengthesBuffer: ListBuffer[HorseStrength] = ListBuffer()
  for (i <- 0 until n) {
    val Array(v, e) = for (i <- scala.io.StdIn.readLine split " ") yield i.toInt
    val hs = HorseStrength(v, e)
    strengthes = strengthes :+ hs
    mutableStrengthesBuffer += hs
  }

  //Console.err.println(differences.mkString(" "))

  var minimalDistances: ListBuffer[Int] = ListBuffer()
  for (hs <- strengthes) {
    mutableStrengthesBuffer -= hs
    if (mutableStrengthesBuffer.nonEmpty) {
      val minimalDistance = mutableStrengthesBuffer.map(x => x.distance(hs)).min
      minimalDistances += minimalDistance
    }
  }
  println(minimalDistances.min.toString)
}

case class HorseStrength(var velocity: Int, var elegance: Int) {

  def distance(otherStrength: HorseStrength): Int = {
    //distance between two strength
    //The distance between two strengths (V1,E1) and (V2,E2) is abs(V2-V1)+abs(E2-E1).
    Math.abs(otherStrength.velocity - this.velocity) + Math.abs(otherStrength.elegance - this.elegance)
  }
}