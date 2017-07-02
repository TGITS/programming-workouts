package tgits;

import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] powers = new int[N];
        for (int i = 0; i < N; i++) {
            powers[i] = in.nextInt();
        }
        Arrays.sort(powers);
        int[] differences = new int[powers.length-1];
        for(int i = 0; i < powers.length-1; i++) {
            differences[i] = powers[i+1] - powers[i];
        }
        Arrays.sort(differences);

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(Integer.toString(differences[0]));
    }
}