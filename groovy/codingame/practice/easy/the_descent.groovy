input = new Scanner(System.in);

/**
 * The while loop represents the game.
 * Each iteration represents a turn of the game
 * where you are given inputs (the heights of the mountains)
 * and where you have to print an output (the index of the mountain to fire on)
 * The inputs you are given are automatically updated according to your last actions.
 **/


// game loop
while (true) {
    mountain_heights = []
    for (i = 0; i < 8; ++i) {
        mountainH = input.nextInt() // represents the height of one mountain.
        mountain_heights << mountainH
    }
    int highest_mountain = mountain_heights.max()
    int index = 0;
    int i = 0;
    for(elem in mountain_heights) {
        if (elem == highest_mountain) {
            index = i;
        }
        i++
    }
    // Write an action using println
    // To debug: System.err << "Debug messages...\n"

    println index // The index of the mountain to fire on.
}
