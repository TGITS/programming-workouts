import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

n = int(input())

# Write an action using print
# To debug: print("Debug messages...", file=sys.stderr)

# a try : 5 points
# after a try, a transformation kick is played and is worth 2 extra points if successful
# penalty kicks and drops are worth 3 points

print("tries transformations penalties", file=sys.stderr)

global_index = 0
factors = [ 7, 5, 3]
list_of_decompositions = []
while global_index < 3:
    score = n
    decomposition = [0, 0, 0]
    index = global_index

    while score >= 0 and index < 3:
        value = score // factors[index]
        if index == 0:
            decomposition[0] += value
            decomposition[1] += value
        elif index == 1:
            decomposition[0] += value
        else:
            decomposition[index] += value
        score = score % factors[index]
        index += 1

    if (score == 0):
        list_of_decompositions.append(" ".join(map(str,decomposition)))

    global_index += 1

print(str(list_of_decompositions), file=sys.stderr)
print(str(sorted(list_of_decompositions)), file=sys.stderr)

for item in sorted(list_of_decompositions):
    print(item)