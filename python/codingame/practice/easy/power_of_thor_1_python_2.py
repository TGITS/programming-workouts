import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.
# ---
# Hint: You can use the debug stream to print initialTX and initialTY, if Thor seems not follow your orders.

# light_x: the X position of the light of power
# light_y: the Y position of the light of power
# initial_tx: Thor's starting X position
# initial_ty: Thor's starting Y position
light_x, light_y, initial_tx, initial_ty = [int(i) for i in raw_input().split()]
coordinates={'x': (initial_tx - light_x), 'y' : (initial_ty - light_y)}
# game loop
while True:
    remaining_turns = int(raw_input())  # The remaining amount of turns Thor can move. Do not remove this line.

    # Write an action using print
    # To debug: print >> sys.stderr, "Debug messages..."

    move=""
    if(coordinates['x'] > 0):
        if(coordinates['y'] > 0):
            move="NW"
            coordinates['x'] = coordinates['x']-1
            coordinates['y'] = coordinates['y']-1
        elif(coordinates['y'] < 0):
            move="SW"
            coordinates['x'] = coordinates['x']-1
            coordinates['y'] = coordinates['y']+1
        else:
            move="W"
            coordinates['x'] = coordinates['x']-1
    elif(coordinates['x'] < 0):
        if(coordinates['y'] > 0):
            move="NE"
            coordinates['x'] = coordinates['x']+1
            coordinates['y'] = coordinates['y']-1
        elif(coordinates['y'] < 0):
            move="SE"
            coordinates['x'] = coordinates['x']+1
            coordinates['y'] = coordinates['y']+1
        else:
            move="E"
            coordinates['x'] = coordinates['x']+1
    else:
        if(coordinates['y'] > 0):
            move="N"
            coordinates['y'] = coordinates['y']+1
        elif(coordinates['y'] < 0):
            move="S"
            coordinates['y'] = coordinates['y']-1
        else:
            pass #we are on the right spot
    # A single line providing the move to be made: N NE E SE S SW W or NW
    print(move)
