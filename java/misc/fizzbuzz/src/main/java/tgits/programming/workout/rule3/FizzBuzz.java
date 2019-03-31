/**
 * 
 */
package tgits.programming.workout.rule3;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cvaudry
 *
 */
public class FizzBuzz {
	public static class TransformationParameter {
		private final int source;
		private final String target;

		public TransformationParameter(int source, String target) {
			this.source = source;
			this.target = target;
		}

		public int getSource() {
			return source;
		}

		public String getTarget() {
			return target;
		}
	}

	private TransformationParameter[] parameters;

	public FizzBuzz(int source1, String target1, int source2, String target2) {
		this.parameters = new TransformationParameter[3];
		this.parameters[0] = new TransformationParameter(source1, target1);
		this.parameters[1] = new TransformationParameter(source2, target2);
		this.parameters[2] = new TransformationParameter(source1 * source2, target1 + target2);
	}

	public List<String> transform(List<Integer> numbers) {
		List<String> strings = new ArrayList<String>();
		for (Integer number : numbers) {
			strings.add(transform(number));
		}
		return strings;
	}

	public String transform(int number) {
		for (int i = 2; i >= 0; i--) {
			if (number % this.parameters[i].getSource() == 0) {
				return (this.parameters[i].getTarget());
			}
		}
		return (Integer.toString(number));
	}
}
