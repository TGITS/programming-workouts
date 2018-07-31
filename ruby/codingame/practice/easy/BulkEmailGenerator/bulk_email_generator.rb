# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

n = gets.to_i
text = ""
n.times do
    text += gets
end

STDERR.puts text
# Write an action using puts
# To debug: STDERR.puts "Debug messages..."

offset = 0
left_parenthesis_indexes = []
right_parenthesis_indexes = []

#loop to find all the begin and end indexes of the template string to replace
while text.index('(', offset) do
    offset = text.index('(', offset)
    left_parenthesis_indexes << offset
    offset = text.index(')', offset)
    right_parenthesis_indexes << offset
end

i = 0
size = left_parenthesis_indexes.size
replacement_elements = []

while i < size do
    current_template = text[(left_parenthesis_indexes[i]+1)...right_parenthesis_indexes[i]]
    #STDERR.puts "Currently found template : " + current_template 
    elements = current_template.split("|")
    if current_template.count("|") + 1 != elements.size
        elements << ""
    end
    #STDERR.puts "Found elements of the template : " + elements.join(" ")
    elements_size = elements.size
    #STDERR.puts "Number of elements " + elements_size.to_s
    element_index = ((i+1) % elements_size) - 1
    #STDERR.puts "Calculated index : " + element_index.to_s
    replacement_element = elements[element_index]
    #STDERR.puts "Replacement text : " + replacement_element
    replacement_elements << replacement_element
    i += 1
end

i = size - 1
while i >= 0 do
    text[left_parenthesis_indexes[i]...right_parenthesis_indexes[i]+1] = replacement_elements[i]
    i -= 1
end

puts text