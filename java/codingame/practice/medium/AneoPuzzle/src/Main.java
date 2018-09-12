import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Line 1: An integer speed for the maximum speed allowed on the portion of the road (in km / h).
 *
 * Line 2: An integer lightCount for the number of traffic lights on the road.
 *
 * lightCount next lines:
 * - An integer distance representing the distance of the traffic light from the starting point (in meters).
 * - An integer duration representing the duration of the traffic light on each color.
 *
 * A traffic light alternates a period of duration seconds in green and then duration seconds in red.
 * All traffic lights turn green at the same time as you enter the area.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int speed = in.nextInt();
        int lightCount = in.nextInt();
        for (int i = 0; i < lightCount; i++) {
            int distance = in.nextInt();
            int duration = in.nextInt();
        }

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println("answer");
    }
}