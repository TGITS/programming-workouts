import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

n = int(input())  # the number of temperatures to analyse
temps = input()  # the n temperatures expressed as integers ranging from -273 to 5526

if n > 0:
    temperatures = "".join(temps).split(" ")

    print("temperatures : {}".format(temperatures),file=sys.stderr)

    integers_list = [int(x) for x in temperatures]
    positive_integers_list = [x for x in integers_list if x >= 0]
    negative_integers_list = [x for x in integers_list if x < 0]
    print("integers_list : {}".format(integers_list),file=sys.stderr)
    print("positive_integers_list : {}".format(positive_integers_list),file=sys.stderr)
    print("negative_integers_list : {}".format(negative_integers_list),file=sys.stderr)
    min_positive_integer = None
    if positive_integers_list:
        min_positive_integer = min(positive_integers_list)
    max_negative_integer = None
    if negative_integers_list:
        max_negative_integer = max(negative_integers_list)

    print("min_positive_integer : {}".format(min_positive_integer),file=sys.stderr)
    print("max_negative_integer : {}".format(max_negative_integer),file=sys.stderr)

    if not positive_integers_list and not negative_integers_list:
        print("found : {}".format("0"),file=sys.stderr)
        print("0")
    elif not positive_integers_list :
        print("found : {}".format(max_negative_integer),file=sys.stderr)
        print(max_negative_integer)
    elif not negative_integers_list :
        print("found : {}".format(min_positive_integer),file=sys.stderr)
        print(min_positive_integer)
    elif min_positive_integer == abs(max_negative_integer):
        print("found : {}".format(min_positive_integer),file=sys.stderr)
        print(min_positive_integer)
    elif min_positive_integer < abs(max_negative_integer):
        print("found : {}".format(min_positive_integer),file=sys.stderr)
        print(min_positive_integer)
    else:
        print("found : {}".format(max_negative_integer),file=sys.stderr)
        print(max_negative_integer)
else:
    print("0")
