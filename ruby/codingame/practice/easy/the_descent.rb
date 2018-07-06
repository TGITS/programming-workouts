STDOUT.sync = true # DO NOT REMOVE
# The while loop represents the game.
# Each iteration represents a turn of the game
# where you are given inputs (the heights of the mountains)
# and where you have to print an output (the index of the mountain to fire on)
# The inputs you are given are automatically updated according to your last actions.


# game loop
loop do
    max_height = 0
    max_height_index = 0
    index = 0
    8.times do
        mountain_h = gets.to_i # represents the height of one mountain.
        if mountain_h > max_height
            max_height = mountain_h
            max_height_index = index
        end
        index += 1
    end
    
    # Write an action using puts
    # To debug: STDERR.puts "Debug messages..."
    
    puts max_height_index # The index of the mountain to fire on.
end