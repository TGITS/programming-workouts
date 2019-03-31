/**
 * 
 */
package tgits.programming.workout.rule2.withstreams;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author cvaudry
 *
 */
public class FizzBuzz {

	public static final Pattern PATTERN = Pattern.compile("^\\d+$");

	public boolean isNumber(String input) {
		return input != null && PATTERN.matcher(input).matches();
	}

	public String transform2FizzBuzz(String input) {
		if (isNumber(input) && Integer.parseInt(input) % 15 == 0) {
			return ("FIZZBUZZ");
		}
		return input;
	}

	public String transform2Buzz(String input) {
		if (isNumber(input) && Integer.parseInt(input) % 5 == 0) {
			return ("BUZZ");
		}
		return input;
	}

	public String transform2Fizz(String input) {
		if (isNumber(input) && Integer.parseInt(input) % 3 == 0) {
			return ("FIZZ");
		}
		return input;
	}

	public List<String> transform(List<Integer> numbers) {
		FizzBuzz fb = new FizzBuzz();
		return numbers.stream().map((Integer i) -> i.toString()).map(fb::transform2FizzBuzz).map(fb::transform2Fizz)
				.map(fb::transform2Buzz).collect(Collectors.toList());
	}

}
