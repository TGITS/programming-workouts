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
print(sequence_4)
