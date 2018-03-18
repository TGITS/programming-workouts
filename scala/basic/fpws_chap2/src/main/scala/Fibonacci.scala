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

}

