import math._
import scala.util._

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 * ---
 * Hint: You can use the debug stream to print initialTX and initialTY, if Thor seems not follow your orders.
 **/
object Player extends App {
    // lightx: the X position of the light of power
    // lighty: the Y position of the light of power
    // initialtx: Thor's starting X position
    // initialty: Thor's starting Y position
    val Array(lightx, lighty, initialtx, initialty) = for(i <- readLine split " ") yield i.toInt
    var dx = initialtx - lightx
    var dy = initialty - lighty
    var horizontalMove = ""
    var verticalMove = ""
    
    // game loop
    while(true) {
        val remainingturns = readInt // The remaining amount of turns Thor can move. Do not remove this line.
        
        // Write an action using println
        // To debug: Console.err.println("Debug messages...")
        horizontalMove = if (dx > 0) { dx = dx + 1; "W" } else if (dx < 0) { dx = dx - 1; "E" } else {""};
        verticalMove = if (dy > 0) { dy = dy - 1; "N" } else if (dy < 0) { dy = dy + 1; "S" } else {""};

        // A single line providing the move to be made: N NE E SE S SW W or NW
        println(verticalMove + horizontalMove)
    }
}