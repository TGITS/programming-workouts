# A digital river is a sequence of numbers where every number is followed by the same number plus the sum of its digits. 
# In such a sequence 123 is followed by 129 (since 1 + 2 + 3 = 6), which again is followed by 141.
# We call a digital river river K, if it starts with the value K.
# For example, river 7 is the sequence beginning with {7, 14, 19, 29, 40, 44, 52, ... } and river 471 is the sequence beginning 
# with {471, 483, 498, 519, ... }.
# Digital rivers can meet. This happens when two digital rivers share the same values. 
# River 32 meets river 47 at 47, while river 471 meets river 480 at 519.
# Given two meeting digital rivers print out the meeting point.

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

@r_1 = gets.to_i
@r_2 = gets.to_i

# Write an action using puts
# To debug: STDERR.puts "Debug messages..."

river_1 = [@r_1] 
river_2 = [@r_2] 

STDERR.puts "First river " + river_1.to_s
STDERR.puts "Second river " + river_2.to_s

def compute_next_river_item!(river)
    # We take and remove the first element of the list
    current_number = river.shift
    # We calculate the next_number
    next_number = current_number + current_number.to_s.scan(/\w/).map{|s| s.to_i}.sum
    # We put the new calculated element at the head of the list 
    river.unshift(next_number)
end

STDERR.puts "river_1: " + river_1.to_s
STDERR.puts "river_2: " + river_2.to_s

while river_1[0] != river_2[0] do
    STDERR.puts "river_1: " + river_1.to_s
    STDERR.puts "river_2: " + river_2.to_s
    if river_1[0] < river_2[0]
        compute_next_river_item!(river_1)
    elsif river_1[0] > river_2[0]
        compute_next_river_item!(river_2)
    else
        next
    end
end

puts river_1[0].to_s