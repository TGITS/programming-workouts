/**
 * 6 Degrees of Kevin Bacon!
 * Single-file version for CodinGame editor.
 */

type MovieCast = {
  movieName: string;
  actors: string[];
};

type ProblemInput = {
  actorName: string;
  movieCasts: MovieCast[];
};

const KEVIN_BACON = "Kevin Bacon";

declare const readline: () => string;

function parseProblemInput(raw: string): ProblemInput {
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

function addUndirectedEdge(graph: Map<string, Set<string>>, actorA: string, actorB: string): void {
  if (!graph.has(actorA)) {
    graph.set(actorA, new Set<string>());
  }

  if (!graph.has(actorB)) {
    graph.set(actorB, new Set<string>());
  }

  graph.get(actorA)?.add(actorB);
  graph.get(actorB)?.add(actorA);
}

function buildActorGraph(input: ProblemInput): Map<string, Set<string>> {
  const graph = new Map<string, Set<string>>();

  for (const cast of input.movieCasts) {
    const actors = cast.actors;

    for (let i = 0; i < actors.length; i += 1) {
      for (let j = i + 1; j < actors.length; j += 1) {
        addUndirectedEdge(graph, actors[i]!, actors[j]!);
      }
    }
  }

  return graph;
}

function computeBaconNumber(input: ProblemInput): number {
  if (input.actorName === KEVIN_BACON) {
    return 0;
  }

  const graph = buildActorGraph(input);

  if (!graph.has(input.actorName) || !graph.has(KEVIN_BACON)) {
    return -1;
  }

  const queue: Array<{ actor: string; distance: number }> = [
    { actor: input.actorName, distance: 0 },
  ];
  const visited = new Set<string>([input.actorName]);

  while (queue.length > 0) {
    const current = queue.shift();
    if (!current) {
      continue;
    }

    if (current.actor === KEVIN_BACON) {
      return current.distance;
    }

    const neighbors = graph.get(current.actor);
    if (!neighbors) {
      continue;
    }

    for (const next of neighbors) {
      if (visited.has(next)) {
        continue;
      }

      visited.add(next);
      queue.push({ actor: next, distance: current.distance + 1 });
    }
  }

  return -1;
}

const maybeReadline = (globalThis as unknown as { readline?: () => string }).readline;

if (typeof maybeReadline === "function") {
  const actorName: string = maybeReadline();
  const n: number = Number.parseInt(maybeReadline(), 10);
  const movieCastLines: string[] = [];

  for (let i = 0; i < n; i += 1) {
    movieCastLines.push(maybeReadline());
  }

  const rawInput = [actorName, String(n), ...movieCastLines].join("\n");
  const parsed = parseProblemInput(rawInput);
  const result = computeBaconNumber(parsed);
  console.log(result);
} else {
  const rawInput = await Bun.stdin.text();
  const parsed = parseProblemInput(rawInput);
  const result = computeBaconNumber(parsed);
  console.log(result);
}
