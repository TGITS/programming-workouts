import java.util.*;
import java.util.stream.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // the number of temperatures to analyse
        if (in.hasNextLine()) {
            in.nextLine();
        }
        String temps = in.nextLine(); // the n temperatures expressed as integers ranging from -273 to 5526

        if( n > 0) {
            String[] temperatures = temps.split("\\s");
            System.err.println("temps : " + temps);
            for(String t:temperatures) {
                System.err.println("t : " + t);
            }

            OptionalInt min = Stream.of(temperatures).mapToInt(Integer::parseInt).filter(t -> t >= 0).min();
            OptionalInt max = Stream.of(temperatures).mapToInt(Integer::parseInt).filter(t -> t < 0).max();
            if(min.isPresent()) {
                System.err.println("min positive integer : " + min.getAsInt());
            }
            if(max.isPresent()) {
                System.err.println("max negative integer : " + max.getAsInt());
            }

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            if(!min.isPresent() && !max.isPresent()) {
                System.out.println(0);
            }
            else if(!min.isPresent()) {
                System.out.println(max.getAsInt());
            }
            else if(!max.isPresent()) {
                System.out.println(min.getAsInt());
            }
            else if(min.getAsInt() <= Math.abs(max.getAsInt())) {
                System.out.println(min.getAsInt());
            }
            else {
                System.out.println(max.getAsInt());
            }
        }
        else {
          System.out.println(0);
        }
    }
}
