import { parseProblemInput } from "./io.ts";
import { computeBaconNumber } from "./solver.ts";

export function solveFromRawInput(raw: string): number {
  const input = parseProblemInput(raw);
  return computeBaconNumber(input);
}

export function solveFromCodingameLines(actorName: string, n: number, movieCastLines: string[]): number {
  const raw = [actorName, String(n), ...movieCastLines].join("\n");
  return solveFromRawInput(raw);
}
