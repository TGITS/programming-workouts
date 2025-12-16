import { assertEquals } from "@std/assert";
import {
  Bank,
  findLargestPossibleVoltagePart1,
  findLargestPossibleVoltagePart2,
  parseInput,
  solvePart1,
  solvePart2,
} from "./main.ts";

Deno.test("Test parseInput", () => {
  const banks: Bank[] = parseInput("./data/input_test.txt");
  const expectedBanks: Bank[] = [
    [9, 8, 7, 6, 5, 4, 3, 2, 1, 1, 1, 1, 1, 1, 1],
    [8, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9],
    [2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 7, 8],
    [8, 1, 8, 1, 8, 1, 9, 1, 1, 1, 1, 2, 1, 1, 1],
  ];
  assertEquals(banks, expectedBanks);
});

Deno.test("Test findLargestPossibleVoltagePart1", () => {
  const bank1: Bank = [9, 8, 7, 6, 5, 4, 3, 2, 1, 1, 1, 1, 1, 1, 1];
  const bank2: Bank = [8, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9];
  const bank3: Bank = [2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 7, 8];
  const bank4: Bank = [8, 1, 8, 1, 8, 1, 9, 1, 1, 1, 1, 2, 1, 1, 1];
  const bank5: Bank = [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1];
  const bank6: Bank = [
    4,
    3,
    4,
    6,
    3,
    4,
    3,
    2,
    3,
    5,
    1,
    4,
    9,
    4,
    5,
    6,
    5,
    4,
    3,
    4,
    4,
    5,
    2,
    3,
    3,
    3,
    5,
    3,
    5,
    3,
    4,
    2,
    4,
    4,
    5,
    3,
    3,
    3,
    3,
    3,
    3,
    3,
    3,
    4,
    3,
    4,
    3,
    2,
    5,
    9,
    3,
    3,
    3,
    3,
    2,
    6,
    3,
    3,
    7,
    3,
    3,
    4,
    3,
    3,
    4,
    3,
    3,
    3,
    8,
    3,
    3,
    2,
    5,
    3,
    3,
    3,
    4,
    5,
    2,
    4,
    3,
    3,
    2,
    2,
    3,
    3,
    5,
    2,
    4,
    4,
    3,
    2,
    4,
    3,
    2,
    4,
  ];

  assertEquals(findLargestPossibleVoltagePart1(bank1), 98);
  assertEquals(findLargestPossibleVoltagePart1(bank2), 89);
  assertEquals(findLargestPossibleVoltagePart1(bank3), 78);
  assertEquals(findLargestPossibleVoltagePart1(bank4), 92);
  assertEquals(findLargestPossibleVoltagePart1(bank5), 11);
  assertEquals(findLargestPossibleVoltagePart1(bank6), 99);
});

Deno.test("Test solvePart1", () => {
  const result = solvePart1("./data/input_test.txt");
  const expected = 98 + 89 + 78 + 92; // Sum of largest possible voltages from each bank
  assertEquals(result, expected);
});

Deno.test("Test findLargestPossibleVoltagePart2", () => {
  const bank1: Bank = [9, 8, 7, 6, 5, 4, 3, 2, 1, 1, 1, 1, 1, 1, 1];
  const bank2: Bank = [8, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9];
  const bank3: Bank = [2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 7, 8];
  const bank4: Bank = [8, 1, 8, 1, 8, 1, 9, 1, 1, 1, 1, 2, 1, 1, 1];

  assertEquals(findLargestPossibleVoltagePart2(bank1), 987654321111);
  assertEquals(findLargestPossibleVoltagePart2(bank2), 811111111119);
  assertEquals(findLargestPossibleVoltagePart2(bank3), 434234234278);
  assertEquals(findLargestPossibleVoltagePart2(bank4), 888911112111);
});

Deno.test("Test solvePart2", () => {
  const result = solvePart2("./data/input_test.txt");
  const expected = 987654321111 + 811111111119 + 434234234278 + 888911112111; // Sum of largest possible voltages from each bank
  assertEquals(result, expected);
});
