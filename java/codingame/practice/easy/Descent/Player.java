import java.util.*;
import java.io.*;
import java.math.*;

/**
 * The while loop represents the game.
 * Each iteration represents a turn of the game
 * where you are given inputs (the heights of the mountains)
 * and where you have to print an output (the index of the mountain to fire on)
 * The inputs you are given are automatically updated according to your last actions.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int[] mountainHeight = new int[8];
        // game loop
        while (true) {
            for (int i = 0; i < 8; i++) {
                mountainHeight[i] = in.nextInt();
            }

            int max = Arrays.stream(mountainHeight).max().getAsInt();
            int maxIndex = indexOf(mountainHeight, max);
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            System.out.println(maxIndex); // The index of the mountain to fire on.
        }
    }

    public static int indexOf(int[] array, int value){
        int index = -1;
        int i = 0;
        for(int elem:array){
            if(elem == value) {
                index = i;
                break;
            }
            i++;
        }
        return index;
    }
}
