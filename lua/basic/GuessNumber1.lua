print "Guess a number"
math.randomseed(os.time())
rnd = math.random()
number = math.random( 100 )
count = 0
while true do
    print("What is the number you propose ?")
    answer =  io.read("*n")
    count = count + 1
    print("Your answer is : " .. answer)
    if answer < number then
        print("Your answer is too low")
    elseif answer > number then
        print("Your answer is too high") 
    else 
        print("Congrats, you guess it in " .. count .. " tries")
        print("The number was, as you guessed, " .. number)
        break
    end
    print("Try to guess again")
end