import pytest
from AoC2024_d21_p1 import *


@pytest.mark.parametrize(
    "code,expected", [("029A", "<A^A>^^AvvvA"), ("179A", "^<<A^^A>>AvvvA")]
)
def test_commands_for_numeric_pad(code, expected):
    assert commands_for_numeric_pad(code) == expected


@pytest.mark.parametrize(
    "code,expected",
    [("^<<A", "<Av<AA>>^A")],
)
def test_commands_for_directional_pad(code, expected):
    assert commands_for_directional_pad(code) == expected


def test_commands_for_directional_pad_bis():
    assert commands_for_directional_pad("<") in ("<v<A", "v<<A")


def test_commands_for_numeric_pad():
    hashmap = {
        "029A": "<vA<AA>>^AvAA<^A>A<v<A>>^AvA^A<vA>^A<v<A>^A>AAvA^A<v<A>A>^AAAvA<^A>A",
    }
    expected = 1972
    assert compute_complexity(hashmap) == expected

    hashmap = {
        "029A": "<vA<AA>>^AvAA<^A>A<v<A>>^AvA^A<vA>^A<v<A>^A>AAvA^A<v<A>A>^AAAvA<^A>A",
        "980A": "<v<A>>^AAAvA^A<vA<AA>>^AvAA<^A>A<v<A>A>^AAAvA<^A>A<vA>^A<A>A",
        "179A": "<v<A>>^A<vA<A>>^AAvAA<^A>A<v<A>>^AAvA^A<vA>^AA<A>A<v<A>A>^AAAvA<^A>A",
        "456A": "<v<A>>^AA<vA<A>>^AAvAA<^A>A<vA>^A<A>A<vA>^A<A>A<v<A>A>^AAvA<^A>A",
        "379A": "<v<A>>^AvA^A<vA<AA>>^AAvA<^A>AAvA^A<vA>^AA<A>A<v<A>A>^AAAvA<^A>A",
    }
    expected = 126384
    assert compute_complexity(hashmap) == expected
