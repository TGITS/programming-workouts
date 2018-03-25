package fpis.datasctructures

import fpis.datastructures.{Cons, List, Nil}
import org.scalatest._

class ListSpec extends FlatSpec with Matchers {
  "Applying tail to a non-empty List" should "result in the same list minus its first element" in {
    val list = Cons("a", Cons("b", Cons("c", Cons("d",Nil))))
    List.tail(list) should be (Cons("b", Cons("c", Cons("d",Nil))))
  }

  "Applying tail to an empty list" should "result in an empty list this is Nil" in {
    val list:List[String] = Nil
    List.tail(list) should be (Nil)
  }

  "Applying drop on an empty list" should "result in an empty list this is Nil" in {
    val list:List[String] = Nil
    for( i <- 0 to 100) {
      List.drop(list,i) should be (Nil)
    }
  }

  "Applying setHead on an empty list" should "result in a list with only the element provided in parameter" in {
    val list:List[String] = Nil
    List.setHead(list,"A") should be (List("A"))
  }

  "Applying setHead on a non-empty list" should "result in a list for which the head as been replaced by the element provided in parameter" in {
    val ints = List(1,2,3,4,5)
    List.setHead(ints,9) should be (List(9,2,3,4,5))
  }

  "Applying drop on a non-empty list for n elements" should "result in a list with the first n elements remove" in {
    val list = Cons("a", Cons("b", Cons("c", Cons("d",Nil))))
    List.drop(list,1) should be (Cons("b", Cons("c", Cons("d",Nil))))
    List.drop(list,3) should be (Cons("d",Nil))
    List.drop(list,4) should be (Nil)
  }

  "Applying drop on a non-empty list for more elements than there are in the list" should "result in Nil" in {
    val list = Cons("a", Cons("b", Cons("c", Cons("d",Nil))))
    for( i <- 4 to 100) {
      List.drop(list,i) should be (Nil)
    }
  }

  "Applying dropWhile on a non empty list" should "result in a list with all the front elements that match the predicate removed" in {
    val ints:List[Int] = Cons(1, Cons(2, Cons(3, Cons(4,Cons(1, Cons(2, Cons(3, Cons(4,Nil))))))))
    List.dropWhile[Int](ints, (x) => x < 4) should be (Cons(4,Cons(1, Cons(2, Cons(3, Cons(4,Nil))))))
    List.dropWhile[Int](ints, (x) => x < 10) should be (Nil)
    val strings:List[String] = List("a","a","a","b","b","a")
    List.dropWhile[String](strings, (s) => s == "a") should be (List("b","b","a"))
    List.dropWhile[String](strings, (s) => s.length < 2) should be (Nil)
  }

  "Applying dropWhile on an empty list" should "result in Nil" in {
    val ints:List[Int] = Nil
    List.dropWhile[Int](ints:List[Int],(x) => x < 4 ) should be (Nil)
  }

  "Applying init on a non-empty list" should "result in a list with the same element but the last" in {
    val ints = List(1,2,3,4)
    List.init(ints) should be (List(1,2,3))
  }

  "Applying init on an empty list" should "result in Nil" in {
    val as = Nil
    List.init(as) should be (Nil)
  }

  "Applying sumWithFoldRight on a list of integers" should "result in the sum of the element of the list" in {
    val as = List(1,2,3,4)
    List.sumWithFoldRight(as) should be (10)
  }

  "Applying productWithFoldRight on a list of floats" should "result in the product of the element of the list" in {
    val as = List(1.0,2.0,3.0,4.0,5.0)
    List.productWithFoldRight(as) should be (120.0)
  }

  "Applying productWithFoldRight on a list of floats with one which is 0.0" should "result in the value 0" in {
    val as = List(1.0,2.0,3.0,4.0,5.0,0.0)
    List.productWithFoldRight(as) should be (0)
  }

  /* Exercise 3.8
  * See what happens when you pass Nil and Cons themselves to foldRight, like this:
  * foldRight(List(1,2,3), Nil:List[Int])(Cons(_,_)).
  * What do you think this says about the relationship between foldRight and the data constructors of List?
  */
  "Applying foldRight on a List passing Nil and Cons as seed and function" should "result in the same list" in {
    //passing Nil and Cons to foldRight, result in the identity function
    val as=List(1,2,3,4,5)
    List.foldRight(as,Nil:List[Int])(Cons(_,_)) should be (as)
  }

  "Applying length on a list of n elements" should "result in the value n" in {
    val list = List(0,1,2,3,4,5,6,7)
    List.length(list) should be (8)
  }

  "Applying length on Nil " should "result in the value 0" in {
    List.length(Nil) should be (0)
  }
}
