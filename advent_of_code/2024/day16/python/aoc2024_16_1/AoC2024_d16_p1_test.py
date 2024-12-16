import pytest
from AoC2024_d16_p1 import *


@pytest.mark.parametrize(
    "current_direction, neighbour_direction, expected",
    [
        (0, 0, 0),
        (0, 90, 1),
        (0, 180, 2),
        (0, 270, 1),
        (90, 0, 1),
        (90, 90, 0),
        (90, 180, 1),
        (90, 270, 2),
        (180, 0, 2),
        (180, 90, 1),
        (180, 180, 0),
        (180, 270, 1),
        (270, 0, 1),
        (270, 90, 2),
        (270, 180, 1),
        (270, 270, 0),
    ],
)
def test_calculate_rotation(current_direction, neighbour_direction, expected):
    assert calculate_rotation(current_direction, neighbour_direction) == expected
