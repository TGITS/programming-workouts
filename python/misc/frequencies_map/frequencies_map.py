from itertools import groupby
from collections import Counter


def frequencies_map_with_for_from(elements):
    frequencies_map = {}
    for element in elements:
        if element in frequencies_map.keys():
            frequencies_map[element] = frequencies_map[element] + 1
        else:
            frequencies_map[element] = 1
    return frequencies_map


def frequencies_map_with_map_from(elements):
    return dict(map(lambda t: (t[0], len(list(t[1]))), groupby(sorted(elements))))


def frequencies_map_with_comprehension_from(elements):
    return {k: len(list(v)) for k, v in groupby(sorted(elements))}


def frequencies_map_with_counter_from(elements):
    return Counter(elements)
