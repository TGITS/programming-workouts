# README

Défi du Jour 7 du [Advent Of Code 2025](https://adventofcode.com/2025).
Tentative de résolution avec [TypeScript](https://www.typescriptlang.org/) en
utilisant le runtime/outil [Deno](https://deno.com/)

Pour lancer le script : `deno -P --allow-read main.ts` Pour lancer les tests :
`deno test -P`

## Problème posé

### Première partie

Compter le nombre de fois où le rayon se divise.

### Deuxième partie

Compter le nombre de _timeline_ possible. C'est-à-dire qu'à chaque possibilité
de division on a une nouvelle timeline possible. Le point délicat c'est comment
on considère quand on a la même _timeline_ mais avec des particules venant de
_splitter_ différent (quand les rayons se superposent)

## Idées pour la résolution

### Première partie

Je me suis fait avoir sur le fonctionnement des `Set` en JavaScript/TypeScript,
cela m'a fait perdre pas mal de temps. Mais bon c'est l'occasion d'apprendre les
spécificités du langage sur ce point. Cela a également été l'occasion de
découvrir la méthode de `console.table` de Deno, assez sympa pour afficher des
tableaux simplement et proprement.

### Deuxième partie

C'est quoi une _timeline_ ? Comment je la matérialise au niveau du code ? Le
nombre de _timelines_ cela sera le nombre de rayons qui arrive en bas, en
comptant les duplications. Bien sûr cette première solution fonctionne sur les
données de tests mais pas sur le cas complet ! Cela fait exploser le programme,
cela croit de manière exponentielle. Tant pis, il faut revoir la copie !

```typescript
export function solvePart2(filename: string): number {
  const grid = parseInput(filename);
  let currentBeams: Coordinate[] = [];
  let nextBeams: Coordinate[] = [];
  const start = findStart(grid);
  currentBeams.push({ i: start.i + 1, j: start.j });
  let currentLineIndex = start.i + 2;
  while (currentLineIndex < grid.length) {
    for (const currentBeam of currentBeams) {
      const nextI = currentBeam.i + 1;
      const nextJ = currentBeam.j;
      if (grid[nextI][nextJ] === "^") {
        if (nextJ - 1 >= 0) {
          nextBeams.push({ i: nextI, j: nextJ - 1 });
        }
        if (nextJ + 1 < grid.length) {
          nextBeams.push({ i: nextI, j: nextJ + 1 });
        }
      } else {
        nextBeams.push({ i: nextI, j: nextJ });
      }
    }
    currentBeams = nextBeams;
    nextBeams = [];
    currentLineIndex++;
  }
  return currentBeams.length;
}
```

Au final, la fonction pour résoudre le problème est la suivante :

```typescript
export function solvePart2(filename: string): number {
  const grid = parseInput(filename);
  let currentBeams: Map<number, number> = new Map<number, number>();
  let nextBeams: Map<number, number> = new Map<number, number>();
  const start = findStart(grid);
  currentBeams.set(start.j, 1);
  let currentLineIndex = 1;
  while (currentLineIndex < grid.length - 1) {
    currentBeams.forEach((v, k, _current) => {
      if (grid[currentLineIndex][k] === "^") {
        nextBeams.set(k - 1, (nextBeams.get(k - 1) ?? 0) + v);
        nextBeams.set(k + 1, (nextBeams.get(k + 1) ?? 0) + v);
      } else {
        nextBeams.set(k, (nextBeams.get(k) ?? 0) + v);
      }
    });
    currentBeams = nextBeams;
    nextBeams = new Map<number, number>();
    currentLineIndex++;
  }
  return Array.from(currentBeams.values()).reduce(
    (previous, current) => previous + current,
    0,
  );
}
```

Au lieu de conserver chaque rayon et faire exploser Deno, je consolide les
résultats à chaque ligne. J'ai une _map_ qui contient chaque colonne qui
correspond à un ou plusieurs rayons pour la ligne en cours. Pour chacun de ces
rayons de la ligne en cours, je teste si l'élément juste en dessous est un
déflecteur ou pas. Si c'est un déflecteur, je remplace la valeur correspondant
au rayon dans la _map_ par 2 nouvelles valeurs correspondant aux 2 nouveaux
rayons créés par le déflecteur, en récupérant la valeur qui peut déjà s'y
trouver et en y ajoutant la valeur qu'avait le rayon initiale. S'il n'y a pas la
valeur je reporte juste la valeur précédente. Je passe par une deuxième _map_,
je cycle entre une _map_ pour qui contient les informations pour les rayons de
la ligne en cours et une _map_ pour les rayons de la prochaine ligne. A la fin
de la boucle la _map_ des rayons de la prochaine ligne, devient celle de la
ligne en cours, et celle des prochaines lignes est réinitialisée. J'aurais
peut-être pu m'en sortri avec une seule _map_ en utilisant `delete`, mais j'ai
préféré ne pas modifier la _map_ sur laquelle j'étais en train d'effectuer
l'itération. On notera que comme je récupère une valeur dans la _map_ des rayons
de la prochaine ligne qui peut ne pas exister, j'utilise le _nullish coalescing
operator_ afin de retourner 0 si la valeur n'existe pas déjà dans la _map_ et
que la valeur retournée est `undefined`. Si on ne tombe pas sur un déflecteur,
il ne faut pas oublier de _reporter_ le rayon et la valeur associée dans la
_map_ des rayons de la prochaine ligne. A noter que je ne boucle que jusqu'au
nombre de lignes moins 2, car dans la boucle, je regarde toujours la ligne
suivante. Au final, je calcule la somme des des valeurs dans la _map_ pour
connaitre le nombre de _timelines_.

## Ressources et liens utilisés

Ressources et liens utilisés lors de la résolution de ce problème : aussi bien
des liens vers des pages rappelant la syntaxe du langage utilisé pour la
résolution, la manière de réaliser tel ou tel type d'opération dans ce langage,
des rappels algorithmique, etc.

- https://www.geeksforgeeks.org/node-js/how-to-print-console-without-trailing-newline-in-node-js/
- https://stackoverflow.com/questions/64398431/how-to-console-log-without-a-newline-in-deno
- https://docs.deno.com/api/node/console/~/Console
- https://nodejs.org/docs/latest-v22.x/api/util.html#utilformatformat-args
- https://developer.mozilla.org/fr/docs/Web/JavaScript/Reference/Global_Objects/Set
- https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/from
- https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Equality_comparisons_and_sameness
- https://stackoverflow.com/questions/201183/how-can-i-determine-equality-for-two-javascript-objects
- https://www.freecodecamp.org/news/javascript-comparison-operators-how-to-compare-objects-for-equality-in-js/
- https://www.greatfrontend.com/questions/quiz/how-do-sets-and-maps-handle-equality-checks-for-objects
- https://stackoverflow.com/questions/29759480/how-to-customize-object-equality-for-javascript-set
- https://medium.com/@conboys111/javascript-sets-how-does-the-equality-check-work-1c193c6ef047
- https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/includes
- https://medium.com/norsys-octogone/op%C3%A9rateurs-de-gestion-des-valeurs-nulles-en-javascript-typescript-a6aeaa1ed47e
- https://developer.mozilla.org/fr/docs/Web/JavaScript/Reference/Global_Objects/Map/get
- https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Map/forEach
- https://www.geeksforgeeks.org/javascript/how-to-iterate-over-map-elements-in-javascript/

### Humour

- https://www.reddit.com/media?url=https%3A%2F%2Fpreview.redd.it%2Fyear-2025-day-7-part-2-it-just-works-v0-4edbhcio5r5g1.jpeg%3Fwidth%3D320%26crop%3Dsmart%26auto%3Dwebp%26s%3D6628e8a0eae027f61035a55cd770e18488258dd7
- https://www.reddit.com/media?url=https%3A%2F%2Fpreview.redd.it%2F2025-day-07-part-2-i-dont-feel-so-good-mr-eric-v0-6gq9xzucgq5g1.jpeg%3Fwidth%3D320%26crop%3Dsmart%26auto%3Dwebp%26s%3Dc8bcb9c4ffb005769c09bc550184f5c2d7a12f44

### Visualization

- https://www.reddit.com/r/adventofcode/comments/1pgbz9m/2025_day_7_part_2_honeycombs/
- https://www.reddit.com/r/adventofcode/comments/1pgfa0g/2025_day_7_part_12c_visualization_of_part_one/
- https://www.reddit.com/r/adventofcode/comments/1pgbg8a/2025_day_7_part_2_visualization_for_the_sample/
- https://www.reddit.com/r/adventofcode/comments/1pgdszb/2025_day_07_part_2_visualiser_for_on_solve/
- https://www.reddit.com/r/adventofcode/comments/1pgbgsk/2025_day_7_visualization/
- https://www.reddit.com/r/adventofcode/comments/1pgdelw/2025_day_7_part_1_visualization_of_depthfirst/
