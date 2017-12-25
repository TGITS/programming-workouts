import math._
import scala.util._
import scala.io._
import scala.collection.mutable._


/**
  * Auto-generated code below aims at helping you parse
  * the standard input according to the problem statement.
  **/
object Solution extends App {
  val message = StdIn.readLine
  Console.err.println(message)

  //val characters = for (c <- message) yield Char.char2int(c).toBinaryString
  val characters = message.flatMap(c => Char.char2int(c).toBinaryString.reverse.padTo(7,'0').reverse)
  Console.err.println(characters.mkString(" "))
  val answer = characters.foldLeft(List[String]("")) {
    (accumulator:List[String], currentValue:Char) => {
      Console.err.println("Accumulator : " + accumulator.mkString("|"))
      Console.err.println("Current Value : " + currentValue.toString)
      val lastElement = accumulator.lastOption.getOrElse("")
      Console.err.println("lastElement : " + lastElement)
      val newElement: List[String] = if (lastElement.isEmpty) {
        currentValue match {
          case '0' => List("00 0")
          case '1' => List("0 0")
          case _ => List(lastElement)
        }
      }
      else if (lastElement.startsWith("0 0")) {
        currentValue match {
          case '0' => List(lastElement) ::: List("00 0")
          case '1' => List(lastElement + "0")
          case _ => List(lastElement)
        }
      }
      else if (lastElement.startsWith("00 0")) {
        currentValue match {
          case '0' => List(lastElement + "0")
          case '1' => List(lastElement) ::: List("0 0")
          case _ => List(lastElement)
        }
      }
      else {
        List(lastElement)
      }
      accumulator.dropRight(1) ::: newElement
    }
  }
  println(answer.mkString(" "))
}