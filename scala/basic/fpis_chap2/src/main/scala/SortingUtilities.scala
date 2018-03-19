object SortingUtilities {

  /* Exercise 2.2 from Functional Programming in Scala
  *  Implement isSorted, which checks whether an Array[A] is sorted according to a given comparison function:
  * def isSorted[A](as: Array[A], ordered: (A,A) => Boolean): Boolean
  * */

  def isSorted[A](as: Array[A], ordered: (A,A) => Boolean): Boolean = {
    @annotation.tailrec
    def loop[A](as: Array[A], ordered: (A,A) => Boolean, acc: Boolean): Boolean = {
      if (as.length <= 1 || !acc) acc
      else
        loop(as.slice(1,as.length), ordered, acc && ordered(as(0),as(1))) }

    loop(as, ordered, true)
  }

  // From https://github.com/fpinscala/fpinscala/blob/master/answers/src/main/scala/fpinscala/gettingstarted/GettingStarted.scala
  // Exercise 2: Implement a polymorphic function to check whether
  // an `Array[A]` is sorted
  def isSortedFpisSol[A](as: Array[A], gt: (A,A) => Boolean): Boolean = {
    @annotation.tailrec
    def go(n: Int): Boolean =
      if (n >= as.length-1) true
      else if (gt(as(n), as(n+1))) false
      else go(n+1)

    go(0)
  }
}
