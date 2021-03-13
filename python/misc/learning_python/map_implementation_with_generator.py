def my_map(function, sequence):
    for elt in sequence:
        yield function(elt)

powers_of_two = my_map(lambda x: x**2, range(1,11))

print(powers_of_two)
print(next(powers_of_two))
print(list(powers_of_two))