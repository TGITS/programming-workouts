import scala.collection.mutable.ListBuffer
import scala.io.StdIn

/**
  * Auto-generated code below aims at helping you parse
  * the standard input according to the problem statement.
  **/
object Solution extends App {
  val n = StdIn.readInt()
  val telephoneNumbers = new ListBuffer[List[Char]]()
  for (i <- 0 until n) {
    telephoneNumbers += StdIn.readLine().toList
  }

  val root = Node[Char](' ', new ListBuffer[Node[Char]])
  for (number <- telephoneNumbers) {
    root.translateInto(number)
  }

  val answer = root.count
  // Write an action using println
  // To debug: Console.err.println("Debug messages...")


  // The number of elements (referencing a number) stored in the structure.
  println((answer - 1).toString)
}

case class Node[A](content: A, children: ListBuffer[Node[A]]) {
  def translateInto(list: List[A]): Node[A] = list match {
    case Nil => this
    case _ => {
      children.find(n => n.content == list.head) match {
        case None => {
          val newNode = Node[A](list.head, new ListBuffer[Node[A]])
          children += newNode
          newNode.translateInto(list.tail)
        }
        case Some(node) => node.translateInto(list.tail)
      }
    }
  }

  def findLongestBranch[A]: Int = {
    if (this.children.isEmpty) 1 else 1 + (children map (node => node.findLongestBranch)).max
  }

  def count[A]: Int = {
    if (this.children.isEmpty) 1 else 1 + (children map (node => node.count)).sum
  }
}