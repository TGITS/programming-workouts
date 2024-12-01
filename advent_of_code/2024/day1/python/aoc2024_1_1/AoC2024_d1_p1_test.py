import pytest
from AoC2024_d1_p1 import *


@pytest.mark.parametrize(
    "test_input,expected", [("input.txt", 1000), ("input_test.txt", 6)]
)
def test_extract_sequences(test_input, expected):
    (left_sequence, right_sequence) = extract_sequences(test_input, "   ")
    assert len(left_sequence) == expected
    assert len(right_sequence) == expected


@pytest.mark.parametrize(
    "input_pair,expected",
    [
        (([3, 4, 2, 1, 3, 3], [4, 3, 5, 3, 9, 3]), 11),
        (([3, 3, 3, 3, 3, 3], [3, 3, 3, 3, 3, 3]), 0),
    ],
)
def test_compute_total_distance(input_pair, expected):
    assert compute_total_distance(input_pair) == expected


@pytest.mark.parametrize(
    "test_input,expected", [("input.txt", 3246517), ("input_test.txt", 11)]
)
def test_whole_computation(test_input, expected):
    assert compute_total_distance(extract_sequences(test_input, "   ")) == expected
