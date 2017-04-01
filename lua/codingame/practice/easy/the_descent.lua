-- The while loop represents the game.
-- Each iteration represents a turn of the game
-- where you are given inputs (the heights of the mountains)
-- and where you have to print an output (the index of the mountain to fire on)
-- The inputs you are given are automatically updated according to your last actions.


-- game loop
while true do
    max_height=0
    max_height_index=0
    for i=0,8-1 do
        mountainH = tonumber(io.read()) -- represents the height of one mountain.
        if(max_height<mountainH)
        then
            max_height=mountainH
            max_height_index=i
        end
    end

    -- Write an action using print()
    -- To debug: io.stderr:write("Debug message\n")

    print(string.format("%d",max_height_index)) -- The index of the mountain to fire on.
end
