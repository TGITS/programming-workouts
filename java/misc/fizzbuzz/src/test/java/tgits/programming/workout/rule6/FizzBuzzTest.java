/**
 * 
 */
package tgits.programming.workout.rule6;

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
	public void testCreateFizzBuzzList() {
		List<String> expectedOutput = Arrays.asList(new String[]{"1","2","FIZZ","4","BUZZ","FIZZ","7","8","FIZZ","BUZZ","11","FIZZ","13","14","FIZZBUZZ"});
		FizzBuzz fb = new FizzBuzz();
		List<String> generatedFizzBuzzList = fb.generateFizzBuzzList(15);
		for(int i = 0; i < expectedOutput.size(); i++){
			assertEquals(expectedOutput.get(i),generatedFizzBuzzList.get(i));
		}
	}

}
