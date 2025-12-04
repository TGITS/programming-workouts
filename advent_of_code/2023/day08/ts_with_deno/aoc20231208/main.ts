import {lcm} from "npm:mathjs";

export type Children = {
  R: string;
  L: string;
}

export type Input = {
  instructions: string[];
  nodes: Map<string, Children>;
}

export function parseInput(filename: string): Input {
  const input: Input = {
    instructions: [],
    nodes: new Map<string, Children>(),
  };
  const data = Deno.readTextFileSync(filename).split("\n").map((line) => line.trim()).filter((line) =>
    line.length > 0
  );
  input.instructions = data[0].trim().split("");
  for (let i = 1; i < data.length; i++) {
    const [node, children] = data[i].substring(0, data[i].length - 1).split(" = (");
    const [left, right] = children.split(", ");
    input.nodes.set(node, {R: right, L: left})
  }
  return input;
}

export function solvePart1(filename:string) : number {
  let steps = 0;
  let currentNode = "AAA";
  let currentInstructionIndex = 0;
  const input = parseInput(filename);

  while (currentNode !== "ZZZ") {
    const currentInstruction = input.instructions[currentInstructionIndex%input.instructions.length]
    const currentChildren = input.nodes.get(currentNode);
    if(currentChildren) {
      if (currentInstruction === "L") {
          currentNode = currentChildren.L;
      } else {
        currentNode = currentChildren.R;
      }
    }
    currentInstructionIndex += 1;
    steps += 1;
  }
  return steps;
}

function findPeriods(filename:string):number[] {
  const periods = new Map<number,number>();
  let steps = 0;
  const input = parseInput(filename);
  const currentNodes = Array.from(input.nodes.keys()).filter((node) => node.endsWith("A"));
  let currentInstructionIndex = 0;

  while (periods.size < currentNodes.length) {
    const currentInstruction = input.instructions[currentInstructionIndex%input.instructions.length]
    currentNodes.forEach( (currentNode, index) => {
      const currentChildren = input.nodes.get(currentNode);
      if(currentChildren) {
        if (currentInstruction === "L") {
          currentNodes[index] = currentChildren.L;
        } else {
          currentNodes[index] = currentChildren.R;
        }
        if (currentNodes[index].endsWith("Z") && !periods.has(index)) {
          periods.set(index, steps + 1);
        }
      }
    });
    currentInstructionIndex += 1;
    steps += 1;
  }
  return [...periods.values()]
}

export function solvePart2(filename:string) : number {
  return findPeriods(filename).reduce((accumulator, currentValue) => lcm(accumulator, currentValue));
}

// Learn more at https://docs.deno.com/runtime/manual/examples/module_metadata#concepts
if (import.meta.main) {
  console.log(`Solution for part1: ${solvePart1("./data/input.txt")}`); // 19631 on my input data
  console.log(`Solution for part2: ${solvePart2("./data/input.txt")}`); // 21003205388413 on my input data
}
