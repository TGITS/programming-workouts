/**
 * 
 */
package tgits.programming.workout.rule5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author cvaudry
 *
 */
public class TransformationRuleTest {

	@Test
	public void testCompose() {
		TransformationRule<Integer,String> tr1 = new TransformationRule<Integer,String>("FIZZ", new NumberPresenceTrigger("3"));
		TransformationRule<Integer,String> tr2 = new TransformationRule<Integer,String>("BUZZ", new NumberPresenceTrigger("5"));
		Composer<NumberPresenceTrigger> nptc = new NumberPresenceTriggerComposer();
		NumberPresenceTrigger trigger = nptc.compose((NumberPresenceTrigger)tr1.getTrigger(), (NumberPresenceTrigger)tr2.getTrigger());
		TransformationRule<Integer,String> tr3 = TransformationRule.<Integer,String>compose(tr1, tr2, (Composer<String>)new StringConcatenationComposer(), trigger);
		assertEquals("FIZZBUZZ",tr3.getOutput());
		assertTrue(tr3.getTrigger().isTriggerValue(35));
		assertTrue(tr3.getTrigger().isTriggerValue(13350));
		assertTrue(tr3.getTrigger().isTriggerValue(53));
		assertFalse(tr3.getTrigger().isTriggerValue(3));
		assertFalse(tr3.getTrigger().isTriggerValue(5));
		
	}

}
