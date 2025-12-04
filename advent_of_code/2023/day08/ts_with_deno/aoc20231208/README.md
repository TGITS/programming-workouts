https://developer.mozilla.org/fr/docs/Web/JavaScript/Reference/Statements/for...of



# README

Défi du Jour 4 du [Advent Of Code 2025](https://adventofcode.com/2025).
Tentative de résolution avec [TypeScript](https://www.typescriptlang.org/) en
utilisant le runtime/outil [Deno](https://deno.com/)

Pour lancer le script : `deno -P --allow-read main.ts` Pour lancer les tests :
`deno test -P`

## Problème posé

### Première partie

Il faut trouver le nombre d'étapes pour parcourir un graphe d'un noeud de départ à un noeud d'arrivée.

### Deuxième partie

Il y a un ensemble de noeuds de départ et il faut arriver sur un ensemble de noeuds qui respectent tous en même temps la condition pour être des noeuds d'arrivée.

## Idées pour la résolution

### Première partie

Rien de particulier, c'est un parcours de graphe pas trop compliqué.
Il faut juste faire attention que la séquence d'instructions gauche-droite doit pouvoir se répéter infinimement

### Deuxième partie

Cela ne semble pas compliqué sur le principe, on fait tous les parcours en même temps pour tous les noeuds, et cela constitue une étape.
Par contre ma première solution naïve ci-dessous prend beaucoup de temps, beaucoup trop de temps, même si cela fonctionne sur le jeux de données de départ.

```typescript
export function solvePart2(filename:string) : number {
  let steps = 0;
  const input = parseInput(filename);
  let currentNodes = Array.from(input.nodes.keys()).filter((node) => node.endsWith("A"));
  let currentInstructionIndex = 0;
  while (!currentNodes.every((node) => node.endsWith("Z"))) {
    console.log(`Processing nodes: ${currentNodes}`)
    const currentInstruction = input.instructions[currentInstructionIndex%input.instructions.length]
    const nextNodes = [];
    for (const currentNode of currentNodes) {
      const currentChildren = input.nodes.get(currentNode);
      if(currentChildren) {
        if (currentInstruction === "L") {
          nextNodes.push(currentChildren.L);
        } else {
          nextNodes.push(currentChildren.R);
        }
      }
    }
    currentInstructionIndex += 1;
    steps += 1;
    currentNodes = nextNodes;
  }
  return steps;
}
```

Je n'y aurais pas pensé spontanément mais en m'appuyant sur les ressources ci-dessous, je comprends que le problème est que cela boucle un moment (enfin vraiment très longtemps) pour que l'on tombe sur un Z final en même temps pour chaque noeud de départ. En effet, ils vont avoir chacun un cycle pour arriver jusqu'au noeud de fin et cela va repartir. 
Il faut donc trouver le plus petit commun multiple (PPCM) de ces cycles, cela nous donnera le nombre d'étapes total.
Il faut donc calculer le cycle pour arriver à un noeud final pour chacun des noeuds de départ, puis en déduire le PPCM qui sera notre réponse.

Cela a été l'occasion de découvrir la bibliothèque [mathjs](https://mathjs.org/index.html) et de découvrir quelques points de fonctionnement sur le `for ... of`, le `for ... each` ou le `reduce`.

## Ressources et liens utilisés

Ressources et liens utilisés lors de la résolution de ce problème : aussi bien
des liens vers des pages rappelant la syntaxe du langage utilisé pour la
résolution, la manière de réaliser tel ou tel type d'opération dans ce langage,
des rappels algorithmique, etc.

* https://developer.mozilla.org/fr/docs/Web/JavaScript/Reference/Statements/for...of
  * Attention au fonctionnement du `for...in` par rapport à celui du `for...of`.
* Pour un peu d'aide
  * https://www.reddit.com/r/adventofcode/comments/18df7px/2023_day_8_solutions/
  * https://linuxfr.org/users/elessar/journaux/advent-of-code-2023-day-8
  * https://medium.com/@jatinkrmalik/day-8-haunted-wasteland-advent-of-code-2023-python-87c6b4cf22c2
  * https://fr.wikipedia.org/wiki/Plus_petit_commun_multiple
  * https://observablehq.com/@jwolondon/advent-of-code-2023-day-8
  * https://coreui.io/answers/how-to-find-the-least-common-multiple-in-javascript/
  * https://www.geeksforgeeks.org/javascript/javascript-program-to-find-lcm/
  * https://medium.com/swlh/finding-the-smallest-common-multiple-in-javascript-and-also-in-ruby-e82ae53494d7
  * https://mathjs.org/index.html
    * > Math.js is an extensive math library for JavaScript and Node.js. It features a flexible expression parser with support for symbolic computation, comes with a large set of built-in functions and constants, and offers an integrated solution to work with different data types like numbers, big numbers, complex numbers, fractions, units, and matrices. Powerful and easy to use.
