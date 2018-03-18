import Fibonacci.fib
import SortingUtilities.isSorted

object Main {

  private def doFibonacci():Unit = {
    println("fib(0) : " + fib(0))
    println("fib(1) : " + fib(1))
    println("fib(2) : " + fib(2))
    println("fib(3) : " + fib(3))
    println("fib(4) : " + fib(4))
    println("fib(5) : " + fib(5))
    println("fib(6) : " + fib(6))
    println("fib(7) : " + fib(7))
    println("fib(8) : " + fib(8))
    println("fib(9) : " + fib(9))
    println("fib(10) : " + fib(10))
    println("fib(20) : " + fib(20))
    println("fib(30) : " + fib(30))
  }

  private def doCheckSorting():Unit = {
    val sorted_array = Array(0,1,2,3,4,5,6,7,8)
    val unsorted_array = Array(10,9,1,2,3,4,5,6,7,8)
    val msg = "Is the array %s sorted in %s order ? %b"
    println("Verifying that an Array is sorted")
    println(msg.format(sorted_array.mkString(","), "ascending", isSorted[Int](sorted_array, (i:Int,j:Int) => i <= j)))
    println(msg.format(sorted_array.mkString(","), "descending", isSorted[Int](sorted_array, (i:Int,j:Int) => i >= j)))
    println(msg.format(unsorted_array.mkString(","), "ascending", isSorted[Int](unsorted_array, (i:Int,j:Int) => i <= j)))
    println(msg.format(unsorted_array.mkString(","), "descending", isSorted[Int](unsorted_array, (i:Int,j:Int) => i >= j)))
  }

  def main(args: Array[String]):Unit = {
    println("Some Fibonacci calculations")
    doFibonacci()
    doCheckSorting()
  }
}
