package tgits.programming.workout.rule4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransformationRuleTest {

	@Test
	public void testTransformationRule() {
		TransformationRule tr_fizz = new TransformationRule(3,"FIZZ");
		TransformationRule tr_buzz = new TransformationRule(5,"BUZZ");
		assertEquals(3,tr_fizz.getInput());
		assertEquals("FIZZ", tr_fizz.getOutput());
		assertEquals("(3,FIZZ)", tr_fizz.toString());
		assertEquals(5,tr_buzz.getInput());
		assertEquals("BUZZ", tr_buzz.getOutput());
		assertEquals("(5,BUZZ)", tr_buzz.toString());
	}
	
	@Test
	public void testCompose() {
		TransformationRule tr_fizz = new TransformationRule(3,"FIZZ");
		TransformationRule tr_buzz = new TransformationRule(5,"BUZZ");
		TransformationRule tr_fizzbuzz = TransformationRule.compose(tr_fizz, tr_buzz);
		assertEquals(3,tr_fizz.getInput());
		assertEquals("FIZZ", tr_fizz.getOutput());
		assertEquals("(3,FIZZ)", tr_fizz.toString());
		assertEquals(5,tr_buzz.getInput());
		assertEquals("BUZZ", tr_buzz.getOutput());
		assertEquals("(5,BUZZ)", tr_buzz.toString());
		assertEquals(15,tr_fizzbuzz.getInput());
		assertEquals("FIZZBUZZ", tr_fizzbuzz.getOutput());
		assertEquals("(15,FIZZBUZZ)", tr_fizzbuzz.toString());
	}

}
