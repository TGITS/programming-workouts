package fpis.datasctructures

import fpis.datastructures.{Cons, List, Nil}
import org.scalatest._

class ListSpec extends FlatSpec with Matchers {
  "Applying tail to a non-empty List" should "result in the same list minus its first element" in {
    val list = Cons("a", Cons("b", Cons("c", Cons("d",Nil))))
    List.tail(list) should be (Cons("b", Cons("c", Cons("d",Nil))))
  }

  "Applying tail to an empty List" should "result in an empty list this is Nil" in {
    val list:List[String] = Nil
    List.tail(list) should be (Nil)
  }

  "Applying drop on an empty list" should "result in an empty list this is Nil" in {
    val list:List[String] = Nil
    for( i <- 0 to 100) {
      List.drop(list,i) should be (Nil)
    }
  }

  "Applying drop on a non-empty list for n elements" should "result in a list with the first n elements remove" in {
    val list = Cons("a", Cons("b", Cons("c", Cons("d",Nil))))
    List.drop(list,1) should be (Cons("b", Cons("c", Cons("d",Nil))))
    List.drop(list,3) should be (Cons("d",Nil))
    List.drop(list,4) should be (Nil)
  }

  "Applying drop on a non-empty list for more elements than there are in the list " should "result in Nil" in {
    val list = Cons("a", Cons("b", Cons("c", Cons("d",Nil))))
    for( i <- 4 to 100) {
      List.drop(list,i) should be (Nil)
    }
  }
}
