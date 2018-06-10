import java.util.*
import java.io.*
import java.math.*

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 * ---
 * Hint: You can use the debug stream to print initialTX and initialTY, if Thor seems not follow your orders.
 **/
fun main(args : Array<String>) {
    val `in` = Scanner(System.`in`)
    val lightX = `in`.nextInt() // the X position of the light of power
    val lightY = `in`.nextInt() // the Y position of the light of power
    val initialTX = `in`.nextInt() // Thor's starting X position
    val initialTY = `in`.nextInt() // Thor's starting Y position

    var dx = initialTX - lightX
    var dy = initialTY - lightY
    var horizontalMove = ""
    var verticalMove = ""

    // game loop
    while (true) {
        val remainingTurns = `in`.nextInt() // The remaining amount of turns Thor can move. Do not remove this line.

        if (dx > 0) {
            dx = dx + 1
            horizontalMove = "W"
        } else if (dx < 0) {
            dx = dx - 1
            horizontalMove = "E"
        } else {
            horizontalMove = ""
        }

        if (dy > 0) {
            dy = dy - 1
            verticalMove = "N"
        } else if (dy < 0) {
            dy = dy + 1
            verticalMove = "S"
        } else {
            verticalMove = ""
        }

        ///Write an action using println()
        // To debug: System.err.println("Debug messages...");

        // A single line providing the move to be made: N NE E SE S SW W or NW
        println(verticalMove + horizontalMove)
    }
}
