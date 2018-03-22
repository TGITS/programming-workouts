package fpis.datastructures

object Main {
  def main(args: Array[String]): Unit = {
    val ex1: List[Double] = Nil
    val ex2: List[Int] = Cons(1, Nil)
    val ex3: List[String] = Cons("a", Cons("b", Nil))
    val ex4: List[String] = Cons("a", Cons("b", Cons("c", Cons("d",Nil))))

    println(ex1)
    println(ex2)
    println(ex3)
    println(ex4)

    //Exercise 3.1 - answer : 3 if sum is defined for any type, otherwise compiled error
    val x = List(1, 2, 3, 4, 5) match {
      case Cons(x, Cons(2, Cons(4, _))) => x
      case Nil => 42
      case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
      //case Cons(h,t) => h + sum(t)
      case _ => 101
    }
    println(x)

    //Testing tail
    println(List.tail(ex1))
    println(List.tail(ex2))
    println(List.tail(ex3))
    println(List.tail(ex4))

    //Testing setHead
    println(List.setHead(ex1, "e"))
    println(List.setHead(ex4, "e"))

  }

}
