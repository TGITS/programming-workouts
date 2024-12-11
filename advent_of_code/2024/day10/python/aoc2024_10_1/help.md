# Ressources pour le Jour 10 de AoC

* https://www.reddit.com/r/adventofcode/comments/1hau6hl/2024_day_10_solutions/

## Une solution en Python

> Like a lot of other people here, I accidentally solved part 2 first. Boy was I happy to have commited my wrong solution to part 1 when I got to part 2!

Solution 10.1

```python
def walk_trail(x: int, y: int, end_positions: set[tuple[int, int]]) -> int:
    height = map[y][x]
    if height == 9:
        end_positions.add((x, y))
        return

    # left
    if x > 0 and map[y][x - 1] == height + 1:
        walk_trail(x - 1, y, end_positions)
    # up
    if y > 0 and map[y - 1][x] == height + 1:
        walk_trail(x, y - 1, end_positions)
    # right
    if x < len(map[y]) - 1 and map[y][x + 1] == height + 1:
        walk_trail(x + 1, y, end_positions)
    # down
    if y < len(map) - 1 and map[y + 1][x] == height + 1:
        walk_trail(x, y + 1, end_positions)

total_score = 0
for y in range(len(map)):
    for x in range(len(map[y])):
        if map[y][x] == 0:
            end_positions = set()
            walk_trail(x, y, end_positions)
            total_score += len(end_positions)
```

* Solution 10.2

```python
def walk_trail(x, y) -> int:
    height = map[y][x]
    if height == 9:
        return 1

    score = 0
    # left
    if x > 0 and map[y][x - 1] == height + 1:
        score += walk_trail(x - 1, y)
    # up
    if y > 0 and map[y - 1][x] == height + 1:
        score += walk_trail(x, y - 1)
    # right
    if x < len(map[y]) - 1 and map[y][x + 1] == height + 1:
        score += walk_trail(x + 1, y)
    # down
    if y < len(map) - 1 and map[y + 1][x] == height + 1:
        score += walk_trail(x, y + 1)
    return score

total_score = 0
for y in range(len(map)):
    for x in range(len(map[y])):
        if map[y][x] == 0:
            total_score += walk_trail(x, y)
```

