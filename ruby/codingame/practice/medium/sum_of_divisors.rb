require "prime"

def calculate_sum_divisors(number)
    STDERR.puts "#{number}"
    if number.prime?
        return number + 1
    else
        # For an arbitrary integer:
        # n = p_1**e_1 * p_2**e_2 * .... * p_n**e_n,
        # prime_division(n) returns:
        # [[p_1, e_1], [p_2, e_2], ...., [p_n, e_n]].
        # Prime.prime_division(12) #=> [[2,2], [3,1]]
        factors = Prime.prime_division(number)
        STDERR.puts "List of the prime factors with their exponents for #{number} : #{factors.map{|item| item.to_s}}"
        sum_divisors = 1
        for item in factors do
            for i in 1..item[1] do
                sum_divisors += item[0] ** i
            end
        end
        if factors.length > 1
            factors_list = []
            immutable_factors_list = []
            for item in factors do
                factors_list << item[0]
                immutable_factors_list << item[0]
            end
            while factors_list.length > 1 do
                current_factor = factors_list.shift
                intermediate_sum = 0
                intermediate_product = 1
                for num in immutable_factors_list do
                    if current_factor != num
                        intermediate_product *= current_factor * num
                        intermediate_sum += intermediate_product
                    end
                end
            end
            sum_divisors += intermediate_sum
        end
        STDERR.puts "#{sum_divisors}"
        return sum_divisors
    end
end


# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

n = gets.to_i

# Write an action using puts
# To debug: STDERR.puts "Debug messages..."
STDERR.puts "the provided number : #{n}"

total_sum = 1

if n>1
    for i in 2..n
        total_sum += calculate_sum_divisors(i)
    end
end

puts "#{total_sum}"
