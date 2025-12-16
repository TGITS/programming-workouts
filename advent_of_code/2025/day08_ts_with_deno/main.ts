export type Point = { x:number, y: number, z:number}
export type InputData = Point[];

export function parseInput(filename: string): InputData {
  const data = Deno.readTextFileSync(filename);
  const points:InputData = data.split("\n").map((line) => line.trim()).filter((line) =>
    line.length > 0
  ).map((line) =>
    line.split(",").map((element) => parseInt(element.trim()))
  ).map((numbers) => {
    return {x:numbers[0], y:numbers[1], z:numbers[2]};
  });
  return points;
}

export function euclideanDistance(p:Point, q:Point): number {
  return Math.sqrt((p.x-q.x)**2 + (p.y-q.y)**2 + (p.z-q.z)**2);
}

export function solvePart1(filename: string): number {
  const inputData = parseInput(filename);
  return 0;
}

export function solvePart2(filename: string): number {
  const inputData = parseInput(filename);
  return 0;
}

if (import.meta.main) {
  console.log(`Solution for part 1: ${solvePart1("./data/input.txt")}`);
  console.log(`Solution for part 2: ${solvePart2("./data/input.txt")}`);
}
