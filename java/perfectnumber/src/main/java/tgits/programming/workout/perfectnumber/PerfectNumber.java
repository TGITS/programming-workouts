package tgits.programming.workout.perfectnumber;

import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * Perfect Number with Streams
 *
 */
public class PerfectNumber {
	
	private static final int MAX_ITEMS_DEFAULT = 10000;
	
	
	public static void main(String[] args) {
		
		int maxItems;
		if (args.length > 0) {
			try {
				maxItems = Integer.parseInt(args[0]);
			} catch (NumberFormatException ex) {
				ex.printStackTrace();
				maxItems = MAX_ITEMS_DEFAULT;
			}
		} else {
			maxItems = MAX_ITEMS_DEFAULT;
		}
		System.out.println("Perfect Numbers up to " + maxItems);
		String list = IntStream.rangeClosed(1, maxItems).filter(new PerfectNumberPredicate()).mapToObj(i -> ((Integer) i).toString())
				.collect(Collectors.joining(", "));
		System.out.println(list);
	}
}

class PerfectNumberPredicate implements IntPredicate {
	/* *
	 * A number is perfect if it is equal to the sum of its dividers divided by 2 
	 * 2 × 6 = 12 = 1 + 2 + 3 + 6 or 6 = 1 + 2 + 3
	 */
	
	private int sumOfDividers(int value){
		return IntStream.rangeClosed(1, value).filter(i -> value % i == 0).sum();
	}
	
	@Override
	public boolean test(int value) {
		//value must be a positive integer
		return sumOfDividers(value) == 2*value;
	}

}
