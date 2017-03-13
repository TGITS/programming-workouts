import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

l = int(input())
h = int(input())
t = input()
rows = []
output_rows = []
print("l: {} - h : {} - t : {}".format(l,h,t), file=sys.stderr)
for i in range(h):
    row = input()
    rows.append(row)
    output_rows.append([])
    print(row, file=sys.stderr)

ref_string="ABCDEFGHIJKLMNOPQRSTUVWXYZ?"
text = t.upper()
print("t : {}".format(t), file=sys.stderr)
print("text : {}".format(text), file=sys.stderr)
for c in text:
    index = 26
    if c in ref_string:
        index = ref_string.index(c)
    print("index : {}".format(index), file=sys.stderr)
    for i in range(h):
        output_rows[i].append(rows[i][index*l:(index+1)*l])
        print(output_rows[i], file=sys.stderr)

# Write an action using print
# To debug: print("Debug messages...", file=sys.stderr)

for i in range(h):
    print("".join(output_rows[i]))
