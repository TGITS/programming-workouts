import math._
import scala.collection.mutable.ListBuffer
import scala.io.StdIn
import scala.util._

/**
  * Auto-generated code below aims at helping you parse
  * the standard input according to the problem statement.
  **/
object Solution extends App {
  val n = StdIn.readInt
  val houses:ListBuffer[Coordinate] = ListBuffer()
  for(i <- 0 until n) {
    val Array(x, y) = for(i <- StdIn.readLine split " ") yield i.toInt
    houses += Coordinate(x,y)
  }

  Console.err.println("List of the coordinates of the houses : ")
  Console.err.println(houses)

  val minX = houses.minBy(c => Math.abs(c.x)).x
  val maxX = houses.maxBy(c => Math.abs(c.x)).x
  val minY = houses.minBy(c => Math.abs(c.y)).y
  val maxY = houses.maxBy(c => Math.abs(c.y)).y
  Console.err.println("minX : " + minX)
  Console.err.println("maxX : " + maxX)
  Console.err.println("minY : " + minY)
  Console.err.println("maxY : " + maxY)

  // Write an action using println
  // To debug: Console.err.println("Debug messages...")
  val mainCableLength = maxX - minX
  val answer = mainCableLength

  println(answer)
}

case class Coordinate(x:Int,y:Int)

object Tool {
  def taxicabDistance(c1:Coordinate,c2:Coordinate):Int = {
    Math.abs(c2.x - c1.x) + Math.abs(c2.y - c1.y)
  }
}