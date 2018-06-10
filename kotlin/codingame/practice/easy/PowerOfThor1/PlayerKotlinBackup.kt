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
    val input = Scanner(System.`in`)
    val lightX = input.nextInt() // the X position of the light of power
    val lightY = input.nextInt() // the Y position of the light of power
    val initialTX = input.nextInt() // Thor's starting X position
    val initialTY = input.nextInt() // Thor's starting Y position

    // game loop
    while (true) {
        val remainingTurns = input.nextInt() // The remaining amount of turns Thor can move. Do not remove this line.

        // Write an action using println()
        // To debug: System.err.println("Debug messages...");


        // A single line providing the move to be made: N NE E SE S SW W or NW
        println("SE")
    }
}