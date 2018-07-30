STDOUT.sync = true # DO NOT REMOVE
# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.
# ---
# Hint: You can use the debug stream to print initialTX and initialTY, if Thor seems not follow your orders.

class Coordinates
    attr_accessor :x, :y
    
    def initialize(x,y)
        @x = x
        @y = y
    end
end

# light_x: the X position of the light of power
# light_y: the Y position of the light of power
# initial_tx: Thor's starting X position
# initial_ty: Thor's starting Y position
light_x, light_y, initial_tx, initial_ty = gets.split(" ").collect {|x| x.to_i}
coordinates = Coordinates.new(initial_tx - light_x, initial_ty - light_y)

# game loop
loop do
    remaining_turns = gets.to_i # The remaining amount of turns Thor can move. Do not remove this line.
    
    # Write an action using puts
    # To debug: STDERR.puts "Debug messages..."
    move=case 
        when coordinates.x > 0 && coordinates.y > 0
            coordinates.x -= 1
            coordinates.y -= 1
            "NW"
        when coordinates.x > 0 && coordinates.y < 0
            coordinates.x -= 1
            coordinates.y += 1
            "SW"
        when coordinates.x > 0 && coordinates.y == 0
            coordinates.x -= 1
            "W"     
        when coordinates.x < 0 && coordinates.y > 0
            coordinates.x += 1
            coordinates.y -= 1
            "NE"
        when coordinates.x < 0 && coordinates.y < 0
            coordinates.x += 1
            coordinates.y += 1
            "SE"
        when coordinates.x < 0 && coordinates.y == 0
            coordinates.x += 1
            move="E"
        when coordinates.x == 0 && coordinates.y > 0
            coordinates.y += 1
            move="N"
        when coordinates.x == 0 && coordinates.y < 0
            move="S"
        else
            ""
        end
    # A single line providing the move to be made: N NE E SE S SW W or NW
    puts move
end