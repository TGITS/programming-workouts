import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

n = int(input())  # Number of elements which make up the association table.
q = int(input())  # Number Q of file names to be analyzed.

print("n : {}".format(n), file=sys.stderr)
print("q : {}".format(q), file=sys.stderr)

# For each of the Q filenames, display on a line the corresponding MIME type. If there is no corresponding type, then display UNKNOWN.

mimeTypeByExtension = {}
DOT='.'

for i in range(n):
    # ext: file extension
    # mt: MIME type.
    ext, mt = input().split()
    print("ext : {}".format(ext), file=sys.stderr)
    print("mt : {}".format(mt), file=sys.stderr)
    mimeTypeByExtension[ext] = mt
    mimeTypeByExtension[ext.upper()] = mt
    mimeTypeByExtension[ext.lower()] = mt

for i in range(q):
    fname = input()  # One file name per line.
    print("fname : {}".format(fname), file=sys.stderr)
    if DOT in fname:
        dot_position = fname.rindex(".")
        extension = fname[dot_position+1:]
        if extension in mimeTypeByExtension.keys():
            print(mimeTypeByExtension[extension])
        elif extension.upper() in mimeTypeByExtension.keys():
            print(mimeTypeByExtension[extension.upper()])
        elif extension.lower() in mimeTypeByExtension.keys():
            print(mimeTypeByExtension[extension.lower()])
        else:
            print("UNKNOWN")
    else:
        print("UNKNOWN")
