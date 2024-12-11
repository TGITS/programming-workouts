# Ressources pour le problème du jour 11 de AoC

* https://www.reddit.com/r/adventofcode/comments/1hbm0al/2024_day_11_solutions/

## Une solution Python utilisant un cache

* https://github.com/thomasjevskij/advent_of_code/blob/master/2024/aoc11/day11.py

```python
from functools import cache

def parse_input(filename=0):
    with open(filename) as f:
        numbers = [int(n) for n in f.read().strip().split()]
    return numbers

def blink(number):
    if number == 0:
        return [1]
    s = f'{number}'
    l = len(s)
    if l % 2 == 0:
        return [int(n) for n in [s[:l//2], s[l//2:]]]
    return [number * 2024]

@cache
def count_splits(number, blinks):
    if blinks == 0:
        return 1
    return sum(count_splits(n, blinks - 1) for n in blink(number))

def p1(numbers):
    stones = sum(count_splits(n, 25) for n in numbers)
    print(stones)

def p2(numbers):
    stones = sum(count_splits(n, 75) for n in numbers)
    print(stones)

puzzle_input = parse_input()

p1(puzzle_input[:])
p2(puzzle_input[:])
```

## Une autre solution Python utilisant un cache

* https://topaz.github.io/paste/#XQAAAQCsAwAAAAAAAAARiEJHiiMzw3cPM/1Vl+2nx/DqKkM2yi+HVdpp+qLh9JUkZFMYSletBNSBBCq+27db5Jboz+0hQ3TWEaFnggkF/Eik9sUKbyyXfS89QeX+S/WqGw2zURbS910kwBM80xLBF2KeONgG6Q0x0I41EiFzwV1PSd5PByr9fZd/59Zt1U975MEVKOn0KGTw7nRPhEvYy+O91CfkUwTMt1q4qkgRJREBV9vSWyT5Ux2m3qxMnr6JqQPjn+fNawvKN+m6Q5tkSXX+E3sx1DJ7JbQFvtdlPlrFTWWcPtiNHtc0gEdrC5XWmUiP2RfMnlGcdwS1H63Blh3S3mk3nrFTM0NozM3vrueoK0bhx8HgmSgo510HXqRwSdPqynNIjpavFHvQapCVcsohPKS6z0v0nP6a1MIyyP+3bw25T+ZoQfrLvXCZfI0VEC5x1u9ST1GXxMFVB9eQewxlzz5Mb4qXAJERY9F3Uvntc2FLgTv0xl0ei4YbNZrmPTR+KyYedgr2V5ZDPUbUXNyY6rjIztlEsa2SO4EaVHsOmmFi7wzUJAAh8wXMW/yi+unHqmAE0aSn1bc+WNgRqUq4RfuvcYkDfh2HKg5kSitPJzyrZ3AVP3xRVvSUhPbYSAja2a+3ioIqrxzz4KYQUtk+XWprJ1Oj/2wp2gA=

> When you read and solve Part 1 even though you know it won't work for what's coming in Part 2 ...
> I initially coded up a solution for Part 1 that implemented the logic using a deque. It worked well enough, but I knew that Part 2 was going to ramp things up into heat-death-of-the-universe scale,
> For Part 2, I recognized that each stone is independent of the others. The bit about the order of stones being preserved is really unnecessary, since iterating on stone #1 25 times can happen before iterating on stone #2 once and it will always produce the same number of stones. Likewise, mapping out the initial few transforms of a stone with value 0 showed some repetition. Value 0 reappeared at iteration 5, and value 2 appeared twice at iteration 5. Seeing that repetition made me think that caching results would yield some speedups, so I re-implemented Part 1 using a depth-first, recursive counting function and threw memoization at it. That returned fast enough.
> Runtime is 145ms.
> I can almost see the solution that takes advantage of the fact that once you perform the first N iterations of a value, you don't need to perform them again, but I can't quite see the right way to structure it.


```python
#!/usr/bin/env python3

import functools
import math
import pathlib
import sys

sys.path.append(str(pathlib.Path(__file__).resolve().parents[3] / 'lib' / 'python'))

import aoc

def tens(n: int) -> int:
  return int(math.log10(n)) + 1

@functools.cache
def transform(stone: int, times: int) -> int:
  if times == 0:
    return 1
  if stone == 0:
    return transform(1, times - 1)
  if (digits := tens(stone)) % 2 == 0:
    return iterate(divmod(stone, 10 ** (digits // 2)), times - 1)
  return transform(stone * 2024, times - 1)

def iterate(stones: tuple[int, ...], times: int) -> int:
  return sum(transform(stone, times) for stone in stones)

def run() -> None:
  with open(aoc.inputfile('input.txt')) as f:
    stones = tuple(int(n) for n in f.read().strip().split())

  print(f"Length after 25 blinks: {iterate(stones, 25)}")
  print(f"Length after 75 blinks: {iterate(stones, 75)}")

if __name__ == '__main__':
  run()
  sys.exit(0)

```

## Solution JS élégante

* https://www.reddit.com/user/MrPulifrici/

```javascript
let advent = document.body.innerText;
    advent = advent.replace(/\r/g, "");
    if (advent.endsWith("\n")) advent = advent.slice(0, -1);
    const stones = advent.split(' ').map(Number);
    console.time("Speed");
    const cache = new Map();
    const blink = (number, blinks) => {
        const cacheKey = `${number}:${blinks}`;
        if (cache.has(cacheKey)) return cache.get(cacheKey);
    
        if (blinks === 0) return 1;
    
        if (number === 0) {
            const result = blink(1, blinks - 1);
            return cache.set(cacheKey, result), result;
        }
        const stoneString = number.toString();
        if (stoneString.length % 2 === 0) {
            const middle = stoneString.length / 2;
            const result = blink(Number(stoneString.slice(0, middle)), blinks - 1)
                + blink(Number(stoneString.slice(middle)), blinks - 1);
            return cache.set(cacheKey, result), result;
        }
        const result = blink(number * 2024, blinks - 1);
        return cache.set(cacheKey, result), result;
    
    }
    console.log("Part 1:",stones.reduce((a, b) => a + blink(b, 25), 0));
    console.log("Part 2", stones.reduce((a, b) => a + blink(b, 75), 0));
    console.timeEnd("Speed");
```