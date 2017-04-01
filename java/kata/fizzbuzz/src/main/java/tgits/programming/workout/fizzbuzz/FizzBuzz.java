/**
 * 
 */
package tgits.programming.workout.fizzbuzz;

import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author cvaudry
 *
 */
public class FizzBuzz {

	private static final int MAX_ITEMS_DEFAULT = 50;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int maxItems;
		if (args.length > 0) {
			try {
				maxItems = Integer.parseInt(args[0]);
			} catch (NumberFormatException ex) {
				ex.printStackTrace();
				maxItems = MAX_ITEMS_DEFAULT;
			}
		} else {
			maxItems = MAX_ITEMS_DEFAULT;
		}
		List<String> list = Stream.iterate(1, i -> i + 1).map(FizzBuzz::getNumberOrFizzBuzz).limit(maxItems)
				.collect(Collectors.<String>toList());
		System.out.println(list);
	}

	public static IntPredicate makeDivisibilityTester(int divider) {
		return (int number) -> number % divider == 0;
	}

	public static String getNumberOrFizzBuzz(int number) {
		IntPredicate divisibleBy15 = makeDivisibilityTester(15);
		IntPredicate divisibleBy5 = makeDivisibilityTester(5);
		IntPredicate divisibleBy3 = makeDivisibilityTester(3);

		if (divisibleBy15.test(number)) {
			return "FizzBuzz";
		} else if (divisibleBy5.test(number)) {
			return "Fizz";
		} else if (divisibleBy3.test(number)) {
			return "Buzz";
		} else {
			return Integer.toString(number);
		}
	}
}
