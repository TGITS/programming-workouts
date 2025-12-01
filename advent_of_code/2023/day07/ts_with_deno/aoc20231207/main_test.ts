import { assertEquals } from "@std/assert";
import {
  compareHands,
  computeTotalWinnings,
  computeTypeOfHandPart1,
  computeTypeOfHandPart2,
  FIVE_OF_A_KIND,
  FOUR_OF_A_KIND,
  FULL_HOUSE,
  HIGH_CARD,
  ONE_PAIR,
  parseInput,
  solvePart1,
  solvePart2,
  sortHandsAscending,
  THREE_OF_A_KIND,
  TWO_PAIR,
} from "./main.ts";

Deno.test("Function computeTypeOfHandPart1 should detect a Five of a Kind", function computeTypeOfHandPart1Test() {
  const hand = ["A", "A", "A", "A", "A"]; // Five of a Kind

  assertEquals(computeTypeOfHandPart1(hand), FIVE_OF_A_KIND);
});

Deno.test("Function computeTypeOfHandPart1 should detect a Four of a Kind", function computeTypeOfHandPart1Test() {
  const hand = ["K", "K", "K", "K", "9"]; // Four of a Kind
  assertEquals(computeTypeOfHandPart1(hand), FOUR_OF_A_KIND);
});

Deno.test("Function computeTypeOfHandPart1 should detect a Full House", function computeTypeOfHandPart1Test() {
  const hand = ["Q", "Q", "Q", "J", "J"]; // Full House
  assertEquals(computeTypeOfHandPart1(hand), FULL_HOUSE);
});

Deno.test("Function computeTypeOfHandPart1 should detect a Three of a Kind", function computeTypeOfHandPart1Test() {
  const hand = ["5", "5", "5", "3", "2"]; // Three of a Kind
  assertEquals(computeTypeOfHandPart1(hand), THREE_OF_A_KIND);
});

Deno.test("Function computeTypeOfHandPart1 should detect a Two Pair", function computeTypeOfHandPart1Test() {
  const hand = ["8", "8", "6", "6", "4"]; // Two Pair
  assertEquals(computeTypeOfHandPart1(hand), TWO_PAIR);
});

Deno.test("Function computeTypeOfHandPart1 should detect a One Pair", function computeTypeOfHandPart1Test() {
  const hand = ["9", "9", "7", "5", "3"]; // One Pair
  assertEquals(computeTypeOfHandPart1(hand), ONE_PAIR);
});

Deno.test("Function computeTypeOfHandPart1 should detect a High Card", function computeTypeOfHandPart1Test() {
  const hand = ["A", "K", "J", "7", "2"]; // High Card
  assertEquals(computeTypeOfHandPart1(hand), HIGH_CARD);
});

Deno.test("Parsing the example input file for part 1", function parseInputTest() {
  const result = parseInput("./data/input_test.txt", true);
  const expected = [
    {
      originalHand: ["3", "2", "T", "3", "K"],
      numericHand: [3, 2, 10, 3, 13],
      bid: 765,
      handType: ONE_PAIR,
    },
    {
      originalHand: ["T", "5", "5", "J", "5"],
      numericHand: [10, 5, 5, 11, 5],
      bid: 684,
      handType: THREE_OF_A_KIND,
    },
    {
      originalHand: ["K", "K", "6", "7", "7"],
      numericHand: [13, 13, 6, 7, 7],
      bid: 28,
      handType: TWO_PAIR,
    },
    {
      originalHand: ["K", "T", "J", "J", "T"],
      numericHand: [13, 10, 11, 11, 10],
      bid: 220,
      handType: TWO_PAIR,
    },
    {
      originalHand: ["Q", "Q", "Q", "J", "A"],
      numericHand: [12, 12, 12, 11, 14],
      bid: 483,
      handType: THREE_OF_A_KIND,
    },
  ];
  assertEquals(result, expected);
});

