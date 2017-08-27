/**
 * 
 */
package com.github.tgits;

import java.util.LinkedList;

/**
 * @author cvaudry
 *
 */
public class StringUtils {

	private StringUtils() {
		super();
	}

	/**
	 * @parameter String s
	 * @return boolean
	 * 
	 *         return true if there is no character that is duplicated in the
	 *         string, false otherwise
	 */
	public static boolean noDuplicate(String s) {
		int length = s.length();
		if (length == 0 || length == 1) {
			return true;// No possible Duplicate if zero or only one character.
		}

		for (int i = 0; i < length - 1; i++) {
			for (int j = i + 1; j < length; j++) {
				if (s.charAt(i) == s.charAt(j)) {
					return false; // Duplicate found
				}
			}
		}

		return true; // No duplicate found
	}

	/**
	 * @parameter String s
	 * @return boolean
	 * 
	 *         return true if there is no character that is duplicated in the
	 *         string, false otherwise
	 */
	public static boolean noDuplicateRec(String s) {
		int length = s.length();
		if (length == 0 || length == 1) {
			return true;// No possible duplicate if zero or only one character.
		}

		for (int i = 0; i < length - 1; i++) {
			if (charIn(i, 1, s)) {
				return false;
			}
		}

		return true;
	}

	private static boolean charIn(int i, int j, String s) {
		int length = s.length();
		if (length - j == 1) {
			return s.charAt(i) == s.charAt(j);
		}

		return charIn(i, j + 1, s);
	}

	/*
	 * @param s String with only {}[]()
	 * 
	 * @return boolean if the sentence are correct {}[]() is correct {[()]} is
	 * correct ([)] is not correct
	 */
	public static boolean openCloseSentenceRecognizer(String s) {
		LinkedList<Character> stack = new LinkedList<>();
		for (int i = 0; i < s.length(); i++) {
			switch (s.charAt(i)) {
			case '{':
			case'(':
			case'[': 
				stack.push(s.charAt(i));
				break;
			case '}':
			case ')':
			case ']':
				if(stack.isEmpty() || !correspondingChar(stack.pop(),s.charAt(i))){
					return false;
				}
				break;
			default:
				break;
			}
		}
		return stack.isEmpty();
	}
	
	private static boolean correspondingChar(char c1, char c2) {
		switch (c1) {
		case '{': 
			return c2 == '}';
		case'(':
			return c2 == ')';
		case'[': 
			return c2 == ']';
		default:
			return false;
		}
	}

}
