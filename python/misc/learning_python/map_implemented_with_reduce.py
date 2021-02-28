import functools

def my_map(fun, seq):
    def apply_function_and_aggregate_as_list(accumulator, current_elt):
        accumulator.append(fun(current_elt))
        return accumulator

    return functools.reduce(apply_function_and_aggregate_as_list, seq, [])

print(my_map(lambda x: x**2, range(1, 10)))
print(list(map(lambda x: x**2, range(1, 10))))