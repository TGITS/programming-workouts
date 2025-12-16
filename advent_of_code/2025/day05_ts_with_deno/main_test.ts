import { assertEquals } from "@std/assert";
import {
  areOverlapping,
  compareRanges,
  extractSortedRanges,
  includes,
  InputData,
  mergeOverlappingRanges,
  parseInput,
  Range,
  solvePart1,
  solvePart2,
} from "./main.ts";

Deno.test("Testing the parsing function on the test input data", function parseInputTest() {
  const expected: InputData = {
    ranges: [[3, 5], [10, 14], [16, 20], [12, 18]],
    ingredients: [1, 5, 8, 11, 17, 32],
  };
  const actual: InputData = parseInput("./data/input_test.txt");
  assertEquals(actual, expected);
});

Deno.test("Comparing ranges", function compareRangesTest() {
  const range1: Range = [3, 5];
  const range2: Range = [10, 14];
  const range3: Range = [16, 20];
  const range4: Range = [12, 18];
  const range5: Range = [12, 18];
  const range6: Range = [12, 17];
  assertEquals(compareRanges(range1, range2), -1);
  assertEquals(compareRanges(range2, range3), -1);
  assertEquals(compareRanges(range3, range4), 1);
  assertEquals(compareRanges(range4, range5), 0);
  assertEquals(compareRanges(range4, range6), 1);
});

Deno.test("Extracted the sorted ranges from the input data", function extractSortedRangesTest() {
  const inputData: InputData = {
    ranges: [[3, 5], [10, 14], [16, 20], [12, 18]],
    ingredients: [1, 5, 8, 11, 17, 32],
  };
  const expected = [[3, 5], [10, 14], [12, 18], [16, 20]];
  const actual = extractSortedRanges(inputData);
  assertEquals(actual, expected);
});

Deno.test("Testing if a number is included in a range", function includesTest() {
  const range: Range = [7, 59];
  assertEquals(includes(range, 1), false);
  assertEquals(includes(range, 6), false);
  assertEquals(includes(range, 60), false);
  assertEquals(includes(range, 1045), false);
  assertEquals(includes(range, 7), true);
  assertEquals(includes(range, 59), true);
  assertEquals(includes(range, 8), true);
  assertEquals(includes(range, 58), true);
  assertEquals(includes(range, 20), true);
});

Deno.test("Testing if two ranges are overlapping", function areOverlappingTest() {
  const range1: Range = [3, 5];
  const range2: Range = [10, 14];
  const range3: Range = [16, 20];
  const range4: Range = [12, 18];
  const range5: Range = [19, 34];
  const range6: Range = [12, 17];
  const range7: Range = [14, 20];
  assertEquals(areOverlapping(range1, range2), false);
  assertEquals(areOverlapping(range2, range3), false);
  assertEquals(areOverlapping(range2, range4), true);
  assertEquals(areOverlapping(range3, range4), true);
  assertEquals(areOverlapping(range4, range5), false);
  assertEquals(areOverlapping(range4, range6), true);
  assertEquals(areOverlapping(range2, range7), true);
});

Deno.test("Testing the merge of to overlapping ranges", function mergeOverlappingRangesTest() {
  assertEquals(mergeOverlappingRanges([10, 14], [12, 18]), [10, 18]);
  assertEquals(mergeOverlappingRanges([16, 20], [12, 18]), [12, 20]);
  assertEquals(mergeOverlappingRanges([16, 20], [12, 17]), [12, 20]);
  assertEquals(mergeOverlappingRanges([14, 20], [12, 17]), [12, 20]);
});

Deno.test("Solving part 1 on the test input data", function solvePart1Test() {
  const expected = 3;
  const actual = solvePart1("./data/input_test.txt");
  assertEquals(actual, expected);
});

Deno.test("Solving part 2 on the test input data", function solvePart1Test() {
  const expected = 14;
  const actual = solvePart2("./data/input_test.txt");
  assertEquals(actual, expected);
});
