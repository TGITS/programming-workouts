# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

n = gets.to_i
strengthes = []
n.times do
    strengthes << gets.to_i
end

STDERR.puts "Before sorting : #{strengthes}"
strengthes.sort!
STDERR.puts "After sorting : #{strengthes}"
index = strengthes.size - 1
strength_1 = strengthes[index]
index -= 1
strength_2 = strengthes[index]

minimal_difference = strength_1 - strength_2

answer = if index == 0
            "#{minimal_difference}"
    else
        while index > 0
            strength_1 = strength_2
            index -= 1
            strength_2 = strengthes[index]
            difference = strength_1 - strength_2
            if difference < minimal_difference
                minimal_difference = difference
            end
        end
        "#{minimal_difference}"
    end
# Write an action using puts
# To debug: STDERR.puts "Debug messages..."

puts answer