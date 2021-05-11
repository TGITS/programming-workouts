import functools


def my_filter(predicate, seq):
    def filter_and_aggregate_as_list(accumulator, current_elt):
        if predicate(current_elt):
            accumulator.append(current_elt)
        return accumulator

    return functools.reduce(filter_and_aggregate_as_list, seq, [])


print("Nombres pairs entre 1 à 9 avec my_filter:",
      my_filter(lambda x: x % 2 == 0, range(1, 10)))
print("Nombres pairs entre 1 à 9 avec filter:", list(
    filter(lambda x: x % 2 == 0, range(1, 10))))
