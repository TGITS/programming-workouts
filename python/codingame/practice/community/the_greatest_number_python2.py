import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.
# Write an action using print
# To debug: print >> sys.stderr, "Debug messages..."

n = int(raw_input())
dot = False
minus = False
numbers = []
characters = raw_input()
print >> sys.stderr, "characters : {}".format(characters)
for c in characters.split(' '):
    if c == '.': #Est-ce que le caractere lu est un '.'
        dot = True
    elif c == '-': #Is this a - minus sign
        minus = True
    else : #Is this a number
        numbers.append(int(c))

print >> sys.stderr, "numbers : {}".format(",".join(map(str,numbers)))
greatest = ""
number_of_zeros=numbers.count(0)
print >> sys.stderr, "number_of_zeros : {}".format(number_of_zeros)
if number_of_zeros == len(numbers):
    greatest = "0"
else :
    if minus: # On cherche a faire le nombre le plus petit possible en valeur absolue
        numbers.sort()
        greatest += '-'
        del numbers[0:number_of_zeros]
        if number_of_zeros > 0 and dot:
            greatest += '0'
            number_of_zeros -= 1
        else:
            greatest += str(numbers[0])
            del numbers[0:1]
        if dot:
            greatest += '.'
        if number_of_zeros > 0:
            greatest += '0' * number_of_zeros
        for n in numbers:
            greatest += str(n)
    else: #On cherche a faire le nombre le plus grand possible
        numbers.sort(reverse=True)
        if number_of_zeros > 0:
            del numbers[-number_of_zeros:]
            #qu'il y ait un '.' ou pas, s'il y des zeros, on fait un nombre sans partie decimale
            for i in range(len(numbers)):
                greatest += str(numbers[i])
            if dot:
                if number_of_zeros > 1:
                    greatest += '0' * (number_of_zeros - 1)
            else:
                greatest += '0' * number_of_zeros
            print >> sys.stderr, "greatest : {}".format(greatest)
        else:
            if dot:
                for i in range(len(numbers)-1):
                    greatest += str(numbers[i])
                greatest += '.' + str(numbers[len(numbers)-1])
                print >> sys.stderr, "greatest : {}".format(greatest)
            else:
                for i in range(len(numbers)):
                    greatest += str(numbers[i])
                print >> sys.stderr, "greatest : {}".format(greatest)
print(greatest)
