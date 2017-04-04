import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

def compute_next_line(line):
    counter = 0
    processed_number = None
    next_line = []
    for item in line:
        #print("item {}".format(item), file=sys.stderr)
        if processed_number == None:
            #print("Bootstrapping the loop", file=sys.stderr)
            processed_number = item

        if  processed_number != item:
            #print(" {} {} ".format(counter,processed_number), file=sys.stderr)
            next_line.append(counter)
            next_line.append(processed_number)
            processed_number = item
            counter = 1
        else:
            #print("Incrementing the counter", file=sys.stderr)
            counter += 1

    next_line.append(counter)
    next_line.append(processed_number)

    return(next_line)

# Write an action using print
# To debug: print("Debug messages...", file=sys.stderr)

def int_list_to_str(int_list):
    #return "{}".format([str(n) for n in int_list])
    string = ""
    for item in int_list:
        string += str(item) + " "
    return string.strip()

r = int(input())
l = int(input())

print(" r : {} - l : {} ".format(r,l), file=sys.stderr)

list_to_process = []
list_to_process.append(r)

for i in range(1,l):
    intermediate_list = compute_next_line(list_to_process)
    print("generate list for {} : {}".format(i+1, int_list_to_str(intermediate_list)), file=sys.stderr)
    list_to_process = intermediate_list

print(int_list_to_str(list_to_process))
