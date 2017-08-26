/**
 * 
 */
package com.github.tgits;

/**
 * @author cvaudry
 *
 */
public class StringUtils {
	
	/**
	 * @parameter String s
	 * @return boolean
	 * 
	 * return true if there is no character that is duplicated in the string, false otherwise
	 *  */
	public static boolean noDuplicate(String s){
		int length = s.length();
		if(length == 1) {
			return true;//No possible Duplicate if only one character.
		}
		
		for(int i = 0; i < length-1; i++) {
			for(int j = i+1; j < length; j++){
				if(s.charAt(i) == s.charAt(j)) {
					return false; //Duplicate found
				}
			}
		}
		
		return true; //No duplicate found
	}
	
	/*
	 * @param s String
	 * @return boolean
	 * */
	public static boolean openCloseSentenceRecognizer(String s){
		return true;
	}

}
