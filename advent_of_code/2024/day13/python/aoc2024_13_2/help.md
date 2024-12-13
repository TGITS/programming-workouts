# Ressources d'aide Jour 13

* https://www.reddit.com/r/adventofcode/comments/1hd4wda/2024_day_13_solutions/


```python
print(*[sum(0 if (q*(x+n)-p*(y+n))%(i*q-j*p) or (i*(y+n)-j*(x+n))%(i*q-j*p) else (3*(q*(x+n)-p*(y+n))+(i*(y+n)-j*(x+n)))//(i*q-j*p) for (i, j, p, q, x, y) in [map(int, t.replace("\n","+").replace(",","+").replace("=","+").split("+")[1::2]) for t in open("input13.txt").read().strip().split("\n\n")]) for n in [0, 10**13]])
```

* https://topaz.github.io/paste/#XQAAAQDHBgAAAAAAAAA0m0pnuFI8c/fBNAqHEWM96O8NHmRW+5JINCUniNMc0G3NQLSo6r5hG630JeE0S2tX5u6GFN66dXckUfwBo7103M0xDE0l0CCU1SVOgNr9eHVqQgbP0Qr0lDKeUGAcOoKBPtj/LGES2OnnyaZoIWoWYtqHlCidBCHZWhs4c6qRAT2Lz3XcGS0Y7DwcFQhrZaTcO1nsEIy3Q0VjUL3vv6+FN9rpPQOBsUG00OIyhzkZ4WqGmJGYPV/Jy3zk5Xg1vgoDjjdL/WL7+1ertz5a/096vNBqpn91mR+hzmeYM1jCJxFqtOhsCKMuaaFw67AELrsi0klAZ1/zj6WFjhES5P4vCktc7SaelcFE9m3PoikFQsSedvEv337qMXGahOsOmkZp4WNJOwbomvXdAUdE2YIAiITkMk6y3jUPn4DAR//ChiOMy1o3K+6i0k3gdR5sMv/ZH52/BM0ucA/4esEWAC97nwKWZwlBlngCTWFN/zsbrzRa01JL4Wk9vHcsOJihVx8bN2zdJ04zM1k46TBWEEhOehsIfCB0LYm0+moPRGClGm8bRsQKAsS/Mdzist2s1AePUW8g2F6BUix8nc7tJfDA05YW9P7agoOxUc/W97qRaaNplhTg5HBzrm3rrzfPFGW64ojGpjVDWvhSNHJtF5ElrUzrLi3wcFhD2Qgr1JhdqLxFpMWkHWe8KG7yQZHtAHaqjBuoCP4FIsLmMIPNq8LmhYfKV2RtpVjqj968UDUxmsfsmO96/MFB0p1XSwCmCKmdAr1LbqzZtTAJrmwp5QPujazgCeR5ScnzXbnVj2yFt/88hlsist3xaJ03xtcFTcLQ2Rg8A3EJXmou7V3FcGIwcu2iylTiLtFCaK0RheFZFVDFWJ5mc751zFp41KE2kfMWroflStz/94GNjg==


* https://topaz.github.io/paste/#XQAAAQAMAwAAAAAAAAA0m0pnuFI8c/T1e9w2DUnbzll5OZqm7f1QV2i2XBg3gMxysmohKga4I7WFa7Z/+fizNdDfQfaXA678jxpjSKtGeipN8cBYPK7Q8bQQNrTmUPd3EvDRED6AFxIPuWthoDNwf4CpNO/Cy7SFgkmCCFX6w0jbmCQEZ6LYCdocxSZSpNPRKNK8W3BJmpXwQTRlrseLt+XzKe/hwMkV0W2HG0LV6NLWkvyR2FTnUkAkrLePGnq4MEDKr3aQOEIf8Rdqf4Cnbtmr/m/eUg7I/gd9VVCAFPG/WpXbjN2KEr1x8Iu0jGSZIXV7IFh+sD9l6SloSOipr0NwjJTv6bamh1t3xXPPl0oWC2t6RgRE8G4yK48Sco/7zvrhhDDAvqx9745ydtKk1E6iEsq2VuOn2qxxyBZ5xUKDZQhElnKn4G3nBzcxwhtTD6+aXpsIIpL9bfIcSnngx+L9kOgSumZQw7L0Tbh6PmD+2XSqoC+pCRm9YVNgN7kQgmoN5rHu94S/KPB/gsV/up0dDuWebtQvv/t+M7VsO4N5+BiR0SgFoxHbFPAIRXFVHB8K53s+zD+3HE8EvXZob/vQUGA=

