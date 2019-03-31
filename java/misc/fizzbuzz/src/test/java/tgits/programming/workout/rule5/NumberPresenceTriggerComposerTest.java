package tgits.programming.workout.rule5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberPresenceTriggerComposerTest {

	@Test
	public void testCompose() {
		NumberPresenceTriggerComposer npcc = new NumberPresenceTriggerComposer();
		NumberPresenceTrigger npc1 = new NumberPresenceTrigger("5");
		NumberPresenceTrigger npc2 = new NumberPresenceTrigger("3");
		NumberPresenceTrigger npc3 = npcc.compose(npc1, npc2);
		assertTrue(npc3.getNumbers().contains("5"));
		assertTrue(npc3.getNumbers().contains("3"));
		assertFalse(npc3.getNumbers().contains("53"));
		assertFalse(npc3.getNumbers().contains("35"));
	}

}
