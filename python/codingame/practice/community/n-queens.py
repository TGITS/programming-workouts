import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

n = int(input())
number_of_solutions = 0
print("Number of queens : {}".format(n), file=sys.stderr)

if n >= 1 and n <= 11:
    queen_position_in_row = []
    upward_diagonal = []
    downward_diagonal = []
    # Write an action using print
    # To debug: print("Debug messages...", file=sys.stderr)
    print("Number of solutions : {}".format(number_of_solutions), file=sys.stderr)
    print("{}".format(number_of_solutions))
