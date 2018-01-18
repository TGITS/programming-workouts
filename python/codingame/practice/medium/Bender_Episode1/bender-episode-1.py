import sys
import math

# Créer une classe pour représenter la ville/le plan
# Création des objets de cette classe à partir d'une liste de liste ?
# Il faut une fonction pour trouver les coordonnées du point de départ
# Créer une classe Case représentant une case du plan ?
# Créer une classe pour représenter Bender/l'automate

# What are the abstractions needed to express the problem

class Cell:
    def __init__(self,x,y,content):
        self.x = x
        self.y = y
        self.content = content

class CityMap:
    def __init__(self, height, width, city_map):
        self.height = height
        self.width = width
        self.map = city_map

    def display(self, output=sys.stderr):
        for row in self.map :
            for col in row :
                print("{0}".format(col), file=output, end='', flush=True)
            print(flush=True)            

class Bender:
    def __init__(self,x,y,direction="SOUTH"):
        self.x = x
        self.y = y
        self.direction = direction
    
    def changeDirection(self,direction):
        self.direction = direction

    def nextPosition(self):
        pass

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

l, c = [int(i) for i in input().split()]
city_map = []
for i in range(l):
    row = list(input())
    city_map.append(row)

futurama = CityMap(l, c, city_map)
futurama.display()
# print("map : {0}".format(str(city_map)), file=sys.stderr)


# Write an action using print
# To debug: print("Debug messages...", file=sys.stderr)

print("answer")