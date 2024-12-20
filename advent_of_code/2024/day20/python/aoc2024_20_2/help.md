# Aide pour le jour 20 de AoC

* Taxicab / Manhattan distance
  * https://fr.wikipedia.org/wiki/Distance_de_Manhattan
    * https://fr.wikipedia.org/wiki/Norme_(math%C3%A9matiques)#En_dimension_finie
    * https://fr.wikipedia.org/wiki/M%C3%A9thode_des_k_plus_proches_voisins
  * https://www.datacamp.com/fr/tutorial/manhattan-distance

* https://www.reddit.com/r/adventofcode/comments/1hid65a/2024_day_20_part_2_how_to_interpret_weird_clause/

* https://www.reddit.com/r/adventofcode/comments/1hicdtb/2024_day_20_solutions/

* https://www.reddit.com/user/Atlante45/
  * https://github.com/Atlante45/advent-of-code/blob/main/solutions/y2024/d20.py

> I started by running dijkstra and extrating the path into an array
> Then iterating over the array, I run a second loop starting 100 steps further and compute the manhattan distance between the 2 points
> If the path distance minus the manhattan distance is equal to 2 then it counts for part 1, if it's less than 20 it counts for part 2

* https://www.reddit.com/user/KeeperOfTheFeels/

> So close to leaderboard for part 2. Was nice that my part 1 solution was easily adaptible to part 2. I solved it by doing a BFS from both the start and end to create cost maps. Then looped through all points accessible from the start, found all points within 'X' taxicab distance of that point and looked if "distance_from_start[cheat_start_point] + distance_from_end[cheat_end_point] + mh_distance(cheat_start_point, cheat_end_point)" was short enough. There's definitley some optimizations I could make, but that'll have to wait for tomorrow. Part 1 completes in ~1ms and part 2 in ~50ms.

* https://www.reddit.com/user/piman51277/
  * https://github.com/piman51277/AdventOfCode/tree/master/solutions/2024/20

* https://www.reddit.com/user/DutchDataNerd/
  * https://topaz.github.io/paste/#XQAAAQBDEAAAAAAAAAA0m0pnuFI8c+p2+Rz3XuIPf73C+fYxkQ5/VLoRVQdncHBPrEh1Qey5U1IfMRpXo6UlsSHRIT0acJSDSoDqRiOepxtkqCHCU3dbqv193rMVL8X+taMbyDQITM4/ZcfRyLGHDqc7bLMuFseNd7pQRsRFlAy7k5zzAIyEMdfLAJfUU4EWVgh0O3TjTmOmB1pdd2UIX2fwaY7BkUntyTCMWoeRj3AyWOg3Cfn+yVQ/ssKxL8TRrbg2l6/WJs2Aavu6XaQ0DZsTc08gk4tzft+IBLbBmdMmqZVh2Lr+1hreNN/zpseo8iakw6f+AyuuuN4EdBp3eDJfxAbWFQpxTAV56OZIybZIirO2vrBVDnL1jMb5IShPGd+sG96Q9t9veSara9mmkq2/ktv29nUSv1dcEi/uzUD0jo24JJ+7sAhpFWbS7aC73JF66KmO5ep6pjh7G+RGQ4EsoUMBJA1hruXPxb+baQQwGONTl3L55WC8tUz+A/mJDZkKDAAmWbybnmDfeNuBLQ5K4W4zCoSR1MqIKw8iQsxNpgD71EFX7gMcJSmzL69GUcf6E5rstocnDBgj7HVeF1yzMxFCVIlN1FDVMwjNSDWeFPp8lUJYYk/NM0EZyKI9hZO50MAWJnYDAuC0jbFXNJwDOglTW1QVEPbmBAXlNv0xT3qIIzV61RtSrukAXog5EQXgn6JO/daqK/0V4TZA9L03MOQ5vbyC+XyZxYpP33s7X2St+bwCXyp9rV41FF4rpYw5j5jBftZqU5uUTLRHJ/6F31fGfKhNqwlRiPLlbNoY7Ml5raTSD5FUTMvuB+LXuYKDT0D1GFHgxMIT5Vwpikr9I8/GxeWb0mqwa+YtPJoW/9mJkOmjSrvEFdDKyYUika+9Zfwp31jkZUt/2kgkbyA7Y1ktKiEj8C5VUoPy/LDbgm6aJ0Nl58g0q8+56oelByDJ5RJBFstmwF690RgX7Qeo8TJ0KIlAFPS7jOxsfnv/M/Bh1QBwvO5RvqT93KBIVPKwQej4TZFdibNXRJ4LIK9y5BCfykftEBZCAtqM9xUcAAGzfVUE/zdQIoRqlfQhSZdbIyZAwN9zpIlMsBLd7nRuktDgFRmfl1TSlp1goUXRMox0etVDA1JFK3uv8Ln6Inp6jjqbD3K1AlkW/enkjq33V40P3zWoY6LiaKotMW7r47iF0o1xB+X2THIOc+edRbrJVJbLc/xKolv+r7pDoy+xo2Qd706oxej3AMTYSeMaqwZwIK9feKJ6+rcMm4M6TiT2VAvlY/LVylkF8+SNSyNgtoUNm3qg4/SmW5e3QEWkL6DTlGP+yUhKEOPkGxBjY25nwebu3ShHV1EjnnG3zIYOx+YT2kD7lsgzSduGGqQzxvP4Xp3+P4IgpMIB5S8uEX3zocUnDv3zkJvN8BP6o5JAUzZO28VbTj+0W/hstpyJsbDRqVGGIr4wk1G6Ceo2BZ7a5ZgT5zi4bySeXMyoCTUqPDveqfATQTPMKWO4xzNuIQl3Kg2EOs2u9IXDBBBymQbWicI/uzI84XbFZlou00Jit9bm0LHfJzTzU4azdodGOMvGlcZOmvvab89OWq9aMVpH+7sgWEv405OLdukzsIMGmVU/AvNLILuQ2c2lP4QmHds3RGQYucNKop1d4RJcemPRzlfmkgdQ4k/7jG33FrTu/LwEG5vf5hL8QkKE1uJsS7DI8Waxlz9+POVAYlEmKMweb92edC1ngMSwsh42hiE4JywANmwIstNRFIZu2P7j8zULzH8mJixMtQw0l6Hn1V32UVuICewu6OPt8b/zfkLD/ApfQQGlC9bXLmBXxgmX9ObYZkFsNd3Nhc94ZOP8cVBh

