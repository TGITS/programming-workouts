package tgits.workouts.basic

//Code adapted from : https://blog.frankel.ch/rolling-dice-kotlin/

import java.util.*
import java.security.SecureRandom

interface Rollable<T> {
    fun roll(): T
}

open class Die(val sides: Int, private val random: Random = SecureRandom()): Rollable<Int> {
    override fun roll() = random.nextInt(sides) + 1
}

object d2: Die(2)
object d4: Die(4)
object d6: Die(6)
object d8: Die(8)
object d10: Die(10)
object d12: Die(12)
object d20: Die(20)
object d100: Die(100)

fun main(args: Array<String>) {
    println("\n\nDice, Dice, Baby !\n\n")
    println("roll the D4 ${d4.roll()}")
    println("roll the D6 ${d6.roll()}")
    println("roll the D8 ${d8.roll()}")
    println("roll the D10 ${d10.roll()}")
    println("roll the D12 ${d12.roll()}")
    println("roll the D20 ${d20.roll()}")
    println("roll the D100 ${d100.roll()}")
}