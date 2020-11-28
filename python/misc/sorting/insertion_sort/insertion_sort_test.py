import insertion_sort
import pytest
import random


def test_insertion_sort_on_empty_list():
    tab = []
    insertion_sort.sort(tab)
    assert [] == tab


def test_insertion_sort_on_one_element_list():
    for i in range(1,10):
        tab = [i]
        insertion_sort.sort(tab)
        assert [i] == tab

def test_insertion_sort_on_already_sorted_list():
    tab = [0, 1, 2, 3, 4, 5]
    insertion_sort.sort(tab)
    assert [0, 1, 2, 3, 4, 5] == tab


def test_insertion_sort_on_list():
    list_to_sort = list(range(-10, 10))
    random.shuffle(list_to_sort)
    insertion_sort.sort(list_to_sort)
    assert list(range(-10, 10)) == list_to_sort
