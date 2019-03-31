/**
 * 
 */
package tgits.programming.workout.rule5;

/**
 * @author cvaudry
 *
 */
public class NumberConcatenationComposer implements Composer<Integer> {

	@Override
	public Integer compose(Integer val1, Integer val2) {
		if(val1 == null || val2 == null) {
			throw new IllegalArgumentException("We can only compose valid NumberPresenceChecker");
		}
		return Integer.parseInt(val1.toString() + val2.toString());
	}

}
