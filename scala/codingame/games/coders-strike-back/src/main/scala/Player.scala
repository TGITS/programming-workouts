import math._
import scala.util._

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
object Player extends App {

    // game loop
    while(true) {
        // nextcheckpointx: x position of the next check point
        // nextcheckpointy: y position of the next check point
        // nextcheckpointdist: distance to the next checkpoint
        // nextcheckpointangle: angle between your pod orientation and the direction of the next checkpoint
        val Array(x, y, nextcheckpointx, nextcheckpointy, nextcheckpointdist, nextcheckpointangle) = for(i <- readLine split " ") yield i.toInt
        val Array(opponentx, opponenty) = for(i <- readLine split " ") yield i.toInt
        
        // Write an action using println
        // To debug: Console.err.println("Debug messages...")
        

        // You have to output the target position
        // followed by the power (0 <= thrust <= 100) or "BOOST" or "SHIELD"
        // i.e.: "x y thrust"
        println(nextcheckpointx + " " + nextcheckpointy + " 80")
    }
}