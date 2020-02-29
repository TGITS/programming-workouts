package com.github.tgits.fizzbuzz;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FizzBuzz {

	public static final int TRESHOLD = 1;
	public static final String INPUT_INTEGER_DOES_NOT_MATCH_FOR_THE_GAME = "Input integer does not match for the game";
	public static final String START_VALUE_IS_GREATER_THAN_END_VALUE = "Start value is greater than end value";
	public static final String FIZZ = "Fizz";
	public static final String BUZZ = "Buzz";
	public static final String FIZZ_BUZZ = FIZZ + BUZZ;

	public String execute(int input) {

		if (input < TRESHOLD) {
			throw new IllegalArgumentException(INPUT_INTEGER_DOES_NOT_MATCH_FOR_THE_GAME);
		}

		boolean isMultipleOf3 = (input % 3 == 0);
		boolean isMultipleOf5 = (input % 5 == 0);

		String output = "";
		if (isMultipleOf3) output += FIZZ;
		if (isMultipleOf5) output += BUZZ;
		return output.isBlank() ? Integer.toString(input) : output;
	}

	public List<String> execute(List<Integer> values) {
		return values.stream().map(this::execute).collect(Collectors.toList());
	}

	public List<String> generate(int start, int end) {
		if (end < start) {
			throw new IllegalArgumentException(START_VALUE_IS_GREATER_THAN_END_VALUE);
		}
		return IntStream.rangeClosed(start, end).mapToObj(this::execute).collect(Collectors.toList());
	}
}
