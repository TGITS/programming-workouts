package com.github.tgits.fizzbuzz;

import net.jqwik.api.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;


public class FizzBuzzTest {

	private FizzBuzz fb;

	@BeforeEach
	void init() {
		fb = new FizzBuzz();
	}

	@ParameterizedTest
	@ValueSource(ints = {3, 6, 9, 12, 18})
	@DisplayName("Given a multiple of 3 then we get Fizz")
	void shouldReturnFizz(int input) {
		Assertions.assertEquals(FizzBuzz.FIZZ, fb.execute(input));
	}


	@ParameterizedTest
	@ValueSource(ints = {5, 10, 20, 25, 35})
	@DisplayName("Given a multiple of 5 then we get Fizz")
	void shouldReturnBuzz(int input) {
		Assertions.assertEquals(FizzBuzz.BUZZ, fb.execute(input));
	}

	@ParameterizedTest
	@ValueSource(ints = {15, 30, 45, 60, 75})
	@DisplayName("Given a multiple of 3 and 5 then we get FizzBuzz")
	void shouldReturnFizzBuzz(int input) {
		Assertions.assertEquals("FizzBuzz", fb.execute(input));
	}

	@ParameterizedTest
	@ValueSource(ints = {1, 2, 4, 7, 11, 61, 67, 43, 502})
	@DisplayName("Given a number that is neither multiple of 3 and 5 then we get the number as a String")
	void shouldReturnTheNumberAsString(int input) {
		Assertions.assertEquals(Integer.toString(input), fb.execute(input));
	}

	@ParameterizedTest
	@ValueSource(ints = {0, -1, -2, -4, -7, -12, -60, -500})
	@DisplayName("Given a number that is strictly less than 1, an exception is raised")
	void shouldRaisedAnIllegalArgumentException(int input) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> fb.execute(input));
	}

	@Test
	@DisplayName("Given a list of numbers greater or equal to 1, then a list of strings correctly 'fizzbuzed' is returned")
	void shouldFizzBuzzListOfNumber() {
		List<Integer> values = List.of(2, 3, 4, 5, 7, 12, 15, 26);
		List<String> expected = List.of("2", FizzBuzz.FIZZ, "4", FizzBuzz.BUZZ, "7", FizzBuzz.FIZZ, FizzBuzz.FIZZ_BUZZ, "26");
		List<String> result = fb.execute(values);
		Assertions.assertIterableEquals(expected, result);
	}

	@Test
	@DisplayName("Given a start and end value for a range of integers then a 'Fizzbuzzed' list is returned")
	void shouldGiveAFizzBuzzList() {
		int start = 1;
		int end = 15;
		List<String> expected = List.of("1", "2", FizzBuzz.FIZZ, "4", FizzBuzz.BUZZ, FizzBuzz.FIZZ,
				"7", "8", FizzBuzz.FIZZ, FizzBuzz.BUZZ, "11", FizzBuzz.FIZZ, "13", "14", FizzBuzz.FIZZ_BUZZ);
		List<String> result = fb.generate(start, end);
		Assertions.assertIterableEquals(expected, result);
	}

	@Test
	@DisplayName("Given a start value greater than the end value, an IllegalArgumentException should be raised")
	void shouldRaiseAnIllegalArgumentExceptionOnIncoherentRangeLimit() {
		int start = 15;
		int end = 1;
		Assertions.assertThrows(IllegalArgumentException.class, () -> fb.generate(start, end));
	}

	@Test
	@DisplayName("Given a negative start range, an exception should be raised")
	void shouldRaiseAnIllegalArgumentExceptionOnNegativeStartRange() {
		int start = -5;
		int end = 5;
		Assertions.assertThrows(IllegalArgumentException.class, () -> fb.generate(start, end));
	}

}
