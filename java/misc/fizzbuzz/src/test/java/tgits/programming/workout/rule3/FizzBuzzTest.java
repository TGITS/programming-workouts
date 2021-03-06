/**
 * 
 */
package tgits.programming.workout.rule3;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author cvaudry
 *
 */
public class FizzBuzzTest {

	@Test
	public void testTransformNumber1(){
		FizzBuzz fb = new FizzBuzz(3, "FIZZ",5,"BUZZ");
		int[] input_numbers = new int[]{1,2,3,5,15,19,20};
		String[] expected_result = new String[]{"1","2","FIZZ","BUZZ","FIZZBUZZ","19","BUZZ"};
		for(int i = 0; i < input_numbers.length; i++  ) {
			assertEquals(expected_result[i], fb.transform(input_numbers[i]));
		}
	}
	
	@Test
	public void testTransformNumber2(){
		FizzBuzz fb = new FizzBuzz(2, "FUZZ",7,"BIZZ");
		int[] input_numbers = new int[]{1,2,3,5,7,10,14,15,19,20};
		String[] expected_result = new String[]{"1","FUZZ","3","5","BIZZ","FUZZ","FUZZBIZZ","15","19","FUZZ"};
		for(int i = 0; i < input_numbers.length; i++  ) {
			assertEquals(expected_result[i], fb.transform(input_numbers[i]));
		}
	}
	
	@Test
	public void testTransformList() {
		FizzBuzz fb = new FizzBuzz(3, "FIZZ",5,"BUZZ");
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
		List<String> result_list = fb.transform(input_list);
		for(int i = 0; i < result_list.size(); i++  ) {
			assertEquals(expected_output_list.get(i),result_list.get(i));
		}
	}
	
	@Test
	public void testTransformAnyList() {
		FizzBuzz fb = new FizzBuzz(3, "FIZZ",5,"BUZZ");
		List<Integer> input_list = Arrays.asList(new Integer[]{1,2,3,5,15,19,20});
		List<String> expected_output_list = Arrays.asList(new String[]{"1","2","FIZZ","BUZZ","FIZZBUZZ","19","BUZZ"});
		List<String> result_list = fb.transform(input_list);
		for(int i = 0; i < result_list.size(); i++  ) {
			assertEquals(expected_output_list.get(i),result_list.get(i));
		}
	}
}
