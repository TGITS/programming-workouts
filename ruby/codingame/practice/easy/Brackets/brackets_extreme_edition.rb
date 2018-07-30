# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

# You must determine whether a given expression has valid brackets. 
# This means all the parentheses (), square brackets [] and curly brackets {} must be correctly paired & nested.

# The expression does not contain whitespace characters.
# Input
# A single line: expression.
# Output
# A single line: true if each kind of bracket (), [] and {} in expression are paired correctly, false otherwise.

def corresponding_char(c1,c2)
case c1
when '}'
    c2 == '{'
when ']'
    c2 == '['
when ')'
    c2 == '('
end
end

expression = gets.chomp

characters = expression.chars
STDERR.puts "characters : #{characters}"
stack = []
# Write an action using puts
# To debug: STDERR.puts "Debug messages..."
answer = true
for c in characters do
    STDERR.puts "Processing #{c}"
    case c 
    when '{', '(', '['
        STDERR.puts "stack << #{c}"
        stack << c
        STDERR.puts "stack : #{stack}"
    when '}', ')', ']'
        STDERR.puts "stack : #{stack}"
        if stack.empty?
            answer = false 
            break
        else 
            top_char = stack.pop
            STDERR.puts "top_char : #{top_char}"
            STDERR.puts "stack : #{stack}"
            if !corresponding_char(c,top_char)
                answer = false 
                break
            end
        end
    end
end

answer = answer ? stack.empty? : false
STDERR.puts "answer : #{answer}"
puts(answer ? 'true' : 'false')