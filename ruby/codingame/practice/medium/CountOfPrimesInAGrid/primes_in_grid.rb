require "prime"
require "set"

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

r, c = gets.split(" ").collect { |x| x.to_i }
initial_grid = []
r.times do
    row = gets.chomp
    initial_grid << row.split(" ")
end

transposed_grid = initial_grid.transpose 
lines = []

for line in initial_grid do
    lines << line.join("")
end

for line in transposed_grid do
    lines << line.join("")
end

STDERR.puts "#{lines.join(' ')}"

numbers_to_check = Set.new

for line in lines do
    start_index = 0
    while start_index < line.size do
        end_index = line.size
        while end_index >= start_index do
            numbers_to_check << line[start_index..end_index].to_i
            end_index -= 1
        end
        start_index += 1
    end
end

STDERR.puts "#{numbers_to_check.to_a.join(' ')}"

answer = numbers_to_check.collect { |x| x.to_i }.select{ |x| x != 1 && Prime.prime?(x) }.size

puts "#{answer}"