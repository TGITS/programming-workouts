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
    var vector: Vector[HorseStrength] = Vector()
    for(i <- 0 until n) {
        val Array(v, e) = for(i <- readLine split " ") yield i.toInt
        vector = vector :+ HorseStrength(v,e)
    }
    Console.err.println(vector.mkString(" "))
    val origin = HorseStrength(0,0)
    val sortedHorseStrengthes = vector.sortWith( (s1,s2) => s1.distance(origin) < s2.distance(origin))
    Console.err.println(sortedHorseStrengthes.mkString(" "))
    // Write an action using println
    // To debug: Console.err.println("Debug messages...")
    val differences = (sortedHorseStrengthes.tail zip sortedHorseStrengthes.dropRight(1)).map (x => x._1.distance(x._2))
    
    Console.err.println(differences.mkString(" "))
    println(differences.sorted.head.toString)
}

case class HorseStrength(var velocity: Int, var elegance: Int) {

    def distance(otherStrength: HorseStrength): Int = {
        //distance between two strength  
        //The distance between two strengths (V1,E1) and (V2,E2) is abs(V2-V1)+abs(E2-E1).
        Math.abs(otherStrength.velocity - this.velocity) + Math.abs(otherStrength.elegance - this.elegance)
    }
}