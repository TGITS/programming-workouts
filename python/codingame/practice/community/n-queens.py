#https://www.codingame.com/training/community/n-queens
#Puzzle of the weeks (around 02/04/2017)
#See also
# * https://en.wikipedia.org/wiki/Eight_queens_puzzle
# * http://rosettacode.org/wiki/N-queens_problem#Python
# * https://fr.wikipedia.org/wiki/Probl%C3%A8me_des_huit_dames

import sys
import math

# We have to place n queens on a chessboard made of n x n squares.
# We need to place all of them in such a way that none of them is in direct contact nor attacking another one.
# (meaning only one queen is occupying each line, column and diagonal)
#
# Example with n = 4, s = 2 solutions :
#
# - Q - -
# - - - Q
# Q - - -
# - - Q -
#
# and
#
# - - Q -
# Q - - -
# - - - Q
# - Q - -
#
# Input
# n : Number of queens (height and width of the cheessboard).
# Output
# s : Number of solutions.
# Constraints
# 1 <= n <= 11

n = int(input())
number_of_solutions = 0
print("Number of queens : {}".format(n), file=sys.stderr)

if n >= 1 and n <= 27:
    queen_position_in_row = []
    upward_diagonal = []
    downward_diagonal = []
    # Write an action using print
    # To debug: print("Debug messages...", file=sys.stderr)
    print("Number of solutions : {}".format(number_of_solutions), file=sys.stderr)
    print("{}".format(number_of_solutions))
