import scala.io._

object Solution extends App {
  val InvalidInput = "INVALID"
  val encrypt = StdIn.readLine
  Console.err.println("Given input string : " + encrypt)
  val encryptToArray = encrypt.split(" ")
  val decoded = if (!encrypt.isEmpty && encryptToArray.size % 2 == 0) {
    val encryptToList = encryptToArray.sliding(2, 2).toList
    val binarySequence = encryptToList.foldLeft("") {
      (accumulator, current) => {
        if (accumulator.equals(InvalidInput)) {
          InvalidInput
        } else {
          current(0) match {
            case "0" => accumulator + "1" * current(1).length
            case "00" => accumulator + "0" * current(1).length
            case "" => InvalidInput
            case _ => InvalidInput
          }
        }
      }
    }
    if (!binarySequence.equals(InvalidInput) && (binarySequence.length >= 7) && (binarySequence.length % 7 == 0)) {
      binarySequence.sliding(7, 7).map(it => Integer.parseInt(it, 2).toChar.toString).mkString("")
    } else {
      InvalidInput
    }
  } else {
    InvalidInput
  }
  println(decoded)
}