import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

n = int(raw_input())  # Number of elements which make up the association table.
q = int(raw_input())  # Number Q of file names to be analyzed.

mimeTypeByExtension = {}
DOT='.'

for i in xrange(n):
    # ext: file extension
    # mt: MIME type.
    ext, mt = raw_input().split()
    mimeTypeByExtension[ext.lower()] = mt

# For each of the Q filenames, display on a line the corresponding MIME type. If there is no corresponding type, then display UNKNOWN.
for i in xrange(q):
    fname = raw_input()  # One file name per line.
    if DOT in fname:
        dot_position = fname.rindex(DOT)
        extension = fname[dot_position+1:]
        if extension.lower() in mimeTypeByExtension.keys():
            print(mimeTypeByExtension[extension.lower()])
        else:
            print("UNKNOWN")
    else:
        print("UNKNOWN")
