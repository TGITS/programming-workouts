import pytest
from binary_search import __version__, recursive_find, iterative_find


def test_version():
    assert __version__ == '0.1.0'

#### tests of recursive_find

def test_recursive_finds_a_value_in_an_array_with_one_element():
    assert recursive_find([6], 6) == 0


def test_recursive_finds_a_value_in_the_middle_of_an_array():
    assert recursive_find([1, 3, 4, 6, 8, 9, 11], 6) == 3


def test_recursive_finds_a_value_at_the_beginning_of_an_array():
    assert recursive_find([1, 3, 4, 6, 8, 9, 11], 1) == 0


def test_recursive_finds_a_value_at_the_end_of_an_array():
    assert recursive_find([1, 3, 4, 6, 8, 9, 11], 11) == 6


def test_recursive_finds_a_value_in_an_array_of_odd_length():
    assert recursive_find(
        [1, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 634], 144) == 9


def test_recursive_finds_a_value_in_an_array_of_even_length():
    assert recursive_find(
        [1, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377], 21) == 5


def test_recursive_identifies_that_a_value_is_not_included_in_the_array():
    with pytest.raises(ValueError) as excinfo:
        recursive_find([1, 3, 4, 6, 8, 9, 11], 7)
    assert "Value 7 not found" in str(excinfo.value)


def test_recursive_a_value_smaller_than_the_array_s_smallest_value_is_not_found():
    with pytest.raises(ValueError) as excinfo:
        recursive_find([1, 3, 4, 6, 8, 9, 11], 0)
    assert "Value 0 not found" in str(excinfo.value)


def test_recursive_a_value_larger_than_the_array_s_largest_value_is_not_found():
    with pytest.raises(ValueError) as excinfo:
        recursive_find([1, 3, 4, 6, 8, 9, 11], 13)
    assert "Value 13 not found" in str(excinfo.value)


def test_recursive_nothing_is_found_in_an_empty_array():
    with pytest.raises(ValueError) as excinfo:
        recursive_find([], 1)
    assert "Value 1 not found" in str(excinfo.value)


def test_recursive_nothing_is_found_when_the_left_and_right_bounds_cross():
    with pytest.raises(ValueError) as excinfo:
        recursive_find([1, 2], 0)
    assert "Value 0 not found" in str(excinfo.value)

#### tests of iterative_find

def test_iterative_finds_a_value_in_an_array_with_one_element():
    assert iterative_find([6], 6) == 0


def test_iterative_finds_a_value_in_the_middle_of_an_array():
    assert iterative_find([1, 3, 4, 6, 8, 9, 11], 6) == 3


def test_iterative_finds_a_value_at_the_beginning_of_an_array():
    assert iterative_find([1, 3, 4, 6, 8, 9, 11], 1) == 0


def test_iterative_finds_a_value_at_the_end_of_an_array():
    assert iterative_find([1, 3, 4, 6, 8, 9, 11], 11) == 6


def test_iterative_finds_a_value_in_an_array_of_odd_length():
    assert iterative_find(
        [1, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 634], 144) == 9


def test_iterative_finds_a_value_in_an_array_of_even_length():
    assert iterative_find(
        [1, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377], 21) == 5


def test_iterative_identifies_that_a_value_is_not_included_in_the_array():
    with pytest.raises(ValueError) as excinfo:
        iterative_find([1, 3, 4, 6, 8, 9, 11], 7)
    assert "Value 7 not found" in str(excinfo.value)


def test_iterative_a_value_smaller_than_the_array_s_smallest_value_is_not_found():
    with pytest.raises(ValueError) as excinfo:
        iterative_find([1, 3, 4, 6, 8, 9, 11], 0)
    assert "Value 0 not found" in str(excinfo.value)


def test_iterative_a_value_larger_than_the_array_s_largest_value_is_not_found():
    with pytest.raises(ValueError) as excinfo:
        iterative_find([1, 3, 4, 6, 8, 9, 11], 13)
    assert "Value 13 not found" in str(excinfo.value)


def test_iterative_nothing_is_found_in_an_empty_array():
    with pytest.raises(ValueError) as excinfo:
        iterative_find([], 1)
    assert "Value 1 not found" in str(excinfo.value)


def test_iterative_nothing_is_found_when_the_left_and_right_bounds_cross():
    with pytest.raises(ValueError) as excinfo:
        iterative_find([1, 2], 0)
    assert "Value 0 not found" in str(excinfo.value)
