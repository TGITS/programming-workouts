import itertools
import functools
import operator

sequence_1 = map(lambda x: x + 1, range(0, 10))
print(list(sequence_1))

sequence_2 = map(lambda x: [x, x + 1], range(0, 10))
print(list(sequence_2))

# flatmap do not exist in Python
#sequence_3 = flatmap(lambda x : [x, x + 1], range(0,10))
# print(list(sequence_3))
# > [0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10]


def imperative_flatmap(fun, iterator):
    resulting_iterator = []
    for elem in iterator:
        resulting_iterator.extend(fun(elem))
    return resulting_iterator


sequence_4 = imperative_flatmap(lambda x: [x, x + 1], range(0, 10))
# The value return by imperative_flatmap is a list but the parameter can be any iterator
print(sequence_4)


def functional_flatmap(fun, iterator):
    return functools.reduce(operator.iconcat, map(fun, iterator))


sequence_5 = functional_flatmap(lambda x: [x, x + 1], range(0, 10))
# The value return by functional_flatmap is an iterator
print(list(sequence_5))


def itertools_flatmap(fun, iterator):
    return itertools.chain(*map(fun, iterator))


sequence_6 = itertools_flatmap(lambda x: [x, x + 1], range(0, 10))
# The value return by functional_flatmap is an iterator
print(list(sequence_6))
