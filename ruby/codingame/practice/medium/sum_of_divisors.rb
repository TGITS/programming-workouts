require "prime"

# Pour résumer, il faut pour chaque nombre connaitre la liste de ses diviseurs premiers
# Une fois qu'on a cette liste on applique la formule qu'on peut trouver
# sur https://fr.wikipedia.org/wiki/Somme_des_diviseurs ou http://villemin.gerard.free.fr/Referenc/Prof/APROF/DivSomme.htm
# Dans un premier temps il nous faut donc la liste des facteurs premiers d'un nombre
# Puis dans cette liste j'ai besoin de trouver chaque facteur unique
# Pour chaque facteur unique il faut que je détermine combien de fois il est présent.
# Ces informations me permettront d'appliquer la formule. Je pourrais ainsi calculer la somme pour chaque nombre


def calculate_sum_divisors(number)
    STDERR.puts "The number for which we calculate the sum of its divisors : #{number}"
    # For an arbitrary integer:
    # n = p_1**e_1 * p_2**e_2 * .... * p_n**e_n,
    # prime_division(n) returns:
    # [[p_1, e_1], [p_2, e_2], ...., [p_n, e_n]].
    # Prime.prime_division(12) #=> [[2,2], [3,1]]
    factors = Prime.prime_division(number)
    STDERR.puts "List of the prime factors with their exponents for #{number} : #{factors.map{|item| item.to_s}}"
    #Selon les formules que l'on trouve dans https://fr.wikipedia.org/wiki/Somme_des_diviseurs ou http://villemin.gerard.free.fr/Referenc/Prof/APROF/DivSomme.htm
    sum_divisors = 1 # Initialisation de sa valeur
    for item in factors do
        #Calcul du produit
        sum_divisors = sum_divisors * ((item[0] ** (item[1]+1) - 1)/(item[0] - 1))
    end
    STDERR.puts "The sum of the divisors : #{sum_divisors}"
    return sum_divisors
end

n = gets.to_i

# Write an action using puts
# To debug: STDERR.puts "Debug messages..."
STDERR.puts "the provided number : #{n}"

total_sum = 1

if n>1
    for i in 2..n do
        total_sum += calculate_sum_divisors(i)
    end
end

puts "#{total_sum}"