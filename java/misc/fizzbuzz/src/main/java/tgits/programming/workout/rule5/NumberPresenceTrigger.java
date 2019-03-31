/**
 * 
 */
package tgits.programming.workout.rule5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author cvaudry
 *
 */
public class NumberPresenceTrigger implements TransformationTrigger<Integer> {

	private final List<String> numbers;

	public NumberPresenceTrigger(String number) {
		this.numbers = new ArrayList<String>();
		this.numbers.add(number);
	}
	
	public NumberPresenceTrigger(String[] number) {
		this.numbers = new ArrayList<String>();
		this.numbers.addAll(Arrays.asList(number));
	}
	
	public NumberPresenceTrigger(List<String> number) {
		this.numbers = new ArrayList<String>();
		this.numbers.addAll(number);
	}

	@Override
	public boolean isTriggerValue(Integer input) {
		if (input == null) {
			return false;
		}

		String s = input.toString();
		boolean test = true;
		for(String number:numbers){
			test = test && s.contains(number);
		}
		return test;
	}
	
	public List<String> getNumbers() {
		return this.numbers;
	}

}
