import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.
# To debug: print("Debug messages...", file=sys.stderr)

n = int(input())
dot = False
minus = True
numbers = []
for i in range(n):
    input = input()
    if input == '.': #Est-ce que le caractère lu est
        dot = True
    elif input == '-': #Is this a - minus sign
        minus = True
    else : #Is this a number
        numbers.append(input)


greatest = ""
if minus: # On cherche à faire le nombre le plus petit possible en valeur absolue
    numbers.sort()
    number_of_zeros=numbers.count(0)
    del numbers[0:number_of_zeros]
    if dot:
    greatest = str(numbers[0]) + '.'
else: #On cherche à faire le nombre le plus grand possible
    numbers.sort(reverse=True)


print("greatest")
