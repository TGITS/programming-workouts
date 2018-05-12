import scala.collection.mutable.ListBuffer
import scala.io.StdIn

/**
  * Auto-generated code below aims at helping you parse
  * the standard input according to the problem statement.
  **/
object Solution extends App {
  val n = StdIn.readInt
  val houses: ListBuffer[Coordinate] = ListBuffer()
  for (i <- 0 until n) {
    val Array(x, y) = for (i <- StdIn.readLine split " ") yield i.toLong
    houses += Coordinate(x, y)
  }

  Console.err.println("List of the coordinates of the houses : ")
  Console.err.println(houses)

  val minX = houses.minBy(_.x).x
  val maxX = houses.maxBy(_.x).x
  Console.err.println("minX : " + minX)
  Console.err.println("maxX : " + maxX)

  // Write an action using println
  // To debug: Console.err.println("Debug messages...")
  val mainCableLength:Long = Math.abs(maxX - minX)
  val yList:ListBuffer[Long] = houses.map(c => c.y).sorted
  val numberOfHouses = yList.size
  val medianY:Long = if (numberOfHouses % 2 == 0) {
    Math.ceil((yList(numberOfHouses/2 - 1) + yList(numberOfHouses/2)) / 2).toLong
  } else {
    yList((numberOfHouses - 1)/2)
  }

  val listOfDistances:ListBuffer[Long] = houses.map(c => Math.abs(c.y - medianY))
  val sumHousesFromMainCable:Long = listOfDistances.sum
  val answer:Long = mainCableLength + sumHousesFromMainCable

  Console.err.println("yList : " + yList)
  Console.err.println("numberOfHouses : " + numberOfHouses)
  Console.err.println("medianY : " + medianY)
  Console.err.println("listOfDistances : " + listOfDistances)
  Console.err.println("mainCableLength : " + mainCableLength)
  Console.err.println("sumHousesFromMainCable : " + sumHousesFromMainCable)

  println(answer)
}

case class Coordinate(x: Long, y: Long)