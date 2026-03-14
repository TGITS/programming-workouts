import { solveFromRawInput } from "./src/app.ts";

const filePath = process.argv[2];
const rawInput = filePath ? await Bun.file(filePath).text() : await Bun.stdin.text();

const baconNumber = solveFromRawInput(rawInput);

console.log(baconNumber);