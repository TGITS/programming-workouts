import { describe, expect, it } from "bun:test";
import { dirname, join } from "node:path";
import { fileURLToPath } from "node:url";

import { parseProblemInput, readProblemInputFile } from "./src/io.ts";
import { computeBaconNumber } from "./src/solver.ts";
import { solveFromCodingameLines } from "./src/app.ts";

const baseDir = dirname(fileURLToPath(import.meta.url));
const dataDir = join(baseDir, "data");

function dataPath(fileName: string): string {
  return join(dataDir, fileName);
}

describe("parseProblemInput", () => {
  it("parse correctement le format des fichiers de data", async () => {
    const raw = await Bun.file(dataPath("input1.txt")).text();
    const parsed = parseProblemInput(raw);

    expect(parsed.actorName).toBe("Elvis Presley");
    expect(parsed.movieCasts.length).toBe(3);
    expect(parsed.movieCasts[0]?.movieName).toBe("Change of Habit");
    expect(parsed.movieCasts[0]?.actors).toContain("Elvis Presley");
    expect(parsed.movieCasts[1]?.actors).toContain("Kevin Bacon");
  });

  it("lit et parse un fichier d'entrée", async () => {
    const parsed = await readProblemInputFile(dataPath("input2.txt"));

    expect(parsed.actorName).toBe("Brad Pitt");
    expect(parsed.movieCasts.length).toBe(3);
  });

  it("échoue si l'entrée contient moins de 2 lignes", () => {
    const raw = "Only Actor Name";

    expect(() => parseProblemInput(raw)).toThrow(
      "Invalid input: expected at least 2 lines."
    );
  });

  it("échoue si n n'est pas un entier non négatif", () => {
    const raw = ["Brad Pitt", "abc", "Sleepers: Kevin Bacon, Brad Pitt"].join("\n");

    expect(() => parseProblemInput(raw)).toThrow(
      "Invalid input: second line must be a non-negative integer"
    );
  });

  it("échoue si le nombre de lignes de cast est insuffisant", () => {
    const raw = ["Brad Pitt", "2", "Sleepers: Kevin Bacon, Brad Pitt"].join("\n");

    expect(() => parseProblemInput(raw)).toThrow(
      "Invalid input: expected 2 movie cast lines, got 1."
    );
  });

  it("échoue si une ligne de cast ne contient pas ':'", () => {
    const raw = ["Brad Pitt", "1", "Sleepers Kevin Bacon, Brad Pitt"].join("\n");

    expect(() => parseProblemInput(raw)).toThrow(
      "Invalid movie cast line (missing ':')"
    );
  });

  it("échoue si une ligne de cast n'a pas d'acteurs", () => {
    const raw = ["Brad Pitt", "1", "Sleepers:"].join("\n");

    expect(() => parseProblemInput(raw)).toThrow("Invalid movie cast line");
  });
});

describe("computeBaconNumber", () => {
  const scenarios: Array<{ file: string; expected: number }> = [
    { file: "input1.txt", expected: 2 },
    { file: "input2.txt", expected: 1 },
    { file: "input3.txt", expected: 3 },
    { file: "input4.txt", expected: 0 },
    { file: "input5.txt", expected: 6 },
    { file: "input6.txt", expected: 4 },
  ];

  for (const scenario of scenarios) {
    it(`retourne ${scenario.expected} pour ${scenario.file}`, async () => {
      const parsed = await readProblemInputFile(dataPath(scenario.file));

      const baconNumber = computeBaconNumber(parsed);

      expect(baconNumber).toBe(scenario.expected);
    });
  }

  it("retourne -1 si l'acteur cible n'est pas connecté à Kevin Bacon", () => {
    const parsed = parseProblemInput(
      [
        "Actor A",
        "2",
        "Movie One: Actor A, Actor B",
        "Movie Two: Actor C, Actor D",
      ].join("\n")
    );

    expect(computeBaconNumber(parsed)).toBe(-1);
  });

  it("retourne -1 si Kevin Bacon est absent du graphe", () => {
    const parsed = parseProblemInput(
      ["Actor A", "1", "Movie One: Actor A, Actor B"].join("\n")
    );

    expect(computeBaconNumber(parsed)).toBe(-1);
  });
});

describe("solveFromCodingameLines", () => {
  it("résout correctement un input au format readline de CodinGame", async () => {
    const raw = await Bun.file(dataPath("input1.txt")).text();
    const lines = raw
      .split(/\r?\n/)
      .map((line) => line.trim())
      .filter((line) => line.length > 0);

    const actorName = lines[0]!;
    const n = Number.parseInt(lines[1]!, 10);
    const movieCastLines = lines.slice(2, 2 + n);

    expect(solveFromCodingameLines(actorName, n, movieCastLines)).toBe(2);
  });
});
