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

# Possible sublist categories.
# Change the values as you see fit.
SUBLIST = 0
SUPERLIST = 1
EQUAL = 2
UNEQUAL = 3


def check_subsequences(list_one, list_two):
    n1 = len(list_one)  # The "longest" of the 2 subsequences
    n2 = len(list_two)  # The "shortest" of the 2 subsequences
    return any([list_one[i:n2 + i] == list_two for i in range(0, n1 - n2 + 1)])


def sublist(list_one, list_two):
    if list_one == list_two:
        return EQUAL
    if check_subsequences(list_one, list_two):
        return SUPERLIST
    if check_subsequences(list_two, list_one):
        return SUBLIST
    return UNEQUAL
