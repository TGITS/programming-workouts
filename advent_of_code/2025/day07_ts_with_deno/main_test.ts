import { assertEquals } from "@std/assert";
import {
  Coordinate,
  displayGrid,
  findStart,
  Grid,
  parseInput,
  solvePart1,
  solvePart2,
} from "./main.ts";

Deno.test("Testing the parsing function on the test input data", function parseInputTest() {
  const actual: Grid = parseInput("./data/input_test.txt");
  assertEquals(actual.length, 16);
  assertEquals(actual[0].length, 15);
  displayGrid(actual);
});

Deno.test("Testing the function to find the start position", function findStartTest() {
  const expected: Coordinate = { i: 0, j: 7 };
  assertEquals(findStart(parseInput("./data/input_test.txt")), expected);
});

Deno.test("Solving part 1 on the test input data", function solvePart1Test() {
  const expected = 21;
  const actual = solvePart1("./data/input_test.txt");
  assertEquals(actual, expected);
});

Deno.test("Solving part 2 on the test input data", function solvePart1Test() {
  const expected = 40;
  const actual = solvePart2("./data/input_test.txt");
  assertEquals(actual, expected);
});
