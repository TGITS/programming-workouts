# Count of Primes in a number grid

* Source : https://www.codingame.com/ide/puzzle/count-of-primes-in-a-number-grid

## Problem Statement

Given a grid of single digit numbers in R rows and C columns, count the number of distinct primes that can be found using Across or Down reading order. Partial use of the numbers in any direction is allowed, but skipping digits is not.

For example in the grid below:

2 3
1 7

The primes that can found are : 2, 3, 7, 17, 23, 37. So, the output would be 6. Note that 13 and 71 are not counted as they are not a result of using the across or down reading order.

## Data

### Example 1

#### Input

2 2
2 3
1 7

#### Output

6

### Example 2

#### Input

4 4
0 0 0 0
0 0 0 0
0 2 0 0
0 0 0 0

#### Output

1

### Example 3

#### Input

5 5
3 8 2 1 9
3 3 7 9 7
1 9 4 6 9
9 1 5 7 1
9 1 7 3 9

#### Output

50

### Example 4

#### Input

6 6
3 1 7 3 3 3
9 9 5 6 3 9
1 1 8 1 4 2
1 3 6 3 7 3 
3 4 9 1 9 9
3 7 9 3 7 9

#### Output

78

### Example 5

#### Input

5 7
1 9 3 1 3 3 3
9 9 4 1 9 9 9
1 3 6 9 7 9 1
3 7 7 5 7 2 7
9 6 2 3 8 3 3

#### Output

64