import sys
import math

def sum_divisors(num):
    the_sum = num
    for i in range(2,num):
        if num % i == 0:
            the_sum += i
    return the_sum

n = int(input())

total_sum = n

if n>1:
    for i in range(2,n+1):
        total_sum += sum_divisors(i)

print(total_sum)
