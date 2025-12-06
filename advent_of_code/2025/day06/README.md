# README

Défi du Jour 6 du [Advent Of Code 2025](https://adventofcode.com/2025).
Tentative de résolution avec [TypeScript](https://www.typescriptlang.org/) en
utilisant le runtime/outil [Deno](https://deno.com/)

Pour lancer le script : `deno -P --allow-read main.ts` Pour lancer les tests :
`deno test -P`

## Problème posé

### Première partie

Il faut faire des calculs sur des données d'entrée en colonne, l'opération étant
à la fin.

### Deuxième partie

Toujours des calculs, mais le sens de lecture n'est pas le même (de droite à
gauche au lieu de gauche à droite) et il faut lires les nombres en colonnes en
tenant compte du formattage. PAr contre cette fois-ci on ne peut plus supprimer
les caractères espaces, le formattage devient significatif.

## Idées pour la résolution

### Première partie

Pas super compliqué a priori, le plus de boulot est demandé par la fonction de
parsing et de traitement des données.

### Deuxième partie

Comme d'habitude, il faut bien regarder la tête des données et bien comprendre
ce qui est attendu. Ici je suis partie sur un padding, mais ce qui compte c'est
l'alignement des chiffres par rapport à l'opérateur qui marque le début de la
colonne. Il faut que je revois ma manière de lire les lignes sur laquelle
j'étais initialement parti.

Les fonctions ci-après que j'ai fait pour la partie 2 doivent être revues ou
supprimées : je n'ai plus besoin de la fonction `maxLength()` a priori et je
doit revoir complètement ma fonction `parseInputPart2()`.

```typescript
export function maxLength(strings: string[]): number {
  let max = 0;
  for (const s of strings) {
    if (max < s.length) {
      max = s.length;
    }
  }
  return max;
}

export function parseInputPart2(filename: string): ProblemPart2[] {
  const problems: ProblemPart2[] = [];
  const data: string[][] = Deno.readTextFileSync(filename).split("\n").map((
    line,
  ) => line.trim()).filter((line) => line.length > 0).map((line) =>
    line.split(/\s+/).map((element) => element.trim())
  );
  const operations = data.pop() || [];
  for (const operation of operations) {
    problems.push({ numbers: [], rawData: [], operation: operation });
  }
  for (let i = 0; i < data.length; i++) {
    for (let j = 0; j < data[i].length; j++) {
      problems[j].rawData[i] = data[i][j];
    }
  }

  for (const problem of problems) {
    console.log(`problem :\n${problem.rawData}\n${problem.operation}`);
    const max = maxLength(problem.rawData);
    const nbLines = problem.rawData.length;
    const numbers = [];
    for (let j = max - 1; j >= 0; j--) {
      let number = "";
      for (let i = 0; i < nbLines; i++) {
        problem.rawData[i] = problem.rawData[i].padStart(max, " ");
        number = number + problem.rawData[i].charAt(j);
      }
      numbers.push(parseInt(number.trim()));
    }
    problem.numbers = numbers;
  }

  return problems;
}
```

Dans la foulée il faut que je revois mes tests. Plus besoin des tests pour
`maxLength`.

```typescript
Deno.test("Testing the function that compute the longest string from a list of strings", function maxLengthTest() {
  let expected = ["51", "387", "215"];
  assertEquals(maxLength(expected), 3);
  expected = ["123", "45", "6"];
  assertEquals(maxLength(expected), 3);
  expected = ["12", "45", "6"];
  assertEquals(maxLength(expected), 2);
  expected = ["2", "5", "6"];
  assertEquals(maxLength(expected), 1);
});
```

Au final, j'ai galéré pour bien gérer la dernière _"colonne"_ et je n'ai pas
réussi à trouver une solution élégante pour la gérer. Cela finit avec un gros
`if` assez immonde... mais bon cela donne quand même le résultat correct au final.
Je me suis encore fait avoir en utilisant un `for...in` au lieu d'un `for...of`,
mauvais réflexe venant d'autres langages. 
Comme la fin de ligne était sensible et qu'on ne pouvait pas utiliser `trim()` pour supprimer les caractères espaces,
j'ai eu une problématique d'encodage des fins de lignes windows/unix.

## Ressources et liens utilisés

Ressources et liens utilisés lors de la résolution de ce problème : aussi bien
des liens vers des pages rappelant la syntaxe du langage utilisé pour la
résolution, la manière de réaliser tel ou tel type d'opération dans ce langage,
des rappels algorithmique, etc.

- https://www.freecodecamp.org/news/javascript-string-split-example-with-regex/
- https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String/split
- https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/pop
- https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String/substring
- https://medium.com/@ryan_forrester_/javascript-check-if-a-string-is-a-number-explained-simply-d8aaa6189841