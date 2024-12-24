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

* https://www.reddit.com/user/mgtezak/
  * https://github.com/mgtezak/Advent_of_Code/blob/master/2024/15/p1.py
  * https://github.com/mgtezak/Advent_of_Code/blob/master/2024/15/p2.py
* https://www.reddit.com/user/joshbduncan/
  * https://topaz.github.io/paste/#XQAAAQCiHgAAAAAAAAA0m0pnuFI8c/fBNAqS5B+URcp9LydK0gHvEza9dqBlol6nSnm2whmOxRzKxIHMJ7qEF8eFmXIxAe+0TD8BUJTUfgM3SSwAt3a5Q1ov0fWwSb+FnxMoa5RJP1hWO9O1Pkmkbj2j9BBcK5U5cDXZtEz8lYo2sX0OSyNpbEfXaWN5n+qv8M3Vjdtl0ioQznYJIJ+WWNLRKIBXodPYDQSHir9KfF3oIMgawfIeF/T2YJRWz07tLAH6XBXtLZliWrObjL7LGYFTPuMNxEMw4ClTL+JHeKBtXJXbeRvK7eLw7w6ki2K2TCpXLK95JfrW0N98Q0p/nIa5EMBIpl+aVcWX2UMndkf9B9moBXu16dFV3NTXTkrOMFczsnC64KBrDbB6MeWHHw0B+Mr6BpI/0mfaJ0iGRWsUthZeVUimWrrbTswTOcBeBUiBS9658O8l3LIt+1dAeAd6ADxZk6vLGpF9nxtAiHAjEk/DDUd0c8UYmGWSJhTyvLnOl2vImoswhp7/ByzrZQaLwEtVUzYf6oPPbshcyu08hg94FOPmwB9lFfBzjk4OzA8+yJ0hv83e3SQXUzAQLJsvKsDNe7lovYUQPKir7TTpkxLAhzjuCT0pN2tSajM1GSZDRKN11AEk0uEdwBwo+Gy/aIjo00N97uggzVd3iHDghjlO1uWottB04mvBFZEz+9JRs4pnNl9cMBMhVnCX8PvcQzinXE09bngga10VrOgIveUc2veL9pYM8wuINRVXtKCX3c4XUC3nlL4KJJS9KW97s7BeOKTZQJm0xNvw/X5GTmFEf68OcW1nfoJbdLDFHT0WJxZkl/yzLHjm0RejYAMHDYSN7O2fzE/8yqW7LBUZWnZ3OhO9soyv+OtNxYqq5iG7hjmBJzZY39JgVYh5kNi2kIbQYAY4fLuosUO3cFzNoifM+QFPKJ1tp5vmcA4tE7/1WnaqYn7nOQ/tlwHJKiQQXU1XeN5tq1zVpzizn7NZTIZW8GALR6rs1fn8wksVkskS9QwqvcuqV5hpxP6BiXtLuI9KkWLyx8Ry0BcQSQjtbwrpflnPztjnfeAfP745MEwj5As1d9LqCSOoEsjoX8BoERptPBLOi1ThelGcswQehGDs2ukdxIxULfr4i71LwJBhB13EZoUt+B1f1ZrovSXQK/IAGXOx8eWDhS7nxcKjn2P3GD0PsbgBP0LSjCzjfw0EU7JaaeshyLD7oXE1ucNVllVPqanLl//YLtdCpe6FJvdqclMPsEEHxmFSTw01c+QQ5a+WKPZ7qkDQzy7idk6fOYRoaYTNB69oci2tonw0f/i+pnhL2dnsl33BVj2qeaY60Cuei5x8KxucxO59AmQIRW0bjKLfzMKmOZRuhl/A7qIm98alUiveZbHUjf4wfoTFbDFQWzhr5eUXG6Yfe2OuSFtocLhlMctucnlMOF3Fjeq7iJMYERDTXjDZ9M35GajB4sl18Kna0rOGInsq1uuAuQobeoEWA8MOxAVF1AaTaNHJWeZ5nYMgYTgJfP0+L9u2itDVgnOwK5HtC5SKriffPm+dpMItLvSk+ja66+1gkSMy5uUWbIK5GbPKdcQOBayQLSkPHT05GZeKokrb79/fQUCjadJ77DT1geegV0/ZCiadklJsFqn3tE8PoJqddmIs/Q9FTelakAZY89+uq6ffA2mR79IW83TfiCutRPBfIWLaAznCD+fx3wSb5ybSxj3plE8D/aXuahoIxyrChBzBULcrduQ1loceNm7fM5VcwYpnZmpYANd0cC5UOxWcs+1Nb7bHvzkjx0JGfb/l156dnNggAqKTHBcEHP/vNJzD

* https://www.reddit.com/user/Wide-Progress7019/
  * https://topaz.github.io/paste/#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

* https://www.reddit.com/user/Derailed_Dash/
  * [Welcome to Dazbo’s Advent of Code Walkthroughs](https://aoc.just2good.co.uk/)
  * [Advent of Code: the Best Way to Learn to Code!](https://python.plainenglish.io/advent-of-code-the-best-way-to-learn-to-code-d86c6478d484)
  * https://github.com/derailed-dash/Advent-of-Code/blob/master/src/AoC_2024/Dazbo's_Advent_of_Code_2024.ipynb
  * https://colab.research.google.com/github/derailed-dash/Advent-of-Code/blob/master/src/AoC_2024/Dazbo's_Advent_of_Code_2024.ipynb#scrollTo=Vc_nC9LgB6jr

* https://www.reddit.com/user/JWinslow23/
  * https://winslowjosiah.com/blog/2024/12/16/advent-of-code-2024-day-15/
  * https://github.com/WinslowJosiah/adventofcode/blob/main/aoc/2024/day15/__init__.py