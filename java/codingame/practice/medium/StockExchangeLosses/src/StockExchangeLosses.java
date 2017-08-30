import java.util.*;
import java.util.stream.*;

/**
 * Auto-generated code below aims at helping you parse the standard input
 * according to the problem statement.
 **/
class Solution {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		Integer[] values = new Integer[n];
		for (int i = 0; i < n; i++) {
			values[i] = in.nextInt();
		}
		in.close();
		
		System.err.println("Values : " + String.join(";", Stream.of(values).map(i -> Integer.toString(i)).collect(Collectors.joining(" , "))));

		int iCurrent = 0;
		int iNext = 1;
		int current = 0;
		int next = 0;
		int minGap = 0;
		int currentGap = 0;
		while (iNext < values.length) {
		    current = values[iCurrent];
		    next = values[iNext];
			if (next < current) {
				currentGap += (next - current);
				if (currentGap < minGap) {
					minGap = currentGap;
				}
			} else {
				currentGap = 0;
			}
			iCurrent++;
			iNext++;
		}

		System.out.println(minGap);
	}
}