package tgits.programming.workout.rule5;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberValidatorTest {

	@Test
	public void testValidate() {
		NumberValidator validator = new NumberValidator();

		Random random = new Random();
		for (int i = 0; i < 100; i++) {
			assertTrue(validator.validate(Integer.toString(random.nextInt(10000))));
		}

		String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

		for (int i = 0; i < 100; i++) {
			int size = 1 + random.nextInt(10);
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < size; j++) {
				sb.append(letters.charAt(random.nextInt(51)));
			}
			if(random.nextBoolean()) {
				sb.append(random.nextInt(100));
			}
			assertFalse(validator.validate(sb.toString()));
		}
		
	}

}
