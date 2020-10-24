[x**2 for x in range(1, 10)]

[x**2 for x in range(1, 10) if x % 2 == 0]

powers_of_2 = []
for x in range(1, 10):
    powers_of_2.append(x**2)


powers_of_2 = []
for x in range(1, 10):
    if x % 2 == 0:
        powers_of_2.append(x**2)
