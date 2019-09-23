import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    private Point origin = new Point(0, 0);
    private Point oneOnXAxis = new Point(1, 0);
    private Point oneOnYAxis = new Point(0, 1);

    @Test
    void testDistance() {

        assertEquals(1.0, origin.distance(oneOnXAxis));
        assertEquals(1.0, oneOnXAxis.distance(origin));
        assertEquals(1.0, origin.distance(oneOnYAxis));
        assertEquals(1.0, oneOnYAxis.distance(origin));
        assertEquals(141.0, Math.round(100 * oneOnXAxis.distance(oneOnYAxis)));
    }

    @Test
    void testHorizontalDistance() {
        assertEquals(1, oneOnXAxis.horizontalDistance(oneOnYAxis));
        assertEquals(1, oneOnYAxis.horizontalDistance(oneOnXAxis));
        assertEquals(1, origin.horizontalDistance(oneOnXAxis));
        assertEquals(1, oneOnXAxis.horizontalDistance(origin));
        assertEquals(0, oneOnYAxis.horizontalDistance(origin));
        assertEquals(0, origin.horizontalDistance(oneOnYAxis));
    }

    @Test
    void testVerticalDistance() {
        assertEquals(1, oneOnXAxis.verticalDistance(oneOnYAxis));
        assertEquals(1, oneOnYAxis.verticalDistance(oneOnXAxis));
        assertEquals(0, origin.verticalDistance(oneOnXAxis));
        assertEquals(0, oneOnXAxis.verticalDistance(origin));
        assertEquals(1, origin.verticalDistance(oneOnYAxis));
        assertEquals(1, oneOnYAxis.verticalDistance(origin));
    }

    @Test
    void testHasSameAltitude() {
        Point p1 = new Point(1000, 2000);
        Point p2 = new Point(2000, 500);
        Point p3 = new Point(3000, 500);
        assertFalse(p1.hasSameAltitude(p2));
        assertTrue(p2.hasSameAltitude(p3));
    }

    @Test
    void testHasSameAbscissa() {
        Point p1 = new Point(1000, 2000);
        Point p2 = new Point(2000, 500);
        Point p3 = new Point(3000, 500);
        Point p4 = new Point(3000, 700);
        assertFalse(p1.hasSameAbscissa(p2));
        assertTrue(p3.hasSameAbscissa(p4));
    }

    @Test
    void testIsOnLeft() {
        Point p1 = new Point(1000, 2000);
        Point p2 = new Point(2000, 500);
        Point p3 = new Point(3000, 500);
        Point p4 = new Point(3000, 700);
        assertTrue(p1.isOnLeftOf(p2));
        assertTrue(p2.isOnLeftOf(p3));
        assertFalse(p3.isOnLeftOf(p4));
        assertFalse(p4.isOnLeftOf(p1));
    }

    @Test
    void testIsOnRight() {
        Point p1 = new Point(1000, 2000);
        Point p2 = new Point(2000, 500);
        Point p3 = new Point(3000, 500);
        Point p4 = new Point(3000, 700);
        assertFalse(p1.isOnRightOf(p2));
        assertFalse(p2.isOnRightOf(p3));
        assertFalse(p3.isOnRightOf(p4));
        assertTrue(p3.isOnRightOf(p2));
        assertTrue(p2.isOnRightOf(p1));
    }

    @Test
    void testIsAbove() {
        Point p1 = new Point(1000, 2000);
        Point p2 = new Point(2000, 500);
        Point p3 = new Point(3000, 2500);
        Point p4 = new Point(3000, 700);
        assertTrue(p1.isAboveOf(p2));
        assertTrue(p3.isAboveOf(p4));
        assertFalse(p1.isAboveOf(p3));
        assertFalse(p2.isAboveOf(p4));
    }

    @Test
    void testisBelow() {
        Point p1 = new Point(1000, 2000);
        Point p2 = new Point(2000, 500);
        Point p3 = new Point(3000, 2500);
        Point p4 = new Point(3000, 700);
        assertFalse(p1.isBelowOf(p2));
        assertFalse(p3.isBelowOf(p4));
        assertTrue(p1.isBelowOf(p3));
        assertTrue(p2.isBelowOf(p4));
    }
}