```python
import networkx as nx
from typing import List, Set, Tuple, Dict
from itertools import product

def read_map(filename: str) -> List[List[str]]:
    with open(filename, 'r') as f:
        return [list(line.strip()) for line in f]

Point = Tuple[int, int]

def find_start_end(grid: List[List[str]]) -> Tuple[Point, Point]:
    start: Point = (-1, -1)
    end: Point = (-1, -1)
    for i in range(len(grid)):
        for j in range(len(grid[0])):
            if grid[i][j] == 'S':
                start = (i, j)
            elif grid[i][j] == 'E':
                end = (i, j)
    if start == (-1, -1) or end == (-1, -1):
        raise ValueError("Start or end position not found in grid")
    return start, end

def build_graph(grid: List[List[str]], allow_walls: bool = False) -> nx.Graph:
    """Build a graph representation of the grid."""
    G = nx.Graph()
    height, width = len(grid), len(grid[0])
    directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]
    
    for y, x in product(range(height), range(width)):
        if not allow_walls and grid[y][x] == '#':
            continue
        pos = (y, x)
        for dy, dx in directions:
            new_y, new_x = y + dy, x + dx
            if (0 <= new_y < height and 
                0 <= new_x < width and 
                (allow_walls or grid[new_y][new_x] in '.SE')):
                G.add_edge(pos, (new_y, new_x), weight=1)
    
    return G

def find_all_cheats(grid: List[List[str]], start: Point, end: Point, max_cheat_steps: int) -> List[int]:
    print("Building normal graph and finding shortest path...")
    G = build_graph(grid)
    try:
        normal_time = nx.shortest_path_length(G, start, end, weight='weight')
    except nx.NetworkXNoPath:
        return []
    print(f"Normal path length: {normal_time}")
    
    # Build graph that includes wall passages
    print("Building graph with wall passages...")
    G_walls = build_graph(grid, allow_walls=True)
    
    # Pre-calculate all shortest paths from start and to end
    print("Pre-calculating distances...")
    start_distances = nx.single_source_dijkstra_path_length(G, start, weight='weight')
    end_distances = nx.single_source_dijkstra_path_length(G, end, weight='weight')
    
    saved_times = []
    height, width = len(grid), len(grid[0])
    max_end_dist = normal_time - 100  # Maximum distance from cheat end to end
    
    print("Finding cheats...")
    # For each valid path position
    for y, x in product(range(height), range(width)):
        if grid[y][x] not in '.SE':
            continue
        start_pos = (y, x)
        if start_pos not in start_distances:
            continue
            
        start_dist = start_distances[start_pos]
        
        # Find all reachable positions within max_cheat_steps
        cheat_ends = nx.single_source_dijkstra_path_length(G_walls, start_pos, cutoff=max_cheat_steps)
        
        # Check each possible cheat end
        for end_pos, cheat_steps in cheat_ends.items():
            if grid[end_pos[0]][end_pos[1]] not in '.SE':
                continue
            if end_pos not in end_distances:
                continue
                
            end_dist = end_distances[end_pos]
            if end_dist > max_end_dist:
                continue
                
            # Calculate total time with cheat
            cheat_time = start_dist + cheat_steps + end_dist
            time_saved = normal_time - cheat_time
            
            if time_saved >= 100:
                saved_times.append(time_saved)
    
    print(f"Found {len(saved_times)} cheats that save ≥100 picoseconds")
    return saved_times

def solve_part1(filename: str) -> int:
    grid = read_map(filename)
    start, end = find_start_end(grid)
    saved_times = find_all_cheats(grid, start, end, max_cheat_steps=2)
    return len(saved_times)

def solve_part2(filename: str) -> int:
    grid = read_map(filename)
    start, end = find_start_end(grid)
    saved_times = find_all_cheats(grid, start, end, max_cheat_steps=20)
    return len(saved_times)

if __name__ == "__main__":
    print("Part 1:", solve_part1("input.txt"))
    print("Part 2:", solve_part2("input.txt"))
```

