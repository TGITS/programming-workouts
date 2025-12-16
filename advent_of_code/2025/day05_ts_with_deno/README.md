# README

Défi du Jour 5 du [Advent Of Code 2025](https://adventofcode.com/2025).
Tentative de résolution avec [TypeScript](https://www.typescriptlang.org/) en
utilisant le runtime/outil [Deno](https://deno.com/)

- Pour lancer le script : `deno -P --allow-read main.ts`
- Pour lancer les tests : `deno test -P`

## Problème posé

### Première partie

### Deuxième partie

## Idées pour la résolution

### Première partie

Un peu facile a priori, mes collègues les plus motivés qui se lèvent tôt pour
résoudre le puzzle du jour, ont dû torcher cela en 5 minutes !

### Deuxième partie

Attention, il y a des chevauchement d'intervalles, donc une approche naïve
directe ne fonctionne pas.

J'ai fait un essai en utilisant un `Set` mais cela a dépassé la capacité maximum
de ce dernier.

```typescript
export function solvePart2(filename: string): number {
  const freshIngredientIds: Set<number> = new Set();
  const inputData = parseInput(filename);
  for (const range of inputData.ranges) {
    for (let i = range[0]; i <= range[1]; i++) {
      freshIngredientIds.add(i);
    }
  }
  return freshIngredientIds.size;
}
```

Finalement, pour résoudre le puzzle, je suis parti sur un tri des intervalles,
test de chevauchement entre 2 intervalles et fusion entre 2 intervalles s'il y a
chevauchement.

## Ressources et liens utilisés

Ressources et liens utilisés lors de la résolution de ce problème : aussi bien
des liens vers des pages rappelant la syntaxe du langage utilisé pour la
résolution, la manière de réaliser tel ou tel type d'opération dans ce langage,
des rappels algorithmique, etc.

- https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/sort
- https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/toSorted
- https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/splice
