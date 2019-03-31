/**
 * 
 */
package tgits.programming.workout.rule5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author cvaudry
 *
 */
public class NumberPresenceTriggerTest {

	@Test
	public void testIsTriggerValueTrue() {
		
		int[] testValues = new int[]{125,1250,12524,127589};
		String[] inputValues = new String[]{"5","12"};
		NumberPresenceTrigger npc = null;
		for(String inputValue:inputValues) {
			npc = new NumberPresenceTrigger(inputValue);
			for(int testValue:testValues) {
				assertTrue(npc.isTriggerValue(testValue));
			}
		}
	}
	
	@Test
	public void testIsTriggerValueFalse() {
		
		int[] testValues = new int[]{125,1250,12524,127589};
		String[] inputValues = new String[]{"3","344"};
		NumberPresenceTrigger npc = null;
		for(String inputValue:inputValues) {
			npc = new NumberPresenceTrigger(inputValue);
			for(int testValue:testValues) {
				assertFalse(npc.isTriggerValue(testValue));
			}
		}
	}

}
