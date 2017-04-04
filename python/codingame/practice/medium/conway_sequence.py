import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

r = int(input())
l = int(input())

def compute_next_line(line):
    counter = 0
    processed_number = None
    next_line = []
    for item in line:
        if processed_number == None:
            processed_number = item

        if  processed_number != item:
            next_line.append(counter)
            next_line.append(processed_number)
            processed_number = item
            counter = 1
        else:
            counter += 1

    return(next_line)

# Write an action using print
# To debug: print("Debug messages...", file=sys.stderr)

def int_list_to_str(int_list):
    #return "{}".format([str(n) for n in int_list])
    string = ""
    for item in int_list:
        string += str(item) + " "
    return string.strip()

list_to_process = list(r)

for i in range(0,l):
    intermediate_list = compute_next_line(list_to_process)
    print("generate list for {} : {}".format(i+1, int_list_to_str(intermediate_list)), file=sys.stderr)
    list_to_process = intermediate_list

print(int_list_to_str(list_to_process))