```python
import re

games = open('2024/data/input13.txt', 'r').read().split('\n\n')
OFFSET = 10000000000000

def solve_machine(Ax, Ay, Bx, By, Px, Py):
    # Cramer's rule, Both must be integers and nonnegative (did'nt need nonnegative for my input).
    if (det:= Ax*By - Bx*Ay) == 0: return 0

    A_count = (Px*By - Bx*Py) / det
    B_count = (Ax*Py - Px*Ay) / det

    if A_count < 0 or B_count < 0 or not (A_count.is_integer() and B_count.is_integer()): return 0
    
    return int(3*A_count + B_count)

def solve(offset=0, total=0):
    for game in games:
        (Ax, Ay), (Bx, By), (Px, Py) = [tuple(map(int, re.findall(r'\d+', line))) for line in game.splitlines()]
        total += solve_machine(Ax, Ay, Bx, By, Px + offset, Py + offset)

    print(total)

solve()
solve(OFFSET)
```


* https://topaz.github.io/paste/#XQAAAQCNCwAAAAAAAAA0m0pnuFI8c/fBNApcL0Pua5b5MB08b5WVtNXfgbwt7rD/S9CnUsOjtWCQ4XnaO+wCwnonEa1R48AZQDFwhm9GlwuL8wJFqjQoDY5y+eltBwxfoP7X4AgKLs0Y6e7kXDKiuyjYfJwnCb9tMuhEvu+RQGruZajtKDsADDEUanCWummjRilOTlW6aibN+TI7BrolNtNR1O5bnT8a655fP7cQvlAwS++HOrQCTx1u5lQjeN+O4YdG/gehak56/YQu71IcaPMWoI8q6KTWEmNZ50VTMboxeNplUr24sv12Jon8n6JNPWjwhpXS3kZ5zz5JFFvvgNTsusdOxx4jR+ZU3Rx2ML9bkOgRhJz4egXgDyNl47hbXuWxjII2Fpu1RTf/4u2jjKSZinmYXtmr/HL9IaQH6ehgbc6lh7/8mhVqyJ/N5OFcETAr+vFHCGw++y7qSbTb1aN3b1KVTxGzqlNcPK+zZmu2cgIdPHK2pVA8K8eNBEa3yj0OmcCGqpgq1K4hXFuqft3XLiqbRd+wGY9hFstDlbIKqr4r9Jke/2NXYAMYtlqTLvZGcvexa4ysjeXYoOSU2zn75y32QQNd7oLsK+hhkr0JKB8VpBLb0O9aTHtpXnjRrPCMy3+1mrLQNpZ6g+jCVLsXnpOTtzYDC+/xr50cAhoNc8HIU66IHe49n9Yh0yfagoPqA/RQQxkqWFktDFbmDklJ2KbnpmlH5AJYMknX8pVwgZIiJOChyjaPFMfuy4ZIEYRPZRJFwclRxlxQHh+UTv4S+td8y3ZNebDo/G0dJ73BvE2of7cKXjWHUfYlZk6dOsRB3HWD9Qcf/zi4txBIFISyLLMlBA72XwQ1nFkrvCBZ9yrAdZwMRSbAIrFUUKzWF7xAjgC+wyaYpmHy0wOfh0jgohkRynStBOc0KGgQ5TcefLWMVEY1ukM3Qsy4iLqtXavpyAvQzSddlEX2FQz7Zm52K9uuz3WciuO2jUi568QSoMgZoFgArs1IB/+UdVjSEy6SH3kc9Gwj3hP4bP0dfBJRY20YMQiMZT2Fh9FbpgSwHusikJ3YzsFQWWTe3AP5oT0GGrlVJS9jEtyxVcFQgwGwdgzjO/oUS8qzbmU0Ytg9wf1wm6Xuvu1fKkhhrSVAL/omb2fohh/tDuhgTfkGAEHwUzVwmtazWJZoFUO86uraxfNoHzIJvhjFNGoV/VHEJK3Nt2AGCtfpODqiFh8lSTIwc2dSb5+usbdGlMeGjyp2lkDMcrdGPL2N7T7OnrAzjq3w7VyP0wk5cymzcv6RBuYfqSga9ULv1J0VlLrLGnmQ3ADB2S4J1ihyNH8wZqahOVyQm2FpMPmwmn/mKbLxHtsXB+9Q01ytw1Q993EeQ+tOvdze1dT/IA+VdaOBD04o4+4ztq4JBiFf9oduYImxz58U8gFqJMyvL7e62//6Wszu

