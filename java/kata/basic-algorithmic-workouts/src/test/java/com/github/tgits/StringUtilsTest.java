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
	public void testNoDuplicate() {
		String[] noDuplicateCharsStrings = {"", "1","abcdefghijkmnopqrstuvwxyz","1234567890","abc123"};
		String[] duplicateCharsStrings = {"111111","abcdefgha","abcdefabcdef"};
		
		for(String s:noDuplicateCharsStrings){
			assertTrue(StringUtils.noDuplicate(s));
		}
		
		for(String s:duplicateCharsStrings){
			assertFalse(StringUtils.noDuplicate(s));
		}
	}

	@Test
	public void testNoDuplicateRec() {
		String[] noDuplicateCharsStrings = {"", "1","abcdefghijkmnopqrstuvwxyz","1234567890","abc123"};
		String[] duplicateCharsStrings = {"111111","abcdefgha","abcdefabcdef"};
		
		for(String s:noDuplicateCharsStrings){
			assertTrue(StringUtils.noDuplicateRec(s));
		}
		
		for(String s:duplicateCharsStrings){
			assertFalse(StringUtils.noDuplicateRec(s));
		}
	}
	
	@Test
	public void testRecognizer() {
		String[] correctSentences = {"{}[]()", "{[()]}","{[()]}{}[]()"};
		String[] incorrectSentences = {"{}([)]","{[()]}{}[](","{[()]}{}[])"};
		
		for(String s:correctSentences){
			assertTrue(StringUtils.openCloseSentenceRecognizer(s));
		}
		
		for(String s:incorrectSentences){
			assertFalse(StringUtils.openCloseSentenceRecognizer(s));
		}
	}
}
