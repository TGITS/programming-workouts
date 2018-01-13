import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

def compute_next_line(line):
    counter = 0
    processed_number = None
    next_line = []
    for item in line:
        #print >> sys.stderr, "item {}".format(item)
        if processed_number == None:
            #print >> sys.stderr, "Bootstrapping the loop"
            processed_number = item

        if  processed_number != item:
            #print >> sys.stderr, " {} {} ".format(counter,processed_number)
            next_line.append(counter)
            next_line.append(processed_number)
            processed_number = item
            counter = 1
        else:
            #print >> sys.stderr, "Incrementing the counter"
            counter += 1

    next_line.append(counter)
    next_line.append(processed_number)

    return(next_line)

# Write an action using print
# To debug: print >> sys.stderr, "Debug messages..."

def int_list_to_str(int_list):
    #return "{}".format([str(n) for n in int_list])
    string = ""
    for item in int_list:
        string += str(item) + " "
    return string.strip()

r = int(raw_input())
l = int(raw_input())

print >> sys.stderr," r : {} - l : {} ".format(r,l)

list_to_process = []
list_to_process.append(r)

for i in xrange(1,l):
    intermediate_list = compute_next_line(list_to_process)
    print >> sys.stderr,"generate list for {} : {}".format(i+1, int_list_to_str(intermediate_list))
    list_to_process = intermediate_list

print int_list_to_str(list_to_process)