* [networks](https://networkx.org/)
  * > NetworkX is a Python package for the creation, manipulation, and study of the structure, dynamics, and functions of complex networks.


* https://www.reddit.com/user/michaelgallagher/
  * https://github.com/michaeljgallagher/Advent-of-Code/blob/master/2024/20.py
> I think my approach was similar to others today:
> Find the shortest path
> Calculate the costs (distances) from the start and end for each point on the path
> Count the number of pairs of points on the path that have a Manhattan distance less than or equal to the amount we're allowed to cheat that have a savings greater than or equal to 100
> It takes me a little less than 30 seconds to run both parts. I wonder if there is an optimization I'm missing somewhere; maybe there's something smarter than just checking each possible combination of points on the path?


* https://www.reddit.com/user/Busy-Championship891/

* Part-1: Simple DFS to find single unique path. Store path in a list as well as a map to get index of a node faster. For each node in path, find cheats (in each direction, 1 should be a "#" and 2 should be a "." in the path)
* Part-2: Since many paths leads to same cheat, I used the manhattan distance approach on each pair of nodes in path found out from DFS. I think there might be some more optimizations which I can do but currently it runs in around 4s.
* Link: https://github.com/D3STNY27/advent-of-code-2024/tree/main/day-20

* https://www.reddit.com/user/ash30342/
  * https://github.com/ash42/adventofcode/tree/main/adventofcode2024/src/nl/michielgraat/adventofcode2024/day20
  * > For part 2 I also start with the distances Dijkstra provides and also walk along the path. For every position I get a list of all positions on the path which are within Manhattan distance >= 2 and <= 20 and are closer to the end. For these I calculate the new distance (original distance - (Manhattan distance + distance of end position of the short cut to the end)) and check if this has a minimum saving of 100.
* https://www.reddit.com/user/careyi4/
  * https://github.com/careyi3/aoc_2024/tree/master/solutions/20
  * > Not too bad today, I was surprised that my lazy solution for part 2 worked in a reasonable time. I made a mistake at the start and assumed that there was a third move needed to get you back onto the track and was overcomplicating it, but turned out not to be an issue anyway. Solution is to run dijkstra, then with the known set of visited paths, for each one, see if it is N steps away (through walls) from another visited point on the path who's distance from the start is longer. For part 1 N = 2, for part 2, N = 20. I use a dump 40x40 grid in the second part centered on the point of interst and then calculate all the points that have a manhatten distance of 20 or less as candidates.