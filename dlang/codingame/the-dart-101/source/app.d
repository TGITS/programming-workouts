import std;
import std.uni : isWhite;
import std.array : split;
import std.string : isNumeric;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

struct Score
{
    int scoreValue;
    int numberOfRounds;
}

void main()
{
    int n = readln.strip.to!int;
    stderr.writeln("Number of players : ", n);
    string[] names;
    string[] shootsForEachPlayer;
    Score[] scores;
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
    for (int i = 0; i < n; i++)
    {
        scores ~= evaluateShoots(shootsForEachPlayer[i]);
    }

    const int index_winner = findIndexScoreMax(scores);
    writeln(names[index_winner]);
}

Score evaluateShoots(string shootsForPlayer)
{
    string[] shoots = shootsForPlayer.split!isWhite;
    stderr.writeln("shoots : ", shoots);
    int total_sum = 0;
    int round_sum = 0;
    bool is_round_on = true;
    int consecutive_misses = 0;
    int number_of_rounds = 0;
    int in_round_counter = 0;
    int shoot_value = 0;
    int index_shoots = 0;
    string shoot;
    while (index_shoots < shoots.length)
    {
        is_round_on = true;
        round_sum = 0;
        in_round_counter = 0;
        while (is_round_on)
        {
            shoot_value = 0;
            in_round_counter++;
            shoot = shoots[index_shoots];
            if (isNumeric(shoot))
            {
                shoot_value = shoot.strip.to!int();
                consecutive_misses = 0;
                stderr.write(" ", shoot_value, " ");
            }
            else if ("X" == shoot.strip)
            {
                shoot_value -= 20;
                consecutive_misses++;
                if (consecutive_misses == 2)
                {
                    shoot_value -= 10;
                }
                else if (consecutive_misses == 3)
                {
                    total_sum = 0;
                    shoot_value = 0;
                    round_sum = 0;
                    consecutive_misses = 0;
                }

                stderr.write(" X ");
            }
            else
            {
                string[] vals = shoot.split("*");
                shoot_value = vals[0].to!int * vals[1].to!int;
                consecutive_misses = 0;
                stderr.write(" ", shoot, " ");
            }
            round_sum += shoot_value;
            if (total_sum + round_sum > 101)
            {
                in_round_counter = 0;
                round_sum = 0;
                is_round_on = false;
                number_of_rounds++;
            }

            if (total_sum + round_sum == 101)
            {
                in_round_counter = 0;
                is_round_on = false;
                number_of_rounds++;
            }

            if (in_round_counter == 3)
            {
                in_round_counter = 0;
                is_round_on = false;
                number_of_rounds++;
                consecutive_misses = 0;
            }

            index_shoots++;
        }

        total_sum += round_sum;
        if (total_sum == 101)
        {
            break;
        }

    }
    return Score(total_sum, number_of_rounds);
}

int findIndexScoreMax(Score[] scores)
{
    int max = scores[0].scoreValue;
    int max_index = 0;
    for (int i = 1; i < scores.length; i++)
    {
        if ((max < scores[i].scoreValue) || (max == scores[i].scoreValue
                && (scores[max_index].numberOfRounds > scores[i].numberOfRounds)))
        {
            max = scores[i].scoreValue;
            max_index = i;
        }
    }
    return max_index;
}
