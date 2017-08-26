/**
 * 
 */
package com.github.tgits;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author cvaudry
 *
 */
public class StringUtilsTest {

	@Test
	public void test() {
		String[] noDuplicateCharsStrings = {"abcdefghijkmnopqrstuvwxyz","1234567890","abc123"};
		String[] duplicateCharsStrings = {"111111","abcdefgha","abcdefabcdef"};
		
		for(String s:noDuplicateCharsStrings){
			assertTrue(StringUtils.noDuplicate(s));
		}
		
		for(String s:duplicateCharsStrings){
			assertFalse(StringUtils.noDuplicate(s));
		}
	}

}
