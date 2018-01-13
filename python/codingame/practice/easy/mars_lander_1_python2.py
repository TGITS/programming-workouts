import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

def computeThrustPower(actualPower=0, increase=True):
    newPower = actualPower
    if increase:
        newPower = actualPower + 1
    else:
        newPower = actualPower - 1

    if newPower < 0:
        newPower = 0

    if newPower > 4:
        newPower = 4

    return str(newPower)

surface_n = int(raw_input())  # the number of points used to draw the surface of Mars.
for i in xrange(surface_n):
    # land_x: X coordinate of a surface point. (0 to 6999)
    # land_y: Y coordinate of a surface point. By linking all the points together in a sequential fashion, you form the surface of Mars.
    land_x, land_y = [int(j) for j in raw_input().split()]

# game loop
while True:
    # h_speed: the horizontal speed (in m/s), can be negative.
    # v_speed: the vertical speed (in m/s), can be negative.
    # fuel: the quantity of remaining fuel in liters.
    # rotate: the rotation angle in degrees (-90 to 90).
    # power: the thrust power (0 to 4).
    x, y, h_speed, v_speed, fuel, rotate, power = [int(i) for i in raw_input().split()]

    # Write an action using print
    # To debug: print >> sys.stderr, "Debug messages..."
    calculated_power = "0 "
    if v_speed > 39:
        calculated_power = calculated_power = "0 " + computeThrustPower(power, False)
    elif v_speed < -39:
        calculated_power = calculated_power = "0 " + computeThrustPower(power, True)
    else:
        calculated_power = calculated_power = "0 0"
    print >> sys.stderr, "calculated_power : {} - power = {} - v_speed = {}".format(calculated_power, power, v_speed)
    # 2 integers: rotate power. rotate is the desired rotation angle (should be 0 for level 1), power is the desired thrust power (0 to 4).
    print(calculated_power)