#https://www.codingame.com/ide/puzzle/sum-of-divisors

import sys
import math

def divisors(num):
    list_of_divisors = []
    for i in range (1,num+1):
        if num % i == 0:
            list_of_divisors.append(i)
    print("List of divisors {}".format(','.join(str(divisor for divisor in list_of_divisors))), file=sys.stderr)
    return  list_of_divisors

def sum_list(list_of_numbers):
    sum_of_numbers = sum(list_of_numbers)
    print("Sum of numbers {}".format(sum_of_numbers), file=sys.stderr)
    return sum_of_numbers

n = int(input())

list_of_sum = []
for i in range(1,n+1):
    list_of_sum.append(sum_list(divisors(i)))

total_sum = sum_list(list_of_sum)

print(total_sum)
