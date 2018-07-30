# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

@count = gets.to_i
lines = []
@count.times do
    lines << gets.chomp
end

lines.each do |line| 
    STDERR.puts line
end

STDERR.puts "########"

answers = []
current_answer = [ lines.shift ]
current_depth = 0
count_lines = 0

lines.each do |line| 
    count_lines += 1
    line_depth = line.count(".")
    if ((line_depth == current_depth) && current_depth == 0) || ((line_depth == 0) && (current_depth != 0))
        answers << current_answer
        current_answer = [ line.delete(".") ]
        current_depth = line_depth
    elsif line_depth > current_depth
        current_answer << line.delete(".")
        current_depth += 1
    elsif (line_depth <= current_depth) && (line_depth > 0)
        answers << current_answer
        n = current_answer.size - (current_depth - line_depth) - 1
        current_answer = current_answer[0, n]
        current_answer << line.delete(".")
        current_depth = line_depth
    end
end
answers << current_answer

# Write an action using puts
# To debug: STDERR.puts "Debug messages..."

answers.each do |element| 
    puts element.join(" > ")
end