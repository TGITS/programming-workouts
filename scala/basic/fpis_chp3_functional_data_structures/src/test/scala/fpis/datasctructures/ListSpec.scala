package fpis.datasctructures

import fpis.datastructures.{Cons, List, Nil}
import org.scalatest._

class ListSpec extends FlatSpec with Matchers {
  "Applying tail to a non-empty List" should "result in the same list minus its first element" in {
    val list = Cons("a", Cons("b", Cons("c", Cons("d", Nil))))
    List.tail(list) should be(Cons("b", Cons("c", Cons("d", Nil))))
  }

  "Applying tail to an empty list" should "result in an empty list this is Nil" in {
    val list: List[String] = Nil
    List.tail(list) should be(Nil)
  }

  "Applying head to a non-empty List" should "result in the first element of the list" in {
    val ints = List(0, 1, 2, 3, 4, 5)
    List.head(ints) should be(0)
    val strings = List("a", "b", "c")
    List.head(strings) should be("a")
  }

  //  "Applying head to an empty list" should "result in an error" in {
  //    val list:List[String] = Nil
  //    List.head(list) should be (sys.error("Nil has no head"))
  //  }

  "Applying drop on an empty list" should "result in an empty list this is Nil" in {
    val list: List[String] = Nil
    for (i <- 0 to 100) {
      List.drop(list, i) should be(Nil)
    }
  }

  "Applying setHead on an empty list" should "result in a list with only the element provided in parameter" in {
    val list: List[String] = Nil
    List.setHead(list, "A") should be(List("A"))
  }

  "Applying setHead on a non-empty list" should "result in a list for which the head as been replaced by the element provided in parameter" in {
    val ints = List(1, 2, 3, 4, 5)
    List.setHead(ints, 9) should be(List(9, 2, 3, 4, 5))
  }

  "Applying drop on a non-empty list for n elements" should "result in a list with the first n elements remove" in {
    val list = Cons("a", Cons("b", Cons("c", Cons("d", Nil))))
    List.drop(list, 1) should be(Cons("b", Cons("c", Cons("d", Nil))))
    List.drop(list, 3) should be(Cons("d", Nil))
    List.drop(list, 4) should be(Nil)
  }

  "Applying drop on a non-empty list for more elements than there are in the list" should "result in Nil" in {
    val list = Cons("a", Cons("b", Cons("c", Cons("d", Nil))))
    for (i <- 4 to 100) {
      List.drop(list, i) should be(Nil)
    }
  }

  "Applying dropWhile on a non empty list" should "result in a list with all the front elements that match the predicate removed" in {
    val ints: List[Int] = Cons(1, Cons(2, Cons(3, Cons(4, Cons(1, Cons(2, Cons(3, Cons(4, Nil))))))))
    List.dropWhile[Int](ints, (x) => x < 4) should be(Cons(4, Cons(1, Cons(2, Cons(3, Cons(4, Nil))))))
    List.dropWhile[Int](ints, (x) => x < 10) should be(Nil)
    val strings: List[String] = List("a", "a", "a", "b", "b", "a")
    List.dropWhile[String](strings, (s) => s == "a") should be(List("b", "b", "a"))
    List.dropWhile[String](strings, (s) => s.length < 2) should be(Nil)
  }

  "Applying dropWhile on an empty list" should "result in Nil" in {
    val ints: List[Int] = Nil
    List.dropWhile[Int](ints: List[Int], (x) => x < 4) should be(Nil)
  }

  "Applying init on a non-empty list" should "result in a list with the same element but the last" in {
    val ints = List(1, 2, 3, 4)
    List.init(ints) should be(List(1, 2, 3))
  }

  "Applying init on an empty list" should "result in Nil" in {
    val as = Nil
    List.init(as) should be(Nil)
  }

  "Applying sumWithFoldRight on a list of integers" should "result in the sum of the element of the list" in {
    val as = List(1, 2, 3, 4)
    List.sumWithFoldRight(as) should be(10)
  }

