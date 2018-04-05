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
    Tree.size(lf1) should be(1)
    Tree.size(br1) should be(3)
    Tree.size(root) should be(7)
  }

  "Applying maximum on a Tree of Integer" should "return the biggest integer in the tree" in {
    val lf1 = Leaf(1)
    val lf2 = Leaf(2)
    val lf3 = Leaf(3)
    val lf4 = Leaf(4)
    val br1 = Branch(lf1, lf2)
    val br2 = Branch(lf3, lf4)
    val root = Branch(br1, br2)
    Tree.maximum(root) should be(4)
    Tree.maximum(lf1) should be(1)
    Tree.maximum(br1) should be(2)
    Tree.maximum(br2) should be(4)
  }

  "Applying depth on a Tree" should "return the longest path of nodes" in {
    val lf1 = Leaf(1)
    val lf2 = Leaf(2)
    val lf3 = Leaf(3)
    val lf4 = Leaf(4)
    val br1 = Branch(lf1, lf2)
    val br2 = Branch(lf3, lf4)
    val root = Branch(br1, br2)
    Tree.depth(root) should be(3)
    Tree.depth(lf1) should be(1)
    Tree.depth(br1) should be(2)
    Tree.depth(br2) should be(2)
    val lf5 = Leaf(5)
    val root2 = Branch(root, lf5)
    Tree.depth(root2) should be(4)
  }

  "Applying map on a Tree of Int with a function that convert to String" should "return a Tree of Strings" in {
    val ilf1 = Leaf(1)
    val ilf2 = Leaf(2)
    val ilf3 = Leaf(3)
    val ilf4 = Leaf(4)
    val ibr1 = Branch(ilf1, ilf2)
    val ibr2 = Branch(ilf3, ilf4)
    val iroot = Branch(ibr1, ibr2)

    val slf1 = Leaf("1")
    val slf2 = Leaf("2")
    val slf3 = Leaf("3")
    val slf4 = Leaf("4")
    val sbr1 = Branch(slf1, slf2)
    val sbr2 = Branch(slf3, slf4)
    val sroot = Branch(sbr1, sbr2)

    Tree.map(iroot)(i => i.toString) should be(sroot)
  }

  "Applying size_2 on a Tree with 7 nodes" should "return 7" in {
    val lf1 = Leaf(1)
    val lf2 = Leaf(2)
    val lf3 = Leaf(3)
    val lf4 = Leaf(4)
    val br1 = Branch(lf1, lf2)
    val br2 = Branch(lf3, lf4)
    val root = Branch(br1, br2)
    Tree.size_2(lf1) should be(1)
    Tree.size_2(br1) should be(3)
    Tree.size_2(root) should be(7)
  }

  "Applying maximum_2 on a Tree of Integer" should "return the biggest integer in the tree" in {
    val lf1 = Leaf(1)
    val lf2 = Leaf(2)
    val lf3 = Leaf(3)
    val lf4 = Leaf(4)
    val br1 = Branch(lf1, lf2)
    val br2 = Branch(lf3, lf4)
    val root = Branch(br1, br2)
    Tree.maximum_2(root) should be(4)
    Tree.maximum_2(lf1) should be(1)
    Tree.maximum_2(br1) should be(2)
    Tree.maximum_2(br2) should be(4)
  }

  "Applying depth_2 on a Tree" should "return the longest path of nodes" in {
    val lf1 = Leaf(1)
    val lf2 = Leaf(2)
    val lf3 = Leaf(3)
    val lf4 = Leaf(4)
    val br1 = Branch(lf1, lf2)
    val br2 = Branch(lf3, lf4)
    val root = Branch(br1, br2)
    Tree.depth_2(root) should be(3)
    Tree.depth_2(lf1) should be(1)
    Tree.depth_2(br1) should be(2)
    Tree.depth_2(br2) should be(2)
    val lf5 = Leaf(5)
    val root2 = Branch(root, lf5)
    Tree.depth_2(root2) should be(4)
  }

  "Applying map_2 on a Tree of Int with a function that convert to String" should "return a Tree of Strings" in {
    val ilf1 = Leaf(1)
    val ilf2 = Leaf(2)
    val ilf3 = Leaf(3)
    val ilf4 = Leaf(4)
    val ibr1 = Branch(ilf1, ilf2)
    val ibr2 = Branch(ilf3, ilf4)
    val iroot = Branch(ibr1, ibr2)

    val slf1 = Leaf("1")
    val slf2 = Leaf("2")
    val slf3 = Leaf("3")
    val slf4 = Leaf("4")
    val sbr1 = Branch(slf1, slf2)
    val sbr2 = Branch(slf3, slf4)
    val sroot = Branch(sbr1, sbr2)

    Tree.map_2(iroot)(i => i.toString) should be(sroot)
    Tree.map_2(iroot)(i => i.toString) should be(Tree.map(iroot)(i => i.toString))
  }
}
