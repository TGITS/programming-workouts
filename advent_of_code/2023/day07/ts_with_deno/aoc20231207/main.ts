const cardToValuePart1: Map<string, number> = new Map([
  ["A", 14],
  ["K", 13],
  ["Q", 12],
  ["J", 11],
  ["T", 10],
  ["9", 9],
  ["8", 8],
  ["7", 7],
  ["6", 6],
  ["5", 5],
  ["4", 4],
  ["3", 3],
  ["2", 2],
]);

const cardToValuePart2: Map<string, number> = new Map([
  ["A", 14],
  ["K", 13],
  ["Q", 12],
  ["J", 2],
  ["T", 11],
  ["9", 10],
  ["8", 9],
  ["7", 8],
  ["6", 7],
  ["5", 6],
  ["4", 5],
  ["3", 4],
  ["2", 3],
]);

export const FIVE_OF_A_KIND = 7;
export const FOUR_OF_A_KIND = 6;
export const FULL_HOUSE = 5;
export const THREE_OF_A_KIND = 4;
export const TWO_PAIR = 3;
export const ONE_PAIR = 2;
export const HIGH_CARD = 1;

type CamelCardsHand = {
  originalHand: string[];
  numericHand: number[];
  bid: number;
  handType: number;
};

function typeFromFrequencyMap(frequencyMap: Map<string, number>): number {
  if (frequencyMap.size === 1) {
    return FIVE_OF_A_KIND;
  }

  if (frequencyMap.size === 2) {
    const counts = Array.from(frequencyMap.values());
    if (counts.includes(4)) {
      return FOUR_OF_A_KIND;
    } else {
      return FULL_HOUSE;
    }
  }
  if (frequencyMap.size === 3) {
    const counts = Array.from(frequencyMap.values());
    if (counts.includes(3)) {
      return THREE_OF_A_KIND;
    } else {
      return TWO_PAIR;
    }
  }
  if (frequencyMap.size === 4) {
    return ONE_PAIR;
  }
  return HIGH_CARD;
}

export function computeTypeOfHandPart1(hand: string[]): number {
  const frequencyMap: Map<string, number> = new Map();
  hand.forEach((card) => {
    frequencyMap.set(card, (frequencyMap.get(card) || 0) + 1);
  });
  return typeFromFrequencyMap(frequencyMap);
}

export function computeTypeOfHandPart2(hand: string[]): number {
  const frequencyMap: Map<string, number> = new Map();
  hand.forEach((card) => {
    frequencyMap.set(card, (frequencyMap.get(card) || 0) + 1);
  });
  if (frequencyMap.size === 1) {
    return FIVE_OF_A_KIND;
  }

  const keys = Array.from(frequencyMap.keys());
  if (!keys.includes("J")) {
    return typeFromFrequencyMap(frequencyMap);
  } else {
    const countJ = frequencyMap.get("J")!;
    frequencyMap.delete("J");
    if (frequencyMap.size === 1) {
      return FIVE_OF_A_KIND;
    }
    if (frequencyMap.size === 2) {
      const maxCount = Math.max(...Array.from(frequencyMap.values()));
      if (maxCount + countJ == 4) {
        return FOUR_OF_A_KIND;
      } else {
        return FULL_HOUSE;
      }
    }
    if (frequencyMap.size === 3) {
      const maxCount = Math.max(...Array.from(frequencyMap.values()));
      if (maxCount + countJ == 3) {
        return THREE_OF_A_KIND;
      } else {
        return TWO_PAIR;
      }
    }
    if (frequencyMap.size === 4) {
      return ONE_PAIR;
    }
    return HIGH_CARD;
  }
}

export function parseInput(filename: string, part1: boolean): CamelCardsHand[] {
  const camelCardsHands: CamelCardsHand[] = [];

  const text = Deno.readTextFileSync(filename);

  const lines = text.split("\n").filter((line) => line.trim() !== "");

  lines.forEach((line) => {
    const temp = line.split(" ");
    const originalHand = temp[0].split("");
    const numericHand = part1
      ? originalHand.map((card) => cardToValuePart1.get(card)!)
      : originalHand.map((card) => cardToValuePart2.get(card)!);
    const bid = parseInt(temp[1], 10);
    const handType = part1
      ? computeTypeOfHandPart1(originalHand)
      : computeTypeOfHandPart2(originalHand);
    camelCardsHands.push({
      originalHand,
      numericHand,
      bid,
      handType,
    });
  });

  return camelCardsHands;
}

export function compareHands(
  handA: CamelCardsHand,
  handB: CamelCardsHand,
): number {
  if (handA.handType > handB.handType) {
    return 1;
  } else if (handA.handType < handB.handType) {
    return -1;
  } else {
    for (let i = 0; i < handA.numericHand.length; i++) {
      if (handA.numericHand[i] > handB.numericHand[i]) {
        return 1;
      } else if (handA.numericHand[i] < handB.numericHand[i]) {
        return -1;
      }
    }
    return 0;
  }
}

export function sortHandsAscending(hands: CamelCardsHand[]): CamelCardsHand[] {
  return hands.sort((handA, handB) => compareHands(handA, handB));
}

export function computeTotalWinnings(hands: CamelCardsHand[]): number {
  let totalWinnings = 0;
  const sortedHands = sortHandsAscending(hands);
  for (let i = 0; i < sortedHands.length; i++) {
    totalWinnings += sortedHands[i].bid * (i + 1);
  }
  return totalWinnings;
}

export function solvePart1(filename: string): number {
  const hands = parseInput(filename, true);
  return computeTotalWinnings(hands);
}

export function solvePart2(filename: string): number {
  const hands = parseInput(filename, false);
  return computeTotalWinnings(hands);
}

// Learn more at https://docs.deno.com/runtime/manual/examples/module_metadata#concepts
if (import.meta.main) {
  const resultPart1 = solvePart1("./data/input.txt");
  console.log(
    "The computed value for total winning for part 1 is",
    resultPart1,
  ); // 246912307 for my data input
  const resultPart2 = solvePart2("./data/input.txt");
  console.log(
    "The computed value for total winning for part 2 is",
    resultPart2,
  ); //246894760 for my data input
}