  "Applying productWithFoldRight on a list of floats" should "result in the product of the element of the list" in {
    val as = List(1.0, 2.0, 3.0, 4.0, 5.0)
    List.productWithFoldRight(as) should be(120.0)
  }

  "Applying productWithFoldRight on a list of floats with one which is 0.0" should "result in the value 0" in {
    val as = List(1.0, 2.0, 3.0, 4.0, 5.0, 0.0)
    List.productWithFoldRight(as) should be(0)
  }

  /* Exercise 3.8
  * See what happens when you pass Nil and Cons themselves to foldRight, like this:
  * foldRight(List(1,2,3), Nil:List[Int])(Cons(_,_)).
  * What do you think this says about the relationship between foldRight and the data constructors of List?
  */
  "Applying foldRight on a List passing Nil and Cons as seed and function" should "result in the same list" in {
    //passing Nil and Cons to foldRight, result in the identity function
    val as = List(1, 2, 3, 4, 5)
    List.foldRight(as, Nil: List[Int])(Cons(_, _)) should be(as)
  }

  "Applying length on a list of n elements" should "result in the value n" in {
    val list = List(0, 1, 2, 3, 4, 5, 6, 7)
    List.length(list) should be(8)
  }

  "Applying length on Nil " should "result in the value 0" in {
    List.length(Nil) should be(0)
  }

  "Applying foldRight on a List of Integers with the addition and a seed of 0" should "result in the sum of the elements of the list" in {
    val as = List(1, 2, 3, 4)
    List.foldRight(as, 0)(_ + _) should be(10)
  }

  "Applying foldRight on a List of Integers with the multiplication and a seed of 0" should "result in the product of the elements of the list" in {
    val as = List(1.0, 2.0, 3.0, 4.0, 5.0)
    List.foldRight(as, 1.0)(_ * _) should be(120.0)
    List.foldRight(Cons(0.0, as), 1.0)(_ * _) should be(0.0)
  }

  "Applying foldLeft on a List of Integers with the addition and a seed of 0" should "result in the sum of the elements of the list" in {
    val as = List(1, 2, 3, 4)
    List.foldLeft(as, 0)(_ + _) should be(10)
  }

  "Applying foldLeft on a List of Integers with the addition and a seed of 0" should "result in the product of the elements of the list" in {
    val as = List(1.0, 2.0, 3.0, 4.0, 5.0)
    List.foldLeft(as, 1.0)(_ * _) should be(120.0)
    List.foldLeft(Cons(0.0, as), 1.0)(_ * _) should be(0.0)
  }

  "Applying lengthWithFoldLeft on a list of n elements" should "result in the value n" in {
    val list = List(0, 1, 2, 3, 4, 5, 6, 7)
    List.lengthWithFoldLeft(list) should be(8)
  }

  "Applying lengthWithFoldLeft on Nil " should "result in the value 0" in {
    List.lengthWithFoldLeft(Nil) should be(0)
  }

  "Applying sumWithFoldLeft on a list of integers" should "result in the sum of the element of the list" in {
    val as = List(1, 2, 3, 4)
    List.sumWithFoldLeft(as) should be(10)
  }

  "Applying productWithFoldLeft on a list of floats" should "result in the product of the element of the list" in {
    val as = List(1.0, 2.0, 3.0, 4.0, 5.0)
    List.productWithFoldLeft(as) should be(120.0)
  }

  "Applying productWithFoldLeft on a list of floats with one which is 0.0" should "result in the value 0" in {
    val as = List(1.0, 2.0, 3.0, 4.0, 5.0, 0.0)
    List.productWithFoldLeft(as) should be(0)
  }

  "Applying reverse on the List(0,1,2,3,4,5)" should "result in the List(5,4,3,2,1,0)" in {
    val ints = List(0, 1, 2, 3, 4, 5)
    List.reverse(ints) should be(List(5, 4, 3, 2, 1, 0))
  }