Deno.test("Comparing a higher hand type against a lower hand type", function compareHandsPart1Test() {
  const handA = {
    originalHand: ["T", "5", "5", "J", "5"],
    numericHand: [10, 5, 5, 11, 5],
    bid: 684,
    handType: THREE_OF_A_KIND,
  };
  const handB = {
    originalHand: ["K", "K", "6", "7", "7"],
    numericHand: [13, 13, 6, 7, 7],
    bid: 28,
    handType: TWO_PAIR,
  };
  assertEquals(compareHands(handA, handB), 1);
  assertEquals(compareHands(handB, handA), -1);
});

Deno.test("Comparing two same hand types but different high cards", function compareHandsPart1Test() {
  const handA = {
    originalHand: ["Q", "Q", "Q", "J", "A"],
    numericHand: [12, 12, 12, 11, 14],
    bid: 483,
    handType: THREE_OF_A_KIND,
  };
  const handB = {
    originalHand: ["T", "5", "5", "J", "5"],
    numericHand: [10, 5, 5, 11, 5],
    bid: 684,
    handType: THREE_OF_A_KIND,
  };
  assertEquals(compareHands(handA, handB), 1);
  assertEquals(compareHands(handB, handA), -1);
});

Deno.test("Comparing two identical hands", function compareHandsPart1Test() {
  const handA = {
    originalHand: ["3", "2", "T", "3", "K"],
    numericHand: [3, 2, 10, 3, 13],
    bid: 765,
    handType: ONE_PAIR,
  };
  const handB = {
    originalHand: ["3", "2", "T", "3", "K"],
    numericHand: [3, 2, 10, 3, 13],
    bid: 765,
    handType: ONE_PAIR,
  };
  assertEquals(compareHands(handA, handB), 0);
  assertEquals(compareHands(handB, handA), 0);
});

Deno.test("Comparing two same hand types and same high cards but different bids", function compareHandsPart1Test() {
  const handA = {
    originalHand: ["K", "T", "J", "J", "T"],
    numericHand: [13, 10, 11, 11, 10],
    bid: 220,
    handType: TWO_PAIR,
  };
  const handB = {
    originalHand: ["K", "T", "J", "J", "T"],
    numericHand: [13, 10, 11, 11, 10],
    bid: 250,
    handType: TWO_PAIR,
  };
  assertEquals(compareHands(handA, handB), 0);
  assertEquals(compareHands(handB, handA), 0);
});

Deno.test("Sorting ascenting a list of hands for Part1", function sortHandsAscendingPart1Test() {
  const given = [
    {
      originalHand: ["3", "2", "T", "3", "K"],
      numericHand: [3, 2, 10, 3, 13],
      bid: 765,
      handType: ONE_PAIR,
    },
    {
      originalHand: ["T", "5", "5", "J", "5"],
      numericHand: [10, 5, 5, 11, 5],
      bid: 684,
      handType: THREE_OF_A_KIND,
    },
    {
      originalHand: ["K", "K", "6", "7", "7"],
      numericHand: [13, 13, 6, 7, 7],
      bid: 28,
      handType: TWO_PAIR,
    },
    {
      originalHand: ["K", "T", "J", "J", "T"],
      numericHand: [13, 10, 11, 11, 10],
      bid: 220,
      handType: TWO_PAIR,
    },
    {
      originalHand: ["Q", "Q", "Q", "J", "A"],
      numericHand: [12, 12, 12, 11, 14],
      bid: 483,
      handType: THREE_OF_A_KIND,
    },
  ];

  const expected = [
    {
      originalHand: ["3", "2", "T", "3", "K"],
      numericHand: [3, 2, 10, 3, 13],
      bid: 765,
      handType: ONE_PAIR,
    },
    {
      originalHand: ["K", "T", "J", "J", "T"],
      numericHand: [13, 10, 11, 11, 10],
      bid: 220,
      handType: TWO_PAIR,
    },
    {
      originalHand: ["K", "K", "6", "7", "7"],
      numericHand: [13, 13, 6, 7, 7],
      bid: 28,
      handType: TWO_PAIR,
    },
    {
      originalHand: ["T", "5", "5", "J", "5"],
      numericHand: [10, 5, 5, 11, 5],
      bid: 684,
      handType: THREE_OF_A_KIND,
    },
    {
      originalHand: ["Q", "Q", "Q", "J", "A"],
      numericHand: [12, 12, 12, 11, 14],
      bid: 483,
      handType: THREE_OF_A_KIND,
    },
  ];

  assertEquals(sortHandsAscending(given), expected);
});

