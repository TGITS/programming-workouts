/**
 * 
 */
package tgits.programming.workout.rule5;

/**
 * @author cvaudry
 *
 */
public class StringConcatenationComposer implements Composer<String> {

	@Override
	public String compose(String val1, String val2) {
		if(val1 == null || val2 == null) {
			throw new IllegalArgumentException("We cannot compose null String");
		}
		return val1 + val2;
	}

}
