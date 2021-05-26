from insertion_sort_example import __version__, insertion_sort
from random import shuffle

_start_range = -1000
_end_range = 1000

def test_version():
    assert __version__ == '0.1.0'

def test_sort_already_sorted_array():
    array_to_sort = list(range(_start_range,_end_range))
    expected_result = list(range(_start_range,_end_range))

    insertion_sort(array_to_sort)

    assert expected_result == array_to_sort

def test_sort_array_sorted_in_reversed_order():
    array_to_sort = list(reversed(range(_start_range,_end_range)))
    expected_result = list(range(_start_range,_end_range))

    insertion_sort(array_to_sort)

    assert expected_result == array_to_sort

def test_sort_shuffled_array():
    array_to_sort = list(range(_start_range,_end_range))
    shuffle(array_to_sort)
    expected_result = list(range(_start_range,_end_range))

    insertion_sort(array_to_sort)

    assert expected_result == array_to_sort