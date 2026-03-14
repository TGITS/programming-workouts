import type { ProblemInput } from "./types.ts";

export function parseProblemInput(raw: string): ProblemInput {
  const lines = raw
    .split(/\r?\n/)
    .map((line) => line.trim())
    .filter((line) => line.length > 0);

  if (lines.length < 2) {
    throw new Error("Invalid input: expected at least 2 lines.");
  }

  const actorName = lines[0]!;
  const n = Number.parseInt(lines[1]!, 10);

  if (Number.isNaN(n) || n < 0) {
    throw new Error(`Invalid input: second line must be a non-negative integer, got '${lines[1]!}'.`);
  }

  if (lines.length < n + 2) {
    throw new Error(
      `Invalid input: expected ${n} movie cast lines, got ${Math.max(0, lines.length - 2)}.`
    );
  }

  const movieCasts = lines.slice(2, 2 + n).map((line) => {
    const separatorIndex = line.indexOf(":");

    if (separatorIndex === -1) {
      throw new Error(`Invalid movie cast line (missing ':'): '${line}'.`);
    }

    const movieName = line.slice(0, separatorIndex).trim();
    const actorPart = line.slice(separatorIndex + 1).trim();

    const actors = actorPart
      .split(",")
      .map((actor) => actor.trim())
      .filter((actor) => actor.length > 0);

    if (movieName.length === 0 || actors.length === 0) {
      throw new Error(`Invalid movie cast line: '${line}'.`);
    }

    return { movieName, actors };
  });

  return { actorName, movieCasts };
}

export async function readProblemInputFile(filePath: string): Promise<ProblemInput> {
  const raw = await Bun.file(filePath).text();
  return parseProblemInput(raw);
}
