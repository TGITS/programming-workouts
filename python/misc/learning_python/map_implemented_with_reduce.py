import functools


def my_map(fun, seq):
    def apply_function_and_aggregate_as_list(accumulator, current_elt):
        accumulator.append(fun(current_elt))
        return accumulator

    return functools.reduce(apply_function_and_aggregate_as_list, seq, [])


print("Puissances de 2 des nombres entre 1 à 9 avec my_map:",
      my_map(lambda x: x**2, range(1, 10)))
print("Puissances de 2 des nombres entre 1 à 9 avec map:",
      list(map(lambda x: x**2, range(1, 10))))
