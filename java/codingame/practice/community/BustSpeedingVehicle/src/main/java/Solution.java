/*
 * https://www.codingame.com/ide/puzzle/bust-speeding-vehicles
 * */

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

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
        Recording currentRecording = null;
        List<Result> results = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            rows[i] = in.nextLine();
            elements = rows[i].split(" ");
            if (currentRecording == null) {
                System.err.println("New Recording : ");
                currentRecording = new Recording(elements[0]);
                CameraCapture cc = new CameraCapture(elements[1], elements[2]);
                currentRecording.add(cc);
                System.err.println("New Licence plate " + currentRecording.getLicencePlate());
            } else {
                if (currentRecording.sameLicencePlate(elements[0])) {
                    CameraCapture cc = new CameraCapture(elements[1], elements[2]);
                    currentRecording.add(cc);
                    System.err.println("New Recording for " + currentRecording.getLicencePlate() + " " + elements[0]);
                } else {
                    Recording newRecording = new Recording(elements[0]);
                    CameraCapture cc = new CameraCapture(elements[1], elements[2]);
                    newRecording.add(cc);
                    System.err.println("Computing average speed for " + currentRecording.getLicencePlate());
                    Optional<Result> result = currentRecording.computeSpeedLimitExcess(maxAuthorizedSpeed);
                    if(result.isPresent()){
                        System.err.println("Adding excess speed limit for " + currentRecording.getLicencePlate());
                        results.add(result.get());
                    }
                    currentRecording = newRecording;
                    System.err.println("New Licence plate " + currentRecording.getLicencePlate());
                }
            }
            System.err.println(rows[i] + " : " + elements[0] + " , " + elements[1] + " , " + elements[2]);
        }
        if(currentRecording != null) {
            System.err.println("Computing average speed for " + currentRecording.getLicencePlate());
            Optional<Result> result = currentRecording.computeSpeedLimitExcess(maxAuthorizedSpeed);
            if(result.isPresent()){
                System.err.println("Adding excess speed limit for " + currentRecording.getLicencePlate());
                results.add(result.get());
            }
        }

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");
        if(results.isEmpty()) {
            System.out.println("OK");
        }
        else {
            for(Result result:results){
                System.out.println(result.toString());
            }
        }
    }
}

class CameraCapture {
    private long distance;
    private long timestamp;

    public CameraCapture(String distance, String timestamp) {
        this.distance = Integer.parseInt(distance);
        this.timestamp = Long.parseLong(timestamp);
    }

    public long getDistance() {
        return this.distance;
    }

    public long getTimestamp() {
        return this.timestamp;
    }
}

class Recording {
    private final List<CameraCapture> captures = new ArrayList<>();
    private final String licencePlate;
    private Result result = null;

    public Recording(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public void add(CameraCapture cc) {
        captures.add(cc);
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public Optional<Result> computeSpeedLimitExcess(long maxAuthorizedSpeed) {
        if (captures.size() >= 2) {
            //CameraCapture firstCapture = captures.get(0);
            for (int i = 1; i < captures.size(); i++) {
                CameraCapture firstCapture = captures.get(i-1);
                CameraCapture secondCapture = captures.get(i);
                if (maxAuthorizedSpeed <= averageSpeed(secondCapture.getDistance() - firstCapture.getDistance(), secondCapture.getTimestamp() - firstCapture.getTimestamp())) {
                    return Optional.of(new Result(secondCapture.getDistance(), this.licencePlate));
                }
            }
            return Optional.empty();
        }
        return Optional.empty();
    }

    private long averageSpeed(long distance, long delay) {
        long average = Math.round((double)distance / ((double)delay / (double)3600));
        System.err.println("Computed average speed : " + Long.toString(average));
        return average;
    }

    public boolean sameLicencePlate(String licencePlate) {
        if (licencePlate == null || this.licencePlate == null) {
            return false;
        }
        return this.licencePlate.equals(licencePlate);
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