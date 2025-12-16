import { assertEquals } from "@std/assert";
import {
  parseInputPart1,
  parseInputPart2,
  ProblemPart1,
  ProblemPart2,
  solvePart1,
  solvePart2,
} from "./main.ts";

Deno.test("Testing the parsing function on the test input data for part1", function parseInputPart1Test() {
  const expected: ProblemPart1[] = [
    { numbers: [123, 45, 6], operation: "*" },
    { numbers: [328, 64, 98], operation: "+" },
    { numbers: [51, 387, 215], operation: "*" },
    { numbers: [64, 23, 314], operation: "+" },
  ];
  const actual: ProblemPart1[] = parseInputPart1("./data/input_test.txt");
  assertEquals(actual, expected);
});

Deno.test("Solving part 1 on the test input data", function solvePart1Test() {
  const expected = 4277556;
  const actual = solvePart1("./data/input_test.txt");
  assertEquals(actual, expected);
});

Deno.test("Testing the parsing function on the test input data for part2", function parseInputPart2Test() {
  const expected: ProblemPart2[] = [
    { numbers: [356, 24, 1], rawData: ["123", " 45", "  6"], operation: "*" },
    { numbers: [8, 248, 369], rawData: ["328", "64 ", "98 "], operation: "+" },
    { numbers: [175, 581, 32], rawData: [" 51", "387", "215"], operation: "*" },
    { numbers: [4, 431, 623], rawData: ["64 ", "23 ", "314"], operation: "+" },
  ];
  const actual: ProblemPart1[] = parseInputPart2("./data/input_test.txt");
  assertEquals(actual, expected);
});

Deno.test("Solving part 2 on the test input data", function solvePart1Test() {
  const expected = 3263827;
  const actual = solvePart2("./data/input_test.txt");
  assertEquals(actual, expected);
});
