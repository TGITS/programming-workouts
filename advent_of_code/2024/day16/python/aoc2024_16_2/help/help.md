# Ressources d'aide pour AoC 2024 #16

* https://www.reddit.com/r/adventofcode/comments/1hfboft/2024_day_16_solutions/

* https://www.reddit.com/user/xelf/
  
```python
grid = {Complex(x,y):c for y,r in enumerate(open(filename).read().splitlines())
                       for x,c in enumerate(r) if c!='#'}
start = next(z for z in grid if grid[z]=='S')
end = next(z for z in grid if grid[z]=='E')

queue = [(0, start, 1, [start])]
seen, best, low = {}, set(), float("inf")

while queue:
    score, loc, face, path = heapq.heappop(queue)
    if loc == end:
        low = score
        best |= set(path)
    seen[loc,face] = score

    for d in map(Complex,(-1,1,-1j,1j)):
        cost = 1001 if d!=face else 1
        if loc+d in grid and seen.get((loc+d,d),float("inf")) > score+cost <= low:
            heapq.heappush(queue, (score + cost, loc+d, d, path+[loc+d]))

print(f"part 1: {low} part2: {len(best)}")
```

* https://www.reddit.com/user/Ok-Builder-2348/

```python
import heapq
from collections import defaultdict
import math

with open('2024_16.txt') as f:
    grid = f.read().splitlines()

directions = {(-1,0),(0,-1),(0,1),(1,0)}

walls = {(i,j) for i,line in enumerate(grid) for j,char in enumerate(line) if char=='#'}

start = [(i,j) for i,line in enumerate(grid) for j,char in enumerate(line) if char=='S']
assert len(start) == 1
start = start[0]+(0,1)

end = [(i,j) for i,line in enumerate(grid) for j,char in enumerate(line) if char=='E']
assert len(end) == 1
end = end[0]
ends = [end+direction for direction in directions]

def dijkstra_function(start,ends,distance_function):
    visited = {}
    queue = [(0,start)]
    time_dict = defaultdict(lambda: math.inf, {start:0})
    while True:
        time,loc = heapq.heappop(queue)
        if loc in visited:
            continue
        visited[loc] = 1
        if loc in ends:
            return time
        neighbours = distance_function(loc)
        for coord,distance in neighbours:
            newtime = time+distance
            if coord not in visited:
                if newtime < time_dict[coord]:
                    time_dict[coord] = newtime
                    heapq.heappush(queue,(newtime,coord))
                    
def dijkstra_function_2(starts,distance_function):
    visited = {}
    queue = [(0,start) for start in starts]
    time_dict = defaultdict(lambda: math.inf, {start:0 for start in starts})
    output = dict()
    while True:
        if queue:
            time,loc = heapq.heappop(queue)
            if loc in visited:
                continue
            else:
                output[loc] = time
            visited[loc] = 1
            neighbours = distance_function(loc)
            for coord,distance in neighbours:
                newtime = time+distance
                if coord not in visited:
                    if newtime < time_dict[coord]:
                        time_dict[coord] = newtime
                        heapq.heappush(queue,(newtime,coord))
        else:
            return output
                    
def distance_function(loc):
    x,y,dx,dy = loc 
    output = []
    if (x+dx,y+dy) not in walls:
        output.append([(x+dx,y+dy,dx,dy),1])
    for newdx,newdy in directions:
        if (newdx,newdy) != (dx,dy):
            output.append([(x,y,newdx,newdy),1000])
    return output

answer = dijkstra_function(start,ends,distance_function)
print(answer)

distance_from_start = dijkstra_function_2([start],distance_function)
distance_from_end = dijkstra_function_2(ends,distance_function)

coords = set()
for (x,y,dx,dy) in distance_from_start:
    if distance_from_start[(x,y,dx,dy)]+distance_from_end[(x,y,-dx,-dy)] == answer:
        coords.add((x,y))
print(len(coords))
```

* https://www.reddit.com/user/kupuguy/

* https://www.reddit.com/user/xHyroM/
  * https://github.com/xhyrom/aoc/blob/main/2024/16/solution.py

* https://www.reddit.com/user/fsed123/
  * https://github.com/Fadi88/AoC/blob/master/2024/day16/code.py

