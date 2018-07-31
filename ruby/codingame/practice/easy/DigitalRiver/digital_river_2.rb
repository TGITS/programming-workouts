require 'set'

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

def compute_next_river_item(current_number)
    next_number = current_number
    while current_number > 0 do
        next_number = next_number + (current_number % 10);
        current_number = current_number / 10;
    end
    next_number
end

r_1 = gets.to_i

# Write an action using puts
# To debug: STDERR.puts "Debug messages..."
answer = "NO"
already_tested_number = Set.new()

for i in 1...r_1 do
    current_river = i
    until (current_river >= r_1) or already_tested_number.include?(current_river) do
        already_tested_number.add(current_river)
        current_river = compute_next_river_item(current_river)
    end
    if current_river == r_1
        answer = "YES"
        break
    end
end

puts answer