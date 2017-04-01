/**
 * 
 */
package tgits.programming.workout.fizzbuzz;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author cvaudry
 *
 */
public class FizzBuzzTest {

	@Test
	public void testGetNumberOrFizzBuzz() {
		int[] multiplesOf5ButNotOf15 = { 5, 10, 20, 25, 35 };
		for (int num : multiplesOf5ButNotOf15) {
			assertEquals("Fizz", FizzBuzz.getNumberOrFizzBuzz(num));
		}
		
		int[] multiplesOf3ButNotOf15 = { 3, 6, 9, 12, 18, 21 };
		for (int num : multiplesOf3ButNotOf15) {
			assertEquals("Buzz", FizzBuzz.getNumberOrFizzBuzz(num));
		}
		
		int[] multiplesOf15 = { 15, 30, 45, 60, 75, 90 };
		for (int num : multiplesOf15) {
			assertEquals("FizzBuzz", FizzBuzz.getNumberOrFizzBuzz(num));
		}
		
		
	}

}