* https://www.reddit.com/user/svinther/
  * https://topaz.github.io/paste/#XQAAAQBKDgAAAAAAAAAzHIoib6p4r/McpYgEEgWhHoa5LSRMgpcaJDRy31O2mvvvG2LPgLg22r0Ml2+PtW/9KNm0KiCB87wk7v3uWbM4ee2kfp+atNtotghH/7IzdpEB+Dj5T15X2vomAcuejwPhOLOZu8BVas+fx/iX1TwDNz2D8/u7uHG8ZgtD+d/8txwXNpESJ313p7EYBD+j8rWXIWo/ws7X43pzvgbgAz9HdQucVLu7NdjZV2ZJkFF4Zz//DzQ1ZY/2yiv+7tGyLYTuz949lP0KzreqBqr1+5i/6XEC7Px2qA5vORY+rRdZTFncZVUek1VC9B9XNbxo8Sx1mh707nAU2P8KCF9ya6rUd6r+qJcgXIN98CbzadCYkMsTW43tDwBMZWiAvEiz1l5xQGHnw+ao0E/4LfFpgHn8vBr8uBSq1HjsHt4Lqe36pOPG7uVsiSUk5BCpwfY9XZE50MORRg6xVHrrYkITvTHcvF2rfgMOpqIjP6O/p5GuFtlS3oymQtjhgvbTfzALRqET6LVKrM5qdlWstRX1nTpkXSCgBKaCvgf+KX+cBnrhRoWbAZgBEkg2oikZw4nETYe2EtUeTwgHHfu7yit3jS0g/UWN+eLhAGAl7P6M0lU94/TtIpmyUr2Y+p7Djy6+0uW0OLBqBn7vSpj4xsFilb7vAaPVTIUNHDnAr6EppdCpDrubxGHtx7VRJQedlwZlBA+jvozvg/SabwRV7vrvomRRYyON4b+YDl0Y37o60xH+0JZ02igKjBXWwZoo7h9xkrIa+wYyaJmwYzAl7IwtaIUygxx71MYJkAUhLyM6cA3Sxb/N0Td5xGaVGZM/uCCPAm7dcanjLG1pjG599J+lf0X037NIQYjxtCOjD7HyPsaHrhH0wKnWZ/pA4wbS9atIeao2nnGoHKHDNLDPYuIPrymDODgvBo0fnonYjW8OXxwMAgaTX4++CHZ8gLm1ue1z06uecc9WzVrSqnYuLIAwn/uK4MkNIs14U0FzRCCbhCvTRcwuHOSTdbJXAXtCVahjWZVxmrwaKgD8mnEL0FuWX2E4xLh5vp0pjb3to/bCOLRA8g/5KGtFMNrl20IkO9xWj2j3Z89n4PSyb1btUhA6PczQW+39SFpFlEWt2Vd6FjRDwaFjNTSx+TNbhFHN2Ao+GZEbcqiMsm/YVnHcSkQv1N5FBQlQtzHkB0Wha4JWMc35PxXpZuF1tVmMkMZnZA8xLy556sO8CwIhxvjeudOeC/hFHTifKOydIgeTVpVQ0p/LSVamodQjS2rMZvpUe3ZiUpC7V0u9WNiZFfvXiyqfYp/un7nZSBLZqOODEjApp5VNerKBBSJIQAkTJCGrzLCAlKzYYXFWF1ghZ4CjNpwHIDdvxCMPlogJ+Tjx3Rpp/+1eol0=

