import { assertEquals } from "@std/assert";
import {
  findInvalidIdsForPart1,
  findInvalidIdsForPart2,
  isInvalidIdForPart1,
  isInvalidIdForPart2,
  parseInput,
  Range,
  solvePart1,
  solvePart2,
} from "./main.ts";

Deno.test(function parseInputTest() {
  const ranges = parseInput("data/input_test.txt");
  const expected: Range[] = [
    ["11", "22"],
    ["95", "115"],
    ["998", "1012"],
    ["1188511880", "1188511890"],
    ["222220", "222224"],
    ["1698522", "1698528"],
    ["446443", "446449"],
    ["38593856", "38593862"],
    ["565653", "565659"],
    ["824824821", "824824827"],
    ["2121212118", "2121212124"],
  ];
  assertEquals(ranges, expected);
});

/*
for 11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124
11-22 has two invalid IDs, 11 and 22.
95-115 has one invalid ID, 99.
998-1012 has one invalid ID, 1010.
1188511880-1188511890 has one invalid ID, 1188511885.
222220-222224 has one invalid ID, 222222.
1698522-1698528 contains no invalid IDs.
446443-446449 has one invalid ID, 446446.
38593856-38593862 has one invalid ID, 38593859.
The rest of the ranges contain no invalid IDs.
*/

Deno.test(function isInvalidIdForPart1Test() {
  assertEquals(isInvalidIdForPart1("11"), true);
  assertEquals(isInvalidIdForPart1("22"), true);
  assertEquals(isInvalidIdForPart1("99"), true);
  assertEquals(isInvalidIdForPart1("1010"), true);
  assertEquals(isInvalidIdForPart1("1698522"), false);
  assertEquals(isInvalidIdForPart1("1698528"), false);
});

Deno.test(function findInvalidIdsForPart1Test() {
  const ranges = parseInput("data/input_test.txt");
  const invalidIds = findInvalidIdsForPart1(ranges);
  const expected = [
    11,
    22,
    99,
    1010,
    1188511885,
    222222,
    446446,
    38593859,
  ];
  assertEquals(invalidIds, expected);
});

Deno.test(function solvePart1Test() {
  const result = solvePart1("data/input_test.txt");
  const expected = 1227775554;
  assertEquals(result, expected);
});

/*
Now, an ID is invalid if it is made only of some sequence of digits repeated at least twice.
So, 12341234 (1234 two times), 123123123 (123 three times), 1212121212 (12 five times), and 1111111 (1 seven times) are all invalid IDs.

From the same example as before:

11-22 still has two invalid IDs, 11 and 22.
95-115 now has two invalid IDs, 99 and 111.
998-1012 now has two invalid IDs, 999 and 1010.
1188511880-1188511890 still has one invalid ID, 1188511885.
222220-222224 still has one invalid ID, 222222.
1698522-1698528 still contains no invalid IDs.
446443-446449 still has one invalid ID, 446446.
38593856-38593862 still has one invalid ID, 38593859.
565653-565659 now has one invalid ID, 565656.
824824821-824824827 now has one invalid ID, 824824824.
2121212118-2121212124 now has one invalid ID, 2121212121.
*/

Deno.test(function isInvalidIdForPart1Test() {
  assertEquals(isInvalidIdForPart2("11"), true);
  assertEquals(isInvalidIdForPart2("22"), true);
  assertEquals(isInvalidIdForPart2("99"), true);
  assertEquals(isInvalidIdForPart2("111"), true);
  assertEquals(isInvalidIdForPart2("999"), true);
  assertEquals(isInvalidIdForPart2("1010"), true);
  assertEquals(isInvalidIdForPart2("1188511885"), true);
  assertEquals(isInvalidIdForPart2("222222"), true);
  assertEquals(isInvalidIdForPart2("38593859"), true);
  assertEquals(isInvalidIdForPart2("565656"), true);
  assertEquals(isInvalidIdForPart2("824824824"), true);
  assertEquals(isInvalidIdForPart2("2121212121"), true);
  assertEquals(isInvalidIdForPart2("12341234"), true);
  assertEquals(isInvalidIdForPart2("123123123"), true);
  assertEquals(isInvalidIdForPart2("1212121212"), true);
  assertEquals(isInvalidIdForPart2("1111111"), true);
  assertEquals(isInvalidIdForPart2("1698522"), false);
  assertEquals(isInvalidIdForPart2("1698528"), false);
});

Deno.test(function findInvalidIdsForPart2Test() {
  const ranges = parseInput("data/input_test.txt");
  const invalidIds = findInvalidIdsForPart2(ranges);
  const expected = [
    11,
    22,
    99,
    111,
    999,
    1010,
    1188511885,
    222222,
    446446,
    38593859,
    565656,
    824824824,
    2121212121,
  ];
  assertEquals(invalidIds, expected);
});

Deno.test(function solvePart2Test() {
  const result = solvePart2("data/input_test.txt");
  const expected = 4174379265;
  assertEquals(result, expected);
});
