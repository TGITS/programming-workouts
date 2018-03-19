object Fibonacci {

  /* Exercice 2.1 from Functional Programming in Scala */
  def fib(n: Int): Int = {
    @annotation.tailrec
    def loop(max: Int, current: Int, previous: Int, antePrevious: Int, acc: Int): Int = {
      if (current == max) acc
      else loop(max, current + 1, previous + antePrevious, previous, acc + antePrevious)
    }

    if (n <= 0) 0
    else loop(n, 1, 1, 0, 1)
  }

  // From https://github.com/fpinscala/fpinscala/blob/master/answers/src/main/scala/fpinscala/gettingstarted/GettingStarted.scala
  // 0 and 1 are the first two numbers in the sequence,
  // so we start the accumulators with those.
  // At every iteration, we add the two numbers to get the next one.
  def fibFpisSol(n: Int): Int = {
    @annotation.tailrec
    def loop(n: Int, prev: Int, cur: Int): Int =
      if (n == 0) prev
      else loop(n - 1, cur, prev + cur)
    loop(n, 0, 1)
  }

}

