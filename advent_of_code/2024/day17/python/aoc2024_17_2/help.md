# Ressources pour le jour 17 de l'AoC

* https://www.reddit.com/r/adventofcode/comments/1hg38ah/2024_day_17_solutions/

* https://www.reddit.com/user/hrunt/
  
> Part 1 was straightforward, but I knew Part 2 was going to be some sort of "decompile the program" type problem. I recognized that the value of a shifted right 3 bits each iteration and that b transformed based on the a value and c values, but I couldn't figure out the relationship between any given a and b. I'm not too proud to say that I needed to read a hint here about looking at the value of a as determining opcode/operand pairs, rather than individual values in the program. After that, I put two and two (and two more and two more and ...) together to build the register from the end of the program.

* https://topaz.github.io/paste/#XQAAAQCxBgAAAAAAAAARiEJHiiMzw3cPM/1Vl+2nx/DqKkM2yi+HVdpp+qLiqZwdO8DftYzG7FP2yfr8MZFMEcg/FxuIzqiaFV4/1MOf3SXYts1oELiF2dG3WJ37ywWI7RIBptugKfjfizUMCG95WdyAmt7OJt3OdDRHniQZoSTLVwPyYYJhAvsJTL1G94Hd3QArWxPf8OA88xo/Ys9uvBnBsxyH/j5nzsTwUZbt7+q+kseyF7mf6jf5tSzuoe+QwI4df5aV8JtfplLdjgJ81ZdpGC3fHW5t4PfjL/NvhkTqlADBVwRZsM5Gd+5Bu4dAaOV4eUO7Ijpg871OVsRqBaJ/pLZArTo3rttXoq/a2BLuF4mOI6dcmUG7S9Uw8F9PIq+QZ3KOnn92TeBWtvnh71lLQZJ8O7u1rMbfNBD9RaGuklmLeMlRSbD2nl1Jznd57sE4Ua1x059j5Vn9cyw/DROyM9ulRg1GqX+flkAVrFPkrvVl0zwRRqDG2sqZdy5BfI5SgLCtfCY6CP+z48yYGH82rYmVF5LLTJbWvw6/P8H5SMChGAEnzvepx9J356izxpalqE/cbW+bfc7QoCSeP76hs8+DGamDN7jc2aftrA2ir/nSnkv6AUiYfJyOgKmjSmzm/DAnI+iRnKxqiFyeRJtyMqi5clMjPRVWQnNZeZMU2pZ48DtrQOd5c8HZKaE9xCnrX/6Tqd/AjZQPxIglDptGtSiogUGEeYu4nPADvxTgSJo2ibvHbLnvdPGw8DTffKCCBm6lsUtoXSyRlLtcERwroIQhnIhmUipIYozfPhj6+2LAs2gLpk468yWUzzP7pRpykR52oVpZDjCSjwBH6RUfoBYWRn/nJntgAMLhQz/Bfza3G2JQ32uPBYE9JIu0CKFdIc208JWcZfnmu3rC48fdNqIplOWvaTWciH6LYijoBDXKpctXb0eIHXhOCdhEhdVRQkaQGxFO1gqOXRHGVKB5+wNosR/BhMgKeE6DruLkHbDKm3L+27bScsB9QIwytpHReNQIz50LlZe6xgpB/fHC4P+zGZVC

```python
#!/usr/bin/env python3

import pathlib
import re
import sys

from collections import deque

sys.path.append(str(pathlib.Path(__file__).resolve().parents[3] / 'lib' / 'python'))

import aoc

def run_program(program: list[int], a: int, b: int, c: int) -> list[int]:
  ip = 0

  def combo(operand: int) -> int:
    return (a, b, c)[operand - 4] if operand >= 4 else operand

  out = []
  while ip < len(program) - 1:
    opcode, operand = program[ip], program[ip + 1]

    match opcode:
      case 0:
        a //= 2 ** combo(operand)
      case 1:
        b ^= operand
      case 2:
        b = combo(operand) & 7
      case 3:
        if a:
          ip = operand - 2
      case 4:
        b ^= c
      case 5:
        out.append(combo(operand) & 7)
      case 6:
        b = a // 2 ** combo(operand)
      case 7:
        c = a // 2 ** combo(operand)

    ip += 2

  return out

def self_generate(program: list[int]) -> int:
  candidates = deque([0])
  min_candidate = 2 ** (3 * (len(program) - 1))

  while candidates and candidates[-1] < min_candidate:
    seed = candidates.popleft()
    for a in range(2 ** 6):
      a += seed << 6
      out = run_program(program, a, 0, 0)
      if a < 8:
        out.insert(0, 0)
      if out == program[-(len(out)):]:
        candidates.append(a)
      if out == program:
        break

  return candidates.pop()

def run() -> None:
  with open(aoc.inputfile('input.txt')) as f:
    a, b, c, *program = (int(x) for x in re.findall(r'\d+', f.read()))

  out = run_program(program, a, b, c)
  print(f"Program output: {','.join(str(n) for n in out)}")

  a = self_generate(program)
  print(f'Self-generating register A: {a}')

if __name__ == '__main__':
  run()
  sys.exit(0)
```


