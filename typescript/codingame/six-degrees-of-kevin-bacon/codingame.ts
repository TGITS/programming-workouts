import { solveFromCodingameLines } from "./src/app.ts";

/**
 * 6 Degrees of Kevin Bacon!
 */
declare const readline: () => string;

const maybeReadline = (globalThis as unknown as { readline?: () => string }).readline;

if (typeof maybeReadline === "function") {
  const actorName: string = maybeReadline();
  const n: number = Number.parseInt(maybeReadline(), 10);
  const movieCastLines: string[] = [];

  for (let i = 0; i < n; i += 1) {
    movieCastLines.push(maybeReadline());
  }

  const result = solveFromCodingameLines(actorName, n, movieCastLines);
  console.log(result);
} else {
  const rawInput = await Bun.stdin.text();
  const lines = rawInput
    .split(/\r?\n/)
    .map((line) => line.trim())
    .filter((line) => line.length > 0);

  const actorName = lines[0] ?? "";
  const n = Number.parseInt(lines[1] ?? "0", 10);
  const movieCastLines = lines.slice(2, 2 + n);

  const result = solveFromCodingameLines(actorName, n, movieCastLines);
  console.log(result);
}
