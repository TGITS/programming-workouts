package fpis.option


/*
 * Implement all of the preceding functions on Option. As you implement each function,
 * try to think about what it means and in what situations you’d use it. We’ll explore when
 * to use each of these functions next. Here are a few hints for solving this exercise:
 *  - It’s fine to use pattern matching, though you should be able to implement all the functions
 *  besides map and getOrElse without resorting to pattern matching.
 *  - For map and flatMap, the type signature should be enough to determine the implementation.
 *  - getOrElse returns the result inside the Some case of the Option, or if the Option is None,
 *  returns the given default value.
 *  - orElse returns the first Option if it’s defined; otherwise, it returns the second Option.
* */

sealed trait Option[+A] {
  /* Apply f if the Option is not None.*/
  def map[B](f: A => B): Option[B] = this match {
    case None => None
    case Some(a) => Some(f(a))
  }

  /* getOrElse returns the result inside the Some case of the Option, or if the Option is None, returns the given default value. */
  def getOrElse[B >: A](default: => B): B = this match {
    case None => default
    case Some(a) => a
  }

  /* Apply f, which may fail, to the Option if not None. */
  def flatMap[B](f: A => Option[B]): Option[B] = this map (f) getOrElse None

  def flatMap_1[B](f: A => Option[B]): Option[B] = this match {
    case None => None
    case Some(a) => f(a)
  }

  /* orElse returns the first Option if it’s defined; otherwise, it returns the second Option. */
  def orElse[B >: A](ob: => Option[B]): Option[B] = this map (Some(_)) getOrElse ob

  def orElse_1[B >: A](ob: => Option[B]): Option[B] = this match {
    case None => ob
    case _ => this
  }

  def orElse_2[B >: A](ob: => Option[B]): Option[B] = if (this == None) ob else this

  /* Convert Some to None if the value doesn’t satisfy f */
  def filter(f: A => Boolean): Option[A] = this match {
    case Some(a) if f(a) => Some(a)
    case _ => None
  }

  def filter_1(f: A => Boolean): Option[A] = this flatMap (a => if (f(a)) Some(a) else None)

  def lift[A, B](f: A => B): Option[A] => Option[B] = _ map f

  /* map2 is a generic function that combines two Option values using a binary function.
   * If either Option value is None, then the return value is too
   * */
  def map2[A, B, C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C]

  /* The function sequence combines a list of Options into one Option containing
   * a list of all the Some values in the original list.
   * If the original list contains None even once, the result of the function should be None;
   * otherwise the result should be Some with a list of all the values.
   * */
  def sequence[A](a: List[Option[A]]): Option[List[A]]

  /* It’s straightforward to implement using map and sequence, but a more efficient implementation only looks at the list once.
   * The trick is to  implement sequence in terms of traverse.
   * */
  def traverse[A, B](a: List[A])(f: A => Option[B]): Option[List[B]]
}

object Option {
  def variance(xs: Seq[Double]): Option[Double] = {

  }

  def Try[A](a: => A): Option[A] =
    try Some(a)
    catch {
      case _: Exception => None
    }
}

case class Some[+A](get: A) extends Option[A]

case object None extends Option[Nothing]

