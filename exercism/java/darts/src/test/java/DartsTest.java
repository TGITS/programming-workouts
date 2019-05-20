import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DartsTest {

    @Test
    public void testDartOutsideTarget() {
        Darts darts = new Darts(-9, 9);
        assertEquals(0, darts.score());
    }

    @Test
    public void testDartLandsOnBorderOfTarget() {
        Darts darts = new Darts(0, 10);
        assertEquals(1, darts.score());
    }

    @Test
    public void testDartLandsInOuterCircle() {
        Darts darts = new Darts(4, 4);
        assertEquals(1, darts.score());
    }

    @Test
    public void testDartLandsInBorderBetweenOuterAndMiddleCircles() {
        Darts darts = new Darts(5, 0);
        assertEquals(5, darts.score());
    }

    @Test
    public void testDartLandsOnMiddleOfCircle() {
        Darts darts = new Darts(0.8, -0.8);
        assertEquals(5, darts.score());
    }

    @Test
    public void testDartLandsOnBorderBetweenMiddleAndInnerCircles() {
        Darts darts = new Darts(0, -1);
        assertEquals(10, darts.score());
    }

    @Test
    public void testDartLandsInTheInnerCircle() {
        Darts darts = new Darts(-0.1, -0.1);
        assertEquals(10, darts.score());
    }

}
