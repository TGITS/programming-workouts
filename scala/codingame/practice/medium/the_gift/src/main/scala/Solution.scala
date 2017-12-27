import scala.io._
import scala.Console._
import scala.collection.mutable.ListBuffer

object Util {
  def computeResult(price: Int, contributions: List[Int], accumulator: List[Int]): List[Int] = {
    err.println("contributions restantes : " + contributions.mkString(" "))
    err.println("somme restant à répartir : " + (price - accumulator.sum).toString)
    var mean = (price - accumulator.sum) / contributions.size
    err.println("moyenne : " + mean)
    var (smallerThanMean, largerThanMean) = contributions.partition(_ <= mean)
    if (contributions.sum == price - accumulator.sum) {
      err.println("Somme des contributions : " + contributions.sum.toString)
      err.println("somme restant à répartir : " + (price - accumulator.sum).toString)
      accumulator ++ contributions
    }
    else {
      if (smallerThanMean.isEmpty) {
        var remainder = (price - accumulator.sum) - (mean * contributions.size)
        err.println("Somme des contributions : " + contributions.sum.toString)
        err.println("somme restant à répartir : " + (price - accumulator.sum).toString)
        err.println("reste : " + remainder.toString)
        if (remainder == 0) {
          accumulator ++ (for (_ <- largerThanMean) yield mean)
        } else {
          var result: ListBuffer[Int] = ListBuffer()
          var i = largerThanMean.size
          while (i > 0) {
            if (remainder > 0) {
              result += (mean + 1)
              remainder -= 1
            } else {
              result += mean
            }
            i -= 1
          }
          accumulator ++ result.reverse.toList
        }
      }
      else {
        if (largerThanMean.lengthCompare(1) > 0) {
          mean = (price - accumulator.sum - smallerThanMean.sum) / largerThanMean.size
          err.println("moyenne : " + mean)
          err.println("En-dessous de la moyenne : " + smallerThanMean.mkString(" "))
          err.println("Au-dessus de la moyenne : " + largerThanMean.mkString(" "))
          err.println("Valeurs accumulées : " + accumulator.mkString(" "))
          computeResult(price, largerThanMean, accumulator ++ smallerThanMean)
        }
        else {
          val remainder = price - accumulator.sum - smallerThanMean.sum
          err.println("reste : " + mean)
          accumulator ++ smallerThanMean ++ List(remainder)
        }
      }
    }
  }
}

/**
  * Auto-generated code below aims at helping you parse
  * the standard input according to the problem statement.
  **/
object Solution extends App {

  val n = StdIn.readInt
  val c = StdIn.readInt
  var contributions = (for (i <- 0 until n) yield StdIn.readInt).sorted
  val sum = contributions.sum
  err.println("Somme des contributions : " + contributions.sum)
  err.println("Prix du cadeau : " + c)
  err.println("Nombre de participants : " + n)
  if (sum < c) {
    println("IMPOSSIBLE")
  } else {
    val result = Util.computeResult(c, contributions.toList, List())
    for (e <- result.sorted) {
      println(e.toString)
    }
  }
}