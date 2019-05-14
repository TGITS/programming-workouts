package mars_lander_ep2

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class PointTest {
    @Test fun testPointConstructorWithDefaultValues() {
        val point = Point()
        println("Creation of a Point with default Value : ${point}")
        assertNotNull(point)
        assertEquals(0,point.x,"X value should be 0")
        assertEquals(0,point.y,"Y value should be 0")
    }

    @Test fun testPointConstructorWithProvidedValues() {
        val point = Point(1,3)
        println("Creation of a Point with default Value : ${point}")
        assertNotNull(point)
        assertEquals(1,point.x,"X value should be 1")
        assertEquals(3,point.y,"Y value should be 3")
    }
}
