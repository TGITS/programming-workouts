import math._
import scala.util._
import scala.Console._
import scala.io._
import scala.collection.mutable.{HashMap, ListBuffer}

object Evaluator {
  def mayanNumerals2Long(numbers: Array[String], mayanNumeralsToDecimal: HashMap[String, Long]): Long = numbers.zipWithIndex.foldLeft(0.toLong) {
    (accumulator, number) => {
      err.println("mayanNumerals2Long - accumulator : " + accumulator.toString)
      err.println("mayanNumerals2Long - number._1 : " + number._1.toString)
      err.println("mayanNumerals2Long - number._2 : " + number._2.toString)
      accumulator + (mayanNumeralsToDecimal(number._1).toDouble * pow(20, number._2)).toLong
    }
  }

  /**
    * Soit (n)en base 10 appartenant à N et à convertir en base b : (n) en base 10 = (sk−1 . . . s1s0) en base b
    * On utilise la division euclidienne, encore appelée division entière.
    * 1) on effectue la division entière de n par b :
    *    n = d1 × b + r1, on garde s0 = r1
    * 2) on effectue la division entière de d1 par b :
    *    d1 = d2 × b + r2, on garde s1 = r2
    * ...
    * k-1) on effectue la division entière de dk−2 par b :
    *      dk−2 = dk−1 × b + rk−1, on garde sk−2 = rk−1
    * k) quand dk−1 2 {0, 1, . . . , b − 1}, sk−1 = dk−1 est le reste
    * On détermine d’abord les digits de faible poids et ensuite les digits de poids fort.
    **/
  def long2MayanNumerals(number: Long, decimalToMayanNumerals: HashMap[Long, String]): List[String] = {
    val inBase20Decomposition = ListBuffer[Long]()
    err.println("long2MayanNumerals - initial value : " + number)
    if(number == 0 ) {
      inBase20Decomposition += 0
    }
    else {
      val base20: Long = 20
      var divisor: Long = number
      var remainder: Long = 0
      while (divisor > base20) {
        remainder = divisor % base20
        inBase20Decomposition += remainder
        divisor = divisor / base20
        err.println("long2MayanNumerals - remainder : " + remainder)
        err.println("long2MayanNumerals - divisor : " + divisor)
      }
      if (divisor > 0) {
        err.println("long2MayanNumerals - final remainder : " + divisor)
        inBase20Decomposition += divisor
      }
    }
    inBase20Decomposition.toList.map(decimalToMayanNumerals(_))
  }
}

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
  val lines = Array.ofDim[String](h, 20)
  val mayanNumeralsToDecimal: HashMap[String, Long] = HashMap()
  val decimalToMayanNumerals: HashMap[Long, String] = HashMap()
  for (i <- 0 until h) {
    val temp = StdIn.readLine.sliding(w, w).toArray
    for (j <- temp.indices) {
      lines(i)(j) = temp(j)
    }
  }
  for (j <- 0 until 20) {
    var temp = ""
    for (i <- 0 until h) {
      temp += lines(i)(j)
      err.println(temp)
    }
    mayanNumeralsToDecimal.put(temp, j.toLong)
    decimalToMayanNumerals.put(j.toLong, temp)
  }

  val s1 = StdIn.readInt
  val numberOfComponents1 = s1 / h
  val maxExponents1 = numberOfComponents1 - 1
  val mayanNumber1 = Array.ofDim[String](numberOfComponents1)
  for (i <- 0 until numberOfComponents1) {
    var temp = ""
    for (j <- 0 until h) {
      temp += StdIn.readLine
    }
    mayanNumber1(i) = temp
  }

  val s2 = StdIn.readInt
  val numberOfComponents2 = s2 / h
  val maxExponents2 = numberOfComponents2 - 1
  val mayanNumber2 = Array.ofDim[String](s2 / h)
  for (i <- 0 until numberOfComponents2) {
    var temp = ""
    for (j <- 0 until h) {
      temp += StdIn.readLine
    }
    mayanNumber2(i) = temp
  }

  val mayanNumber1ToLong = Evaluator.mayanNumerals2Long(mayanNumber1.reverse, mayanNumeralsToDecimal)
  err.println(mayanNumber1.mkString(" "))
  err.println("mayanNumber1ToLong : " + mayanNumber1ToLong.toString)
  val mayanNumber2ToLong = Evaluator.mayanNumerals2Long(mayanNumber2.reverse, mayanNumeralsToDecimal)
  err.println(mayanNumber2.mkString(" "))
  err.println("mayanNumber2ToLong : " + mayanNumber2ToLong.toString)
  val operation = StdIn.readLine
  err.println("operation : " + operation)
  val intermediateResult = operation match {
    case "+" => mayanNumber1ToLong + mayanNumber2ToLong
    case "-" => mayanNumber1ToLong - mayanNumber2ToLong
    case "/" => mayanNumber1ToLong / mayanNumber2ToLong
    case "*" => mayanNumber1ToLong * mayanNumber2ToLong
  }
  err.println("Intermediate result : " + intermediateResult.toString)
  val result = Evaluator.long2MayanNumerals(intermediateResult, decimalToMayanNumerals)

  // Write an action using println
  // To debug: Console.err.println("Debug messages...")

  for (elem <- result.reverse) {
    for (s <- elem.sliding(w,w)) {
      println(s)
    }
  }
}