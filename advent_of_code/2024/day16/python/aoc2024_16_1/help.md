# Ressources d'aide pour AoC 2024 #16

* https://www.reddit.com/r/adventofcode/comments/1hfboft/2024_day_16_solutions/

* https://www.reddit.com/user/xelf/
  
```python
class Complex(complex):
    __lt__=lambda s,o: (s.imag,s.real) < (o.imag,o.real)
    __add__=lambda s,o: Complex(complex(s)+o)

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
  * https://topaz.github.io/paste/#XQAAAQCoCgAAAAAAAAA0m0pnuFI8c9q4WY2stx6xnaXQhRJFeaDGgWVcTcMj4tBj783y+jAIWnuSgA1QjvgDaWqtBYdCPGVC9IxyXR61c0IlPORYf53sqjB6v7iNFi207pOX6HvpjcWSp70VJjbLVQhJQe6NDEMdTuOCuu/rYkyeUtua8ZayvNTI5lujdA6AlSXnkPI+TAi227LSDHG/N7IGAGeaC9UJLo1Ybb5+aehuR/0iMJINpkEU84k3erkP+SUhJbnAfBhgDDw0/VC5xL4lf84+/AfQewpBpXwhM3x/utqauxc67Bvau9KodRmmNmWjGW2xeRvbrFec0ISsPEFxqtLVKB9Si2A417cekOauT0lNqgh6a5Az6O+4n5SebPqvLhQF5Iwx5mqg+iRTcK62j+/qXTOA3RAbCEpo4mSpkkwA9niLNpmYVdzkonZOINiCdunwT7Uf5Vf92icX8idpCGYvK+f8K0RUJKE3PM9hG0gLAHw4xws7HsrAgc3loZAfcw+jU4k1cETAWfyi/6dZWWW/NFoCHi8jgIvOmTQb8OjqGXeqDmr1Esx3D3NAhXvOkWdGd0/3o1k23RFVF9+zjpAmm8jpgggjQOCQmmOqMWhWC7GUrZhOnnFJDMBzgU4oy/huBoeyqAZHutmAWJ9WKiXrJiMNnW6anQDhRiipD6AqajAsibT4XD67wzp6jgGuBjHVJYNVPYKypxhxRmAChAzmkJQavwNyENo5BcCLgjoOLBjkCR2k4d8C+tX4ONZAcDXwFlSyO9LRWaPAqoQAkUQ4u1z1r469mEAYmf1CzNYohTpqMHdnYKnng0zF3z04SHAPoR5O8sZ8v9izzpxhFzIkylQIiglQeghAYDqg9Y/BRP0Q1ONVnUblCH622cdEJtBKi1Wn10/ucgrdlwV6dIyJWUAuCiFzS7teULQBYmyGrNdVx4U71/TjL7xNo7UNnoqJ466u3euj28kvufnOsdxmrm8HTV1obvv4wqWN6mCh3mmHV/4UVi0a+zvVijtVh9haDVDArzpjn/m8GbcbuzVVKORLTYApgwp3OnDZkwb5cvf72DdRZNO2qtsPZRCkbMC/NsPUdf/3cWIk

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