import java.util.*
import java.io.*
import java.math.*

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
fun main(args : Array<String>) {
    val input = Scanner(System.`in`)
    val N = input.nextInt()
    val strengthes = mutableListOf<Int>()
    for (i in 0 until N) {
        strengthes.add(input.nextInt())
    }
    val orderedStrengthes = strengthes.sorted()
    val differences = mutableListOf<Int>()
    for (i in 0 until N-1) {
        differences.add(orderedStrengthes[i+1] - orderedStrengthes[i])
    }

    // Write an action using println()
    // To debug: System.err.println("Debug messages...");

    println(differences.min().toString())
}