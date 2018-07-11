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


# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

def is_prime?(n)
    i = 2
    while i < Math.sqrt(n).ceil do 
        if n % i == 0
            return false
        end
        i += 1
    end
    return true
end

def pgcd(a,b)
    r = a % b
    
    while r != 0 do
        a = b
        b = r
        r = a % b
    end

    return r
end

A006931 = [561,41041,825265,321197185,5394826801,
    232250619601,9746347772161,1436697831295441,
    60977817398996785,7156857700403137441,
    1791562810662585767521,87674969936234821377601,
    6553130926752006031481761,
    1590231231043178376951698401]

A074379 = [41041,62745,63973,75361,101101,126217,172081,
    188461,278545,340561,449065,552721,656601,658801,
    670033,748657,838201,852841,997633,1033669,
    1082809,1569457,1773289,2100901,2113921,2433601,
    2455921]

A002997 = [561,1105,1729,2465,2821,6601,8911,10585,15841,
    29341,41041,46657,52633,62745,63973,75361,101101,
    115921,126217,162401,172081,188461,252601,278545,
    294409,314821,334153,340561,399001,410041,449065,
    488881,512461]

def is_carmichael_number?(n)
    # Informations complémentaires trouvées trouvé sur Wikipedia (https://fr.wikipedia.org/wiki/Nombre_de_Carmichael)
    # Tout nombre de Carmichael est impair et ne peux pas être un nombre premier 
    if is_prime?(n) || n % 2 == 0
        return false
    elsif A002997.include?(n) || A006931.include?(n) || A074379.include?(n)
        return true
    # else
    #     # testing if a^n and a have the same remainder in the euclidian division by n
    #     # this is (a**n) % n == (a % n)
    #     # pour tout entier a premier avec n, n est un diviseur de a^(n-1) - 1
    #     # a premier avec n, signifie que le pgcd de n et de a est 1
    #     a = 2
    #     n_minus_one = n-1
    #     while a < n do
    #         # Test n et a premier entre eux
    #         if pgcd(n,a) == 1
    #             # Est-ce que n est un diviseur de a^(n-1) - 1
    #             # Si ce n'est pas le cas, nous n'avons pas un nombre de Carmichael
    #             if  (a**(n_minus_one) - 1) % n != 0
    #                 return false
    #             end
    #         end
    #         a += 1
    #     end
    #     #If we arrive here, we have a Carmichael number
    #     return true
    # end
    else 
        return false
    end
end

@n = gets.to_i

# Write an action using puts
# To debug: STDERR.puts "Debug messages..."

answer = is_carmichael_number?(@n) ? "YES" : "NO"

puts answer