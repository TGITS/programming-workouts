letters = ['a', 'b', 'c', 'd', 'e', 'f', 'g']

print('letters {}'.format(letters))

print('\n##########\n')

print('In Python, the for is a foreach : ')
for letter in letters:
    print(letter)

print('\n##########\n')

# Naive approach to emulate a C-like for loop in Python
print('Naive approach to access both index of element in the list and the associated value : ')
for index in range(len(letters)):
    print('letters[{}] : {}'.format(index, letters[index]))

print('\n##########\n')

# For loop with enumerate
print('using enumerate in a for loop : ')
for index, value in enumerate(letters):
    print('letters[{}] : {}'.format(index, value))

print('\n##########\n')

# For comprehension with enumerate
print('using enumerate in a for comprehension : ')
list_to_print = ['letters[{}] : {}'.format(
    index, value) for index, value in enumerate(letters)]
print('\n'.join(list_to_print))

print('\n##########\n')

# For loop with enumerate, ignoring the value
print('using enumerate in a for loop and ignoring the value : ')
for index, _ in enumerate(letters):
    print('index of letter :', index)

print('\n##########\n')

# Creating a list of tuples from enumerate
print('Creating a list of tuples (index, value) from a list of values')
letters_with_index = list(enumerate(letters))
print(letters_with_index)

print('\n##########\n')

# enumerate works with tuple, string and set for example
print('enumerate with tuple : ')
print(list(enumerate(('a', 'e', 'i', 'o', 'u'))))
print('enumerate with string : ')
print(list(enumerate(('aeiou'))))
print('enumerate with set : ')
print(list(enumerate({'a', 'e', 'i', 'o', 'u', 'a', 'e'})))