```python
import sys
import heapq

from timeit import default_timer as timer
from pprint import pprint

from sympy import symbols, Eq, solve    # I think this should work

OFFSET = 10000000000000

def parse_machine(lines):
    a_parts, b_parts, prize_parts = [part.split() for part in lines.split('\n')]
    a = int(a_parts[2][2:-1]), int(a_parts[3][2:])
    b = int(b_parts[2][2:-1]), int(b_parts[3][2:])
    prize = int(prize_parts[1][2:-1]), int(prize_parts[2][2:])
    return (a, b, prize)

def find_presses(a_step, b_step, goal):
    found = []
    limit = 100
    for a in range(limit + 1):
        remaining = goal - (a * a_step)
        if remaining < 0:
            break   # Cannot press button this many times
        elif remaining == 0:
            heapq.heappush(found, ((a * 3), (a, 0)))
            break
        else:
            b, over = divmod(remaining, b_step)
            if over:
                continue    # No valid B button press
            elif b > limit:
                continue    # Too many B presses
            heapq.heappush(found, ((a * 3) + b, (a, b)))
    return found

def part1(filename):
    with open(filename, 'r') as fp:
        machines = [parse_machine(machine) for machine in fp.read().split('\n\n')]
        
    # pprint(machines)
    # print(f"Total machines: {len(machines)}")

    answer = 0
    for ix, (a, b, prize) in enumerate(machines, 1):
        combos = find_presses(a[0], b[0], prize[0])
        if combos:
            while combos:
                cost, presses = heapq.heappop(combos)
                y = presses[0] * a[1] + presses[1] * b[1]
                if y == prize[1]:
                    answer += cost
                    break
                 
    # print(answer / len(machines))
    print(f"Part 1: {answer}")
    return answer

def part2(filename):
    with open(filename, 'r') as fp:
        machines = [parse_machine(machine) for machine in fp.read().split('\n\n')]
        
    answer = 0
    for ix, (a_btn, b_btn, prize) in enumerate(machines, 1):
        ax, ay = a_btn
        bx, by = b_btn
        x, y = prize

        a, b = symbols('a b', positive=True)
        equ_x = Eq(ax * a + bx * b, x + OFFSET)
        equ_y = Eq(ay * a + by * b, y + OFFSET)
        results = solve([equ_x, equ_y], (a, b))#, dict=True)
        if not results:
            continue
        elif not all (results[btn].is_Integer for btn in results):
            continue
        cost = 3 * results[a] + results[b]
        answer += cost

    print(f"Part 2: {answer}")
    return answer

if __name__ == '__main__':
    if len(sys.argv) < 2:
        print(f"USAGE: {sys.argv[0]} <input file>", file=sys.stderr)
        sys.exit(1)

    before = timer()
    part1(sys.argv[1])
    after = timer()
    print(f"Part 1 Elapsed Time: {(after - before) * 1000.0:.6f} ms.")

    before = timer()
    part2(sys.argv[1])
    after = timer()
    print(f"Part 2 Elapsed Time: {(after - before) * 1000.0:.6f} ms.")
```

Good old high school algebra linear equations. The "minimum" tokens requirement is a red herring.

