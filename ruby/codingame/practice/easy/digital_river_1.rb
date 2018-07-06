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

smallest_river = [] 
smallest_river << (@r_1 < @r_2 ? @r_1 : @r_2)
largest_river = [] 
largest_river << (@r_1 >= @r_2 ? @r_1 : @r_2)

STDERR.puts "Smallest river " + smallest_river.to_s
STDERR.puts "Largest river " + largest_river.to_s

def compute_next_river_item!(river)
    
    last_element = river[-1]
    river << last_element + last_element.to_s.scan(/\w/).map{|s| s.to_i}.sum
    if river.size > 2
        
    end
end

#STDERR.puts "smallest_river[-1] <  largest_river[-1] : " + smallest_river[-1].to_s + " <= "  + largest_river[-1].to_s
while smallest_river[-1] <  largest_river[-1] do
    compute_next_river_item!(smallest_river)
    #STDERR.puts "smallest_river : " + smallest_river.to_s
    #STDERR.puts "smallest_river[-1] : " + smallest_river[-1].to_s
    #STDERR.puts "largest_river[-1]: " + largest_river[-1].to_s
end

while smallest_river[-1] != largest_river[-1] do
    compute_next_river_item!(smallest_river)
    compute_next_river_item!(largest_river)
    #STDERR.puts "smallest_river : " + smallest_river.to_s
    #STDERR.puts "smallest_river[-1] : " + smallest_river[-1].to_s
    #STDERR.puts "largest_river : " + largest_river.to_s
    #STDERR.puts "largest_river[-1]: " + largest_river[-1].to_s
end

puts smallest_river[-1].to_s