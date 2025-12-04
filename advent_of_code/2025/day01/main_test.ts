import { assertEquals, assertFalse } from "@std/assert";
import {
  computePositionAndZeroCrossingsFromRotation,
  computePositionFromRotation,
  parseInput,
  Rotation,
  solvePart1,
  solvePart2,
} from "./main.ts";

Deno.test("Parsing the example input file for part 1", function parseInputTest() {
  const result = parseInput("./data/input_test.txt");
  const expected = [
    ["L", 68],
    ["L", 30],
    ["R", 48],
    ["L", 5],
    ["R", 60],
    ["L", 55],
    ["L", 1],
    ["L", 99],
    ["R", 14],
    ["L", 82],
  ];
  assertEquals(result, expected);
});

Deno.test("Computing new position from rotation to the right without wrapping", function computePositionFromRotationTest() {
  const startPosition = 11;
  const rotation: Rotation = ["R", 8];
  const expected = 19;
  const result = computePositionFromRotation(startPosition, rotation);
  assertEquals(result, expected);
});

Deno.test("Computing new position from rotation to the right with wrapping", function computePositionFromRotationTest() {
  const startPosition = 99;
  const rotation: Rotation = ["R", 1];
  const expected = 0;
  const result = computePositionFromRotation(startPosition, rotation);
  assertEquals(result, expected);
});

Deno.test("Computing new position from rotation to the right with wrapping for large number of rotations", function computePositionFromRotationTest() {
  const startPosition = 50;
  const rotation: Rotation = ["R", 315];
  const expected = 65;
  const result = computePositionFromRotation(startPosition, rotation);
  assertEquals(result, expected);
});

Deno.test("Computing new position from rotation to the left pointing to 0", function computePositionFromRotationTest() {
  const startPosition = 19;
  const rotation: Rotation = ["L", 19];
  const expected = 0;
  const result = computePositionFromRotation(startPosition, rotation);
  assertEquals(result, expected);
});

Deno.test("Computing new position from rotation to the left with wrapping", function computePositionFromRotationTest() {
  const startPosition = 0;
  const rotation: Rotation = ["L", 1];
  const expected = 99;
  const result = computePositionFromRotation(startPosition, rotation);
  assertEquals(result, expected);
});

Deno.test("Computing new position from rotation to the left with wrapping for large number of rotations", function computePositionFromRotationTest() {
  const startPosition = 25;
  const rotation: Rotation = ["L", 727];
  const expected = 98;
  const result = computePositionFromRotation(startPosition, rotation);
  assertEquals(result, expected);
});

Deno.test("Computing new position from rotation to the right with wrapping to point 0", function computePositionFromRotationTest() {
  const startPosition = 52;
  const rotation: Rotation = ["R", 48];
  const expected = 0;
  const result = computePositionFromRotation(startPosition, rotation);
  assertEquals(result, expected);
});

Deno.test("Computing new position from rotation to the left with wrapping to point at 0 once during rotation", function computePositionFromRotationTest() {
  const startPosition = 50;
  const rotation: Rotation = ["L", 68];
  const expected = 82;
  const result = computePositionFromRotation(startPosition, rotation);
  assertEquals(result, expected);
});

Deno.test("Computing new position from rotation to the right with wrapping to point at 0 once during rotation", function computePositionFromRotationTest() {
  const startPosition = 95;
  const rotation: Rotation = ["R", 60];
  const expected = 55;
  const result = computePositionFromRotation(startPosition, rotation);
  assertEquals(result, expected);
});

Deno.test("Computing new position from rotation to the left pointing to 0", function computePositionFromRotationTest() {
  const startPosition = 55;
  const rotation: Rotation = ["L", 55];
  const expected = 0;
  const result = computePositionFromRotation(startPosition, rotation);
  assertEquals(result, expected);
});

