import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PointTest {

    private Point origin = new Point(0, 0);
    private Point oneOnXAxis = new Point(1, 0);
    private Point oneOnYAxis = new Point(0, 1);

    @Test
    public void testDistance() {

        assertEquals(1.0, origin.distance(oneOnXAxis));
        assertEquals(1.0, oneOnXAxis.distance(origin));
        assertEquals(1.0, origin.distance(oneOnYAxis));
        assertEquals(1.0, oneOnYAxis.distance(origin));
        assertEquals(141.0, Math.round(100 * oneOnXAxis.distance(oneOnYAxis)));
    }

    @Test
    public void testHorizontalDistance() {
        assertEquals(1, oneOnXAxis.horizontalDistance(oneOnYAxis));
        assertEquals(1, oneOnYAxis.horizontalDistance(oneOnXAxis));
        assertEquals(1, origin.horizontalDistance(oneOnXAxis));
        assertEquals(1, oneOnXAxis.horizontalDistance(origin));
        assertEquals(0, oneOnYAxis.horizontalDistance(origin));
        assertEquals(0, origin.horizontalDistance(oneOnYAxis));
    }

    @Test
    public void testVerticalDistance() {
        assertEquals(1, oneOnXAxis.verticalDistance(oneOnYAxis));
        assertEquals(1, oneOnYAxis.verticalDistance(oneOnXAxis));
        assertEquals(0, origin.verticalDistance(oneOnXAxis));
        assertEquals(0, oneOnXAxis.verticalDistance(origin));
        assertEquals(1, origin.verticalDistance(oneOnYAxis));
        assertEquals(1, oneOnYAxis.verticalDistance(origin));
    }

    @Test
    public void testHasSameAltitude() {
        Point p1 = new Point(1000, 2000);
        Point p2 = new Point(2000, 500);
        Point p3 = new Point(3000, 500);
        assertFalse(p1.hasSameAltitude(p2));
        assertTrue(p2.hasSameAltitude(p3));
    }
}
