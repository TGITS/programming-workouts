import sys
import math

# n size of the chessboard
# r row in which is the bishop
# c column in which is the bishop


n = int(input())
r = int(input())
c = int(input())


print("size of the chessboard : {} ; position of the bishop (c,r) : ({},{})".format(n, c, r), file=sys.stderr)

def create_and_initialize_chessboard(n, r, c):
    chessboard = []
    for i in range(1,n+1):
        row = []
        for j in range(1,n+1):
            if (i+j == r+c) or (i-j == r -c):
                row.append(1)
            else:
                row.append(0)
        chessboard.append(row)
    return chessboard

def display_chessboard(chessboard):
    for row in chessboard:
        for cell in row:
            #print("{} ".format(cell), file=sys.stderr, end='', flush=True)
            print("{} ".format(cell), end='', flush=True)
        print("\n")

chessboard = create_and_initialize_chessboard(n,r,c)
display_chessboard(chessboard)
