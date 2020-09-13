from itertools import groupby

def create_frequencies_map(elements):
    map = {}
    for k, g in groupby(sorted(elements)):
        map[k] = len(list(g))
    return map