  "Applying foldRightWithFoldLeft on a List of string for the concatenation" should "result in the same value that applying foldRight on the same list" in {
    val strings = List("a", "b", "c", "d", "e")
    val constant = "123"
    List.foldRightWithFoldLeft(strings, constant)((a, b) => a ++ b) should be(List.foldRight(strings, constant)((a, b) => a ++ b))
  }

  "Applying foldLeftWithFoldRight on a List of string for the concatenation" should "result in the same value that applying foldLeft on the same list" in {
    val strings = List("a", "b", "c", "d", "e")
    val constant = "123"
    List.foldLeftWithFoldRight(strings, constant)((a, b) => a ++ b) should be(List.foldLeft(strings, constant)((a, b) => a ++ b))
  }

  "Applying concat on a List of List of integers" should "result in a list of integers, with the elements of the list of list flatten in the new list" in {
    val list1 = List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9))
    val list2 = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
    List.concat(list1) should be(list2)
  }

  "Applying addOne on a List of Integers" should "result in the same list with all elements increase by one" in {
    val list1 = List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    val list2 = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    List.addOne(list1) should be(list2)
  }

  "Applying transformToString on a List of double" should "result in the same list with all elements converted into strings" in {
    val list1 = List(0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0)
    val list2 = List("0.0", "1.0", "2.0", "3.0", "4.0", "5.0", "6.0", "7.0", "8.0", "9.0")
    List.transformToString(list1) should be(list2)
  }

  "Applying map on a List of double with toString" should "result in the same list with all elements converted into strings" in {
    val list1 = List(0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0)
    val list2 = List("0.0", "1.0", "2.0", "3.0", "4.0", "5.0", "6.0", "7.0", "8.0", "9.0")
    List.map[Double, String](list1)(_.toString) should be(list2)
  }

  "Applying filter on a list of integers with a predicate for even numbers" should "result in a list with only even numbers (the odd numbers will have been filtered out)" in {
    val list1 = List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    val list2 = List(0, 2, 4, 6, 8)
    List.filter(list1)(_ % 2 == 0) should be(list2)
  }

  "Applying flatmap on List(1,2,3)) with (i => List(i,i))" should "result in List(1,1,2,2,3,3)" in {
    val list1 = List(1, 2, 3)
    val list2 = List(1, 1, 2, 2, 3, 3)
    List.flatMap(list1)(i => List(i, i)) should be(list2)
  }

  "Applying flatmap_2 on List(1,2,3)) with (i => List(i,i))" should "result in List(1,1,2,2,3,3)" in {
    val list1 = List(1, 2, 3)
    val list2 = List(1, 1, 2, 2, 3, 3)
    List.flatMap_2(list1)(i => List(i, i)) should be(list2)
  }

  "Applying filterWithFlatmap on a list of integers with a predicate for even numbers" should "result in a list with only even numbers (the odd numbers will have been filtered out)" in {
    val list1 = List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    val list2 = List(0, 2, 4, 6, 8)
    List.filterWithFlatmap(list1)(_ % 2 == 0) should be(list2)
  }

  "Applying addIntegerList to List(1,2,3) and List(4,5,6)" should "result in List(5,7,9)" in {
    val list1 = List(1, 2, 3)
    val list2 = List(4, 5, 6)
    val result = List(5, 7, 9)

    List.addIntegerList(list1, list2) should be(result)
  }

  "Applying zipWith to List(1,2,3) and List(4,5,6) with addition" should "result in List(5,7,9)" in {
    val list1 = List(1, 2, 3)
    val list2 = List(4, 5, 6)
    val result = List(5, 7, 9)

    List.zipWith(list1, list2)(_ + _) should be(result)
  }

  "Applying zipWith_2 to List(1,2,3) and List(4,5,6) with addition" should "result in List(5,7,9)" in {
    val list1 = List(1, 2, 3)
    val list2 = List(4, 5, 6)
    val result = List(5, 7, 9)

    List.zipWith_2(list1, list2)(_ + _) should be(result)
  }

  "Applying hasSubsequence_1 to List(1,2,3,4) with respectively with List(1,2), List(2,3), and List(4)" should "return true" in {
    val sup = List(1, 2, 3, 4)
    val sub1 = List(1, 2)
    val sub2 = List(2, 3)
    val sub3 = List(4)
    val sub4 = List(1, 2, 3, 4)
    val sub5 = List(2)
    val sub6 = List(3)

    List.hasSubsequence_1(sup, sub1) should be(true)
    List.hasSubsequence_1(sup, sub2) should be(true)
    List.hasSubsequence_1(sup, sub3) should be(true)
    List.hasSubsequence_1(sup, sub4) should be(true)
    List.hasSubsequence_1(sup, sub5) should be(true)
    List.hasSubsequence_1(sup, sub6) should be(true)
  }

  "Applying hasSubsequence_1 to List(1,2,3,4) with respectively with List(2,1), List(3,2), and List(5)" should "return false" in {
    val sup = List(1, 2, 3, 4)
    val sub1 = List(2, 1)
    val sub2 = List(3, 2)
    val sub3 = List(5)
    val sub4 = List(1, 2, 3, 4, 5)

    List.hasSubsequence_1(sup, sub1) should be(false)
    List.hasSubsequence_1(sup, sub2) should be(false)
    List.hasSubsequence_1(sup, sub3) should be(false)
    List.hasSubsequence_1(sup, sub4) should be(false)
  }

  "Applying hasSubsequence to List(1,2,3,4) with respectively with List(1,2), List(2,3), and List(4)" should "return true" in {
    val sup = List(1, 2, 3, 4)
    val sub1 = List(1, 2)
    val sub2 = List(2, 3)
    val sub3 = List(4)
    val sub4 = List(1, 2, 3, 4)
    val sub5 = List(2)
    val sub6 = List(3)

    List.hasSubsequence(sup, sub1) should be(true)
    List.hasSubsequence(sup, sub2) should be(true)
    List.hasSubsequence(sup, sub3) should be(true)
    List.hasSubsequence(sup, sub4) should be(true)
    List.hasSubsequence(sup, sub5) should be(true)
    List.hasSubsequence(sup, sub6) should be(true)
  }

  "Applying hasSubsequence to List(1,2,3,4) with respectively with List(2,1), List(3,2), and List(5)" should "return false" in {
    val sup = List(1, 2, 3, 4)
    val sub1 = List(2, 1)
    val sub2 = List(3, 2)
    val sub3 = List(5)
    val sub4 = List(1, 2, 3, 4, 5)

    List.hasSubsequence(sup, sub1) should be(false)
    List.hasSubsequence(sup, sub2) should be(false)
    List.hasSubsequence(sup, sub3) should be(false)
    List.hasSubsequence(sup, sub4) should be(false)
  }

  "Applying startsWith on (xs append ys) to xs" should "return true" in {
    val xs = List(1, 2, 3, 4)
    val ys = List(5, 6, 7, 8)
    List.startsWith(List.appendViaFoldRight(xs, ys), xs) should be(true)
  }

  "Applying startsWith on xs with Nil" should "return true" in {
    val xs = List(1, 2, 3, 4)
    List.startsWith(xs, Nil) should be (true)
  }

  "Applying hasSubsequence on (xs append ys append zs) to ys" should "return true" in {
    val xs = List(1, 2, 3, 4)
    val ys = List(5, 6, 7, 8)
    val zs = List(9,10,11)
    List.startsWith(List.appendViaFoldRight(xs, List.appendViaFoldRight(ys,zs)), xs) should be(true)
  }

  "Applying hasSubsequence on xs with Nil" should "return true" in {
    val xs = List(1, 2, 3, 4)
    List.hasSubsequence(xs, Nil) should be (true)
  }
}
