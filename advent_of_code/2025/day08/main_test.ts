import { assertEquals } from "@std/assert";
import { InputData, parseInput, solvePart1, solvePart2 } from "./main.ts";

Deno.test("Testing the parsing function on the test input data", function parseInputTest() {
  const expected: InputData = [
    {x:162, y:817, z:812},
    {x:57, y:618, z:57},
    {x:906, y:360, z:560},
    {x:592, y:479, z:940},
    {x:352, y:342, z:300},
    {x:466, y:668, z:158},
    {x:542, y:29, z:236},
    {x:431, y:825, z:988},
    {x:739, y:650, z:466},
    {x:52, y:470, z:668},
    {x:216, y:146, z:977},
    {x:819, y:987, z:18},
    {x:117, y:168, z:530},
    {x:805, y:96, z:715},
    {x:346, y:949, z:466},
    {x:970, y:615, z:88},
    {x:941, y:993, z:340},
    {x:862, y:61, z:35},
    {x:984, y:92, z:344},
    {x:425, y:690, z:689},
  ];
  const actual: InputData = parseInput("./data/input_test.txt");
  assertEquals(actual, expected);
  console.table(actual);
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
