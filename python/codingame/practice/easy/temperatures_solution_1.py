import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

n = int(input())  # the number of temperatures to analyse
temps = input()  # the n temperatures expressed as integers ranging from -273 to 5526

print(str(n),file=sys.stderr)
print(len(temps),file=sys.stderr)

if n > 0:
    temperatures = "".join(temps).split(" ")

    print(temperatures,file=sys.stderr)

    tuple_min = (int(temperatures[0]), abs(int(temperatures[0])))

    for i in range(1,n):
        print(abs(int(temperatures[i])), tuple_min[1],file=sys.stderr)
        if abs(int(temperatures[i])) < tuple_min[1]:
            tuple_min = (int(temperatures[i]), abs(int(temperatures[i])))
        elif  abs(int(temperatures[i])) == tuple_min[1]:
            if int(temperatures[i]) > tuple_min[0]:
                tuple_min = (int(temperatures[i]), abs(int(temperatures[i])))

    # Write an action using print
    # To debug: print("Debug messages...", file=sys.stderr)

    print(str(tuple_min[0]))
else:
    print(0)
