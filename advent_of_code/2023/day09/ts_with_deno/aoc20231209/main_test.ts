import { assertEquals } from "@std/assert";
import {
  computeSequencesOfDifferences,
  computeNextValueForSequence,
  extrapolatePreviousValueForSequence,
  parseInput,
  Sequences,
  solvePart1,
  solvePart2,
} from "./main.ts";

Deno.test("Testing the parsing function on the test input data", function parseInputTest() {
  const expected: Sequences = [
    [0, 3, 6, 9, 12, 15],
    [1, 3, 6, 10, 15, 21],
    [10, 13, 16, 21, 30, 45],
  ];
  const actual: Sequences = parseInput("./data/input_test.txt");
  assertEquals(actual, expected);
});

Deno.test("Testing the generation of sequences of differences for a sequence", function computeSequencesOfDifferencesTest() {
  const sequence1 = [0, 3, 6, 9, 12, 15];
  const expectedSequence1 = [
    [3, 3, 3, 3, 3],
    [0, 0, 0, 0],
  ];
  const sequence2 = [1, 3, 6, 10, 15, 21];
  const expectedSequence2 = [
    [2, 3, 4, 5, 6],
    [1, 1, 1, 1],
    [0, 0, 0],
  ];
  const sequence3 = [10, 13, 16, 21, 30, 45];
  const expectedSequence3 = [
    [3, 3, 5, 9, 15],
    [0, 2, 4, 6],
    [2, 2, 2],
    [0, 0],
  ];
  assertEquals(computeSequencesOfDifferences(sequence1), expectedSequence1);
  assertEquals(computeSequencesOfDifferences(sequence2), expectedSequence2);
  assertEquals(computeSequencesOfDifferences(sequence3), expectedSequence3);
});

Deno.test("Testing the computation of the next value of a sequence", function computeNextValueForSequenceTest() {
  const sequence1 = [0, 3, 6, 9, 12, 15];
  const expectedValue1 = 18;
  assertEquals(computeNextValueForSequence(sequence1),expectedValue1)

  const sequence2 = [1, 3, 6, 10, 15, 21];
  const expectedValue2 = 28;
  assertEquals(computeNextValueForSequence(sequence2),expectedValue2)

  const sequence3 = [10, 13, 16, 21, 30, 45];
  const expectedValue3 = 68;
  assertEquals(computeNextValueForSequence(sequence3),expectedValue3)
});

Deno.test("Testing the extrapolation of the previous value of a sequence", function extrapolatePreviousValueForSequenceTest() {
  const sequence1 = [0, 3, 6, 9, 12, 15];
  const expectedValue1 = -3;
  assertEquals(extrapolatePreviousValueForSequence(sequence1),expectedValue1)

  const sequence2 = [1, 3, 6, 10, 15, 21];
  const expectedValue2 = 0;
  assertEquals(extrapolatePreviousValueForSequence(sequence2),expectedValue2)

  const sequence3 = [10, 13, 16, 21, 30, 45];
  const expectedValue3 = 5;
  assertEquals(extrapolatePreviousValueForSequence(sequence3),expectedValue3)
});

Deno.test("Solving part 1 on the test input data", function solvePart1Test() {
  const expected = 114;
  const actual = solvePart1("./data/input_test.txt");
  assertEquals(actual, expected);
});

Deno.test("Solving part 2 on the test input data", function solvePart1Test() {
  const expected = 2;
  const actual = solvePart2("./data/input_test.txt");
  assertEquals(actual, expected);
});
