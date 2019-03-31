/**
 * 
 */
package tgits.programming.workout.rule5;

/**
 * @author cvaudry
 *
 */
public class TransformationRule<T, R> {
	private final R output;
	private final TransformationTrigger<T> trigger;

	public static <T, R> TransformationRule<T, R> compose(TransformationRule<T, R> tr1, TransformationRule<T, R> tr2,
			Composer<R> outputValueComposer, TransformationTrigger<T> trigger) {
		return new TransformationRule<T, R>(
				outputValueComposer.compose(tr1.getOutput(), tr2.getOutput()), trigger);
	}

	public TransformationRule(R output, TransformationTrigger<T> trigger) {
		this.output = output;
		this.trigger = trigger;
	}

	public R getOutput() {
		return this.output;
	}

	public TransformationTrigger<T> getTrigger() {
		return this.trigger;
	}
	
	@Override
	public String toString() {
		return "(" + this.output.toString() + ")";
	}
}
