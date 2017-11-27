/**
 * 
 */
package tgits;

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
		if( number % 15 == 0) {
			return ("FIZZBUZZ");
		}
		
		if (number % 5 == 0) {
			return ("BUZZ");
		}
		
		if (number % 3 == 0) {
			return ("FIZZ");
		}
		
		return (Integer.toString(number));
	}
}


