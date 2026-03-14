# Six degrees of Kevin Bacon

## Practical information

To install dependencies:

```bash
bun install
```

Run tests:

```bash
bun test
```

Run locally from `stdin`:

```bash
bun run main.ts < data/input1.txt
```

Run locally from a file path argument:

```bash
bun run main.ts data/input1.txt
```

Run CodinGame-style entrypoint (`readline()` + `console.log`):

```bash
bun run codingame.ts < data/input1.txt
```

Alternative command (PowerShell-safe):

```bash
Get-Content -Raw data/input1.txt | bun run codingame.ts
```

Single-file version for copy/paste into CodinGame editor:

```bash
codingame.single.ts
```

This project was created using `bun init` in bun v1.3.10. [Bun](https://bun.com) is a fast all-in-one JavaScript runtime.

## Project files

- `src/io.ts`: input parsing and file reading helper.
- `src/solver.ts`: graph construction and BFS shortest path to Kevin Bacon.
- `src/app.ts`: application facade (`solveFromRawInput`, `solveFromCodingameLines`).
- `main.ts`: local CLI entrypoint (file argument or `stdin`).
- `codingame.ts`: modular CodinGame-compatible entrypoint using `readline()`.
- `codingame.single.ts`: fully standalone file for CodinGame submission.
- `main.test.ts`: dataset tests + robustness tests.

Notes:
- On CodinGame, `codingame.ts` and `codingame.single.ts` use `readline()`.
- Locally with Bun, they automatically fallback to reading full `stdin`.

## The problem

- CodinGame Puzzle : https://www.codingame.com/ide/puzzle/six-degrees-of-kevin-bacon

Six Degrees of Kevin Bacon is a pop-culture game in which an arbitrarily chosen actor is repeatedly connected to another actor via a movie that both actors have appeared in together, repeating this process to try to find the shortest path that ultimately leads to the prolific American actor Kevin Bacon.

Given an actor_name, an integer n and then that many movie_casts determine the Bacon number of actor_name, i.e. the minimum number of movies needed to link actor_name to Kevin Bacon.

Line 1 : actor_name, the name of the actor whose Bacon number is being calculated
Line 2 : an integer n
Next n lines : a string movie_cast in the format Movie_name: Actor 1, Actor 2, ...

The test data are under ./data

The expected value for each input is as follow :

- the expected value for `./data/input1.txt` is `2`
- the expected value for `./data/input2.txt` is `1`
- the expected value for `./data/input3.txt` is `3`
- the expected value for `./data/input4.txt` is `0`
- the expected value for `./data/input5.txt` is `6`
- the expected value for `./data/input6.txt` is `4`

## How it works

1. Parse the input:
`actor_name`, `n`, then `n` lines of `Movie: Actor 1, Actor 2, ...`.
2. Build an undirected actor graph:
for each movie cast, connect every pair of actors in that cast.
3. Run BFS from `actor_name` to `Kevin Bacon`:
the first time `Kevin Bacon` is reached gives the minimum number of movie links.
4. Return the distance:
`0` if the actor is `Kevin Bacon`; `-1` if no path exists.

Complexity:
- Graph construction: O(sum of k^2) for each movie cast size `k`.
- BFS: O(V + E), where `V` is number of actors and `E` is number of actor-to-actor links.