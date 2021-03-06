# Mars Lander - Episode 2

## Learning Objectives

In this puzzle, iterating on a series of 2D coordinates helps you grasp the concepts of comparisons, distance computation, extrapolation. This puzzles works with angle and orientation constraints. You learn to compute the speed of an object using its coordinates at two different times. If you perform well, you can solve this problem using some distance and trajectory optimization.

## Statement

The goal of this problem is to make you work with 2D coordinates in a big environnement. You will have to manage and extrapolate the speed of a spaceship and make it land on a flat ground at correct speed.

## Goal

The goal for your program is to safely land the "Mars Lander" shuttle, the landing ship which contains the Opportunity rover. Mars Lander is guided by a program, and right now the failure rate for landing on the NASA simulator is unacceptable.

**This puzzle is the second level of the "Mars Lander" trilogy. The controls are the same as the previous level but you must now control the angle in order to succeed.**  

## Rules

Built as a game, the simulator puts Mars Lander on a limited zone of Mars sky.

The zone is 7000m wide and 3000m high. There is a **unique area of flat ground** on the surface of Mars, which is at least 1000 meters wide.  

![](.\marslander.png)

**Every second**, depending on the current flight parameters (location, speed, fuel ...), the program must provide the new desired tilt angle and thrust power of Mars Lander:

Angle goes from -90° to 90° . Thrust power goes from 0 to 4 .

The game simulates **a free fall**  without atmosphere. Gravity on Mars is 3.711 m/s². For a **thrust power of X**, a push force equivalent to **X m/s²** is generated and **X liters of fuel** are consumed. As such, a thrust power of 4 in an almost vertical position is needed to compensate for the gravity on Mars.

For a landing to be successful, the ship must:

- land on flat ground
- land in a vertical position (tilt angle = 0°)
- vertical speed must be limited ( ≤ 40m/s in absolute value)
- horizontal speed must be limited ( ≤ 20m/s in absolute value)

## Game input

The program must first read the initialization data from standard input. Then, **within an infinite loop**, the program must read the data from the standard input related to Mars Lander's current state and provide to the standard output the instructions to move Mars Lander.

### Initialization input

**Line 1:** the number **surfaceN** of points used to draw the surface of Mars.
**Next surfaceN lines:** a couple of integers **landX** **landY** providing the coordinates of a ground point. By linking all the points together in a sequential fashion, you form the surface of Mars which is composed of several segments. For the first point, **landX** = 0 and for the last point, **landX** = 6999

### Input for one game turn

A **single line** : **X** **Y** **hSpeed** **vSpeed** **fuel** **rotate** **power**

- **X**,**Y** are the coordinates of Mars Lander (in **meters**).
- **hSpeed** and **vSpeed** are the horizontal and vertical speed of Mars Lander (in **m/s**). These can be negative depending on the direction of Mars Lander.
- **fuel** is the remaining quantity of fuel in liters. When there is no more fuel, the power of thrusters falls to zero.
- **rotate** is the angle of rotation of Mars Lander expressed in degrees.
- **power** is the thrust power of the landing ship.

### Output for one game turn

A **single line** with 2 integers : **rotate** **power**

- **rotate** is the desired rotation angle for Mars Lander. Please note that for each turn the actual value of the angle is limited to the value of the previous turn +/- 15°.
- **power** is the desired thrust power. 0 = off. 4 = maximum power. Please note that for each turn the value of the actual power is limited to the value of the previous turn +/- 1.

### Constraints

2 ≤ surfaceN < 30
0 ≤ X < 7000
0 ≤ Y < 3000
-500 < hSpeed, vSpeed < 500
0 ≤ fuel ≤ 2000
-90 ≤ rotate ≤ 90
0 ≤ power ≤ 4
Response time per turn ≤ 100ms

## Example

```
-45 4 (rotate power)
```

Requested rotation to the right, maximum thrust power

## Ressources

* https://www.codingame.com/learn/trigonometry
* https://www.codingame.com/learn/distance
* https://en.wikipedia.org/wiki/Euclidean_distance#Two_dimensions
* https://byjus.com/physics/measurement-of-speed/
* https://en.wikipedia.org/wiki/PID_controller
* https://en.wikipedia.org/wiki/Dead_reckoning
* https://en.wikipedia.org/wiki/True_range_multilateration
* https://tech.io/playgrounds/334/genetic-algorithms/history
* https://www.codingame.com/blog/genetic-algorithm-mars-lander/
* https://www.physicsclassroom.com/class
* [https://github.com/texus/codingame/blob/master/SingePlayer/Medium/Mars%20Lander%20-%20Level%202.cpp](https://github.com/texus/codingame/blob/master/SingePlayer/Medium/Mars Lander - Level 2.cpp)
* https://pastebin.com/shmCsvRA
* https://www.quora.com/What-solutions-did-you-imagine-to-solve-the-Mission-to-Mars-problems
* https://github.com/fabriziocucci/CodinGame/tree/master/medium/mars-lander-level-2
* https://www.xarg.org/puzzle/codingame/mars-lander/
