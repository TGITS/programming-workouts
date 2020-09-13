from itertools import groupby


def frequencies_map_from(elements):
    map = {}
    return {k: len(list(g)) for k, g in groupby(sorted(elements))}
