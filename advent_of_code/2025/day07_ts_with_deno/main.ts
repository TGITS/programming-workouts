export type Grid = string[][];
// i =>"y", line number, j => "x", column number
export type Coordinate = {
  i: number;
  j: number;
};

export function parseInput(filename: string): Grid {
  const data = Deno.readTextFileSync(filename);
  const grid: Grid = data.split("\n").map((line) => line.trim()).filter((
    line,
  ) => line.length > 0).map((line) => line.split(""));

  return grid;
}

export function displayGrid(grid: Grid) {
  console.table(grid);
}

export function findStart(grid: Grid): Coordinate {
  const coordinate: Coordinate = { i: 0, j: 0 };
  for (let i = 0; i < grid.length; i++) {
    for (let j = 0; j < grid[i].length; j++) {
      if (grid[i][j] === "S") {
        return { i: i, j: j };
      }
    }
  }
  return coordinate;
}

// The process continues until all of the tachyon beams reach a splitter or exit the manifold.
// What if a tachyon beams exit on the right or left edges ?
// Beware: the question How many times will the beam be split
export function solvePart1(filename: string): number {
  const grid = parseInput(filename);
  let currentBeams: Set<Coordinate> = new Set<Coordinate>();
  const start = findStart(grid);
  currentBeams.add({ i: start.i + 1, j: start.j });
  let currentLineIndex = start.i + 2;
  let countSplits = 0;
  while (currentLineIndex < grid.length) {
    const nextJs = new Set<number>();
    const nextI = currentLineIndex;
    currentBeams.forEach((v, _k, _currentBeams) => {
      const nextJ = v.j;
      if (grid[nextI][nextJ] === "^") {
        countSplits++;
        if (nextJ - 1 >= 0) {
          nextJs.add(nextJ - 1);
        }
        if (nextJ + 1 < grid.length) {
          nextJs.add(nextJ + 1);
        }
      } else {
        nextJs.add(nextJ);
      }
    });
    currentBeams = new Set<Coordinate>();
    nextJs.forEach((v, _k, _nextJs) => {
      currentBeams.add({ i: currentLineIndex, j: v });
    });
    currentLineIndex++;
  }
  return countSplits;
}

export function solvePart2(filename: string): number {
  const grid = parseInput(filename);
  let currentBeams: Map<number, number> = new Map<number, number>();
  let nextBeams: Map<number, number> = new Map<number, number>();
  const start = findStart(grid);
  currentBeams.set(start.j, 1);
  let currentLineIndex = 1;
  while (currentLineIndex < grid.length - 1) {
    currentBeams.forEach((v, k, _current) => {
      if (grid[currentLineIndex][k] === "^") {
        nextBeams.set(k - 1, (nextBeams.get(k - 1) ?? 0) + v);
        nextBeams.set(k + 1, (nextBeams.get(k + 1) ?? 0) + v);
      } else {
        nextBeams.set(k, (nextBeams.get(k) ?? 0) + v);
      }
    });
    currentBeams = nextBeams;
    nextBeams = new Map<number, number>();
    currentLineIndex++;
  }
  return Array.from(currentBeams.values()).reduce(
    (previous, current) => previous + current,
    0,
  );
}

if (import.meta.main) {
  console.log(`Solution for part 1: ${solvePart1("./data/input.txt")}`); // 1640 for my input data
  console.log(`Solution for part 2: ${solvePart2("./data/input.txt")}`); // 40999072541589 for my input data
}