* https://www.reddit.com/user/Jdup1n/

> Part 2 is a different story. Initially, I ended up finding the relation (0*8+X1)*8+X2)*8+... where Xn corresponds to the n-th value between 0 and 7 which generates the 16-n-th digit of the instruction sequence. From there, I generate all the valid combinations from X1 to Xn, increasing n by 1 at each stage.

* https://www.reddit.com/user/WizzyGeek/
  * https://github.com/WizzyGeek/aoc2024/blob/master/day17/part2a2.py
  * [z3](https://github.com/Z3Prover/z3)
  * [Z3 API in Python](https://ericpony.github.io/z3py-tutorial/guide-examples.htm)
  * [Waffle chart](https://python-graph-gallery.com/waffle-chart/)
  * [PyWaffle Documentation](https://pywaffle.readthedocs.io/en/latest/)

* https://www.reddit.com/user/ButchOfBlaviken/
  * https://topaz.github.io/paste/#XQAAAQCIAwAAAAAAAAA0m0pnuFI8c9q4WY2st5/X45wywZBWpHOgZv0r5V7udStKr2dKEHlDMt3J4Exq2sSgidObG3bZX+J7OTkqtrhUaY66/ccKxqoySIVg/bCSW65bg72H0duNnzCr6Np5s+BzOwN8aDlQN0lDH5o9lgvheuBNm9t3hqqPOuhFB3V+iGzC5XLYEvc6YFd9rsK+RylXHSlxnoyPAV2edAs8kc1XEvNZya5Pz6fJ1grmBGC8CpXak4cG15j3mj/HLb5PlkbPlXZrq6jIbjCuKlAJJ9GBp186QDLaUdSVH6htm46pSgetnAVgwPhT/qxZZVPR/7e44wEVvyj20T6jSdhNTnybKltZESd2JtvR9WPfwof8ZsFYAU1GHfmFMBH2ggYq8XFGaVQk8fMiNbYw6A2f5Ct0EC/12v57yvV24jNRwL+Qzqvd7xN1muFBIRBvpCyCVdIpRZfNilg8r3GiM7Txa9NMHB8THm25gDtinLIEY2Wan8C6cjphmdPAziJSrEoljPg8P7eTePiHHdZwD8zggj3sXrgTj7pv7V3QvBNTsVy1mMqSgSwJVL2O8vcaTYqz9maBIQRhwItMFN/n6Ad92Nx/yxf8K1Tr

Basically, like most I noticed that the program was doing the following:

while A:
    B = A % 8
    B = B ^ 5
    C = A >> B
    B = B ^ 6 ^ C
    A = A >> 3

This can be broken down in to going through A 3 bits at a time, with the leading 3 bits producing the last number, the next 3 bits producing the second last number etc. So the solution is to do a tree search to find all matching numbers in reverse order 3 bits at a time. Since there are multiple solutions and we need the minimum solution, I just maintain a priority queue to optimize the search, but it works without one as well.

```python
import heapq

with open('17.txt', 'r') as f:
    reg, ins = f.read().split('\n\n')
    regs = [int(r.split(':')[-1]) for r in reg.split('\n')]
    ins = [int(r) for r in ins.split(':')[-1].split(',')]

def step(A):
    B = A % 8
    B = B ^ 5
    C = A >> B
    B = B ^ 6 ^ C
    return B, C

def run(A):
    out = []
    while A:
        B, C = step(A)
        A = A >> 3
        out.append(B % 8)
    return out

def search_3bits(pA):
    valid_As = []
    for Ashift in range(8):
        A = (pA << 3) + Ashift
        B, C = step(A)
        if (B % 8) == ins[-(A.bit_length()//3 + 1)]: 
            valid_As.append(A)
    return valid_As

print(run(regs[0]))

DP = {}
Q = [0]
minA = 1 << (3*(len(ins)-1))
while Q:
    A = heapq.heappop(Q)
    if A >= minA: 
        print(A)
        break
    if (A.bit_length()//3 + 1) < len(ins):
        for nA in search_3bits(A):
            heapq.heappush(Q,nA)

```

* https://www.reddit.com/user/AllanTaylor314/

[LANGUAGE: Python]

[GitHub](https://github.com/AllanTaylor314/AdventOfCode/blob/main/2024/17.py) (and my [initial part 2](https://github.com/AllanTaylor314/AdventOfCode/blob/main/2024/17-compiled.py), which is rather slow) 704/1557

Part 1: Tediously implement the instructions (fun fact - my input didn't use opcode 6)

Part 2: Initially I chucked it in a loop to try every number (this was doomed to fail...)

I then analysed the input to work out what the program did. It takes the last 3 bits (masked with XOR) as an offset, then takes the 3 bits at that offset (masked with another XOR), and outputs the result. Each iteration consumes 3 bits and outputs 1 value. I generated a series of overlapping ranges and checked which adjacent ranges had overlapping bits (this was quite slow and probably did a lot of unnecessary work). This got me the correct answer (eventually - it took 1:30 to run with PyPy)

Afterwards, I decided to try Z3, since there are a fixed number of loops. Z3 doesn't do optimisation, but it's not too hard to keep adding the constraint that A < the best answer until it's unsatisfiable.

From some analysis, I reckon the inputs are all of (roughly) this form

```
bst A  # All start with this: B = A % 8
bxl X  # All have this here: B ^= X (X differs)
cdv B  # All have this here: C = A >> B
bxl Y  # Location varies: B ^= Y (Y differs)
bxc Z  # Location varies: B ^= C (Z unused - only affects target output)
out B  # Location varies, but after both bxl and bxc
adv 3  # Location varies: A >>= 3
jnz 0  # All end with this: do...while A != 0
```

though some might use bdv (6) instead of cdv (7) (I'm not quite sure how).

* https://www.reddit.com/user/Maravedis/

> Man, this was incredibly fun. Took me a while to realize I had to generate the reverse of the program first to have an easier time. Copy pasting my comments here for readability:

```clojure
;; Analysing the input, we can see that it's a simple loop:
;; bst A {store A mod 8 in B}
;; bxl 1 {store B xor 1 in B}
;; cdv B {store A >> B in C}
;; bxl 5 {store B xor 5 in B}
;; bxc   {store B xor C in B}
;; out B {print B mod 8}
;; adv 3 {store A >> 3 in A}
;; jnz 0 {GOTO start}

;; It means the output is constructed by doing operations on (A mod 8), then looping with (A / 8).
;; B and C are overriden each loop.
;; The output is 16 long, so the max A can be is: `(8^16) - 1 = (2^48) - 1 = 0xFFFFFFFFFFFF`
;; We iterate on the 8 possible power 8^15, keeping the ones matching the last digit of the program.
;; Then the same on the 8 possible 8^14, keeping the ones matching the last 2 digits of the program.
;; So on and so forth until 8^0. We then keep the minimum of the possible numbers.

;; TLDR: It's a BFS on the decreasing power of 8 to build the reverse of the output
;; Note: This code will only work on my input, but I'm pretty sure that it can be adapted to all inputs.
```

* https://www.reddit.com/user/elklepo/
  * https://github.com/elklepo/aoc/blob/main/2024/day17/xpl.py

* https://www.reddit.com/user/Short-Leg3369/
  * https://github.com/p3rki3/AoC2024/blob/main/Day17_solution.py

> Hmmm; I killed the brute force after a minute or so; after a lot of head scratching and playing around, I worked out by trial and error that to get a 16 digit output, then the input to register A must lie between two numbers, one of which is roughly 8 times larger than the other. Turns out that:
> a) the lower and upper bounds for 16 digit output are 8**15 and 8**16-1 respectively,
> b) if you change register A by 1, it affects the first digit of the output not the last.
> c) if you multiply register A by 8, the last digit will always be the same if register A >= 8
> My part 2 thus builds up register A octal byte by byte trying each digit from 0 to 7 until you find the next digit in the original program

```python
import AoCFramework as AoC

def run(regA):
    global Output
    RegA, RegB, RegC, IP, Output = regA, int(Lines[1][12:]), int(Lines[2][12:]), 0, []
    while IP < len(Prog):
        instr, operand = Prog[IP]
        match instr:
            case 0: RegA = RegA // (2 ** [operand, operand, operand, operand, RegA, RegB, RegC][operand])
            case 1: RegB = RegB ^ operand
            case 2: RegB = [operand, operand, operand, operand, RegA, RegB, RegC][operand] % 8
            case 3: IP = operand -1 if RegA!=0 else IP
            case 4: RegB = RegB ^ RegC
            case 5: Output.append(RegB := [operand, operand, operand, operand, RegA, RegB, RegC][operand] % 8)
            case 6: RegB = RegA // (2 ** [operand, operand, operand, operand, RegA, RegB, RegC][operand])
            case 7: RegC = RegA // (2 ** [operand, operand, operand, operand, RegA, RegB, RegC][operand])
        IP += 1

def part_1():
    run(int(Lines[0][12:]))
    return ','.join((str(num) for num in Output))

def part_2(regA=0, posn=0):
    if posn == len(progints): return regA
    for i in range(8):
        run(regA * 8 + i)
        if len(Output) and Output[0] == progints[-(posn + 1)]:
            ret_val = part_2((regA * 8 + i), posn + 1)
            if ret_val: return ret_val

Lines = AoC.Init("data/day17.txt")[0]
progints = [int(val) for val in Lines[4][9:].split(',')]
Prog = [(progints[i], progints[i+1]) for i in range(0, len(progints), 2)]
AoC.verify('2,1,4,0,7,4,0,2,3', 258394985014171)
AoC.run(part_1, part_2)
```

* Interesting AoCFramework : https://github.com/p3rki3/AoC2024/blob/main/AoCFramework.py

* https://www.reddit.com/user/ash30342/
  * https://github.com/ash42/adventofcode/tree/main/adventofcode2024/src/nl/michielgraat/adventofcode2024/day17
  * Solution en Java assez lisible