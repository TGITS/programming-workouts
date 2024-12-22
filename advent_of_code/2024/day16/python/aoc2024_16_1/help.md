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

* https://www.reddit.com/user/prafster/
  * https://github.com/Praful/advent_of_code/blob/main/2024/src/day16.py