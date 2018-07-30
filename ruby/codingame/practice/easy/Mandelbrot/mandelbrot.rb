# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

c = gets.chomp
m = gets.to_i

STDERR.puts c
STDERR.puts m.to_s

c = c.to_c
i = 0
f = Complex(0,0)
while i < m && f.abs < 2 do
    f = f ** 2 + c 
    i += 1
end

puts i.to_s