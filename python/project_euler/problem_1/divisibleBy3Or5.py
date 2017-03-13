# https://projecteuler.net/problem=1
# If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
# Find the sum of all the multiples of 3 or 5 below 1000.

#  S = [x**2 for x in range(10)]
# V = [2**i for i in range(13)]
# M = [x for x in S if x % 2 == 0]
divisible_by_3_or_5 = [x for x in range(1,1000) if x % 3 == 0 or x % 5 == 0]
print("\n".join(map(str,divisible_by_3_or_5)))
print("This is {} numbers ".format(len(divisible_by_3_or_5)))
print("The sum of all the multiples of 3 or 5 below 1000 is {}".format(sum(divisible_by_3_or_5)))
