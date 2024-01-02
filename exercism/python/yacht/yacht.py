"""
This exercise stub and the test suite contain several enumerated constants.

Since Python 2 does not have the enum module, the idiomatic way to write
enumerated constants has traditionally been a NAME assigned to an arbitrary,
but unique value. An integer is traditionally used because it’s memory
efficient.
It is a common practice to export both constants and functions that work with
those constants (ex. the constants in the os, subprocess and re modules).

You can learn more here: https://en.wikipedia.org/wiki/Enumerated_type
"""

from collections import Counter

# Score categories.
# Change the values as you see fit.
YACHT = 0
ONES = 1
TWOS = 2
THREES = 3
FOURS = 4
FIVES = 5
SIXES = 6
FULL_HOUSE = 7
FOUR_OF_A_KIND = 8
LITTLE_STRAIGHT = 9
BIG_STRAIGHT = 10
CHOICE = 11


def score(dice, category):
    counter = Counter(dice)

    def fun(number, counter):
        return number * counter[number]

    score_by_category = {
        YACHT: 50 if counter.most_common(1)[0][1] == 5 else 0,
        ONES: fun(1, counter),
        TWOS: fun(2, counter),
        THREES: fun(3, counter),
        FOURS: fun(4, counter),
        FIVES: fun(5, counter),
        SIXES: fun(6, counter),
        FULL_HOUSE: sum(dice) if sorted(counter.values()) == [2, 3] else 0,
        FOUR_OF_A_KIND: 4 * counter.most_common(1)[0][0]
        if counter.most_common(1)[0][1] >= 4
        else 0,
        LITTLE_STRAIGHT: 30 if sorted(dice) == list(range(1, 6)) else 0,
        BIG_STRAIGHT: 30 if sorted(dice) == list(range(2, 7)) else 0,
        CHOICE: sum(dice),
    }

    return score_by_category[category]
