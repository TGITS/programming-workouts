import sys
import math

# You might know Fermat’s small theorem:
# If n is prime, then for any integer a, we have a^n ≡ a mod n, 
# that means that a^n and a have the same   r in the euclidian division by n.

# There are numbers, called Carmichael numbers, that are not prime but for which the equality remains true for any integer a.
# For example, 561 is a Carmichael numbers because for any integer a, a^561 ≡ a mod 561. It’s in fact the smallest Carmichael number.

# You have to tell if the given number is a Carmichael number or not. Beware, you might be given a prime number.
# Input
# A single number n.
# Output
# YES if n is a Carmichael number, or NO if it’s not.

# Informations complémentaires trouvées trouvé sur Wikipedia (https://fr.wikipedia.org/wiki/Nombre_de_Carmichael)
# # Tout nombre de Carmichael est impair et ne peux pas être un nombre premier 

# testing if a^n and a have the same remainder in the euclidian division by n
# this is (a**n) % n == (a % n)
# pour tout entier a premier avec n, n est un diviseur de a^(n-1) - 1
# a premier avec n, signifie que le pgcd de n et de a est 1

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

def is_prime(n):
    for i in range(2,int(math.ceil(math.sqrt(n)))):
        if n % i == 0:
            return False 
    return True

def pgcd(a,b):
    r = a % b
    while r != 0 :
        a = b
        b = r
        r = a % b
    return r

def is_carmichael_number(n):
    # Informations complémentaires trouvées trouvé sur Wikipedia (https://fr.wikipedia.org/wiki/Nombre_de_Carmichael)
    # Tout nombre de Carmichael est impair et ne peux pas être un nombre premier 
    if is_prime(n) or n % 2 == 0:
        return False
    else:
        # testing if a^n and a have the same remainder in the euclidian division by n
        # this is (a**n) % n == (a % n)
        # pour tout entier a premier avec n, n est un diviseur de a^(n-1) - 1
        # a premier avec n, signifie que le pgcd de n et de a est 1
        a = 2
        n_minus_one = n-1
        while a < n :
            # Test n et a premier entre eux
            # De plus est-ce que n est un diviseur de a^(n-1) - 1
            # Si premier entre eux et n n'est pas un diviseur de a^(n-1) - 1, nous n'avons pas un nombre de Carmichael
            if pgcd(n,a) == 1 and (a**(n_minus_one) - 1) % n != 0:
                return False
            a += 1
    #If we arrive here, we have a Carmichael number
    return True

if __name__ == "__main__":
    n = int(input())
    answer = "YES" if is_carmichael_number(n) else "NO"
    # Write an action using print
    # To debug: print("Debug messages...", file=sys.stderr)
    print(answer)