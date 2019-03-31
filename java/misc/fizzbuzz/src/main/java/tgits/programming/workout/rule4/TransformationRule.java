/**
 * 
 */
package tgits.programming.workout.rule4;

/**
 * @author cvaudry
 *
 */
public class TransformationRule {
	private final int input;
	private final String output;
	
	public static TransformationRule compose(TransformationRule tr1, TransformationRule tr2){
		return new TransformationRule(tr1.getInput() * tr2.getInput(), tr1.getOutput() + tr2.getOutput());
	}
	
	public TransformationRule(int input, String output) {
		this.input = input;
		this.output = output;
	}

	public int getInput() {
		return this.input;
	}
	
	public String getOutput() {
		return this.output;
	}
	
	@Override
	public String toString() {
		return "(" + Integer.toString(this.input) + "," + this.output + ")";
	}
}
