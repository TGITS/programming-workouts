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
			System.err.print(values[i] + " ");
		}
		System.err.println();
		in.close();

		int gap = 0;

		int i = 0;
		while(i < values.length - 1){
			int temp = 0;
			for (int j = i + 1; j < values.length; j++) {
			    if (values[j] > values[i]) {
			        break;
			    }
				if (gap < (values[i] - values[j])) {
					gap = values[i] - values[j];
					temp = j;
				}
			}
			if(temp == 0){
				i++;
			}else {
				i = temp;
			}
		}

		System.out.println(-gap);
	}

}