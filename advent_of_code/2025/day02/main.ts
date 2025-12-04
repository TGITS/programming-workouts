export type Range = [string, string];

export function parseInput(filename: string): Range[] {
  const ranges: Range[] = [];
  const data = Deno.readTextFileSync(filename);
  const couples: string[] = data.split("\n").map((line) => line.trim())[0]
    .split(",");
  couples.forEach((couple) => {
    const [start, end] = couple.split("-");
    ranges.push([start, end]);
  });
  return ranges;
}

export function isInvalidIdForPart1(id: string): boolean {
  if (id.length % 2 !== 0) {
    return false;
  }
  return id.substring(0, id.length / 2) === id.substring(id.length / 2);
}

export function isInvalidIdForPart2(id: string): boolean {
  const pattern = /^(\d+)\1{1,}$/;
  return pattern.test(id);
}

export function findInvalidIdsForPart1(ranges: Range[]): number[] {
  const invalidIds: number[] = [];
  for (const [start, end] of ranges) {
    for (let id = BigInt(start); id <= BigInt(end); id++) {
      if (isInvalidIdForPart1(id.toString())) {
        invalidIds.push(Number(id));
      }
    }
  }
  return invalidIds;
}

export function findInvalidIdsForPart2(ranges: Range[]): number[] {
  const invalidIds: number[] = [];
  for (const [start, end] of ranges) {
    for (let id = BigInt(start); id <= BigInt(end); id++) {
      if (isInvalidIdForPart2(id.toString())) {
        invalidIds.push(Number(id));
      }
    }
  }
  return invalidIds;
}

export function solvePart1(filename: string): number {
  const ranges = parseInput(filename);
  const invalidIds = findInvalidIdsForPart1(ranges);
  return invalidIds.reduce((a, b) => a + b, 0);
}

export function solvePart2(filename: string): number {
  const ranges = parseInput(filename);
  const invalidIds = findInvalidIdsForPart2(ranges);
  return invalidIds.reduce((a, b) => a + b, 0);
}

// Learn more at https://docs.deno.com/runtime/manual/examples/module_metadata#concepts
if (import.meta.main) {
  console.log("Solution fort AoC Day 2 Part1:", solvePart1("data/input.txt")); // 20223751480 sur mes données d'entrée
  console.log("Solution fort AoC Day 2 Part2:", solvePart2("data/input.txt")); // 30260171216 sur mes données d'entrée
}
