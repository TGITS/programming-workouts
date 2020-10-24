import itertools

generator = (x**2 for x in itertools.count(5))
print(next(generator))
print(next(generator))
for i in range(10):
    next(generator)
print(next(generator))
for i in range(10):
    next(generator)
print(next(generator))