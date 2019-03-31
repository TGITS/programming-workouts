/**
 * 
 */
package tgits.programming.workout.rule5;

import java.util.regex.Pattern;

/**
 * @author cvaudry
 *
 */
public class NumberValidator implements InputValidator<String> {
	
	public static final Pattern PATTERN = Pattern.compile("^\\d+$");

	@Override
	public boolean validate(String input) {
		return input != null && PATTERN.matcher(input).matches();
	}

}
