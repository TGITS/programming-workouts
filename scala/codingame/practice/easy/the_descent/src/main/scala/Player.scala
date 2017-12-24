import math._
import scala.util._

/**
 * The while loop represents the game.
 * Each iteration represents a turn of the game
 * where you are given inputs (the heights of the mountains)
 * and where you have to print an output (the index of the mountain to fire on)
 * The inputs you are given are automatically updated according to your last actions.
 **/
object Player extends App {

    // game loop
    while(true) {
        // Write an action using println
        // To debug: Console.err.println("Debug messages...")
        val mountains = for (i <- 0 until 8) yield (i,readInt)
        println(mountains.sortWith(_._2 > _._2).head._1.toString) // The index of the mountain to fire on.
    }
}