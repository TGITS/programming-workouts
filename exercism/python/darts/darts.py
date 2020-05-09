from math import sqrt


def score(x, y):
    if distance_from_origin(x, y) > 10:
        return 0
    elif distance_from_origin(x, y) > 5:
        return 1
    elif distance_from_origin(x, y) > 1:
        return 5
    else:
        return 10


def distance_from_origin(x, y):
    return sqrt(x**2 + y**2)
