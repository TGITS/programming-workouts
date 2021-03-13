def my_reduce(aggregation_function, sequence, initial_value=None):
    aggregation = None
    if sequence is None or len(sequence) == 0:
            return initial_value
            
    if initial_value is None:
        aggregation = sequence[0]
    else:
        aggregation = aggregation_function(initial_value, sequence[0])

    for elt in sequence[1:]:
        aggregation = aggregation_function(aggregation, elt)

    return aggregation


print(my_reduce(lambda a, b: a+b, range(1, 11)))
print(my_reduce(lambda a, b: a+b, range(1, 11), 5))
print(my_reduce(lambda a, b: a+b, []))
print(my_reduce(lambda a, b: a+b, [], 5))
