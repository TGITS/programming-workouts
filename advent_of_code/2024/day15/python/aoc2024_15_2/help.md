# Ressources pour le AoC du jour

* The main idea is a double [DFS](https://en.wikipedia.org/wiki/Depth-first_search)
* https://www.reddit.com/r/adventofcode/comments/1hele8m/2024_day_15_solutions/

# From Reddit

* python solution : https://github.com/Fadi88/AoC/blob/master/2024/day15/code.py
* python solution : https://pastebin.com/vBYyfgyf
* python solution : https://topaz.github.io/paste/#XQAAAQAZCwAAAAAAAAAzHIoib6pMX4ickc1Mep93pgqtuV6rGB3/t2SKtfwDPt2r1515wsQUmcHJvxOgeyqU0+EtagUyY+1FBgQ3Yb+/Cc8VeSOxADCyiZJn2UXCr+fr6LdRoQ/ig56mck4ITPzK+0dtURzK7Q6pQDlVUYPZ7XCZSzP5+dU9tf6ewARHCQXt4MlIkBDQ2qCHZ4eEabRS3sHsBj45Dslz3U/y8OWEipkQf//4KFlAgTCeEEKiwgGlbXpNpXo2YkW5ZJGsIlCR1O1mkLyno9fHRkWu5iaydbNJpViMXdbMzOptAd6zvrKIioGTFzDNq86Ipeomwouf/ZiVbogbpAjveN/8o4LIRnTyv8231+qc4yABgJXea3sx5wr97U3qRnfK0gIiUSBz9oa4sPme08iAuidXH/SCkRUPRh9oejiYAS49z3L8EQnFk4sUTGzM9Pw4dmk8Gz41NrGfp3xRD6pV1usc1uKs3sXghKi2QLeM8wRB/n24Fd64Ojeo8BMomDbSxpyMyPIpK0FJPOGx+gFlYsBCWpBPSCDmfuZ9tpi5AJecF3dJT+GYzjvwTtjLxgHb6urakUBrsejK2vWSxoqAVEtubs9c5T+Bic/4mREqUstB7t3TyfnZEMMg6g2eJ6qvx1PNqFHnnCwgx3jKuGURZugMCPISygCQ0Obo0nvzcYEkTTOmpa1A96Wc8QKlh0bv7F/5P2iuhsTIiUjc4IIC8+LKBSbs2jDNMAKWYHzxBMNAAMiQxJK8Y3BPlofBqmTb24U8wjFyDjdpY3KMDRWQCHlYQV4RIlJXmamm0qlVOhRS+oqx0LhbJy1fpNzjEmrxezMSjIkXBxHGP9CsYXqjGBtZdlVcn6wJYBWj1AN+Klyl/YJ0SrvYTgYL2zWZBsVp4Ft1BgsRhPP2I1XnwxzabIfW2IMT7LUS76kITPDw9lV9dyxjPi73W7qbAqjLyvoYI22BAK1t7CQwH67IQ2rqcYuzRMnaBjZlJLm8rkLCbysoycnuwfbWSuo6tX3QhtWPHzayyR34m7Y9TZ5h7RWKqtDobXO0pO5c8wgqUsBTtHV+dE0JuMUz7RFI9jsruqf+YeS28Gw0u+3fX15BJf4fuugcOV4R14ddwZh/6GJFvAMKV+4IQw1Volt0SEa9z/2jbnv5TvG/DornKdMxI6lcgldC5ZHrSU1WuHL2e3BF9IMgyliVghv4IxonfZHGfux8sGOn2zavskMkijAWOJ1qnA4D1YoC5R9DtoeTUjDM/8c7OUw=

```python
from aoc_tools import *
import sys
import pyperclip
sys.setrecursionlimit(1000000)

with open("input.txt") as f:
    s = f.read().strip()

def printc(x):
    pyperclip.copy(x)
    print_(x)
    
ans = res = 0

s1,s2 = s.split("\n\n")

# grid input
g = [list(r) for r in s1.split("\n")]
n,m = len(g),len(g[0])

g2 = []
for row in g:
    nrow = []
    for cx in row:
        if cx == "#":
            nrow.append("#")
            nrow.append("#")
        elif cx == "O":
            nrow.append("[")
            nrow.append("]")
        elif cx == ".":
            nrow.append(".")
            nrow.append(".")
        elif cx == "@":
            nrow.append("@")
            nrow.append(".")
    g2.append(nrow)

def solve(g,n,m):
    cx,cy = 0,0
    for i in range(n):
        for j in range(m):
            if g[i][j] == "@":
                cx,cy = i,j
                break
    #print(cx,cy)

    ins = s2.replace("\n","")
    #print(len(ins))
    for idx,move in enumerate(ins):
        dx,dy = {
            "^": (-1,0),
            "v": (1,0),
            ">": (0,1),
            "<": (0,-1)
        }[move]

        c2m = [(cx,cy)]
        i = 0
        impos = False
        while i < len(c2m):
            x,y = c2m[i]
            nx,ny = x+dx,y+dy
            if g[nx][ny] in "O[]":
                if (nx,ny) not in c2m:
                    c2m.append((nx,ny))
                if g[nx][ny] == "[":
                    if (nx,ny+1) not in c2m:
                        c2m.append((nx,ny+1))
                if g[nx][ny] == "]":
                    if (nx,ny-1) not in c2m:
                        c2m.append((nx,ny-1))
            elif g[nx][ny] == "#":
                impos = True
                break
            i += 1
        if impos:
            continue

        new_grid = [[g[i][j] for j in range(m)] for i in range(n)]
        for x,y in c2m:
            new_grid[x][y] = "."
        for x,y in c2m:
            new_grid[x+dx][y+dy] = g[x][y]

        g = new_grid

        cx += dx
        cy += dy

    ans = 0
    for i in range(n):
        for j in range(m):
            if g[i][j] not in "[O":
                continue
            ans += 100 * i + j
    printc(ans)

solve(g,n,m)

g = g2
n,m = len(g),len(g[0])
solve(g,n,m)


# old part 1 code:
##    bxs = []
##    nx,ny = cx+dx,cy+dy
##    while g[nx][ny] == "O":
##        bxs.append((nx,ny))
##        nx += dx
##        ny += dy
##    
##    if g[nx][ny] == "#":
##        pass
##    else:
##        assert g[nx][ny] == "."
##        target = [(cx,cy)] + bxs + [(nx,ny)]
##        for (x1,y1),(x2,y2) in list(zip(target, target[1:]))[::-1]:
##            g[x2][y2] = g[x1][y1]
##        g[cx][cy] = "."
##        cx,cy = cx+dx,cy+dy

##for i in range(n):
##    for j in range(m):
##        if g[i][j] != "O":
##            continue
##        ans += 100 * i + j
```

* python solution : https://github.com/jonathanpaulson/AdventOfCode/blob/master/2024/15.py
* python solution : https://topaz.github.io/paste/#XQAAAQBbAwAAAAAAAAAznIlVuo9qg5SL+E+dSUlMqbFSuX55LQwn13BRRp5b6LvWbsHa2XUd+CrT8JzMVS8rlcuskkNKPfrSKh95KoNk6Vh0DQC6gTsapjgOHkaqs6SICpX4JbylKyAUayT0aApUchDitiYM0jW1Aez74DE46+Ew7cBU0oT9X+ztl3P41yV0Pw89TLCImcNdabfOXcNI1I4ghOe5U1d511Mw+v1FZyqGkR9SYWamQcWTPKTJPqGson/QJXUS1lTkAgR0s+dqbNwbUr1Ljhp7T/W8ZGcy5PosnJ2Xp2GURAhh0LYpCD3mQK27McQSbpUb31cUW8sr5/XmpmZ3qXUSXO3BzdHJfki6P6YrKVdzFKObGbg4O75Tao7UDW27lRqirMTHehtjLOJHGyDB2BWSlTJ7efA4wNJRfvZqO8Zuse2QI0ljnzhbNsVdU598cZzOQ15arGr7aqF+TK/x3onUZzj2Mn9T53YqIiDXawuX9iyhXROYsL5GjnPAk2Cz6qERUbkAXYfTRmXS9BlfsceUWNfBu6jAySwdNY00YaXjnO1SUR0UjWz6ng0q4Vr5JVLhSf5Liwg=
* python solution : https://topaz.github.io/paste/#XQAAAQD4AwAAAAAAAAAznIlVuo9qg5SL+E+dSUlMqbFSuX55LQwn13BRRp5b6LvWbsHa2XUd+CrT8JzMVS8rlcutWKaEllj6x8NokMVWXioT860lGfFviVnMaIO4h+chDY5Myy6Cw/X1Uz63zaxJ6dZxYke7g40MxOYtc5NI21U1TP9+RQFBvb7ddbFkhCdwIM55y7P/aq1/p4+7v7XfVUrGfZfAkNcJwPup+e407y7BmeGHpjg7jFOfEQEIQCOlqXLkzimrpcEVt+cJ9SjpkK67jReTzi19q90N2CVs/8Lv6JWbpspqFFRQP11+/NrE2wYf6W5UKGWuVPA08Ijs/9J9Kepha/K60/o/VVGGl4fIcKKlqePEfkbyVd454ww6RtZJbvL2rzSrFadI5SqHcvO1G/osqZKWWaDQcrHZv00BiUzI7CMbx7S1PHVdW7rLLqJbLj8npNXX3ZKPTMpEatheKfp+DZ4FJB1P4Sk+Bf4ElAcGITe0XmbuQT0keOmZ1tCAYr832wLte6Ch0MdevwpnWUkUQ7URE3OqLL6zUH7ynDZii+F1DP1Svak8bhJm827sWiX0Qk5UJzQQErTkqHGqMlISVaa2f6REZR4/sfy0LwLVzLI6/nXr5Q==
* python solution : https://topaz.github.io/paste/#XQAAAQBbAwAAAAAAAAAznIlVuo9qg5SL+E+dSUlMqbFSuX55LQwn13BRRp5b6LvWbsHa2XUd+CrT8JzMVS8rlcuskkNKPfrSKh95KoNk6Vh0DQC6gTsapjgOHkaqs6SICpX4JbylKyAUayT0aApUchDitiYM0jW1Aez74DE46+Ew7cBU0oT9X+ztl3P41yV0Pw89TLCImcNdabfOXcNI1I4ghOe5U1d511Mw+v1FZyqGkR9SYWamQcWTPKTJPqGson/QJXUS1lTkAgR0s+dqbNwbUr1Ljhp7T/W8ZGcy5PosnJ2Xp2GURAhh0LYpCD3mQK27McQSbpUb31cUW8sr5/XmpmZ3qXUSXO3BzdHJfki6P6YrKVdzFKObGbg4O75Tao7UDW27lRqirMTHehtjLOJHGyDB2BWSlTJ7efA4wNJRfvZqO8Zuse2QI0ljnzhbNsVdU598cZzOQ15arGr7aqF+TK/x3onUZzj2Mn9T53YqIiDXawuX9iyhXROYsL5GjnPAk2Cz6qERUbkAXYfTRmXS9BlfsceUWNfBu6jAySwdNY00YaXjnO1SUR0UjWz6ng0q4Vr5JVLhSf5Liwg=
* python solution : https://topaz.github.io/paste/#XQAAAQDMAwAAAAAAAAAznIlVuo9qg5SL+E+dSUlMqbFSuX55LQwn13BRRp5b6LvWbsHa2XUd+CrT8JzMVS8rlcvjAXZoUVQG8KXviEv0Ia607hEMkntB4bnASUnYam8dVjLshAU/JqEA4zIo38vwLlPQ4JfoYFeLCl9dwrbO7NYscRL5YEC+q5mdu5Gd8mIkMofLlNATyA7gwmiVQpkdyuL9VfHHJ7MkUk6ru/mEIiz86DyBecAhCf/UAgaB47DTXBHvF3BSq6xJ/YkasCt4F8rVNGI+4fSZ9w8zbY83Yi1I2B6kLZJ72yVC9VAFG2+pieZFYsjAM3GYYGO4g0uZ1B6qz9/oZgPiJ5vIG9GTLvQUChHaP+mYwtM1yAEOJ3FHzvPXemohtONyaAtrXsrldb4THancrzaEjMm5dq79urjEcLDGenfttDnMMisHBTegleOHnTAQlhAW8YsFOZfOmP9hjVc9mdJd7SbmeaAh00NfrtijHhB90kyIfC8daB9R6yuDxNnWlemo3hm4Et2bEi1EnxWxfgn6Fdt0pc86rluqCGKhM5UtEdVW2/JMOBGdMmnjj92w5Uq1sjL7x3wXsMfUARA82sIPvz48iV9R4QzqSfvaxAas/+QE2KA=
* 