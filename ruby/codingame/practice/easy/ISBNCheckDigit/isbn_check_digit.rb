# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

def valid_10_digits_isbn?(isbn)
    first_9_digits = isbn[0..8]
    last_digit = isbn[9]
    weight = 10
    sum = 0

    first_9_digits.scan(/\w/).each do |digit| 
        sum += digit.to_i * weight
        weight -= 1
    end

    remainder = sum % 11
    final_value = remainder == 0 ? 0 : 11 - remainder
    expected_last_digit = final_value == 10 ?  "X" : final_value.to_s 
    expected_last_digit == last_digit
end

def valid_13_digits_isbn?(isbn)
    first_12_digits = isbn[0..11]
    last_digit = isbn[12]
    weight = [1,3]
    sum = 0
    index = 0

    first_12_digits.scan(/\w/).each do |digit| 
        sum += digit.to_i * weight[index]
        index = (index + 1) % 2
    end

    remainder = sum % 10
    final_value = remainder == 0 ? 0 : 10 - remainder
    final_value.to_s == last_digit
end

def check_authorized_chars?(isbn)
    size = isbn.size
    i = 0
    while i < size-2 do
        if !(isbn[i] =~ /[[:digit:]]/)
            return false
        else
        end
        i += 1
    end

    return isbn[size-1] =~ /[[:digit:]]/ || isbn[size-1] == 'X'
end

def valid_isbn?(isbn)
    
    if not check_authorized_chars? isbn
        return false
    end

    if isbn.size == 10
        return valid_10_digits_isbn? isbn
    end

    if isbn.size == 13
        return valid_13_digits_isbn? isbn
    end

    return false    
end

@n = gets.to_i
isbns = []
@n.times do
    isbns << gets.chomp
end

STDERR.puts isbns.to_s

wrong_isbns = isbns.select do |isbn| 
    not valid_isbn? isbn 
end

# Write an action using puts
# To debug: STDERR.puts "Debug messages..."

puts(wrong_isbns.size.to_s + " invalid:")

wrong_isbns.each do |wrong_isbn| 
    puts wrong_isbn
end
