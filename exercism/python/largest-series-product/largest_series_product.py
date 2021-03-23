from math import prod


def largest_product(series, size):
    if size == 0:
        return 1

    if size < 0:
        raise ValueError("The span cannot be a negative integer")

    index_max = len(series) - size + 1
    return max([prod([int(c) for c in list(series[i:i+size])]) for i in range(0, index_max)])
