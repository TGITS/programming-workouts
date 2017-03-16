import sys
import math
import re

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

prefix_0 = "00 "
prefix_1 = "0 "

def replaceByZeros(match):
    replacement =""
    found = match.group(0)
    print("found : {}".format(found), file=sys.stderr)
    if found.startswith('1'):
        replacement = prefix_1 + found.replace('1','0') + " "
        print("replacement : {}".format(replacement), file=sys.stderr)
    else:
        replacement = prefix_0 + found + " "
        print("replacement : {}".format(replacement), file=sys.stderr)

    return replacement

message = input()

print("The original message  : {}".format(message), file=sys.stderr)

zeros_pattern = re.compile(r"0+")
ones_pattern = re.compile(r"1+")

binary_message = ''.join(format(ord(x), 'b').rjust(7,'0') for x in message)
print("The message {} in binary : {}".format(message, binary_message), file=sys.stderr)

binary_message = zeros_pattern.sub(replaceByZeros,binary_message).strip(" ")
print("The message in binary with 0 substituted : {}".format(binary_message), file=sys.stderr)

binary_message = ones_pattern.sub(replaceByZeros,binary_message).strip(" ")
print("The message in binary with 1 substituted : {}".format(binary_message), file=sys.stderr)

# Write an action using print
# To debug: print("Debug messages...", file=sys.stderr)

print(binary_message.strip(" "))
