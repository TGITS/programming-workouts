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
		
		int minGap = 0;
		int indexMin = indexMinFromEnd(values);
		System.err.println("Index Min : " + indexMin);
		if(indexMin > 0) {
			int indexMaxBeforeIndexMin = indexMaxBeforeLimitIndex(values,indexMin);
			System.err.println("Index Max : " + indexMaxBeforeIndexMin);
			minGap = values[indexMin] - values[indexMaxBeforeIndexMin];
		}
		System.err.println("Gap : " + minGap);
		System.out.println(minGap);
	}
	
	private static int indexMinFromEnd(Integer[] array){
		int maxSize = array.length-1;
		int min = array[maxSize];
		int index = maxSize;
		for(int i=maxSize; i >= 0 ; i--){
			if(min > array[i]){
				min = array[i];
				index = i;
			}
		}
		return index;
	}
	
	private static int indexMaxBeforeLimitIndex(Integer[] array, int limitIndex){
		int max = array[0];
		int index = 0;
		for(int i=1; i < limitIndex ;i++) {
			if(max < array[i]) {
				max = array[i];
				index = i;
			}
		}
		return index;
	}
}