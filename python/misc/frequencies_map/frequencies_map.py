from itertools import groupby

def frequencies_map_with_for_from(elements):
    frequencies_map = {}
    for element in elements:
        if element in frequencies_map.keys():
            frequencies_map[element] = frequencies_map[element] + 1
        else:
            frequencies_map[element] = 1
    return frequencies_map

def frequencies_map_with_comprehension_from(elements):
    return { k: len(list(g)) for k, g in groupby(sorted(elements)) }

def frequencies_map_with_map_from(elements):
    return dict(map(lambda t: (t[0], len(list(t[1]))), groupby(sorted(elements))))


# >>> list(map(lambda t: (t[0], len(list(t[1]))), groupby(["a", "b", "c", "a", "d", "a", "d", "b", "d", "d"])))
# [('a', 1), ('b', 1), ('c', 1), ('a', 1), ('d', 1), ('a', 1), ('d', 1), ('b', 1), ('d', 2)]
# >>> list(map(lambda t: (t[0], len(list(t[1]))), groupby(sorted(["a", "b", "c", "a", "d", "a", "d", "b", "d", "d"]))))
# [('a', 3), ('b', 2), ('c', 1), ('d', 4)]
