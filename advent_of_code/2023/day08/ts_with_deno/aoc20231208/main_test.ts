import { assertEquals } from "@std/assert";
import { solvePart1, solvePart2, Children, Input, parseInput } from "./main.ts";

Deno.test("Testing the parsing of a first example input data", function parseInputTest() {
  const expectedNodes = new Map<string, Children>();
  expectedNodes.set("AAA", {L:"BBB", R:"CCC"});
  expectedNodes.set("BBB", {L:"DDD", R:"EEE"});
  expectedNodes.set("CCC", {L:"ZZZ", R:"GGG"});
  expectedNodes.set("DDD", {L:"DDD", R:"DDD"});
  expectedNodes.set("EEE", {L:"EEE", R:"EEE"});
  expectedNodes.set("GGG", {L:"GGG", R:"GGG"});
  expectedNodes.set("ZZZ", {L:"ZZZ", R:"ZZZ"});
  const expected : Input = {
    instructions : ["R","L"],
    nodes: expectedNodes
  }
  const actual : Input = parseInput("./data/input_test1.txt");
  assertEquals(actual, expected)
});

Deno.test("Testing the parsing of a second example input data", function parseInputTest() {
  const expectedNodes = new Map<string, Children>();
  expectedNodes.set("AAA", {L:"BBB", R:"BBB"});
  expectedNodes.set("BBB", {L:"AAA", R:"ZZZ"});
  expectedNodes.set("ZZZ", {L:"ZZZ", R:"ZZZ"});
  const expected : Input = {
    instructions : ["L","L", "R"],
    nodes: expectedNodes
  }
  const actual : Input = parseInput("./data/input_test2.txt");
  assertEquals(actual, expected)
});

Deno.test("Resolving part1 with the first input data", function solvePart1Test() {
  const expected = 2;
  const actual = solvePart1("./data/input_test1.txt");
  assertEquals(actual, expected);
});

Deno.test("Resolving part1 with the second input data", function solvePart1Test() {
  const expected = 6;
  const actual = solvePart1("./data/input_test2.txt");
  assertEquals(actual, expected);
});

Deno.test("Resolving part2 with the dedicated input data", function solvePart2Test() {
  const expected = 6;
  const actual = solvePart2("./data/input_test_for_part2.txt");
  assertEquals(actual, expected);
});