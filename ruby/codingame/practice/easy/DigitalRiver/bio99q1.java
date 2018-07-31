/* Import the Java I/O package */
import java.io.*;
import AppletConsoleApp;

/**
 * Solution to the 1999 British Informatics Olympiad exam
 * question 1: Digital Rivers
 *
 * This application that can be run either from the console
 * as a stand-alone application or from an in-applet
 * console created with the AppletConsole applet.
 *
 * Solution copyright (c) 1999 The British Informatics Olympiad (BIO).
 *
 * This program may be freely copied by persons or organisations
 * involved in the British Informatics Olympiad or the International
 * Olympiad in Informatics, on condition that no changes are made and
 * this notice is not altered. Distribution for profit is forbidden
 * unless permission is first obtained in writing from the BIO.
 *
 * This program is for educational purposes only and comes with no
 * warranty, implied or otherwise, as to its fitness for any purpose.
 *
 * Author:     Antony Rix
 * Internet: http://www.christs.cam.ac.uk/bio/
 * E-mail:     a.rix@lineone.net
 * S-mail:     The British Informatics Olympiad
 *                     Christ's College
 *                     Cambridge CB2 3BU
 *                     United Kingdom
 *
 * @author    Antony Rix
 * @version 0.1
 * @see         AppletConsoleApp
 * @see         AppletConsole
 */
class BIO99R1Q1App extends AppletConsoleApp {
    /**
     * Start the application from the command line.
     */
    public static void main(String[] args) {
        BIO99R1Q1App thisApp = new BIO99R1Q1App();
        thisApp.redirectStreams(System.in, System.out);
        thisApp.run();
    }

    /**
     * Start value
     */
    int n;

    /**
     * The implementation of this application.
     */
    public void run() {
        out.println(
            "Enter a number from 1 to 16384 for\npart 1(a), or 0 to run part 1(b)." );
        out.print( ">" );
        
        try {
            /* Create a StreamTokenizer to allow us to read in the start numbers
            */
            StreamTokenizer sin = new StreamTokenizer( in );
            sin.nextToken();
            n = (int)sin.nval;
            if( n > 0 )
                part1a();
            else
                part1b();
        } catch (IOException e) { out.println("I/O failure"); };
        out.println( "Program finished." );
    }

    /**
     * Given a starting value n, calculate the next element in the river.
     */
    public int next_river( int n ) {
        int s = n;
        while( s > 0 ) {
            n = n + (s % 10);
            s = s / 10;
        }
        return n;
    }

    /**
     * Solution to part 1(a).
     * Given a start number, find the lowest number at which this river meets
     * one of rivers 1, 3 or 9.  We do this by following rivers 1, 3 and 9
     * and river n until one of them meets.
     */
    public void part1a() {
        /* river1, river3 and river9 will be used to hold the current value
           in river 1, river 3 and river 9 as we follow them along. */
        int river1 = 1;
        int river3 = 3;
        int river9 = 9;

        /* If we have not found a match, move along rivers 1, 3 and 9
           until we meet or pass n.  If we still haven't got a meeting,
           move one step along river n. */
        while( (n != river1) && (n != river3) && (n != river9) ) {
            while( river1 < n ) river1 = next_river( river1 );
            while( river3 < n ) river3 = next_river( river3 );
            while( river9 < n ) river9 = next_river( river9 );
            if( (n != river1) && (n != river3) && (n != river9) )
                n = next_river( n );
        }
        /* Show the solution */
        if( river1 == n ) out.println( "First meets river 1 at " + n );
        if( river3 == n ) out.println( "First meets river 3 at " + n );
        if( river9 == n ) out.println( "First meets river 9 at " + n );
    }

    /**
     * Find the lowest number that is in exactly 100 rivers. We do this
     * by following rivers 1 to 500 and storing an array with the times
     * each number has been met.
     */
    public void part1b() {
        int start;  /* Current starting river */
        int current;   /* Position in river start */
        int[] hits = new int[500];  /* Number of times we've met each value */

        /* Initialise number of hits to zero */
        for( current = 1; current < 500; current++ ) hits[current] = 0;

        /* Follow rivers 1 to 500 */
        for( start = 1; start < 500; start++ ) {
            current = start;
            while( current < 500 ) {
                hits[current]++;
                current = next_river( current );
            }
        }

        /* Find the first time we meet a number 100 times */
        for( current = 1; current < 500; current++ )
            if( hits[current] == 100 ) {
                out.println( "First number in 100 rivers is " + current );
                break;
            }
    }
}
