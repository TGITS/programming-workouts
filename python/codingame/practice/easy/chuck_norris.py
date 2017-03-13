import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

message = input()

print("The original message  : {}".format(message_binary), file=sys.stderr)

prefix_0 = "00 "
prefix_1 = "0 "

message_binary = ''.join(format(ord(x), 'b') for x in message)
print("The message in binary : {}".format(message_binary), file=sys.stderr)

#Parsing the message

# Write an action using print
# To debug: print("Debug messages...", file=sys.stderr)

print("answer")
