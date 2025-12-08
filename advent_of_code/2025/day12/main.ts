export type InputData = string;

export function parseInput(filename: string): InputData {
  const data = Deno.readTextFileSync(filename);
  // const grid = data.split("\n").map((line) => line.trim()).filter((line) =>
  //   line.length > 0
  // ).map((line) =>
  //   line.split("").map((element) => {
  //     if (element === "@") return 1;
  //     else return 0;
  //   })
  // );
  return data;
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