Deno.test("Computing total winnings from list of hands", function computeTotalWinningsTest() {
  const hands = [
    {
      originalHand: ["3", "2", "T", "3", "K"],
      numericHand: [3, 2, 10, 3, 13],
      bid: 765,
      handType: ONE_PAIR,
    },
    {
      originalHand: ["T", "5", "5", "J", "5"],
      numericHand: [10, 5, 5, 11, 5],
      bid: 684,
      handType: THREE_OF_A_KIND,
    },
    {
      originalHand: ["K", "K", "6", "7", "7"],
      numericHand: [13, 13, 6, 7, 7],
      bid: 28,
      handType: TWO_PAIR,
    },
    {
      originalHand: ["K", "T", "J", "J", "T"],
      numericHand: [13, 10, 11, 11, 10],
      bid: 220,
      handType: TWO_PAIR,
    },
    {
      originalHand: ["Q", "Q", "Q", "J", "A"],
      numericHand: [12, 12, 12, 11, 14],
      bid: 483,
      handType: THREE_OF_A_KIND,
    },
  ];

  const expectedTotalWinnings = 6440;

  assertEquals(computeTotalWinnings(hands), expectedTotalWinnings);
});

Deno.test("Solving part 1 with example input file", function solvePart1Test() {
  const result = solvePart1("./data/input_test.txt");
  const expectedTotalWinnings = 6440;
  assertEquals(result, expectedTotalWinnings);
});

Deno.test("Parsing the example input file for part 2", function parseInputTest() {
  const result = parseInput("./data/input_test.txt", false);
  const expected = [
    {
      originalHand: ["3", "2", "T", "3", "K"],
      numericHand: [4, 3, 11, 4, 13],
      bid: 765,
      handType: ONE_PAIR,
    },
    {
      originalHand: ["T", "5", "5", "J", "5"],
      numericHand: [11, 6, 6, 2, 6],
      bid: 684,
      handType: FOUR_OF_A_KIND,
    },
    {
      originalHand: ["K", "K", "6", "7", "7"],
      numericHand: [13, 13, 7, 8, 8],
      bid: 28,
      handType: TWO_PAIR,
    },
    {
      originalHand: ["K", "T", "J", "J", "T"],
      numericHand: [13, 11, 2, 2, 11],
      bid: 220,
      handType: FOUR_OF_A_KIND,
    },
    {
      originalHand: ["Q", "Q", "Q", "J", "A"],
      numericHand: [12, 12, 12, 2, 14],
      bid: 483,
      handType: FOUR_OF_A_KIND,
    },
  ];
  assertEquals(result, expected);
});

Deno.test("Comparing a higher hand type against a lower hand type", function compareHandsPart2Test() {
  const handA = {
    originalHand: ["T", "5", "5", "J", "5"],
    numericHand: [11, 6, 6, 2, 6],
    bid: 684,
    handType: FOUR_OF_A_KIND,
  };
  const handB = {
    originalHand: ["3", "2", "T", "3", "K"],
    numericHand: [4, 3, 11, 4, 13],
    bid: 765,
    handType: ONE_PAIR,
  };
  assertEquals(compareHands(handA, handB), 1);
  assertEquals(compareHands(handB, handA), -1);
});

Deno.test("Comparing two same hand types but different high cards", function compareHandsPart2Test() {
  const handA = {
    originalHand: ["T", "5", "5", "J", "5"],
    numericHand: [11, 6, 6, 2, 6],
    bid: 684,
    handType: FOUR_OF_A_KIND,
  };
  const handB = {
    originalHand: ["K", "K", "6", "7", "7"],
    numericHand: [13, 13, 7, 8, 8],
    bid: 28,
    handType: FOUR_OF_A_KIND,
  };
  assertEquals(compareHands(handA, handB), -1);
  assertEquals(compareHands(handB, handA), 1);
});

