/**
 * 
 */
package tgits.programming.workout.rule5;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cvaudry
 *
 */
public class NumberPresenceTriggerComposer implements Composer<NumberPresenceTrigger> {

	@Override
	public NumberPresenceTrigger compose(NumberPresenceTrigger val1, NumberPresenceTrigger val2) {
		if(val1 == null || val2 == null) {
			throw new IllegalArgumentException("We cannot compose null Integer");
		}
		List<String> numbers = new ArrayList<>();
		numbers.addAll(val1.getNumbers());
		numbers.addAll(val2.getNumbers());
		return new NumberPresenceTrigger(numbers);
	}

}
