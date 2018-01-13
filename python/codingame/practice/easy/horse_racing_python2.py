import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

n = int(raw_input())
print >> sys.stderr, "Number of horses : {}".format(n)
strengths = []
for i in xrange(n):
    pi = int(raw_input())
    strengths.append(pi)

# Write an action using print
# To debug: print >> sys.stderr, "Debug messages..."

strengths.sort()
index = len(strengths) - 1
strength_1 = strengths[index]
index -= 1
strength_2 = strengths[index]

minimal_difference = strength_1 - strength_2

if index == 0:
    print(str(minimal_difference))
else:
    while index > 0:
        strength_1 = strength_2
        index -= 1
        strength_2 = strengths[index]
        difference = strength_1 - strength_2
        if difference < minimal_difference:
            minimal_difference = difference

    print(str(minimal_difference))