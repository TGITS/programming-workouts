package tgits.programming.workout.rule5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FizzBuzzTest {
	
	@Test
	public void testTransformNumber1(){
		
		FizzBuzz fb = new FizzBuzz();
		
		TransformationRule<Integer,String> tr1 = new TransformationRule<Integer,String>("FIZZ", new NumberPresenceTrigger("3"));
		TransformationRule<Integer,String> tr2 = new TransformationRule<Integer,String>("BUZZ", new NumberPresenceTrigger("5"));
		Composer<NumberPresenceTrigger> nptc = new NumberPresenceTriggerComposer();
		NumberPresenceTrigger trigger = nptc.compose((NumberPresenceTrigger)tr1.getTrigger(), (NumberPresenceTrigger)tr2.getTrigger());
		TransformationRule<Integer,String> tr3 = TransformationRule.<Integer,String>compose(tr1, tr2, (Composer<String>)new StringConcatenationComposer(), trigger);

		
		fb.addTransformationRule(tr1);
		fb.addTransformationRule(tr2);
		fb.addTransformationRule(tr3);
		
		
		int[] input_numbers = new int[]{1,2,3,5,15,19,20,23,25,35,53};
		String[] expected_result = new String[]{"1","2","FIZZ","BUZZ","BUZZ","19","20","FIZZ","BUZZ","FIZZBUZZ","FIZZBUZZ"};
		for(int i = 0; i < input_numbers.length; i++  ) {
			assertEquals(expected_result[i], fb.transform(input_numbers[i]));
		}
	}

}
