/**
 * 
 */
package com.github.tgits;

/**
 * @author cvaudry
 *
 */
public class IntegerUtils {
	
	private IntegerUtils() {
		super();
	}
	
	public static boolean isEven(int number){
		if (number < 0) {
			throw new IllegalArgumentException("The given number must be a positive integer");
		}
		return number % 2 == 0;
	}
	
	public static boolean isOdd(int number){
		if (number < 0) {
			throw new IllegalArgumentException("The given number must be a positive integer");
		}
		return !isEven(number);
	}

	public static boolean isPrime(int number) {
		
		if (number <= 1) {
			throw new IllegalArgumentException("The given number must be an integer strictly greater than 1");
		}
		
		if(number%2 == 0) {
			return number == 2; 
		}
		
		for(int i=3; i < Math.sqrt(number)+1; i += 2) {
			if(number%i == 0) {
				return false;
			}
		}
		
		return true;
	}
	
}