Deno.test("Computing new position from rotation to the left pointing to 0", function computePositionFromRotationTest() {
  const startPosition = 99;
  const rotation: Rotation = ["L", 99];
  const expected = 0;
  const result = computePositionFromRotation(startPosition, rotation);
  assertEquals(result, expected);
});

Deno.test("Computing new position from rotation to the left with wrapping to point at 0 once during rotation", function computePositionFromRotationTest() {
  const startPosition = 14;
  const rotation: Rotation = ["L", 82];
  const expected = 32;
  const result = computePositionFromRotation(startPosition, rotation);
  assertEquals(result, expected);
});

Deno.test("Solving part 1 with the example input file", function solvePart1Test() {
  const result = solvePart1("./data/input_test.txt");
  const expected = 3;
  assertEquals(result, expected);
});

Deno.test("Computing new position from rotation to the left with wrapping for large number of rotations with several 0 pointing", function computePositionFromRotationTest() {
  const startPosition = 0;
  const rotation: Rotation = ["L", 727];
  const expected = [73, 7];
  const result = computePositionAndZeroCrossingsFromRotation(
    startPosition,
    rotation,
  );
  assertEquals(result, expected);
});

Deno.test("Computing new position from rotation to the left with wrapping for large number of rotations with several 0 pointing", function computePositionFromRotationTest() {
  const startPosition = 25;
  const rotation: Rotation = ["L", 727];
  const expected = [98, 8];
  const result = computePositionAndZeroCrossingsFromRotation(
    startPosition,
    rotation,
  );
  assertEquals(result, expected);
});

Deno.test("Computing new position from rotation to the left with wrapping for large number of rotations with several 0 pointing", function computePositionFromRotationTest() {
  const startPosition = 25;
  const rotation: Rotation = ["L", 724];
  const expected = [1, 7];
  const result = computePositionAndZeroCrossingsFromRotation(
    startPosition,
    rotation,
  );
  assertEquals(result, expected);
});

Deno.test("Computing new position from rotation to the left with wrapping for large number of rotations with several 0 pointing", function computePositionFromRotationTest() {
  const startPosition = 25;
  const rotation: Rotation = ["L", 725];
  const expected = [0, 8];
  const result = computePositionAndZeroCrossingsFromRotation(
    startPosition,
    rotation,
  );
  assertEquals(result, expected);
});

Deno.test("Computing new position from rotation to the left with wrapping for large number of rotations with several 0 pointing", function computePositionFromRotationTest() {
  const startPosition = 50;
  const rotation: Rotation = ["L", 140];
  const expected = [10, 1];
  const result = computePositionAndZeroCrossingsFromRotation(
    startPosition,
    rotation,
  );
  assertEquals(result, expected);
});

Deno.test("Computing new position from rotation to the left with 1 crossing", function computePositionFromRotationTest() {
  const startPosition = 50;
  const rotation: Rotation = ["L", 68];
  const expected = [82, 1];
  const result = computePositionAndZeroCrossingsFromRotation(
    startPosition,
    rotation,
  );
  assertEquals(result, expected);
});

Deno.test("Computing new position from rotation to the left with 0 crossing", function computePositionFromRotationTest() {
  const startPosition = 82;
  const rotation: Rotation = ["L", 30];
  const expected = [52, 0];
  const result = computePositionAndZeroCrossingsFromRotation(
    startPosition,
    rotation,
  );
  assertEquals(result, expected);
});

Deno.test("Computing new position from rotation to the right with 1 crossing", function computePositionFromRotationTest() {
  const startPosition = 52;
  const rotation: Rotation = ["R", 48];
  const expected = [0, 1];
  const result = computePositionAndZeroCrossingsFromRotation(
    startPosition,
    rotation,
  );
  assertEquals(result, expected);
});

Deno.test("Computing new position from rotation to the left with 0 crossing", function computePositionFromRotationTest() {
  const startPosition = 0;
  const rotation: Rotation = ["L", 5];
  const expected = [95, 0];
  const result = computePositionAndZeroCrossingsFromRotation(
    startPosition,
    rotation,
  );
  assertEquals(result, expected);
});

