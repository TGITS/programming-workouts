package tgits.programming.workout.perfectnumber;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class PerfectNumberTest 
    
{
	@Test
   public void testPerfectNumberPredicate() {
	   PerfectNumberPredicate pnPredicate = new PerfectNumberPredicate();
	   /* *
	    * The first four perfect numbers
	    * 6 = 21(22 – 1) = (1 + 2) + 3 ;
	    * 28 = 22(23 – 1) = (1 + 2 + 4) + (7 + 14) ;
	    * 496 = 24(25 – 1) = (1 + 2 + 4 + 8 + 16) + (31 + 62 + 124 + 248) ;
	    * 8 128 = 26(27 – 1) = (1 + 2 + 4 + 8 + 16 + 32 + 64) + (127 + 254 + 508 + 1 016 + 2 032 + 4 064). 
	    * */
	   
	   assertTrue(pnPredicate.test(6));
	   assertTrue(pnPredicate.test(28));
	   assertTrue(pnPredicate.test(496));
	   assertTrue(pnPredicate.test(8128));
	   
	   /* Des nombres pas parfaits */
	   assertTrue(!pnPredicate.test(5));
	   assertTrue(!pnPredicate.test(7));
	   assertTrue(!pnPredicate.test(10));
	   assertTrue(!pnPredicate.test(27));
	   assertTrue(!pnPredicate.test(29));
	   assertTrue(!pnPredicate.test(30));
	   assertTrue(!pnPredicate.test(495));
	   assertTrue(!pnPredicate.test(497));
	   assertTrue(!pnPredicate.test(500));
	   assertTrue(!pnPredicate.test(8127));
	   assertTrue(!pnPredicate.test(8129));
	   assertTrue(!pnPredicate.test(8130));
   }
}
