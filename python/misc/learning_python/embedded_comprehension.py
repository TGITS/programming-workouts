columns = ('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h')
rows = (1, 2, 3, 4, 5, 6, 7, 8)

chessboard = [(c, r) for c in columns for r in rows]
print(chessboard)

print()

chessboard_with_for = []
for c in columns:
    for r in rows:
        chessboard_with_for.append((c, r))

print(chessboard_with_for)

print()

computations = [x*y for x in range(1, 10) if x % 2 == 1 for y in range(10) if y % 2 == 1]
print(computations)
