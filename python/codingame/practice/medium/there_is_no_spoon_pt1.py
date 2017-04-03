import sys
import math
import pprint

# Don't let the machines win. You are humanity's last hope...
NODE="NODE"
EMPTY="EMPTY"


class Cell:
    """Represent a cell"""
    def __init__(self,x,y,status):
        self.x = x
        self.y = y
        self.status = status

    def __str__(self):
        return "x:{};y:{};{}".format(self.x,self.y,self.status)

    def __repr__(self):
        return "x:{};y:{};{}".format(self.x,self.y,self.status)


width = int(input())  # the number of cells on the X axis
height = int(input())  # the number of cells on the Y axis
grid = []
to_process = []
for i in range(height):
    line = input()  # width characters, each either 0 or .
    row = []
    x=0
    for c in line:
        if c == '.':
            row.append(Cell(x,i,EMPTY))
        else:
            cell = Cell(x,i,NODE)
            row.append(cell)
            to_process.append(cell)
        x += 1
    grid.append(row)


pp=pprint.PrettyPrinter(stream=sys.stderr, compact=True)
print("The grid :", file=sys.stderr)
pp.pprint(grid)
print("The cell to process :", file=sys.stderr)
pp.pprint(to_process)
# Write an action using print
# To debug: print("Debug messages...", file=sys.stderr)


# Three coordinates: a node, its right neighbor, its bottom neighbor
print("0 0 1 0 0 1")
