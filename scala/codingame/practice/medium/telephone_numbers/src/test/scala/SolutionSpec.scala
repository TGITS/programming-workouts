import org.scalatest.{FlatSpec, Matchers}

import scala.collection.mutable.ListBuffer

class SolutionSpec extends FlatSpec with Matchers {
  "Applying findLongestBranch on a Node on which we had added 0467123456" should "result in the value 11" in {
    val root = Node[Char](' ', new ListBuffer[Node[Char]])
    root.translateInto("0467123456".toList)
    root.findLongestBranch should be(11)
  }

  "Applying findLongestBranch on a Node on which we had added 0123456789 and 1123456789" should "result in the value 11" in {
    val root = Node[Char](' ', new ListBuffer[Node[Char]])
    root.translateInto("0123456789".toList)
    root.translateInto("1123456789".toList)
    root.findLongestBranch should be(11)
  }

  "Applying count on a Node on which we had added 0467123456" should "result in the value 11" in {
    val root = Node[Char](' ', new ListBuffer[Node[Char]])
    root.translateInto("0467123456".toList)
    root.count should be(11)
  }

  "Applying count on a Node on which we had added 0123456789 and 1123456789" should "result in the value 21" in {
    val root = Node[Char](' ', new ListBuffer[Node[Char]])
    root.translateInto("0123456789".toList)
    root.translateInto("1123456789".toList)
    root.count should be(21)
  }

  "Applying count on a Node on which we had added 0123456789 and 0123" should "result in the value 11" in {
    val root = Node[Char](' ', new ListBuffer[Node[Char]])
    root.translateInto("0123456789".toList)
    root.translateInto("0123".toList)
    root.count should be(11)
  }

  "Applying count on a Node on which we had added 0412578440, 0412199803, 0468892011, 112 and 15" should "result in the value 29" in {
    val root = Node[Char](' ', new ListBuffer[Node[Char]])
    root.translateInto("0412578440".toList)
    root.translateInto("0412199803".toList)
    root.translateInto("0468892011".toList)
    root.translateInto("112".toList)
    root.translateInto("15".toList)
    root.count should be(29)
  }
}
