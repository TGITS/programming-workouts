import { assertEquals } from "@std/assert";
import { InputData, parseInput, solvePart1, solvePart2 } from "./main.ts";

Deno.test("Testing the parsing function on the test input data", function parseInputTest() {
  const expected: InputData = "";
  const actual: InputData = parseInput("./data/input_test.txt");
  assertEquals(actual, expected);
});

Deno.test("Solving part 1 on the test input data", function solvePart1Test() {
  const expected = 0;
  const actual = solvePart1("./data/input_test.txt");
  assertEquals(actual, expected);
});

Deno.test("Solving part 2 on the test input data", function solvePart1Test() {
  const expected = 0;
  const actual = solvePart2("./data/input_test.txt");
  assertEquals(actual, expected);
});
