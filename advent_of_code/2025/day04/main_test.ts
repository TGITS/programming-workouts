import { assertEquals } from "@std/assert";
import { countNeighbors, parseInput, solvePart1, solvePart2 } from "./main.ts";

Deno.test(function parseInputTest() {
  const expectedFirstLine = [
    0,
    0,
    1,
    1,
    0,
    1,
    1,
    1,
    1,
    0,
  ];
  const grid = parseInput("./data/input_test.txt");
  assertEquals(grid[0], expectedFirstLine);
});

Deno.test(function countNeighborsTest() {
  const grid = parseInput("./data/input_test.txt");
  let count = countNeighbors(grid, 2, 0);
  assertEquals(count, 3);
  count = countNeighbors(grid, 0, 1);
  assertEquals(count, 3);
  count = countNeighbors(grid, 0, 4);
  assertEquals(count, 3);
});

Deno.test(function solvePart1Test() {
  const result = solvePart1("./data/input_test.txt");
  assertEquals(result, 13);
});

Deno.test(function solvePart2Test() {
  const result = solvePart2("./data/input_test.txt");
  assertEquals(result, 43);
});
