/**
 * 
 */
package tgits.programming.workout.rule2.variante;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cvaudry
 *
 */
public class FizzBuzz {
	public List<String> transform(List<Integer> numbers){
		List<String> strings = new ArrayList<String>();
		for(Integer number:numbers) {
			strings.add(transform(number));
		}
		return strings;
	}
	
	public String transform(int number){
		StringBuilder sb = new StringBuilder();
		
		if (number % 3 == 0) {
			sb.append("FIZZ");
		}
		
		if (number % 5 == 0) {
			sb.append("BUZZ");
		}
		
		if (number % 3 != 0 && number % 5 != 0){
			sb.append(number);
		}
		
		return (sb.toString());
	}
}
