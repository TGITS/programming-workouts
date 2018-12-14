import scala.collection.mutable
import scala.util.Random

class Robot() {
  var name = Robot.createUniqueName(2,3)
  def reset(): Unit = {
    Robot.deleteNameFromSet(name)
    name = Robot.createUniqueName(2,3)
  }
}

object Robot {
  val random = new Random()
  val distinctNames = mutable.HashSet.empty[String]

  def createUniqueName(numberOfLetters: Int, numberOfDigit: Int):String = {
    var uniqueName = createRandomName(numberOfLetters,numberOfDigit)
    while(distinctNames contains uniqueName) {
      uniqueName = createRandomName(numberOfLetters,numberOfDigit)
    }
    distinctNames += uniqueName
    uniqueName
  }

  def deleteNameFromSet(name:String):Unit = {
    distinctNames -= name
  }

  def createRandomName(numberOfLetter: Int, numberOfDigit: Int):String = {
    randomString(2) + randomDigits(3)
  }

  def randomDigit(): String = {
    random.nextInt(10).toString
  }

  def randomDigits(times:Int): String = {
    return List.fill(times){randomDigit()}.mkString
  }

  def randomString(length:Int):String = {
    random.shuffle(('A' to 'Z').toList).take(length).mkString
  }

}
