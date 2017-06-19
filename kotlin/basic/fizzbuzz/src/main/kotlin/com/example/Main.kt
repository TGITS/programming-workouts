package com.example

fun main(args: Array<String>): Unit {
    println("\n\nAnd now your average FizzBuzz for the numbers between 1 and 30\n\n")
    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30)
    println ("Fizzbuzzification of ${numbers} without when")
    numbers.map { fizzbuzzification(it) }.forEach { print(it + " ") }
    println ("")
    println ("Fizzbuzzification of ${numbers} with when")
    numbers.map { fizzbuzzificationWithWhen(it) }.forEach { print(it + " ") }
}

fun fizzbuzzification(number: Int): String {
    return if (number % 15 == 0) {
        "FizzBuzz"
    } else if (number % 3 == 0) {
        "Fizz"
    } else if (number % 5 == 0) {
        "Buzz"
    } else {
        number.toString()
    }
}

fun fizzbuzzificationWithWhen(number: Int): String {
    return when {
        number % 15 == 0 -> "FizzBuzz"
        number % 3 == 0  ->  "Fizz"
        number % 5 == 0 -> "Buzz"
        else  -> number.toString()
    }
}