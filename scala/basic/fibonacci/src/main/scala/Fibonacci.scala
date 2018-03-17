object Fibonacci {
  private def fib(n:Int):Int = {
    @annotation.tailrec
    def loop(max: Int, current: Int, previous: Int, antePrevious: Int, acc: Int):Int = {
      if (current == max) acc
      else loop(max,current+1,previous+antePrevious, previous, acc+antePrevious)
    }
    if(n <= 0) 0
    else loop(n,1,1,0,1)
  }

  def main(args: Array[String]):Unit = {
    println(fib(0))
    println(fib(1))
    println(fib(2))
    println(fib(3))
    println(fib(4))
    println(fib(5))
    println(fib(6))
    println(fib(7))
    println(fib(8))
    println(fib(9))
    println(fib(10))
  }
}