[Topaz Link](https://topaz.github.io/paste/#XQAAAQBBBQAAAAAAAAAxHUrq6kJ/fcvcXrbfUBqZFknYmApvK4caUMdd2Dsc4zDDVb+INVNIBRfXfmoe128YWbj7qtWLrXVfwQiYKId2Q8qfbz4JjrFj5h8F9SN+AzVm8eBQ7Iw9KwcW6u4iTNPN1AasFpb/OrsloiznCuaEl9xhW3OddXJIGwAvYg1amxapPG6BLG8OaaQIMIrEzP09mQbLkF39jzV+OLpiRU5+dGUwHkLROLM+Zbm8TxmL4MdmVK1a9VuiIe7Iwxditc985k4G59xrZXdDwkxFHOSs83cNgzyVDfK/yF29Npbubm6mk1L7Q1eKuBpHYT0jSzjWuz7boFSkw/CVUedddEmEDQSxuSABeJXlU/T0z0hvJ5ko3jVcFay8o3MfkI6SfrkA2mu313I0sbFEXjjBU012vVwPCbmafpJikJ5o6AFgwMCeMKuNzfxgY/32ndOtyGfhNmWjcOiFf2NuVbEQcxCmdOJe9i2jfeZNcX9oVjZLJrTqve8ec0ZUWmFjicdmGpbj5OWJWMuHmeWRQAioAwMLfmWXphVHWhjhBPgCFRIPyHbsup+9hOMxvSWPRlHlrQu+gofjCIyV81KQUvwk992KPgjuvd5edpP1DshY6qP5fkijboTfDsy5O7sftIq5n7ayYmEZ0nY8Gv8oVUcA)

```python
buttons = []
prizes = []
new_button = {}
with open('input-13.txt') as f:
    for line in f:
        if line.startswith('Prize'):
            prize_line = line.split()
            x = int(prize_line[1][2:-1])
            y = int(prize_line[2][2:])
            prizes.append((x,y))
        if line.startswith('Button'):
            button_line = line.split()
            button = button_line[1][:-1]
            dx = int(button_line[2][1:-1])
            dy = int(button_line[3][1:])
            new_button[button] = (dx,dy)
            if button == 'B':
                buttons.append(new_button)
                new_button = {}

def min_tokens_prize(button, prize):
    a_x, a_y = button['A']
    b_x, b_y = button['B']
    x,y = prize
    denom = a_x*b_y - a_y*b_x
    numer_a = b_y*x - b_x*y
    numer_b = a_y*x - a_x*y
    if not denom == 0:
        if numer_a % denom == 0 and (numer_b % (-denom)) == 0:
            return numer_a // denom, numer_b //(-denom)
    return 0, 0

# Part 1
result = 0
for button, prize in zip(buttons,prizes):
    a, b = min_tokens_prize(button, prize)
    result += a*3 + b

# Part 2

part2_prizes = []
for x,y in prizes:
    part2_prizes.append((10000000000000+x,10000000000000+y))

result = 0
for button, prize in zip(buttons,part2_prizes):
    a, b = min_tokens_prize(button, prize)
    result += a*3 + b    
```


[Language: Python]
Today one was quite straight forward. Had to make those numbers to formula:

x1*A + x2*B = X
y1*A + y2*B = Y

Where X and Y are price coordinates. If so, then

denumerator = x1*y2 - x2*y1
and 
A = (X*y2 - x2*Y)/denumerator
B = (x1*Y - X*y1)/denumerator

12 ms in my computer,code here :

```python
input,p1,p2,currx,curry,i, bnum = "input.txt",0,0,[],[],0,10000000000000

def calcResult(x,y,bn,p1c,p2c):
  X1,Y1,X2,Y2,div,p2Res,p1Res = x[2],y[2],x[2]+bn,y[2]+bn,x[0]*y[1]-x[1]*y[0],0,0
  A1,B1,A2,B2 = (X1*y[1]-x[1]*Y1)/div, (x[0]*Y1-X1*y[0])/div,(X2*y[1]-x[1]*Y2)/div,(x[0]*Y2-X2*y[0])/div
  p1Res = 3*int(A1) + int(B1) if A1.is_integer() and B1.is_integer() else 0
  p2Res = 3*int(A2) + int(B2) if A2.is_integer() and B2.is_integer() else 0
  return [p1Res+p1c,p2Res+p2c]

for k in open(input):
  if k.strip() == "":continue
  arr,i = ((k.replace("=","+")).split(": ")[1]).split(", "), i+1
  currx.append(int(arr[0].split("+")[1]))
  curry.append(int(arr[1].split("+")[1]))
  if i%3 == 0:[p1,p2],currx,curry = calcResult(currx,curry,bnum,p1,p2), [],[]

print("Part 1:", p1)
print("Part 2:", p2)
````