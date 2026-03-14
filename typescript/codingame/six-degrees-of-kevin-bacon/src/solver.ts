import type { ProblemInput } from "./types.ts";

const KEVIN_BACON = "Kevin Bacon";

export function computeBaconNumber(input: ProblemInput): number {
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

function addUndirectedEdge(
  graph: Map<string, Set<string>>,
  actorA: string,
  actorB: string
): void {
  if (!graph.has(actorA)) {
    graph.set(actorA, new Set<string>());
  }

  if (!graph.has(actorB)) {
    graph.set(actorB, new Set<string>());
  }

  graph.get(actorA)?.add(actorB);
  graph.get(actorB)?.add(actorA);
}
