# Langton's Ant - CodinGame

https://www.codingame.com/ide/puzzle/langton's-ant

Langton's ant is a turing machine built on simple rules:
* Consider a grid of __W__x__H__ squares, each of which can be either black __#__ or white __.__.
* The ant starts at the position __(x, y)__ of the grid, the upper left square coordinates are __(0,0)__.
* The ant is facing one of the following directions (__N__ = up, __E__ = right, __S__ = down, __W__ = left).
* The ant moves __T__ turns. For each turn:
  * it rotates __90°__ left if it is on a white square, __90°__ right otherwise.
  * it inverts the color of the square it is located on.
  * it moves 1 square forward in its current direction.

From an arbitrarily coloured grid and the ant's initial state, you have to print the grid state after __T__ turns.

On the given test cases, the ant never has to go out of bounds.

## Input

* Ligne 1 : Two integers W and H for the width and height of the grid.
* Ligne 2 : Two integers x and y for the ant's initial coordinates.
* Ligne 3 : A character N, E, S or W for the ant's initial direction.
* Ligne 4 : An integer T for the number of turns.
* H following lines : A line of size W composed of # (black square) and . (white square) for the initial state of the grid.

## Output

* H lines representing the final state of the grid.
