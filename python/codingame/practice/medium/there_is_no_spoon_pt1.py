import sys
import math
import pprint

# Don't let the machines win. You are humanity's last hope...
NODE="NODE"
EMPTY="EMPTY"
VOID_COORD="-1 -1"


class Cell:
    """Represent a cell"""
    def __init__(self,x,y,status):
        self.x = x
        self.y = y
        self.status = status
        self.rightNeighbor = None
        self.bottomNeighbor = None

    def __str__(self):
        return "x:{};y:{};{}".format(self.x,self.y,self.status)

    def __repr__(self):
        return "x:{};y:{};{}".format(self.x,self.y,self.status)

    def coordinates(self):
        coord = str(self.x) + " " + str(self.y) + " "

        if self.rightNeighbor == None or self.rightNeighbor.status == EMPTY:
            coord += VOID_COORD + " "
        else:
            coord += str(self.rightNeighbor.x) + " " +  str(self.rightNeighbor.y) + " "

        if self.bottomNeighbor == None or self.bottomNeighbor.status == EMPTY:
            coord += VOID_COORD
        else:
            coord += str(self.bottomNeighbor.x) + " " +  str(self.bottomNeighbor.y)

        return coord

    def displayCoordinates(self):
        print(self.coordinates())


def associateNeighbors(grid, cell, height, width):
    """Associate a right neigbor (if any) to the given cell"""
    #if we are not on the right edge, we get the cell on the right
    next_x = cell.x+1
    found = False
    if next_x < width:
        while next_x < width and not found:
            rightNeighbor = grid[cell.y][next_x]
            if rightNeighbor.status == NODE:
                cell.rightNeighbor = rightNeighbor
                found = True
            else:
                next_x += 1
    #if we are not on the bottom edge, we get the cell on the bottom
    next_y = cell.y+1
    found = False
    if next_y<height:
        while next_y < height and not found:
            bottomNeighbor = grid[next_y][cell.x]
            if bottomNeighbor.status == NODE:
                cell.bottomNeighbor = bottomNeighbor
                found = True
            else:
                next_y += 1



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


print("width : {} - height : {}".format(width,height), file=sys.stderr)
pp=pprint.PrettyPrinter(stream=sys.stderr, compact=True)
print("The grid :", file=sys.stderr)
pp.pprint(grid)
print("The cell to process before association :", file=sys.stderr)
pp.pprint(to_process)

for cell in to_process:
    associateNeighbors(grid,cell,height,width)

print("The cell to process after association :", file=sys.stderr)
pp.pprint(to_process)
# Write an action using print
# To debug: print("Debug messages...", file=sys.stderr)


# Three coordinates: a node, its right neighbor, its bottom neighbor
#print("0 0 1 0 0 1")
for cell in to_process:
    cell.displayCoordinates()
