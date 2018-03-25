package fpis.datastructures

sealed trait List[+A]

case object Nil extends List[Nothing]

case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {
  def sum(ints: List[Int]): Int = ints match {
    case Nil => 0
    case Cons(x, xs) => x + sum(xs)
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x, xs) => x * product(xs)
  }

  def apply[A](as: A*): List[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  /* Exercise 3.2
   * Implement the function tail for removing the first element of a List.
   * Note that the function takes constant time.
   * What are different choices you could make in your implementation if the List is Nil?
   * */
  def tail[A](as: List[A]): List[A] = as match {
    case Nil => Nil //One way to handle Nil : tail on Nil returns Nil
    case Cons(_, xs) => xs
  }

  /* Exercise 3.3
   * Using the same idea, implement the function setHead for replacing the first element of a List with a different value.
   * */
  def setHead[A](as: List[A], newHead: A): List[A] = as match {
    case Nil => Cons(newHead, Nil) /* How do we manage Nil ? */
    case Cons(_, t) => Cons(newHead, t)
  }

  /* Exercise  3.4
   * Generalize tail to the function drop, which removes the first n elements from a list.
   * Note that this function takes time proportional only to the number of elements being dropped :
   * we don’t need to make a copy of the entire List.
   */
  def drop[A](as: List[A], n: Int): List[A] = {
    @annotation.tailrec
    def loop(n: Int, list: List[A]): List[A] =
      if (n == 0 || list == Nil) list
      else loop(n - 1, tail(list))

    loop(n, as)
  }

  /* Exercise 3.5
   * Implement dropWhile, which removes elements from the List prefix as long as they match a predicate.
   */
  def dropWhile[A](as: List[A], f: A => Boolean): List[A] = {
    @annotation.tailrec
    def loop(as: List[A], f: A => Boolean): List[A] = as match {
      case Nil => Nil
      case Cons(h, t) => if (f(h)) loop(t, f) else as
    }

    loop(as, f)
  }

  /* Exercise 3.6
   * Not everything works out so nicely. Implement a function, init, that returns a List
   * consisting of all but the last element of a List. So, given List(1,2,3,4), init will return List(1,2,3).
   * Why can’t this function be implemented in constant time like tail?
   */
  def init[A](as: List[A]): List[A] = {
    def loop(as: List[A]): List[A] = as match {
      case Nil => Nil
      case Cons(_, Nil) => Nil
      case Cons(x, xs) => Cons(x, loop(xs))
    }

    loop(as)
  }

  /* Exercise 3.7
   * Can product, implemented using foldRight, immediately halt the recursion and return 0.0 if it encounters a 0.0?
   * Why or why not? Consider how any short-circuiting might work if you call foldRight with a large list.
   */
  def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B = {
    as match {
      case Nil => z
      case Cons(x, xs) => f(x, foldRight(xs, z)(f))
    }
  }

  def sumWithFoldRight(ints: List[Int]): Int = foldRight(ints, 0)(_ + _)

  def productWithFoldRight(ds: List[Double]): Double = foldRight(ds, 1.0)(_ * _)

  /* Exercise 3.8
   * See what happens when you pass Nil and Cons themselves to foldRight, like this:
   * foldRight(List(1,2,3), Nil:List[Int])(Cons(_,_)).
   * What do you think this says about the relationship between foldRight and the data constructors of List?
   */
  //See the corresponding test in ListSpec.scala for this exercise

  /* Exercise 3.9
   * Compute the length of a list using foldRight.
   * */
  def length[A](as: List[A]): Int = foldRight[A, Int](as, 0)((_, b) => b + 1)

  /* Exercise 3.10
   * Our implementation of foldRight is not tail-recursive and will result in a StackOverflowError
   * for large lists (we say it’s not stack-safe). Convince yourself that this is the
   * case, and then write another general list-recursion function, foldLeft, that is
   * tail-recursive, using the techniques we discussed in the previous chapter.
   * */
  //  def foldLeft[A, B](as: List[A], z: B)(f: (B, A) => B): B = {
  //
  //  }

  /* Exercise 3.11
   * Write sum, product, and a function to compute the length of a list using foldLeft.
   * */
  //  def length[A](as: List[A]): Int = {
  //
  //  }

  //  def sum[A](as: List[A])(add: (A, A) => A): A = {
  //
  //  }

  //  def product[A](as: List[A])(mul: (A, A) => A): A = {
  //
  //  }

  /* Exercise 3.12
   * Write a function that returns the reverse of a list (given List(1,2,3) it returns List(3,2,1)).
   * See if you can write it using a fold.
   * */
  //  def reverse[A](as: List[A]): List[A] = {
  //
  //  }

  /* Exercise 3.13 - Hard
   * Can you write foldLeft in terms of foldRight? How about the other way around?
   * Implementing foldRight via foldLeft is useful because it lets us implement foldRight tail-recursively,
   * which means it works even for large lists without overflowing the stack.
   * */

  /* Exercise 3.14
   * Implement append in terms of either foldLeft or foldRight.
   * */
  //  def append[A](as: List[A], elem: A): List[A] = {
  //
  //  }

  /* Exercise 3.15
   * Hard: Write a function that concatenates a list of lists into a single list. Its runtime
   * should be linear in the total length of all lists. Try to use functions we have already defined.
   * */
  //  def concatenates[A](as: List[A], bs: List[A]): List[A] = {
  //
  //  }

  /* Exercise 3.16
   * Write a function that transforms a list of integers by adding 1 to each element.
   * (Reminder: this should be a pure function that returns a new List!)
   * */
  //  def addOne(ints: List[Int]): List[Int] = {
  //
  //  }

  /* Exercise 3.17
   * Write a function that turns each value in a List[Double] into a String.
   * You can use the expression d.toString to convert some d: Double to a String.
   * */
  //  def transformToString(ds: List[Double]): List[String] = {
  //
  //  }

  /* Exercise 3.18
   * Write a function map that generalizes modifying each element in a list while maintaining the structure of the list.
   * */
  //  def map[A, B](as: List[A])(f: A => B): List[B] = {
  //
  //  }

  /* Exercise 3.19
   * Write a function filter that removes elements from a list unless they satisfy a given
   * predicate. Use it to remove all odd numbers from a List[Int].
   * */
  //  def filter[A](as: List[A])(f: A => Boolean): List[A] = {
  //
  //  }

  /* Exercise 3.20
   * Write a function flatMap that works like map except that the function given will return a list instead of a single result,
   * and that list should be inserted into the final resulting list.
   * Here is its signature: def flatMap[A,B](as: List[A])(f: A => List[B]): List[B]
   * For instance, flatMap(List(1,2,3))(i => List(i,i)) should result in List(1,1,2,2,3,3).
   * */
  //  def flatMap[A, B](as: List[A])(f: A => List[B]): List[B] = {
  //
  //  }

  /* Exercise 3.21
   * Use flatMap to implement filter.
   * */
  //  def filter[A](as: List[A])(p: A => Boolean): List[A] = {
  //
  //  }

  /* Exercise 3.22
   * Write a function that accepts two lists and constructs a new list by adding corresponding
   * elements. For example, List(1,2,3) and List(4,5,6) become List(5,7,9).
   * */
  //  def addIntegerList(xs: List[Int], ys: List[Int]): List[Int] = {
  //
  //  }

  /* Exercise 3.23
   * Generalize the function you just wrote so that it’s not specific to integers or addition.
   * Name your generalized function zipWith.
   * */
  //  def zipWith[A](as: List[A], bs: List[A])(f: (A, A) => A): List[A] = {
  //
  //  }

  /* Exercise 3.24 - Hard
   * As an example, implement hasSubsequence for checking whether a List contains
   * another List as a subsequence.
   * For instance, List(1,2,3,4) would have List(1,2), List(2,3), and List(4) as subsequences, among others.
   * You may have some difficulty finding a concise purely functional implementation that is also efficient.
   * That’s okay. Implement the function however comes most naturally.
   * Note: Any two values x and y can be compared for equality in Scala using the expression x == y.
   * */
  //  def hasSubsequence[A](sup: List[A], sub: List[A]): Boolean = {
  //
  //  }
}
