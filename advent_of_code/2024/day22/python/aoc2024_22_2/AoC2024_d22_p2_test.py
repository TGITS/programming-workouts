import pytest
from AoC2024_d22_p2 import *


@pytest.mark.parametrize("num1, num2,expected", [(42, 15, 37)])
def test_xor(num1, num2, expected):
    assert mix(num1, num2) == expected


@pytest.mark.parametrize(
    "num,expected",
    [(100000000, 16113920)],
)
def test_prune(num, expected):
    assert prune(num) == expected


compute_new_secret_number


@pytest.mark.parametrize(
    "num,expected",
    [(123, 15887950)],
)
def test_compute_new_secret_number(num, expected):
    assert compute_new_secret_number(num) == expected


@pytest.mark.parametrize(
    "num,expected",
    [(1, 8685429), (10, 4700978), (100, 15273692), (2024, 8667524)],
)
def test_compute_2000th_secret_number(num, expected):
    assert compute_nth_secret_number(num, 2000) == expected
