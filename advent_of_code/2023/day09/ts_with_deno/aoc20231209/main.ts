export type Sequences = number[][];

export function parseInput(filename: string): Sequences {
  const sequences: Sequences = Deno.readTextFileSync(filename).split("\n").map((
    line,
  ) => line.trim()).filter((line) => line.length > 0).map((line) =>
    line.split(" ").map((num) => parseInt(num.trim()))
  );
  return sequences;
}

export function computeSequencesOfDifferences(sequence: number[]): Sequences {
  const sequencesOfDifferences: Sequences = [];
  let currentSequence = sequence;
  let nextSequence = [];
  while (!currentSequence.every((num) => num === 0)) {
    let i = 0;
    while (i < currentSequence.length - 1) {
      nextSequence.push(currentSequence[i + 1] - currentSequence[i]);
      i++;
    }
    sequencesOfDifferences.push(nextSequence);
    currentSequence = nextSequence;
    nextSequence = [];
  }

  return sequencesOfDifferences;
}

export function computeNextValueForSequence(sequence: number[]): number {
  let nextValueForSequence: number = 0;
  const sequencesOfDifferences = computeSequencesOfDifferences(sequence);
  sequencesOfDifferences.unshift(sequence);
  let i = sequencesOfDifferences.length - 1;
  while( i >= 0) {
    nextValueForSequence += sequencesOfDifferences[i][sequencesOfDifferences[i].length-1];
    i--;
  }
  return nextValueForSequence;
}

export function extrapolatePreviousValueForSequence(sequence: number[]): number {
  let previousValueForSequence: number = 0;
  const sequencesOfDifferences = computeSequencesOfDifferences(sequence);
  sequencesOfDifferences.unshift(sequence);
  let i = sequencesOfDifferences.length - 2;
  while( i >= 0) {
    previousValueForSequence = sequencesOfDifferences[i][0] - previousValueForSequence;
    i--;
  }
  return previousValueForSequence;
}

export function solvePart1(filename: string): number {
  const sequences = parseInput(filename);
  let total = 0;
  for(const sequence of sequences) {
    total += computeNextValueForSequence(sequence);
  }
  return total;
}

export function solvePart2(filename: string): number {
  const sequences = parseInput(filename);
  let total = 0;
  for(const sequence of sequences) {
    total += extrapolatePreviousValueForSequence(sequence);
  }
  return total;
}

if (import.meta.main) {
  console.log(`Solution for part 1: ${solvePart1("./data/input.txt")}`);
  console.log(`Solution for part 2: ${solvePart2("./data/input.txt")}`);
}
