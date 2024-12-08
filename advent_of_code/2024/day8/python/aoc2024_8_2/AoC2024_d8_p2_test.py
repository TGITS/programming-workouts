import pytest
from AoC2024_d8_p2 import *


@pytest.mark.parametrize(
    "test_input,expected_y_max, expected_x_max",
    [("input.txt", 50, 50), ("input_test.txt", 12, 12)],
)
def test_extract_sequences(test_input, expected_y_max, expected_x_max):
    map_of_antennas = extract_data(test_input)
    assert len(map_of_antennas) == expected_y_max
    assert len(map_of_antennas[0]) == expected_x_max
