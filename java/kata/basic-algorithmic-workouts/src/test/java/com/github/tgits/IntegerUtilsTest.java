/**
 * 
 */
package com.github.tgits;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

/**
 * @author cvaudry
 *
 */
public class IntegerUtilsTest {
	
	private Random random = new Random();

	@Test
	public void testIsEven() {
		for (int i = 0; i < 100; i += 2) {
			assertTrue(IntegerUtils.isEven(i));
		}
		
		for(int i = 1; i < 100; i += 2) {
			assertFalse(IntegerUtils.isEven(i));
		}
	}
	
	@Test
	public void testIsOdd() {
		for (int i = 0; i < 100; i += 2) {
			assertFalse(IntegerUtils.isOdd(i));
		}
		
		for(int i = 1; i < 100; i += 2) {
			assertTrue(IntegerUtils.isOdd(i));
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testIsEvenOnNegativeNumber(){
		int number = 0;
		while(number >= 0) {
			number = random.nextInt();
		}
		IntegerUtils.isEven(number);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testIsOddOnNegativeNumber(){
		int number = 0;
		while(number >= 0) {
			number = random.nextInt();
		}
		IntegerUtils.isOdd(number);
	}
	
	@Test
	public void testIsPrime() {
		int[] knownPrimes = {2,3,5,7,11,13,17,19,23};
		int[] knownNotPrimes = {4,6,8,9,10,12,14,15,16,18,20,33,100,1000,2000};
		for (int number:knownPrimes) {
			assertTrue(IntegerUtils.isPrime(number));
		}
		
		for (int number:knownNotPrimes) {
			assertFalse(IntegerUtils.isPrime(number));
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testIsPrimeOnNegativeNumber(){
		int number = 0;
		while(number > 1) {
			number = random.nextInt();
		}
		IntegerUtils.isPrime(number);
	}

}
