import 'dart:io';
import 'dart:math';

/**
 * The while loop represents the game.
 * Each iteration represents a turn of the game
 * where you are given inputs (the heights of the mountains)
 * and where you have to print an output (the index of the mountain to fire on)
 * The inputs you are given are automatically updated according to your last actions.
 **/
void main() {

    // game loop
    while (true) {
        var max_height = 0;
        var max_height_index = 0;
        for (int i = 0; i < 8; i++) {
            int mountainH = int.parse(stdin.readLineSync()); // represents the height of one mountain.
            if (max_height < mountainH) {
                max_height = mountainH;
                max_height_index = i;
            }
        }

        // Write an action using print()
        // To debug: stderr.writeln('Debug messages...');

        print(max_height_index); // The index of the mountain to fire on.
    }
}
