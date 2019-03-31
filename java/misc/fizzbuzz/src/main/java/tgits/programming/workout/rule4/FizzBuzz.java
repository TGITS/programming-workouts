/**
 * 
 */
package tgits.programming.workout.rule4;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author cvaudry
 *
 */
public class FizzBuzz {
	private final Deque<TransformationRule> transformationRules = new LinkedList<TransformationRule>();
	
	public FizzBuzz() {
		super();
	}
	
	public void addTransformationRule(TransformationRule tr) {
		transformationRules.addFirst(tr);
	}
	
	public List<String> transform(List<Integer> numbers) {
		List<String> strings = new ArrayList<String>();
		for (Integer number : numbers) {
			strings.add(transform(number));
		}
		return strings;
	}

	public String transform(int number) {
		for (TransformationRule rt:transformationRules) {
			if (number % rt.getInput() == 0) {
				return (rt.getOutput());
			}
		}
		return (Integer.toString(number));
	}
	
	public String displayTransformationRule() {
		StringBuilder sb = new StringBuilder();
		for (TransformationRule rt:transformationRules) {
			sb.append(rt.toString());
		}
		return sb.toString();
	}

}
