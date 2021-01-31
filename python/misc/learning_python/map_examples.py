import itertools


def squared(x): return x**2


print("Itérateurs créés à partir d'une map")
iterator_of_powers_of_2_for_first_numbers = map(squared, range(1, 10))
print(iterator_of_powers_of_2_for_first_numbers)
print(next(iterator_of_powers_of_2_for_first_numbers))
print(next(iterator_of_powers_of_2_for_first_numbers))
print(*iterator_of_powers_of_2_for_first_numbers)
print(sum(map(squared, range(1, 10))))
print(max(map(squared, range(1, 10))))
print(min(map(squared, range(1, 10))))

print('\n##############\n')

print("Itérateurs _infinis_ à partir d'une map")
neverending_iterator_of_powers_of_2 = map(squared, itertools.count(1))
print(next(neverending_iterator_of_powers_of_2))
print(next(neverending_iterator_of_powers_of_2))
print(*itertools.takewhile(lambda x: x < 1000,
                           neverending_iterator_of_powers_of_2))
print(next(neverending_iterator_of_powers_of_2))

print('\n##############\n')

print('Compréhension imbriquée')
columns = ('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h')
rows = (1, 2, 3, 4, 5, 6, 7, 8)
chessboard = map(lambda c: list(map(lambda c, r :(c, r),rows)), columns)
print(list(chessboard))

print('\n##############\n')
