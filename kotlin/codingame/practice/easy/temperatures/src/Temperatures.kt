import java.util.*

fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    val n = input.nextInt() // the number of temperatures to analyse
    val result = if (n > 0) {
        val initialValues: MutableList<Int> = mutableListOf()
        for (i in 0 until n) {
            initialValues.add(input.nextInt()) // a temperature expressed as an integer ranging from -273 to 5526
        }
        initialValues.map { it -> Pair(it, Math.abs(it)) }.minWith(compareBy<Pair<Int, Int>> { it.second }.thenByDescending { it.first })?.first.toString()
    } else {
        "0"
    }
    System.err.println("result : $result");
    println("$result")
}