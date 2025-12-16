from main import (
    extract_data,
    solve_part1,
    solve_part2,
)


def test_extract_data():
    filename = "./data/input_test.txt"
    expected = ((7, 1), (11, 1), (11, 7), (9, 7), (9, 5), (2, 5), (2, 3), (7, 3))
    assert extract_data(filename) == expected


def test_solve_part1():
    filename = "./data/input_test.txt"
    assert solve_part1(filename) == 50


def test_solve_part2():
    filename = "./data/input_test.txt"
    assert solve_part2(filename) == 24
