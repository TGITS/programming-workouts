import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

n = int(input())
print("Number of horses : {}".format(n), file=sys.stderr)
strengths = []
for i in range(n):
    pi = int(input())
    print("Strength of horse n°{} : {}".format(i+1, pi), file=sys.stderr)
    strengths.append(pi)

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
