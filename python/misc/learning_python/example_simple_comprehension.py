import itertools

print('Compréhension de liste')
list_of_powers_of_2_for_first_numbers = [x**2 for x in range(1, 10)]
print(list_of_powers_of_2_for_first_numbers)

print('\n##############\n')

print("Compréhension d'ensemble")
set_of_powers_of_2_for_first_numbers = {x**2 for x in range(1, 10)}
print(set_of_powers_of_2_for_first_numbers)

print('\n##############\n')

print("Compréhension de dictionnaire")
dict_powers_of_2_for_first_numbers = {x: x**2 for x in range(1, 10)}
print(dict_powers_of_2_for_first_numbers)

print('\n##############\n')

print("Générateurs créés à partir d'une compréhension")
generator_of_powers_of_2_for_first_numbers = (x**2 for x in range(1, 10))
print(generator_of_powers_of_2_for_first_numbers)
print(next(generator_of_powers_of_2_for_first_numbers))
print(next(generator_of_powers_of_2_for_first_numbers))
print(*generator_of_powers_of_2_for_first_numbers)
neverending_generator_of_powers_of_2 = (x**2 for x in itertools.count(1))
print(next(neverending_generator_of_powers_of_2))
print(next(neverending_generator_of_powers_of_2))
print(*itertools.takewhile(lambda x: x < 1000, neverending_generator_of_powers_of_2))
print(next(neverending_generator_of_powers_of_2))

print('\n##############\n')

print('Compréhension de liste avec un filtrage')
list_powers_of_2_for_first_event_numbers = [
    x**2 for x in range(1, 10) if x % 2 == 0]
print(list_powers_of_2_for_first_event_numbers)
