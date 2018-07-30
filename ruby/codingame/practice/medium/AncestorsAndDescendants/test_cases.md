# Test Cases

## Origin

* https://www.codingame.com/ide/puzzle/ancestors-&-descendants

## Problem Statement

### Goal

Print the names of a family's descendants.

An individual in the family is represented by a line of input. The parent/child relationship of that individual is determined by the number of dots preceding his or her name.

Each dot represents a previously mentioned ancestor in the family tree. So if a name is preceded by two dots, then the first dot represents the most recently mentioned name with zero dots before it, and the second dot represents the most recently mentioned name with a single dot before it.

An example set of input lines to represent a family would be:

Jade
.Andrew
..Rose
.Mark
Heidi

The explanation for this input is:

Jade is a grandfather.
Andrew is Jade's son.
Rose is Andrew's daughter.
Mark is Jade's son.
Heidi has no children.

The correct output lines to represent this family's descendants would be:

Jade > Andrew > Rose
Jade > Mark
Heidi

### Input

Line 1: An integer N for the number of family members.
Next N lines: Each line represents one family member.

### Output

Output the family descendants full names each on a separate line.

## Test case 1

### Input

8
a
.b
..c
d
.e
..f
.g
h

### Expected Output

a > b > c
d > e > f
d > g
h

## Test case 2

### Input

19
a
.a1
..a11
...a111
...a112
..a12
.a2
b
.b1
..b11
.b2
..b21
...b211
c
.c1
..c11
...c111
....c1111
.c2

### Expected Output

a > a1 > a11 > a111
a > a1 > a11 > a112
a > a1 > a12
a > a2
b > b1 > b11
b > b2 > b21 > b211
c > c1 > c11 > c111 > c1111
c > c2

## Test case 3

### Input

28
a
.a1
..a11
..a12
...a121
..a13
...a131
....a1311
.....a13111
......a131111
...a132
..a14
..a15
.a2
.a3
.a4
b
.b1
.b2
c
.c1
..c11
...c111
....c1111
..c12
...c121
....c1211
...c122

### Expected Output

a > a1 > a11
a > a1 > a12 > a121
a > a1 > a13 > a131 > a1311 > a13111 > a131111
a > a1 > a13 > a132
a > a1 > a14
a > a1 > a15
a > a2
a > a3
a > a4
b > b1
b > b2
c > c1 > c11 > c111 > c1111
c > c1 > c12 > c121 > c1211
c > c1 > c12 > c122

## Test case 4

### Input

22
John
.Sam
..Randy
..George
...Alfred
.Justin
.Kelly
..Jack
...Lily
....Susan
.Jade
Robert
.Luke
Jonathan
Paul
Ken
.Flare
..Kelly
...Jessica
....Martin
.....Penelope
..David

### Expected Output

John > Sam > Randy
John > Sam > George > Alfred
John > Justin
John > Kelly > Jack > Lily > Susan
John > Jade
Robert > Luke
Jonathan
Paul
Ken > Flare > Kelly > Jessica > Martin > Penelope
Ken > Flare > David

## Test case 5

### Input

26
a
.b
..c
...d
....e
.....f
......g
.......h
........i
.........j
..........k
...........l
............m
............n
...........o
..........p
.........q
........r
.......s
......t
.....u
....v
...w
..x
.y
z

### Expected Output

a > b > c > d > e > f > g > h > i > j > k > l > m
a > b > c > d > e > f > g > h > i > j > k > l > n
a > b > c > d > e > f > g > h > i > j > k > o
a > b > c > d > e > f > g > h > i > j > p
a > b > c > d > e > f > g > h > i > q
a > b > c > d > e > f > g > h > r
a > b > c > d > e > f > g > s
a > b > c > d > e > f > t
a > b > c > d > e > u
a > b > c > d > v
a > b > c > w
a > b > x
a > y
z

## Test case 6

### Input

5
Jade
.Andrew
..Rose
.Mark
Heidi

### Expected Output

Jade > Andrew > Rose
Jade > Mark
Heidi
