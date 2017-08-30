import java.util.*;

/**
 * Auto-generated code below aims at helping you parse the standard input
 * according to the problem statement.
 **/
class Solution {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] values = new int[n];
		for (int i = 0; i < n; i++) {
			values[i] = in.nextInt();
		}
		in.close();

		int gap = 0;

		for (int i = 0; i < values.length - 1; i++) {
			for (int j = i + 1; j < values.length; j++) {
				if (gap < (values[i] - values[j])) {
					gap = values[i] - values[j];
				}
			}
		}

		System.out.println(-gap);
	}

}