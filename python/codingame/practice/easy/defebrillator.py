import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

class Position:
    def __init__(self, longitude, latitude):
        self.longitude = math.radians(longitude)
        self.latitude = math.radians(latitude)

    def __str__(self):
        return "longitude: {} - latitude: {}".format(self.longitude,self.latitude)

class Defibrillator:
    def __init__(self, number, name, address, longitude, latitude):
        self.number = number
        self.name = name
        self.address = address
        self.position = Position(float(longitude.replace(',','.')),float(latitude.replace(',','.')))

    def __str__(self):
        return "Defibrillator {} - {} : \n\taddress : {}\n\tposition : {}".format(
            self.number,self.name,self.address,self.position)

def distance(A,B):
    '''Return the distance between a position A and a position B'''
    x = (B.longitude - A.longitude) * math.cos((A.latitude+B.latitude)/2)
    y = B.latitude - A.latitude
    d = math.sqrt(x**2+y**2)*6371
    return d

lon = input()
lat = input()
n = int(input())
# Write an action using print
# To debug: print("Debug messages...", file=sys.stderr)
print("User's longitude in degrees : {}".format(lon), file=sys.stderr)
print("User's latitude in degrees : {}".format(lat), file=sys.stderr)
user_position = Position(float(lon.replace(',','.')),float(lat.replace(',','.')))
print("User's position in radians : {}".format(user_position), file=sys.stderr)
print("Number of defribillators : {}".format(n), file=sys.stderr)

defibs = []
for i in range(n):
    defib = input()
    #print("Defribillator number {} : {}".format(i,defib), file=sys.stderr)
    defib_info = defib.split(';')
    f = Defibrillator(defib_info[0],defib_info[1],defib_info[2],defib_info[4],defib_info[5])
    defibs.append(f)
    print("Defribillator : {}".format(str(f)), file=sys.stderr)

#initialization before the loop

nearest_defribillator = defibs.pop()
minimal_distance = distance(user_position, nearest_defribillator.position)


for defribillator in defibs:
    calculated_distance =  distance(user_position, defribillator.position)
    if calculated_distance < minimal_distance:
        minimal_distance = calculated_distance
        nearest_defribillator = defribillator


print(nearest_defribillator.name)
