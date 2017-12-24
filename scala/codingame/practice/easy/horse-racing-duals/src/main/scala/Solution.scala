import math._
import scala.util._

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
object Solution extends App {
    val n = readInt
    //for(i <- 0 until n) {
    //    val pi = readInt
    //}
    val strengthes = for(i <- 0 until n) yield readInt
    Console.err.println(strengthes.mkString(" "))
    val sortedStrengthes = strengthes.sorted
    Console.err.println(sortedStrengthes.mkString(" "))
    // Write an action using println
    // To debug: Console.err.println("Debug messages...")
    val differences = (sortedStrengthes.tail zip sortedStrengthes.dropRight(1)).map (x=>x._1 - x._2)
    
    Console.err.println(differences.mkString(" "))
    println(differences.sorted.head.toString)
}