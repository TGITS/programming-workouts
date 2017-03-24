import sys
import math

# To debug: print >> sys.stderr, "Debug messages..."

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

lon = raw_input()
lat = raw_input()
n = int(raw_input())

print >> sys.stderr, "User's longitude in degrees : {}".format(lon)
print >> sys.stderr, "User's latitude in degrees : {}".format(lat)
user_position = Position(float(lon.replace(',','.')),float(lat.replace(',','.')))
print >> sys.stderr, "User's position in radians : {}".format(user_position)
print >> sys.stderr, "Number of defribillators : {}".format(n)

defibs = []
for i in xrange(n):
    defib = raw_input()
    #print >> sys.stderr,"Defribillator number {} : {}".format(i,defib)
    defib_info = defib.split(';')
    f = Defibrillator(defib_info[0],defib_info[1],defib_info[2],defib_info[4],defib_info[5])
    defibs.append(f)
    print >> sys.stderr, "Defribillator : {}".format(str(f))

#initialization before the loop
nearest_defribillator = defibs.pop()
minimal_distance = distance(user_position, nearest_defribillator.position)

for defribillator in defibs:
    calculated_distance =  distance(user_position, defribillator.position)
    if calculated_distance < minimal_distance:
        minimal_distance = calculated_distance
        nearest_defribillator = defribillator


print(nearest_defribillator.name)
