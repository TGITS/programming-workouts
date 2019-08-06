package mars_lander_ep2

import kotlin.test.*

class PointTest {
    @Test
    fun testPointConstructorWithDefaultValues() {
        val point = Point()
        println("Creation of a Point with default Value : ${point}")
        assertNotNull(point)
        assertEquals(0, point.x, "X value should be 0")
        assertEquals(0, point.y, "Y value should be 0")
    }

    @Test
    fun testPointConstructorWithProvidedValues() {
        val point = Point(1, 3)
        println("Creation of a Point with default Value : ${point}")
        assertNotNull(point)
        assertEquals(1, point.x, "X value should be 1")
        assertEquals(3, point.y, "Y value should be 3")
    }

    @Test
    fun testThatTwoPointsAreStrictlyHigherAndLowerWithOneAnother() {
        val highestPoint = Point(1, 3)
        val lowestPoint = Point(3, 1)
        assertTrue(highestPoint.isStrictlyHigherThan(lowestPoint))
        assertTrue(lowestPoint.isStrictlyLowerThan(highestPoint))
    }

    @Test
    fun testThatAPointIsNotStrictlyHigherOrLowerWithHimself() {
        val highestPoint = Point(1, 3)
        assertFalse(highestPoint.isStrictlyHigherThan(highestPoint))
        assertFalse(highestPoint.isStrictlyLowerThan(highestPoint))
    }

    @Test
    fun testThatPointHasTheSameHeightThanAnotherPoint() {
        val point_1 = Point(1, 3)
        val point_2 = Point(1,3)
        assertTrue(point_1.asSameHeightAs(point_1))
        assertTrue(point_1.asSameHeightAs(point_2))
    }
}
