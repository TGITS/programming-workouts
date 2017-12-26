import math._
import scala.util._
import scala.io._

/**
  * Auto-generated code below aims at helping you parse
  * the standard input according to the problem statement.
  **/
object Solution extends App {
  val encrypt = StdIn.readLine
  //val pattern = """((00 0+ ?)|(0 0+ ?))+""".r
  Console.err.println("encrypt : " + encrypt)
  /*val decoded = encrypt match {
    case pattern(_*) => {*/
  val encryptToArray = encrypt.split(" ")
  val decoded = if (encryptToArray.size % 2 == 0) {
    val encryptToList = encryptToArray.sliding(2, 2).toList
    Console.err.println("encryptToList : " + encryptToList.mkString(" "))
    val binarySequence = encryptToList.foldLeft("") {
      (accumulator, current) => {
        Console.err.println("accumulator : " + accumulator)
        Console.err.println("current : " + current.mkString(";"))
        if (accumulator.equals("INVALID")) {
          "INVALID"
        } else {
          val value = current(0) match {
            case "0" => "1"
            case "00" => "0"
            case _ => "INVALID"
          }
          if (value.equals("INVALID")) {
            "INVALID"
          }
          else {
            accumulator + value * current(1).length
          }
        }
      }
    }
    Console.err.println("binarySequence : " + binarySequence.mkString(""))
    if (!binarySequence.equals("INVALID")) {
      if (binarySequence.length >= 7) {
        binarySequence.sliding(7, 7).map(it => Integer.parseInt(it, 2).toChar.toString).mkString("")
      }
      else {
        "INVALID"
      }
    } else {
      "INVALID"
    }
  } else {
    "INVALID"
  }
  /*}
  case _ => "INVALID"
}*/

  Console.err.println("decoded : " + decoded)
  println(decoded)
}