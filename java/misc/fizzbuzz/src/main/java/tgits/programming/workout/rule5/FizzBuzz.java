/**
 * 
 */
package tgits.programming.workout.rule5;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author cvaudry
 *
 */
public class FizzBuzz {
	private final Deque<TransformationRule<Integer,String>> transformationRules = new LinkedList<TransformationRule<Integer,String>>();
	
	public FizzBuzz() {
		super();
	}
	
	public void addTransformationRule(TransformationRule<Integer,String> tr) {
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
		for (TransformationRule<Integer,String> rt:transformationRules) {
			if (rt.getTrigger().isTriggerValue(number)) {
				return (rt.getOutput());
			}
		}
		return (Integer.toString(number));
	}
	
	public String displayTransformationRule() {
		StringBuilder sb = new StringBuilder();
		for (TransformationRule<Integer,String> rt:transformationRules) {
			sb.append(rt.toString());
		}
		return sb.toString();
	}
}
