def my_filter(predicate, sequence):
    for elt in sequence:
        if predicate(elt):
            yield elt


even_numbers = my_filter(lambda x: x % 2 == 0, range(1, 11))

print(even_numbers)
print(next(even_numbers))
print(list(even_numbers))
