# Test Data for the Aneo Puzzle on CodinGame

* https://www.codingame.com/ide/puzzle/aneo

## Input Data

* An integer speed for the maximum speed allowed on the portion of the road (in km / h).
* An integer lightCount for the number of traffic lights on the road.

* lightCount next lines:
  * An integer distance representing the distance of the traffic light from the starting point (in meters).
  * An integer duration representing the duration of the traffic light on each color.

A traffic light alternates a period of duration seconds in green and then duration seconds in red.
All traffic lights turn green at the same time as you enter the area.

## Expected Output

The integer speed (km/h) as high as possible that cross all the green lights without committing speeding.

## Example Data

### Input

* Maximum speed allowed in km/h : 50
* Number of lightcount : 1
* the distance of the traffic light from the starting point in meters : 200 
* the duration of the traffic light on each color in second : 15

### Expected Output

* 50

