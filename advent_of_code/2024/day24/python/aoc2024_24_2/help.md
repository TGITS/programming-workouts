# Aides en cas de besoin et autres solutions

* https://www.reddit.com/r/adventofcode/comments/1hl698z/2024_day_24_solutions/

* https://www.reddit.com/user/xHyroM/
  * https://github.com/xhyrom/aoc/blob/main/2024/24/part_1.py
  * https://github.com/xhyrom/aoc/blob/main/2024/24/part_2.py
    * https://www.geeksforgeeks.org/full-adder/
    * https://www.geeksforgeeks.org/carry-look-ahead-adder/
    * https://en.wikipedia.org/wiki/Adder_(electronics)#Full_adder
    * https://en.m.wikipedia.org/wiki/Karnaugh_map

* https://www.reddit.com/user/mebeim/
  * https://en.m.wikipedia.org/wiki/Adder_%28electronics%29
  * solution by hand : 
    * > The correct pattern is simple enough to spot: we are looking for a single initial half adder plus a bunch of full adders chained together (see binary adder wiki page). Output bit zN must be the result of sumN XOR (carryN-1 OR <chain of ANDs>), where sumN is xN XOR yN and carryN is xN AND yN. Once recognized the errors in the graph, I manually marked the bad wires to swap and when I got 4 pairs it was done.
    * https://raw.githubusercontent.com/mebeim/aoc/refs/heads/master/2024/misc/day24/solution.svg

* https://www.reddit.com/user/david3x3x3/
  * Solution by hand using graphviz
    * For part 2, I converted my input file to a .dot file and used Graphviz to view the file. First I looked up a page showing the logic gates in a half adder and full adder so that I knew what each step should look like. When I found differences, I added code to swap the outputs and make a new graph of the file.
    * Script to generate the dotfile : https://topaz.github.io/paste/#XQAAAQBjAgAAAAAAAAA0m0pnuFI8c/fBNAqHEWM96O8NHmRW+5JINCUniNMc0G3NQLSo6r5hG630JeE0S2tX5u6GFN66dXckUfwBo7103M0xDE0l0CCU1SVOgNr9eHVqQgbP0Qr0lDInmEqrCvBYMvHuF665CN/GcuEHbIOHTTjE2tNkSJ0IWMiPk83qpTiANgYKXsuaj+dnrNg+s//1w8dHMf75efBmLaZ/fQ/OJktxLX1ZX7EvcY5niWd7jP4WiQOk2+9T0wTlXS8smrXnbVgrRiXs1+//EXVqlyhwq11oDdHBGR4R/UVlKhVjJ1/w1d73ZK9RKAj+jeO3Qq2xdakKRfXxyJFrPpAU5QDZ3a73VcwlUhZOTWvZdaOF9Q07zp9XSUqPrAo3grZFKNmz1rqWjwi2+xm55L/gCH1eoheXJ6YXaYk3xFgxyLz3+5BpOg==

* https://www.reddit.com/user/johnpeters42/
  * >  Not fully automated, but arranges the rules to the point that you can manually look at the output and spot the mismatches. If a Z value is swapped then it's obvious, otherwise you need to inspect how the intermediate registers are supposed to fit into the add-and-carry pattern.
  * https://pastebin.com/DMg1kQBY

* https://www.reddit.com/user/DeadlyRedCube/


> For part 1 I considered for a moment how to sort the operations topologically, but then just decided "what the sleigh, I'm going to just do it the dumb way" so (inside of an outer loop) it loops through every instruction until it finds the first one that it can execute (both names exist in the map of values), executes it, then removes it from the list. It just keeps doing that until the list is empty, at which point it finds the z bits in the values map and shifts them into the right place.

> Part 2, I first had to work out how an adder works (been a long time since I've had to know that). Once I figured out the 5 instructions that have to run per digit (excluding the simpler first digit), I separated the instruction list into those 5 categories:
  * > xn ^ yn -> XYADD[n] (The addition of the two digits)
  * > xn & yn -> XYCARRY[n] (Carry the 1 from that addition)
  * > XYADD[n] ^ CARRY[n-1] -> Zn (Add in the previous digit's carry digit)
  * > XYADD[n] & CARRY[n-1] -> ANDS[n] (Figure out if that addition also carried)
  * > XYCARRY[n] | ANDS[n] -> CARRY[n] (Combine the two carry values into the final carry value to be used for the next digit)
> I decided to start with some simple tests, which turned out to be sufficient for my input (which is to say, this is not a general solution):
  * > If any or or and operation outputs to a 'z' register, it's wrong (excluding one or which is allowed to export to the final Zn which is the carry from the last x/y bit addition)
  * > If an XYADD[n] register is a 'z' register (excluding x00 ^ y00) it's wrong
  * > If an XYADD[n] register does not show up in any of the xor instructions that should be outputting to Zn, it's wrong
  * > If an XYCARRY[n] register doesn't show up in any "or" instructions (the ones computing the CARRY[n] values), it's wrong
  * > If the result of the "ZOut" instructions isn't a 'z' register, it's wrong
> These tests (surprisingly) worked to find all 8 incorrect registers in my puzzle input, so thankfully I didn't have to do any deeper analysis (which I probably would have done by hand out). Instead, I could just sort them, join them with a comma, and go on my merry way!

* https://www.reddit.com/user/GreninjaNerfer/
  * https://topaz.github.io/paste/#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

> part 2: this is a ripple carry adder! lots of the output wires correspond to the internal configuration of a single block in a 44-block ripple-carry adder, and you can uniquely identify them based on the operand (^, &, |) and inputs.
> what i did was make a function to iterate from lower-indexed blocks to higher-indexed blocks -- in a ripple-carry adder, since the computation "flows" from LSB to MSB, higher-indexed blocks can only be correct if lower-indexed blocks are. the only confirmation you get that you are doing fine is when you check a certain gate outputs the `z` wires -- if that one is correct, then all the wires it depends on must also be correct, you commit the relevant wires from the previous block to a `correct` set, and you keep going. if not, you return the last block that outputted the correct thing as well as this `correct` set.
> run this function once to see the base correctness. then, for all pairs of gates in the circuit, swap their output wires only if they are not in the `correct` set. run the function to see if we did better and got to a later block -- if we did, we know this swap must be correct and we can proceed with it. do this 4 times and we get a fully functional ripple-carry adder!

* https://www.reddit.com/user/morgoth1145/

* https://www.reddit.com/user/Cyphase/