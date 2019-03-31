/**
 * 
 */
package tgits.programming.workout.rule6;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cvaudry
 *
 */
public class FizzBuzz {
	
	public List<String> generateFizzBuzzList(int upperBound){
		List<String> result = new ArrayList<>();
		for(int i = 1; i <= upperBound; i++) {
			result.add(Integer.toString(i));
		}
		
		for(int i = 2; i <= upperBound; i+=3){
			result.set(i,"FIZZ");
		}
		
		for(int i = 4; i <= upperBound; i+=5){
			result.set(i,"BUZZ");
		}
		
		for(int i = 14; i <= upperBound; i+=15){
			result.set(i,"FIZZBUZZ");
		}
		
		return result;
	}

}
