package codingame.temperatures

import math._
import scala.util._

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
object Solution extends App {
    val n = readInt // the number of temperatures to analyse
    Console.err.println("Number of temperatures " + n.toString)
    result = if (n > 0) {
        val temps = readLine // the n temperatures expressed as integers ranging from -273 to 5526
        Console.err.println("The list of temperatures as a string : " + temps)
        val temps_array = temps.split(" ")
        val temperatures = for (t <- temps_array) yield (t.toInt, math.abs(t.toInt))
        val sorted_temperatures = temperatures.sortWith { case ((t1, absT1), (t2, absT2))  => 
            if ( absT1 < absT2 ) true 
            else  if ( absT1 == absT2 && t1 > t2 ) true else false
        }
        //val sorted_temperatures = temperatures.sortBy(_._2)
        //scala.util.Sorting.stableSort(v)(manifest[(Char, Int)], Ordering.by(_.swap))
        //stableSort(v, (_._2 < _._2) : ((Char,Int),(Char,Int)) => Boolean)
        //val sorted_temperatures = scala.util.Sorting.stableSort(temperatures)((_._2 < _._2) : ((Int,Int),(Int,Int)) => Boolean, Ordering.by(_._1))
        Console.err.println("Sorted temperatures : " + sorted_temperatures.mkString(","))
        sorted_temperatures(0)._1.toString
    }
    else {
        "0"
    }
    
    println(result)
}
