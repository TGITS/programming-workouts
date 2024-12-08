import pytest
from AoC2024_d8_p1 import *


@pytest.mark.parametrize(
    "test_input,expected_y_max, expected_x_max",
    [("input.txt", 50, 50), ("input_test.txt", 12, 12)],
)
def test_extract_sequences(test_input, expected_y_max, expected_x_max):
    map_of_antennas = extract_data(test_input)
    assert len(map_of_antennas) == expected_y_max
    assert len(map_of_antennas[0]) == expected_x_max


@pytest.mark.parametrize(
    "antenna1,antenna2, expected_value", [((4, 3), (5, 5), ((3, 1), (6, 7)))]
)
def test_get_antinodes_for_pair(antenna1, antenna2, expected_value):
    result = get_antinodes_for_pair(antenna1, antenna2)
    assert len(result) == len(expected_value)
    assert result == expected_value


@pytest.mark.parametrize(
    "coord, expected_value",
    [
        ((3, 1), True),
        ((11, 3), False),
        ((0, 2), True),
        ((2, 6), True),
        ((12, 5), False),
        ((-2, 5), False),
    ],
)
def test_is_inbound(coord, expected_value):
    result = is_inbound(coord, 10, 10)
    assert result == expected_value
