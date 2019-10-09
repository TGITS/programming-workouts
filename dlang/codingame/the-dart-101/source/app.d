import std;
import std.uni : isWhite;
import std.array : split;
import std.string : isNumeric;

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
    int[] scores;
    for (int i = 0; i < n; i++)
    {
        names ~= readln.strip;
    }
    for (int i = 0; i < n; i++)
    {
        shootsForEachPlayer ~= readln.strip;
    }

    stderr.writeln("names : ", names);
    stderr.writeln("shoots for the ", n, " players : ", shootsForEachPlayer);
    // Write an action using writeln().
    // To debug: stderr.writeln("Debug messages...");
    for (int i = 0; i < n; i++)
    {
        scores ~= evaluateShoots(shootsForEachPlayer[i]);
    }

    const int index_winner = findIndexScoreMax(scores);
    writeln(names[index_winner]);
}

int evaluateShoots(string shootsForPlayer)
{
    stderr.writeln("shoots : ", shootsForPlayer.split!isWhite);
    string[] shoots = shootsForPlayer.split!isWhite;
    int sum = 0;
    int consecutiveMisses = 0;
    foreach (shoot; shoots)
    {
        int shootValue = 0;
        if (isNumeric(shoot))
        {
            shootValue = shoot.strip.to!int();
        }
        else if ("X" == shoot.strip)
        {
            shootValue -= 20;
            if (consecutiveMisses == 1)
            {
                shootValue -= 10;
                consecutiveMisses++;
            }
            else if (consecutiveMisses == 2)
            {
                sum = 0;
                shootValue = 0;
                consecutiveMisses = 0;
            }
            else
            {
                consecutiveMisses++;
            }
        } 
        else 
        {
            string[] vals = shoot.split("*");
            shootValue = vals[0].to!int * vals[1].to!int;
        }
        
        if(sum + shootValue <= 101) {
            sum += shootValue;
        }

        if(sum == 101) {
            break;
        }
    }

    return sum;
}

int findIndexScoreMax(int[] scores) {
    int max = scores[0];
    int max_index = 0;
    for(int i=1; i < scores.length; i++) {
        if(max < scores[i]) {
            max = scores[i];
            max_index = i;
        } 
    }
    return max_index;
}