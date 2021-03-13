even_numbers_below_10_with_for = []
for i in range(1, 10):
    if i % 2 == 0:
        even_numbers_below_10_with_for.append(i)

print("[for] Liste des nombres pairs plus petits que 10 :", even_numbers_below_10_with_for)

even_numbers_below_10_with_comprehension = [n for n in range(1,10) if n % 2 == 0]
print("[compréhension] Liste des nombres pairs plus petits que 10 :", even_numbers_below_10_with_comprehension)

even_numbers_below_10_with_filter = filter(lambda n: n % 2 == 0, range(1, 10))
print("[filter] Liste des nombres pairs plus petits que 10 :", list(even_numbers_below_10_with_filter))