Deno.test("Comparing two same hand types but different high cards", function compareHandsPart2Test() {
  const handA = {
    originalHand: ["Q", "Q", "Q", "J", "A"],
    numericHand: [12, 12, 12, 2, 14],
    bid: 483,
    handType: FOUR_OF_A_KIND,
  };
  const handB = {
    originalHand: ["T", "5", "5", "J", "5"],
    numericHand: [11, 6, 6, 2, 6],
    bid: 684,
    handType: FOUR_OF_A_KIND,
  };
  assertEquals(compareHands(handA, handB), 1);
  assertEquals(compareHands(handB, handA), -1);
});

Deno.test("Comparing two identical hands", function compareHandsPart2Test() {
  const handA = {
    originalHand: ["3", "2", "T", "3", "K"],
    numericHand: [4, 3, 11, 4, 13],
    bid: 765,
    handType: ONE_PAIR,
  };
  const handB = {
    originalHand: ["3", "2", "T", "3", "K"],
    numericHand: [4, 3, 11, 4, 13],
    bid: 765,
    handType: ONE_PAIR,
  };
  assertEquals(compareHands(handA, handB), 0);
  assertEquals(compareHands(handB, handA), 0);
});

Deno.test("Comparing two same hand types and same high cards but different bids", function compareHandsPart2Test() {
  const handA = {
    originalHand: ["K", "T", "J", "J", "T"],
    numericHand: [13, 11, 2, 2, 11],
    bid: 220,
    handType: FOUR_OF_A_KIND,
  };
  const handB = {
    originalHand: ["K", "T", "J", "J", "T"],
    numericHand: [13, 11, 2, 2, 11],
    bid: 250,
    handType: FOUR_OF_A_KIND,
  };
  assertEquals(compareHands(handA, handB), 0);
  assertEquals(compareHands(handB, handA), 0);
});

Deno.test("Sorting ascenting a list of hands for Part2", function sortHandsAscendingPart2Test() {
  const given = [
    {
      originalHand: ["3", "2", "T", "3", "K"],
      numericHand: [4, 3, 11, 4, 13],
      bid: 765,
      handType: ONE_PAIR,
    },
    {
      originalHand: ["T", "5", "5", "J", "5"],
      numericHand: [11, 6, 6, 2, 6],
      bid: 684,
      handType: FOUR_OF_A_KIND,
    },
    {
      originalHand: ["K", "K", "6", "7", "7"],
      numericHand: [13, 13, 7, 8, 8],
      bid: 28,
      handType: TWO_PAIR,
    },
    {
      originalHand: ["K", "T", "J", "J", "T"],
      numericHand: [13, 11, 2, 2, 11],
      bid: 220,
      handType: FOUR_OF_A_KIND,
    },
    {
      originalHand: ["Q", "Q", "Q", "J", "A"],
      numericHand: [12, 12, 12, 2, 14],
      bid: 483,
      handType: FOUR_OF_A_KIND,
    },
  ];

  const expected = [
    {
      originalHand: ["3", "2", "T", "3", "K"],
      numericHand: [4, 3, 11, 4, 13],
      bid: 765,
      handType: ONE_PAIR,
    },
    {
      originalHand: ["K", "K", "6", "7", "7"],
      numericHand: [13, 13, 7, 8, 8],
      bid: 28,
      handType: TWO_PAIR,
    },
    {
      originalHand: ["T", "5", "5", "J", "5"],
      numericHand: [11, 6, 6, 2, 6],
      bid: 684,
      handType: FOUR_OF_A_KIND,
    },
    {
      originalHand: ["Q", "Q", "Q", "J", "A"],
      numericHand: [12, 12, 12, 2, 14],
      bid: 483,
      handType: FOUR_OF_A_KIND,
    },
    {
      originalHand: ["K", "T", "J", "J", "T"],
      numericHand: [13, 11, 2, 2, 11],
      bid: 220,
      handType: FOUR_OF_A_KIND,
    },
  ];

  assertEquals(sortHandsAscending(given), expected);
});

Deno.test("Solving part 2 with example input file", function solvePart1Test() {
  const result = solvePart2("./data/input_test.txt");
  const expectedTotalWinnings = 5905;
  assertEquals(result, expectedTotalWinnings);
});
