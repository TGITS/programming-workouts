export type Rotation = [string, number];
export type Answer = [number, number];

export function parseInput(filename: string): Rotation[] {
  const rotations: Rotation[] = [];
  const data = Deno.readTextFileSync(filename);
  const lines = data.split("\n").map((line) => line.trim()).filter((line) =>
    line.length > 0
  );
  lines.forEach((element) => {
    const direction = element.charAt(0);
    const distance = parseInt(element.slice(1));
    rotations.push([direction, distance]);
  });
  return rotations;
}

export function computePositionFromRotation(
  startPosition: number,
  rotation: Rotation,
): number {
  const [direction, distance] = rotation;
  let newPosition: number;

  if (direction === "L") {
    newPosition = startPosition - distance;
    while (newPosition < 0) {
      newPosition = newPosition + 100;
    }
  } else if (direction === "R") {
    newPosition = startPosition + distance;

    while (newPosition > 99) {
      newPosition = newPosition - 100;
    }
  } else {
    throw new Error(`Invalid direction: ${direction}`);
  }

  return newPosition;
}

export function computePositionAndZeroCrossingsFromRotation(
  startPosition: number,
  rotation: Rotation,
): Answer {
  const [direction, distance] = rotation;
  let newPosition: number;
  let countZeroCrossings = 0;
  const rotationsToDo = Math.floor(distance / 100);
  const remainder = distance % 100;
  if (direction === "L") {
    if (remainder > startPosition && startPosition !== 0) {
      newPosition = 100 + startPosition - remainder;
      countZeroCrossings = rotationsToDo + 1;
    } else if (remainder > startPosition && startPosition === 0) {
      newPosition = 100 + startPosition - remainder;
      countZeroCrossings = rotationsToDo;
    } else {
      newPosition = startPosition - remainder;
      countZeroCrossings = rotationsToDo;
    }
    if (newPosition === 0) {
      countZeroCrossings += 1;
    }
  } else if (direction === "R") {
    newPosition = startPosition + remainder;
    countZeroCrossings = rotationsToDo;

    if (newPosition === 0) {
      countZeroCrossings += 1;
    }

    if (newPosition > 99) {
      countZeroCrossings += 1;
      newPosition = newPosition - 100;
    }
  } else {
    throw new Error(`Invalid direction: ${direction}`);
  }

  return [newPosition, countZeroCrossings];
}

export function solvePart1(filename: string): number {
  let startPosition = 50;
  let countZeros = 0;
  const rotations = parseInput(filename);
  rotations.forEach((rotation) => {
    startPosition = computePositionFromRotation(startPosition, rotation);
    if (startPosition === 0) {
      countZeros += 1;
    }
  });
  return countZeros;
}

export function solvePart2(filename: string): number {
  let startPosition = 50;
  let zeroCrossings: number = 0;
  const rotations = parseInput(filename);
  rotations.forEach((rotation) => {
    let crossings: number;
    [startPosition, crossings] = computePositionAndZeroCrossingsFromRotation(
      startPosition,
      rotation,
    );
    zeroCrossings += crossings;
  });
  return zeroCrossings;
}

// Learn more at https://docs.deno.com/runtime/manual/examples/module_metadata#concepts
if (import.meta.main) {
  const resultPart1 = solvePart1("./data/input.txt");
  console.log(
    "The computed value for the password for part 1 is",
    resultPart1,
  ); //1081 for my input data
  const resultPart2 = solvePart2("./data/input.txt");
  console.log(
    "The computed value for the password for part 2 is",
    resultPart2,
  );//6689 for my input data
}
