import std;
import std.uni : isWhite;
import std.array : split;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

void main()
{
    int n = readln.strip.to!int;
    stderr.writeln("Number of players : ", n);
    string[] names;
    string[] shootsForEachPlayer;
    for (int i = 0; i < n; i++)
    {
        names ~= readln.strip;
    }
    for (int i = 0; i < n; i++)
    {
        shootsForEachPlayer ~= readln.strip;
    }

    stderr.writeln("names : ", names);
    stderr.writeln("shoots : ", shootsForEachPlayer);
    // Write an action using writeln().
    // To debug: stderr.writeln("Debug messages...");
    for (int i = 0; i < n; i++)
    {
        evaluateShoots(shootsForEachPlayer[i]);
    }
    
    writeln("winner");
}

void evaluateShoots(string shootsForPlayer)
{
    stderr.writeln("shoots : ", shootsForPlayer.split!isWhite);
}
