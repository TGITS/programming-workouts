import math._
import scala.util._
import scala.io._
import scala.collection.mutable.HashMap

case class MayanNumeral(width: Int, height: Int, representation: Array[String])

/**
  * Input
  * Line 1: the width W and height H of a mayan numeral.
  * H next lines: the ASCII representation of the 20 mayan numerals. Each line is (20 x L) characters long.
  * Next line: the amount of lines S1 of the first number.
  * S1 next lines: the first number, each line having L characters.
  * Next line: the amount of lines S2 of the second number.
  * S2 next lines: the second number, each line having L characters.
  * Last line: the operation to carry out: *, /, +, or -
  **/
object Solution extends App {
  val Array(w, h) = for (i <- StdIn.readLine split " ") yield i.toInt
  //val a = Array.ofDim[String](rows, cols)
  val lines = Array.ofDim[String](h, 20)
  val mayanNumeralsToDecimal: HashMap[MayanNumeral, Int] = HashMap()
  val decimalToMayanNumerals: HashMap[Int,MayanNumeral] = HashMap()
  for (i <- 0 until h) {
    val temp = StdIn.readLine.sliding(w, w).toArray
    for (j <- 0 until temp.length) {
      lines(i)(j) = temp(j)
    }
  }
  for (j <- 0 until 20) {
    val temp = Array.ofDim[String](h)
    for (i <- 0 until h) {
      temp(i) = lines(i)(j)
      Console.err.println(temp(i))
    }
    mayanNumeralsToDecimal.put(MayanNumeral(w, h, temp), j)
    decimalToMayanNumerals.put(j,MayanNumeral(w, h, temp))
  }

  val s1 = StdIn.readInt
  val numberOfComponents1 = s1 / h
  val maxExponents1 = numberOfComponents1 - 1
  val mayanNumber1 = Array.ofDim[MayanNumeral](numberOfComponents1)
  for (i <- 0 until numberOfComponents1) {
    val temp = Array.ofDim[String](h)
    for (j <- 0 until h) {
      temp(j) = StdIn.readLine
    }
    mayanNumber1(i) = MayanNumeral(w, h, temp)
  }
  val s2 = StdIn.readInt
  val numberOfComponents2 = s2 / h
  val maxExponents2 = numberOfComponents2 - 1
  val mayanNumber2 = Array.ofDim[MayanNumeral](s2 / h)
  for (i <- 0 until numberOfComponents2) {
    val temp = Array.ofDim[String](h)
    for (j <- 0 until h) {
      temp(j) = StdIn.readLine
    }
    mayanNumber2(i) = MayanNumeral(w, h, temp)
  }
  val operation = StdIn.readLine

  // Write an action using println
  // To debug: Console.err.println("Debug messages...")

  println("result")
}