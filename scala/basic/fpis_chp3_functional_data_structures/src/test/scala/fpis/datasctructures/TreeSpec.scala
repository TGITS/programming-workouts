package fpis.datasctructures

import fpis.datastructures.{Branch, Leaf, Tree}
import org.scalatest._

class TreeSpec extends FlatSpec with Matchers {

  "Applying size on a Leaf" should "return 1" in {
    val leaf = Leaf(5)
    Tree.size(leaf) should be(1)
  }

  "Applying size on a Tree with 7 nodes" should "return 7" in {
    val lf1 = Leaf(1)
    val lf2 = Leaf(2)
    val lf3 = Leaf(3)
    val lf4 = Leaf(4)
    val br1 = Branch(lf1, lf2)
    val br2 = Branch(lf3, lf4)
    val root = Branch(br1, br2)
    Tree.size(root) should be(7)
  }
}
