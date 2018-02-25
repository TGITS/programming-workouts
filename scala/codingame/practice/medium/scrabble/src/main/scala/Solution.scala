import scala.io._
import scala.collection.mutable.ArrayBuffer

/**
  * Auto-generated code below aims at helping you parse
  * the standard input according to the problem statement.
  **/
object Solution extends App {
  val n = StdIn.readInt
  var words: ArrayBuffer[Word] = ArrayBuffer()
  var word:Word = null;
  for (i <- 0 until n) {
    word = Word(StdIn.readLine,None)
    word.computeValue()
    words += word
  }
  //Console.err.println("The Given Dictionnary :")
  //Console.err.println(words.mkString("\n"))
  val letters = StdIn.readLine
  //Console.err.println("The Given Letters :")
  //Console.err.println(letters)

  // Write an action using println
  // To debug: Console.err.println("Debug messages...")
  val writableWords = words.filter(w => w.canBeWrittenWith(letters))
  //Console.err.println("The words writable with the given Letters :")
  //Console.err.println(writableWords.mkString("\n"))
  val max = writableWords.maxBy(w => w.computeValue()).computeValue
  //Console.err.println("The computed max value obtain by words :" + max.toString)
  println(writableWords.filter(w => w.computeValue == max).head.word)
}

case class Word(val word: String, var value:Option[Int]) {

  def computeValue(): Int = {
    if(value.isEmpty){
      val letterToValue: Map[String, Int] = Map("e" -> 1, "a" -> 1, "i" -> 1, "o" -> 1, "n" -> 1, "r" -> 1, "t" -> 1,
      "l" -> 1, "s" -> 1, "u" -> 1, "d" -> 2, "g" -> 2, "b" -> 3, "c" -> 3, "m" -> 3, "p" -> 3, "f" -> 4, "h" -> 4,
      "v" -> 4, "w" -> 4, "y" -> 4, "k" -> 5, "j" -> 8, "x" -> 8, "q" -> 10, "z" -> 10);

      value = Some(word.map(c => letterToValue.get(c.toString).get).sum)
    }
    value.get
  }

  def canBeWrittenWith(charSeq:String): Boolean = {
    if(charSeq.size < word.size) {
      false
    }
    else {
      word.forall(c => charSeq.contains(c) && (word.count(_ == c) <= charSeq.count(_ == c)))
    }
  }
}