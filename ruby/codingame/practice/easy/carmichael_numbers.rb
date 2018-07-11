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
    (2..Math.sqrt(n).ceil).each do |i|
        if n % i == 0
            return false
        end
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

def is_carmichael_number?(n)
    if is_prime?(n)
        return false
    elsif n % 2 == 0
        return false
    else
        # testing if a^n and a have the same remainder in the euclidian division by n
        # this is (a**n) % n == (a % n)
        # Tout nombre de Carmichael est impair et ne peux pas être un nombre premier 
        # Autre test possible (trouvé sur Wikipedia) : https://fr.wikipedia.org/wiki/Nombre_de_Carmichael
        # pour tout entier a premier avec n, n est un diviseur de a^(n-1) - 1
        # a premier avec n, signifie que le pgcd de n et de a est 1
        a = 3
        while a < n do
            # Test n et a premier entre eux
            if pgcd(n,a) == 1
                # Est-ce que n est un diviseur de a^(n-1) - 1
                # Si ce n'est pas le cas, nous n'avons pas un nombre de Carmichael
                if  (a **(n-1) - 1) % n != 0
                    return false
                end
            end
            a += 2
        end
    end
    #If we arrive here, we have a carmichael number
    return true
end

@n = gets.to_i

# Write an action using puts
# To debug: STDERR.puts "Debug messages..."

answer = is_carmichael_number?(@n) ? "YES" : "NO"

puts answer