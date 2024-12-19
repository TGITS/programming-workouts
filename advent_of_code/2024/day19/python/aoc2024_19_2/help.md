# Ressources d'aide pour le AoC du jour 19

* https://www.reddit.com/r/adventofcode/comments/1hhlb8g/2024_day_19_solutions/

* https://www.reddit.com/user/manu_2468/
  * https://github.com/manucordova/adventofcode/blob/main/aoc_python/2024/day_19/solution.py

* https://www.reddit.com/user/bakibol/
  * https://topaz.github.io/paste/#XQAAAQC8AwAAAAAAAAAzHIoib6poHLpewxtGE3pTrRdzrponK3G2+gEMNmwefryz1dsQeYnN+tKcoEOB2FRtI5xqbhbT5JkduScYJrWqIFyNB7W6RIk0voA3RURNSVbvdhqlWGDYcnl83czfnUVrbZ7/l6qmjnqUORk/2YSgC7Zg4JbRBEBhWi76NxVnBnSN/jrCWCjnQvQ4qN/g1IBpoWrFIsqMo+SxAk/g9vDaERqnfOZLmfnrvf7Z/8ik+xwMhSv9pGWP1FAlGX20JzD8Ymw4RkuviDE7hrvLpOiB49DZ+hSliBtP7+DNH+ju4VM0Bp+JIg5U0fEmk4fmA/ZVByeaQpqqHba0EyvO0ypnLlfIJmycehQkxtIYKL3XsqI4CLF9+bnVKpqE/sBYQ+LkJu/6es+nCzvb97jMQ/D0l/sSuisls4AXnjTQ0otEIkPEg77235SSbOHLis735g3pR0dV9iqMiUZ/4495MiqkMIXoqPiauXNTCy3PTLM5AXpy35eCJg5CHOclMWV6lXoLvir+1SAfWbrf/m42Og==

```python
from functools import lru_cache

FILENAME = "input.txt"


def parse_input(filename):
    with open(filename) as input_file:
        patterns, designs = input_file.read().split("\n\n")
    return frozenset(patterns.split(", ")), designs.split()


def remove_prefix(prefix, word):
    return word[len(prefix) :]


@lru_cache(maxsize=None)
def check_design(design, patterns):
    if not design:
        return True
    return sum(
        check_design(remove_prefix(pattern, design), patterns)
        for pattern in patterns
        if design.startswith(pattern)
    )


def part_one(designs, patterns):
    return sum(check_design(design, patterns) > 0 for design in designs)

def part_two(designs, patterns):
    return sum(check_design(design, patterns) for design in designs)


def main():
    patterns, designs = parse_input(FILENAME)
    print(part_one(designs, patterns))
    print(part_two(designs, patterns))


if __name__ == "__main__":
    main()

```