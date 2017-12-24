import math._
import scala.util._

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
object Player extends App {

    // game loop
    while(true) {
        val myscore = readInt
        val enemyscore1 = readInt
        val enemyscore2 = readInt
        val myrage = readInt
        val enemyrage1 = readInt
        val enemyrage2 = readInt
        val unitcount = readInt
        for(i <- 0 until unitcount) {
            val Array(_unitid, _unittype, _player, _mass, _radius, _x, _y, _vx, _vy, _extra, _extra2) = readLine split " "
            val unitid = _unitid.toInt
            val unittype = _unittype.toInt
            val player = _player.toInt
            val mass = _mass.toFloat
            val radius = _radius.toInt
            val x = _x.toInt
            val y = _y.toInt
            val vx = _vx.toInt
            val vy = _vy.toInt
            val extra = _extra.toInt
            val extra2 = _extra2.toInt
        }
        
        // Write an action using println
        // To debug: Console.err.println("Debug messages...")
        
        println("WAIT")
        println("WAIT")
        println("WAIT")
    }
}