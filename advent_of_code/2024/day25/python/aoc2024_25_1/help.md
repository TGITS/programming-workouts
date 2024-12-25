# Ressources pour le jour 25

* https://www.reddit.com/r/adventofcode/comments/1hlu4ht/2024_day_25_solutions/
* https://www.reddit.com/user/ExternalAware9257/
  * Une solution compacte intéressante en Python

```python
locks = []
keys = []

def fits(key, lock):
    return all(a + b <= 5 for a, b in zip(key, lock))

for schematic in open(0).read().strip().split("\n\n"):
    columns = list(zip(*schematic.split("\n")))
    if schematic[0] == "#":
        locks.append([column.count("#") - 1 for column in columns])
    else:
        keys.append([column.count("#") - 1 for column in columns])

print(sum(fits(key, lock) for key in keys for lock in locks))
```

## Some reactions

* https://www.reddit.com/r/adventofcode/comments/1hlkpw4/thank_you_eric_the_team_for_helping_me_learn_so/
* https://www.reddit.com/r/adventofcode/comments/1hlju98/how_did_you_all_get_so_smart/
  * https://xkcd.com/1053/
  * https://x.com/xsphi/status/1779701103099318386

> First, don't discount brute force. I love rule 3 of Rob Pike's 5 rules of programming (inventor of Go):
> Fancy algorithms are slow when n is small, and n is usually small. Fancy algorithms have big constants.
> Try "the simplest thing that could possibly work" first, and only get clever if the simple thing doesn't work, or is too slow.
> Second, focus on data structures before algorithms. Figuring out how too transform the problem you have into a useful data structure is key to a good programming mindset. Once you've figured out how to get the data you have into, say, a graph then you can search the web for "fully-connected graph component algorithm" and see what you can do with the data structure. You don't actually need to have every possible graph algorithm in your head.

Solving puzzles like this is a pretty niche skill set. It’s not the kind of thing you’d do in and out at a typical dev or IT job. That said, solving these puzzles can make you better at those kinds of jobs.

Ultimately, it just comes with practice. Even within this puzzle space, every place you interact with has its own unique flavor of puzzle, so there’s a learning curve with all of these. I’ve solved about 160 or so Project Euler problems (including #202), but AOC was the first of this kind of thing I’ve done that has a timed element to it, and getting fast was rough at first. But I’m happy to state that I’m currently #2 in my workplace’s private leaderboard and I cracked the Top 500 for the first time with today’s Part 2!

Just make it a habit. And also make a habit of reading up on the various methods others have used. There’s always more than one way to skin a cat. You’ll get faster before you know it.


## Rob Pike's 5 Rules of Programming

* https://users.ece.utexas.edu/~adnan/pike.html

Rule 1. You can't tell where a program is going to spend its time. Bottlenecks occur in surprising places, so don't try to second guess and put in a speed hack until you've proven that's where the bottleneck is.

Rule 2. Measure. Don't tune for speed until you've measured, and even then don't unless one part of the code overwhelms the rest.

Rule 3. Fancy algorithms are slow when n is small, and n is usually small. Fancy algorithms have big constants. Until you know that n is frequently going to be big, don't get fancy. (Even if n does get big, use Rule 2 first.)

Rule 4. Fancy algorithms are buggier than simple ones, and they're much harder to implement. Use simple algorithms as well as simple data structures.

Rule 5. Data dominates. If you've chosen the right data structures and organized things well, the algorithms will almost always be self-evident. Data structures, not algorithms, are central to programming.

Pike's rules 1 and 2 restate Tony Hoare's famous maxim "Premature optimization is the root of all evil." Ken Thompson rephrased Pike's rules 3 and 4 as "When in doubt, use brute force.". Rules 3 and 4 are instances of the design philosophy KISS. Rule 5 was previously stated by Fred Brooks in The Mythical Man-Month. Rule 5 is often shortened to "write stupid code that uses smart objects".

* [Programming Live with Larry](https://www.youtube.com/@Algorithmist)
  * Advent of Code and Leetcode problem