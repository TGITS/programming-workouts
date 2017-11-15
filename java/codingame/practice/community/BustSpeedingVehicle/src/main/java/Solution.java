/*
 * https://www.codingame.com/ide/puzzle/bust-speeding-vehicles
 * */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static boolean aboveAverageSpeed(long speedLimit, long distance, long delay) {
        double speedLimitInKmPerSecond = speedLimit / 3600.0;
        double average = (double) distance / (double) delay;
        System.err.println("Computed average speed : " + Double.toString(average) + " versus speed limit in km/s : " + Double.toString(speedLimitInKmPerSecond));
        return average > speedLimitInKmPerSecond;
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int maxAuthorizedSpeed = in.nextInt();
        System.err.println("Speed Limit : " + maxAuthorizedSpeed);
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        String[] rows = new String[N];
        String licencePlate = null;
        String[] elements = null;
        String currentLicencePlate = null;
        Long currentDistance = null;
        Long currentTimestamp = null;
        List<Result> results = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            rows[i] = in.nextLine();
            elements = rows[i].split(" ");

            if (currentLicencePlate != null && currentLicencePlate.equals(elements[0])) {
                Long newDistance = Long.parseLong(elements[1]);
                Long newTimestamp = Long.parseLong(elements[2]);
                if (aboveAverageSpeed(maxAuthorizedSpeed, newDistance - currentDistance, newTimestamp - currentTimestamp)) {
                    results.add(new Result(newDistance, currentLicencePlate));
                }
                currentDistance = newDistance;
                currentTimestamp = newTimestamp;
            } else {
                currentLicencePlate = elements[0];
                currentDistance = Long.parseLong(elements[1]);
                currentTimestamp = Long.parseLong(elements[2]);
                System.err.println("New Licence plate " + currentLicencePlate);
            }
            System.err.println(rows[i]);
        }

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");
        if (results.isEmpty()) {
            System.out.println("OK");
        } else {
            for (Result result : results) {
                System.out.println(result.toString());
            }
        }
    }
}

class Result {
    private final long distance;
    private final String licencePlate;

    public Result(long distance, String licencePlate) {
        this.distance = distance;
        this.licencePlate = licencePlate;
    }

    public long getDistance() {
        return this.distance;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public String toString() {
        return licencePlate + " " + Long.toString(this.distance);
    }
}