```python
from itertools import *
from heapq import *
from collections import *

dirs = [(0, 1), (1, 0), (0, -1), (-1, 0)]


def solvep1(G):
    R = len(G)
    C = len(G[0])
    S, E = None, None
    for r in range(R):
        for c in range(C):
            if G[r][c] == "S":
                S = r, c
            elif G[r][c] == "E":
                E = r, c
    costs = {(S, 0): 0}
    pq = [(0, (S, 0))]
    while pq:
        cost, (s, d) = heappop(pq)
        if cost != costs[(s, d)]:
            continue
        if s == E:
            return cost
        sr, sc = s

        # move forward
        dr, dc = dirs[d]
        nbr, nbc = sr + dr, sc + dc
        if 0 <= nbr < R and 0 <= nbc < C and G[nbr][nbc] != "#":
            nb = ((nbr, nbc), d)
            if nb not in costs or costs[nb] > cost + 1:
                costs[nb] = cost + 1
                heappush(pq, (cost + 1, nb))

        # rotate
        for dn in [(d + 1) % 4, (d - 1) % 4]:
            nb = ((sr, sc), dn)
            if nb not in costs or costs[nb] > cost + 1000:
                costs[nb] = cost + 1000
                heappush(pq, (cost + 1000, nb))


def solvep2(G):
    R = len(G)
    C = len(G[0])
    S, E = None, None
    for r in range(R):
        for c in range(C):
            if G[r][c] == "S":
                S = r, c
            elif G[r][c] == "E":
                E = r, c

    cost = solvep1(G)
    costs = {(S, 0): 0}
    allp = set()

    P = [(S, 0)]
    cur = {S}

    def bt(c):
        s, d = P[-1]

        if c == cost and s == E:
            allp.update(cur)
        elif c > cost:
            return

        sr, sc = s
        for dn, dcost in [(d, 0), ((d + 1) % 4, 1000), ((d - 1) % 4, 1000)]:
            dr, dc = dirs[dn]
            nbr, nbc = sr + dr, sc + dc
            if (
                0 <= nbr < R
                and 0 <= nbc < C
                and G[nbr][nbc] != "#"
                and (nbr, nbc) not in cur
            ):
                nb = ((nbr, nbc), dn)
                newcost = c + 1 + dcost
                if nb not in costs or newcost <= costs[nb]:
                    costs[nb] = newcost

                    cur.add((nbr, nbc))
                    P.append(nb)
                    bt(newcost)
                    cur.remove((nbr, nbc))
                    P.pop()

    bt(0)
    return len(allp)


def parse(input_: str):
    parsed = []
    for l in input_.split("\n"):
        l = l.strip()
        if not l:
            continue
        parsed.append(l)
    return parsed


def testp1p2():
    # input_=Path(f"{DAY}ex.txt").read_text()
    input_ = """\
###############
#.......#....E#
#.#.###.#.###.#
#.....#.#...#.#
#.###.#####.#.#
#.#.#.......#.#
#.#.#####.###.#
#...........#.#
###.#.#####.#.#
#...#.....#.#.#
#.#.#.###.#.#.#
#.....#...#.#.#
#.###.#.#.#.#.#
#S..#.....#...#
###############
"""
    parsed = parse(input_)
    assert solvep1(parsed) == 7036
    assert solvep2(parsed) == 45


def testp1p2_2():
    # input_=Path(f"{DAY}ex.txt").read_text()
    input_ = """\
#################
#...#...#...#..E#
#.#.#.#.#.#.#.#.#
#.#.#.#...#...#.#
#.#.#.#.###.#.#.#
#...#.#.#.....#.#
#.#.#.#.#.#####.#
#.#...#.#.#.....#
#.#.#####.#.###.#
#.#.#.......#...#
#.#.###.#####.###
#.#.#...#.....#.#
#.#.#.#####.###.#
#.#.#.........#.#
#.#.#.#########.#
#S#.............#
#################
"""
    parsed = parse(input_)
    assert solvep1(parsed) == 11048
    assert solvep2(parsed) == 64


def run():
    input_ = open(0).read()
    parsed = parse(input_)
    p1result = solvep1(parsed)
    print(p1result)

    parsed = parse(input_)
    p2result = solvep2(parsed)
    print(p2result)


if __name__ == "__main__":
    run()
```


* https://www.reddit.com/user/hrunt/

```python
#!/usr/bin/env python3

import pathlib
import sys

from collections import deque
from typing import Iterator

sys.path.append(str(pathlib.Path(__file__).resolve().parents[3] / 'lib' / 'python'))

import aoc

Position = tuple[int, int]

def moves(node: Position, dir: int) -> Iterator[tuple[Position, int]]:
  for turn in range(-1, 2):
    ndir = (dir + turn) % 4
    yield (node[0] + (1, 0, -1, 0)[ndir], node[1] + (0, 1, 0, -1)[ndir]), ndir

def shortest_paths(
  node: Position,
  dir: int,
  end: Position,
  depth: dict[tuple[Position, int], int],
  path: list[Position]
) -> Iterator[tuple[Position, ...]]:
  path.append(node)

  if node == end:
    yield tuple(path)
  else:
    for nnode, ndir in moves(node, dir):
      cost = (ndir != dir) * 1000 + 1
      if (nnode, ndir) in depth and depth[nnode, ndir] == depth[node, dir] + cost:
        for shortest in shortest_paths(nnode, ndir, end, depth, path):
          yield shortest

  path.pop()

def run() -> None:
  with open(aoc.inputfile('input.txt')) as f:
    map = [line.strip() for line in f.readlines()]

  mx, my = len(map[0]), len(map)
  start = min((x, y) for y in range(my) for x in range(mx) if map[y][x] == 'S')
  end = min((x, y) for y in range(my) for x in range(mx) if map[y][x] == 'E')

  depth = {(start, 0): 0}

  nodes = deque([(start, 0)])
  while nodes:
    (x, y), dir = nodes.popleft()
    if (x, y) == end:
      continue

    for (nx, ny), ndir in moves((x, y), dir):
      cost = depth[(x, y), dir] + (dir != ndir) * 1000 + 1
      if map[ny][nx] != '#' and (((nx, ny), ndir) not in depth or depth[(nx, ny), ndir] > cost):
        depth[(nx, ny), ndir] = cost
        nodes.append(((nx, ny), ndir))

  score = min(v for (p, _), v in depth.items() if p == end)
  print(f"Lowest score: {score}")

  seats = set()
  for path in shortest_paths(start, 0, end, depth, []):
    seats |= set(path)

  print(f"Optimal seats: {len(seats)}")

if __name__ == '__main__':
  run()
  sys.exit(0)

```

