# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

def compute_next_river_item!(river)
    # We take and remove the first element of the list
    current_number = river.shift
    # We calculate the next_number
    next_number = current_number + current_number.to_s.scan(/\w/).map{|s| s.to_i}.sum
    # We put the new calculated element at the head of the list 
    river.unshift(next_number)
end

r_1 = gets.to_i

# Write an action using puts
# To debug: STDERR.puts "Debug messages..."
answer = "NO"

for i in 1...r_1 do
    current_river = [i]
    until current_river[0] >= r_1 do
        compute_next_river_item!(current_river)
    end
    if current_river[0] == r_1
        answer = "YES"
        break
    end
end

puts answer