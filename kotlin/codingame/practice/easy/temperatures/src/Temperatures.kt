import java.util.*

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    val n = input.nextInt() // the number of temperatures to analyse
    val result = if (n > 0) {
        val initialValues: MutableList<Int> = mutableListOf()
        for (i in 0 until n) {
            initialValues.add(input.nextInt()) // a temperature expressed as an integer ranging from -273 to 5526
        }

        val temperatures = initialValues.map { it -> Pair(it, Math.abs(it)) }.sortedWith(compareBy<Pair<Int, Int>> { it.second }.thenByDescending { it.first })
        System.err.println(temperatures.joinToString(","));
        temperatures[0].first.toString()
    } else {
        "0"
    }
    println(result)
}