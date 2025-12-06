export type ProblemPart1 = {
  numbers: number[];
  operation: string;
};
export type ProblemPart2 = {
  numbers: number[];
  rawData: string[];
  operation: string;
};

export function parseInputPart1(filename: string): ProblemPart1[] {
  const problems: ProblemPart1[] = [];
  const data: string[][] = Deno.readTextFileSync(filename).split("\n").map((
    line,
  ) => line.trim()).filter((line) => line.length > 0).map((line) =>
    line.split(/\s+/).map((element) => element.trim())
  );
  const operations = data.pop() || [];
  for (const operation of operations) {
    problems.push({ numbers: [], operation: operation });
  }
  for (let i = 0; i < data.length; i++) {
    for (let j = 0; j < data[i].length; j++) {
      problems[j].numbers[i] = parseInt(data[i][j]);
    }
  }

  return problems;
}

export function parseInputPart2(filename: string): ProblemPart2[] {
  const problems: ProblemPart2[] = [];
  const lines: string[] = Deno.readTextFileSync(filename).split("\n").filter((
    line,
  ) => line.trim().length > 0);

  const operationsLine = lines.pop() || ""; //the line with the operators

  let i = 0;
  while (i < operationsLine.length) {
    if ((operationsLine[i] === "*") || (operationsLine[i] === "+")) {
      const currentIndex = i;
      i++;
      while ((operationsLine[i] === " ") && (i <= operationsLine.length)) {
        i++;
      }
      const rawData = [];
      for (const line of lines) {
        if (i === operationsLine.length) {
          rawData.push(line.substring(currentIndex));
          i++;
        } else {
          rawData.push(line.substring(currentIndex, i - 1));
        }
      }
      problems.push({
        numbers: [],
        rawData: rawData,
        operation: operationsLine[currentIndex],
      });
    }
  }

  for (const problem of problems) {
    const max = problem.rawData[0].length;
    const nbLines = problem.rawData.length;
    const numbers = [];
    for (let j = max - 1; j >= 0; j--) {
      let number = "";
      for (let i = 0; i < nbLines; i++) {
        number = number + problem.rawData[i].charAt(j);
      }
      numbers.push(parseInt(number.trim()));
    }
    problem.numbers = numbers;
  }
  return problems;
}

export function solvePart1(filename: string): number {
  const problems: ProblemPart1[] = parseInputPart1(filename);
  return problems.map((problem) => {
    if (problem.operation === "+") {
      return problem.numbers.reduce(
        (previous, current) => previous + current,
        0,
      );
    } else {
      return problem.numbers.reduce(
        (previous, current) => previous * current,
        1,
      );
    }
  }).reduce((previous, current) => previous + current, 0);
}

export function solvePart2(filename: string): number {
  const problems = parseInputPart2(filename);
  return problems.map((problem) => {
    if (problem.operation === "+") {
      return problem.numbers.reduce(
        (previous, current) => previous + current,
        0,
      );
    } else {
      return problem.numbers.reduce(
        (previous, current) => previous * current,
        1,
      );
    }
  }).reduce((previous, current) => previous + current, 0);
}

if (import.meta.main) {
  console.log(`Solution for part 1: ${solvePart1("./data/input.txt")}`); // 5361735137219 on my input data
  console.log(`Solution for part 2: ${solvePart2("./data/input.txt")}`); // 11744693538946 on my input data
}
