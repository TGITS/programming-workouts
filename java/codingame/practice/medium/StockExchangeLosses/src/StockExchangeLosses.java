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
		
		List<Integer> gaps = new ArrayList<>();
		
		for(int i=0; i < values.length - 1; i++){
			for (int j = i+1; j < values.length; j++){
				if(values[j]<values[i]) {
					gaps.add(values[i] - values[j]);
				}
			}
		}
		int gap = 0;
		if(!gaps.isEmpty()) {
			gap = -Collections.max(gaps);
		}
		
		System.err.println("Gap : " + gap);
		System.out.println(gap);
	}
	
}