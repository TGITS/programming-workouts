require "prime"

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



def is_carmichael_number?(n)
    # Informations complémentaires trouvées trouvé sur Wikipedia (https://fr.wikipedia.org/wiki/Nombre_de_Carmichael)
    # En utilisant judicieusement le module "prime" de Ruby et en s'appuyant sur le Théorème de Korselt 1899 qui dit :
    # Un entier positif composé n est un nombre de Carmichael si et seulement si aucun carré de nombre premier ne divise n 
    # (on dit que n est sans facteur carré) et pour chaque diviseur premier p de n, le nombre p − 1 divise n − 1. 
    # De plus, un tel n divise tous les a^n – a (même pour a non premier à n).

    # For an arbitrary integer:
    # n = p_1**e_1 * p_2**e_2 * .... * p_n**e_n,
    # prime_division(n) returns:
    # [[p_1, e_1], [p_2, e_2], ...., [p_n, e_n]].
    # Prime.prime_division(12) #=> [[2,2], [3,1]]
    fs = Prime.prime_division(n)
    if fs.size > 1
        return fs.all?{|x| x[1] == 1 && (n - 1) % (x[0] - 1) == 0} ? true : false
    else
        return false
    end
end

n = gets.to_i

# Write an action using puts
# To debug: STDERR.puts "Debug messages..."

answer = is_carmichael_number?(n) ? "YES" : "NO"

puts answer