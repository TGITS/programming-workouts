package tgits.programming.workout.rule2.withstreams;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FizzBuzzTest {

	@Test
	public void testIsNumber() {
		FizzBuzz fb = new FizzBuzz();
		String input = "1234567890";
		assertTrue(fb.isNumber(input));
		input = "1234567890a";
		assertFalse(fb.isNumber(input));
		input = "FIZZ";
		assertFalse(fb.isNumber(input));
	}
	
	@Test
	public void testTransform2FizzBuzz() {
		FizzBuzz fb = new FizzBuzz();
		String number = "15";
		assertEquals(fb.transform2FizzBuzz(number), "FIZZBUZZ");
		number = "5";
		assertEquals(fb.transform2FizzBuzz(number), number);
		number = "3";
		assertEquals(fb.transform2FizzBuzz(number), number);
		number = "2";
		assertEquals(fb.transform2FizzBuzz(number), number);
	}

	@Test
	public void testTransform2Buzz() {
		FizzBuzz fb = new FizzBuzz();
		String number = "5";
		assertEquals(fb.transform2Buzz(number), "BUZZ");
		number = "15";
		assertEquals(fb.transform2Buzz(number), "BUZZ");
		number = "3";
		assertEquals(fb.transform2Buzz(number), number);
		number = "2";
		assertEquals(fb.transform2Buzz(number), number);
	}

	@Test
	public void testTransform2Fizz() {
		FizzBuzz fb = new FizzBuzz();
		String number = "3";
		assertEquals(fb.transform2Fizz(number), "FIZZ");
		number = "15";
		assertEquals(fb.transform2Fizz(number), "FIZZ");
		number = "5";
		assertEquals(fb.transform2Fizz(number), number);
		number = "2";
		assertEquals(fb.transform2Fizz(number), number);
	}

	@Test
	public void testTransform() {
		List<Integer> input_list = Arrays.asList( new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
				11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
				21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
				31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
				41, 42, 43, 44, 45, 46, 47, 48, 49, 50});
			List<String> expected_output_list = Arrays.asList(new String[] {"1", "2", "FIZZ", "4", "BUZZ", "FIZZ", "7", "8", "FIZZ", "BUZZ",
					"11", "FIZZ", "13", "14", "FIZZBUZZ", "16", "17", "FIZZ", "19", "BUZZ",
					"FIZZ", "22", "23", "FIZZ", "BUZZ", "26", "FIZZ", "28", "29", "FIZZBUZZ",
					"31", "32", "FIZZ", "34", "BUZZ", "FIZZ", "37", "38", "FIZZ", "BUZZ",
					"41", "FIZZ", "43", "44", "FIZZBUZZ", "46", "47", "FIZZ", "49", "BUZZ"});
			List<String> result_list = new FizzBuzz().transform(input_list);
			for(int i = 0; i < result_list.size(); i++  ) {
				assertEquals(expected_output_list.get(i),result_list.get(i));
			}
			
			input_list = Arrays.asList(new Integer[]{1,2,3,5,15,19,20});
			expected_output_list = Arrays.asList(new String[]{"1","2","FIZZ","BUZZ","FIZZBUZZ","19","BUZZ"});
			result_list = new FizzBuzz().transform(input_list);
			for(int i = 0; i < result_list.size(); i++  ) {
				assertEquals(expected_output_list.get(i),result_list.get(i));
			}
	}

}
