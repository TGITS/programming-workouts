/**
 * 
 */
package tgits.programming.workout.fizzbuzz;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author cvaudry
 *
 */
public class FizzBuzz {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> list = Stream.iterate(1, (i) -> i+1).map((i) -> { return (i%15 == 0 ? "FizzBuzz" : (i%5 == 0 ? "Fizz" : (i%3 == 0 ? "Buzz" : Integer.toString(i))));}).limit(45).collect(Collectors.<String>toList());
		System.out.println(list);
	}

}