Deno.test("Computing new position from rotation to the right with 1 crossing", function computePositionFromRotationTest() {
  const startPosition = 95;
  const rotation: Rotation = ["R", 60];
  const expected = [55, 1];
  const result = computePositionAndZeroCrossingsFromRotation(
    startPosition,
    rotation,
  );
  assertEquals(result, expected);
});

Deno.test("Computing new position from rotation to the left with 0 crossing", function computePositionFromRotationTest() {
  const startPosition = 55;
  const rotation: Rotation = ["L", 55];
  const expected = [0, 1];
  const result = computePositionAndZeroCrossingsFromRotation(
    startPosition,
    rotation,
  );
  assertEquals(result, expected);
});

Deno.test("Computing new position from rotation to the left with 0 crossing", function computePositionFromRotationTest() {
  const startPosition = 0;
  const rotation: Rotation = ["L", 1];
  const expected = [99, 0];
  const result = computePositionAndZeroCrossingsFromRotation(
    startPosition,
    rotation,
  );
  assertEquals(result, expected);
});

Deno.test("Computing new position from rotation to the left with 1 crossing", function computePositionFromRotationTest() {
  const startPosition = 99;
  const rotation: Rotation = ["L", 99];
  const expected = [0, 1];
  const result = computePositionAndZeroCrossingsFromRotation(
    startPosition,
    rotation,
  );
  assertEquals(result, expected);
});

Deno.test("Computing new position from rotation to the right with 0 crossing", function computePositionFromRotationTest() {
  const startPosition = 0;
  const rotation: Rotation = ["R", 14];
  const expected = [14, 0];
  const result = computePositionAndZeroCrossingsFromRotation(
    startPosition,
    rotation,
  );
  assertEquals(result, expected);
});

Deno.test("Computing new position from rotation to the left with 1 crossing", function computePositionFromRotationTest() {
  const startPosition = 14;
  const rotation: Rotation = ["L", 82];
  const expected = [32, 1];
  const result = computePositionAndZeroCrossingsFromRotation(
    startPosition,
    rotation,
  );
  assertEquals(result, expected);
});

Deno.test("Computing new position from rotation to the right with wrapping for large number of rotations with several 0 pointing", function computePositionFromRotationTest() {
  const startPosition = 50;
  const rotation: Rotation = ["R", 315];
  const expected = [65, 3];
  const result = computePositionAndZeroCrossingsFromRotation(
    startPosition,
    rotation,
  );
  assertEquals(result, expected);
});

Deno.test("Computing new position from rotation to the right with wrapping for large number of rotations with several 0 pointing", function computePositionFromRotationTest() {
  const startPosition = 0;
  const rotation: Rotation = ["R", 315];
  const expected = [15, 3];
  const result = computePositionAndZeroCrossingsFromRotation(
    startPosition,
    rotation,
  );
  assertEquals(result, expected);
});

Deno.test("Computing new position from rotation to the right with wrapping for large number of rotations with several 0 pointing", function computePositionFromRotationTest() {
  const startPosition = 90;
  const rotation: Rotation = ["R", 315];
  const expected = [5, 4];
  const result = computePositionAndZeroCrossingsFromRotation(
    startPosition,
    rotation,
  );
  assertEquals(result, expected);
});

Deno.test("Computing new position from rotation to the right with wrapping for large number of rotations with several 0 pointing", function computePositionFromRotationTest() {
  const startPosition = 50;
  const rotation: Rotation = ["R", 199];
  const expected = [49, 2];
  const result = computePositionAndZeroCrossingsFromRotation(
    startPosition,
    rotation,
  );
  assertEquals(result, expected);
});

Deno.test("Solving part 2 with the example input file", function solvePart2Test() {
  const result = solvePart2("./data/input_test.txt");
  const expected = 6;
  assertEquals(result, expected);
});
