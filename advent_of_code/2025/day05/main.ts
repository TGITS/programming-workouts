export type Range = [number, number];

export type InputData = {
  ranges: Range[];
  ingredients: number[];
};

export function parseInput(filename: string): InputData {
  const data = Deno.readTextFileSync(filename);
  const lines = data.split("\n").map((line) => line.trim());
  let i = 0;
  const ranges: Range[] = [];
  const ingredients: number[] = [];
  while (lines[i] !== "") {
    const [start, end] = lines[i].split("-");
    ranges.push([parseInt(start), parseInt(end)]);
    i++;
  }

  i++;

  while (i < lines.length && lines[i].length > 0) {
    ingredients.push(parseInt(lines[i]));
    i++;
  }

  return {
    ranges: ranges,
    ingredients: ingredients,
  };
}

export function compareRanges(range1: Range, range2: Range): number {
  if (range1[0] === range2[0]) {
    if (range1[1] === range2[1]) {
      return 0;
    } else if (range1[1] < range2[1]) {
      return -1;
    } else {
      return 1;
    }
  } else if (range1[0] < range2[0]) {
    return -1;
  } else {
    return 1;
  }
}

export function extractSortedRanges(inputData: InputData): Range[] {
  return inputData.ranges.toSorted(compareRanges);
}

export function includes(range: Range, num: number) {
  return num >= range[0] && num <= range[1];
}

export function areOverlapping(r1: Range, r2: Range): boolean {
  if (
    includes(r1, r2[0]) ||
    includes(r1, r2[1]) ||
    includes(r2, r1[0]) ||
    includes(r2, r1[1])
  ) {
    return true;
  }
  return false;
}

export function mergeOverlappingRanges(r1: Range, r2: Range): Range {
  return [Math.min(r1[0], r2[0]), Math.max(r1[1], r2[1])];
}

export function solvePart1(filename: string): number {
  let count = 0;
  const inputData = parseInput(filename);
  for (const ingredient of inputData.ingredients) {
    for (const range of inputData.ranges) {
      if (ingredient >= range[0] && ingredient <= range[1]) {
        count++;
        break;
      }
    }
  }
  return count;
}

export function solvePart2(filename: string): number {
  let count = 0;
  const inputData = parseInput(filename);

  //Sorting the ranges, to facilitate the merge of ranges
  const ranges = extractSortedRanges(inputData);

  //Merging the ranges to avoid duplicate
  let i = 0;
  while (i < ranges.length - 1) {
    const r1 = ranges[i];
    const r2 = ranges[i + 1];
    if (areOverlapping(r1, r2)) {
      const mergedRange = mergeOverlappingRanges(r1, r2);
      ranges.splice(i, 2, mergedRange);
    } else {
      i++;
    }
  }

  //Counting from the merged ranges
  for (const range of ranges) {
    count += range[1] - range[0] + 1;
  }

  return count;
}

if (import.meta.main) {
  console.log(`Solution for part 1: ${solvePart1("./data/input.txt")}`); // 756 for my input data
  console.log(`Solution for part 2: ${solvePart2("./data/input.txt")}`); // 355555479253787 for my input data
}
