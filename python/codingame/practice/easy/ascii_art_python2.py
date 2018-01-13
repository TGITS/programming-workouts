import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

l = int(raw_input())
h = int(raw_input())
t = raw_input()
rows = []
output_rows = []
print >> sys.stderr, "l: {} - h : {} - t : {}".format(l,h,t) 
for i in xrange(h):
    row = raw_input()
    rows.append(row)
    output_rows.append([])
    print >> sys.stderr, row

ref_string="ABCDEFGHIJKLMNOPQRSTUVWXYZ?"
text = t.upper()
print >> sys.stderr, "t : {}".format(t)
print >> sys.stderr, "text : {}".format(text)
for c in text:
    index = 26
    if c in ref_string:
        index = ref_string.index(c)
    print >> sys.stderr, "index : {}".format(index)
    for i in range(h):
        output_rows[i].append(rows[i][index*l:(index+1)*l])
        print >> sys.stderr, output_rows[i]

# Write an action using print
# To debug: print >> sys.stderr, "Debug messages..."

for i in xrange(h):
    print "".join(output_rows[i]) 