import sys
import math
import re

prefix_0 = "00 "
prefix_1 = "0 "

def replaceByZeros(match):
    replacement =""
    found = match.group(0)
    print >> sys.stderr, "found : {}".format(found)
    if found.startswith('1'):
        replacement = prefix_1 + found.replace('1','0') + " "
        print >> sys.stderr, "replacement : {}".format(replacement)
    else:
        replacement = prefix_0 + found + " "
        print >> sys.stderr, "replacement : {}".format(replacement)

    return replacement

message = raw_input()

print >> sys.stderr, "The original message  : {}".format(message)

zeros_pattern = re.compile(r"0+")
ones_pattern = re.compile(r"1+")

binary_message = ''.join(format(ord(x), 'b').rjust(7,'0') for x in message)
print >> sys.stderr, "The message {} in binary : {}".format(message, binary_message)

binary_message = zeros_pattern.sub(replaceByZeros,binary_message).strip(" ")
print >> sys.stderr, "The message in binary with 0 substituted : {}".format(binary_message)

binary_message = ones_pattern.sub(replaceByZeros,binary_message).strip(" ")
print >> sys.stderr, "The message in binary with 1 substituted : {}".format(binary_message)


print binary_message.strip(" ")
