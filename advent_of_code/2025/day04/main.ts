export function parseInput(filename: string): number[][] {
  const data = Deno.readTextFileSync(filename);
  const grid = data.split("\n").map((line) => line.trim()).filter((line) =>
    line.length > 0
  ).map((line) =>
    line.split("").map((element) => {
      if (element === "@") return 1;
      else return 0;
    })
  );

  return grid;
}

export function countNeighbors(grid: number[][], x: number, y: number): number {
  const directions = [
    [-1, -1],
    [-1, 0],
    [-1, 1],
    [0, -1],
    [0, 1],
    [1, -1],
    [1, 0],
    [1, 1],
  ];
  let count = 0;

  for (const [dx, dy] of directions) {
    const newX = x + dx;
    const newY = y + dy;
    if (
      newX >= 0 && newX < grid.length &&
      newY >= 0 && newY < grid[0].length
    ) {
      count += grid[newY][newX];
    }
  }

  return count;
}

export function solvePart1(filename: string): number {
  const grid = parseInput(filename);
  let accessibleRolls = 0;
  for (let y = 0; y < grid.length; y++) {
    for (let x = 0; x < grid[0].length; x++) {
      if (grid[y][x] === 1) { // Roll is present
        const neighbors = countNeighbors(grid, x, y);
        if (neighbors < 4) {
          accessibleRolls += 1;
        }
      }
    }
  }
  return accessibleRolls;
}

export function solvePart2(filename: string): number {
  const grid = parseInput(filename);
  let rollsToRemove: number[][] = [];
  let accessibleRolls = 0;
  while (true) {
    for (let y = 0; y < grid.length; y++) {
      for (let x = 0; x < grid[0].length; x++) {
        if (grid[y][x] === 1) { // Roll is present
          const neighbors = countNeighbors(grid, x, y);
          if (neighbors < 4) {
            accessibleRolls += 1;
            rollsToRemove.push([x, y]);
          }
        }
      }
    }
    if (rollsToRemove.length === 0) {
      break;
    }

    for (const [x, y] of rollsToRemove) {
      grid[y][x] = 0; // Remove the roll
    }
    rollsToRemove = [];
  }
  return accessibleRolls;
}

// Learn more at https://docs.deno.com/runtime/manual/examples/module_metadata#concepts
if (import.meta.main) {
  console.log(`Solution for part 1: ${solvePart1("./data/input.txt")}`); // 1478
  console.log(`Solution for part 2: ${solvePart2("./data/input.txt")}`); // 9120
}
