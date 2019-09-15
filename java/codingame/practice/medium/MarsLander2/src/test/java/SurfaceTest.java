import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SurfaceTest {

    @Test
    public void testSurfaceGetBiggestFlatGround() {
        /*
         * 6         (surfaceN) Surface made of 6 points
         * 0 1500    (landX landY)
         * 1000 2000 (landX landY)
         * 2000 500	 (landX landY) Start of flat ground
         * 3500 500	 (landX landY) End of flat ground
         * 5000 1500 (landX landY)
         * 6999 1000 (landX landY)
         **/
        Surface marsSurface = new Surface();
        marsSurface.addPoint(new Point(0, 1500));
        marsSurface.addPoint(new Point(1000, 2000));
        marsSurface.addPoint(new Point(2000, 500));
        marsSurface.addPoint(new Point(3500,500));
        marsSurface.addPoint(new Point(5000, 1500));
        marsSurface.addPoint(new Point(6999, 1000));

        Surface flatGround = marsSurface.getBiggestFlatGround();
        assertEquals(6, marsSurface.getNumberOfDefiningPoints());
        assertEquals(2, flatGround.getNumberOfDefiningPoints());
        assertEquals(new Point(2000, 500), flatGround.getPoint(0));
        assertEquals(new Point(3500,500), flatGround.getPoint(1));
    }
}