* [Print all shortest paths between given source and destination in an undirected graph](https://www.geeksforgeeks.org/print-all-shortest-paths-between-given-source-and-destination-in-an-undirected-graph/)
* [Shortest path problem](https://en.wikipedia.org/wiki/Shortest_path_problem)
* [Floyd–Warshall algorithm](https://en.wikipedia.org/wiki/Floyd%E2%80%93Warshall_algorithm)
* [Breadth First Search or BFS for a Graph](https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/)
* [Number of Shortest Paths in a Graph](https://www.baeldung.com/cs/graph-number-of-shortest-paths)
* [How to find Shortest Paths from Source to all Vertices using Dijkstra’s Algorithm](https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/)
* [How to find all shortest paths](https://stackoverflow.com/questions/20257227/how-to-find-all-shortest-paths)
  
> You can easily do it by maintaining a list or vector of parents for each node. If two or more nodes ( say X, Y, Z) at the same distance from the starting node , leads to another node M , make all X , Y and Z as the parents of M.
> You just have to add a check to see while adding a parent to the node whether that parent is in the same level as the previous parents.
> By level , I mean the distance from the starting point.
> This way you can get all the shortest paths by tracing back the parent vectors. Below is my C++ implementation.
> I hope you know how to print the paths by starting from the destination ,tracing the parents and reach the starting point.

```
bfs (start , end)

    enqueue(start)
    visited[start] = 1

    while queue is NOT empty

        currentNode = queue.front()
        dequeue()

        if(currentNode == end)
            break

        for each node adjacent to currentNode

            if node is unvisited
                visited[node] = visited[curr] + 1
                enqueue(node)
                parent[node].add(currentNode)

            else if(currentNode is in same level as node's parents)
                parent[node].add(currentNode)

return 
```

* https://www.reddit.com/user/kenlies/
  * https://github.com/kenlies/advent_of_code_2024/blob/main/day16/day16.py

* https://www.reddit.com/user/mgtezak/
  * https://github.com/mgtezak/Advent_of_Code/blob/master/2024/16/p1.py
  * https://github.com/mgtezak/Advent_of_Code/blob/master/2024/16/p2.py

* https://www.reddit.com/user/CDawn99/
  * https://github.com/Cdawn99/AoC2024/tree/master/Day16

* https://www.reddit.com/user/Singing-In-The-Storm/
  * https://github.com/JoanaBLate/advent-of-code-js/blob/main/2024/day16-solve2.js

* https://www.reddit.com/user/OilAppropriate2827/
  * https://github.com/hlabs-dev/aoc/blob/main/2024/16.py

* https://www.reddit.com/user/nebble_longbottom/
  * https://github.com/blm34/AoC/blob/main/src/solutions/2024/day16.py

* https://www.reddit.com/user/prafster/
  * https://github.com/Praful/advent_of_code/blob/main/2024/src/day16.py
  * https://github.com/Praful/advent_of_code/blob/main/shared/python/utils.py

```python
def solve(input):
    grid, start, end = input
    def move_condition(g, x): return tile(g, x) != WALL

    q = PriorityQueue()
    q.put(Reindeer(start, 0, EAST, set()))

    tile_costs = {}
    min_cost = float('inf')
    min_paths = set()

    while not q.empty():
        reindeer = q.get()

        for adj in neighbours(reindeer.pos, grid, True, move_condition):

            if reindeer.cost > min_cost:
                continue

            if tile_costs.get((reindeer.pos, reindeer.direction), float('inf')) < reindeer.cost:
                continue

            tile_costs[(reindeer.pos, reindeer.direction)] = reindeer.cost

            cost = new_cost(reindeer, adj)
            dir = direction(adj, reindeer.pos)

            if adj == end:
                if cost < min_cost:
                    min_cost = cost
                    min_paths = set(reindeer.visited)
                elif cost == min_cost:
                    min_paths |= reindeer.visited
            elif adj not in reindeer.visited:
                visited2 = reindeer.visited.copy()
                visited2.add(adj)
                if cost < min_cost:
                    q.put(Reindeer(adj, cost, dir, visited2))

    return min_cost, len(min_paths)+2  # add 2 for start and end
```