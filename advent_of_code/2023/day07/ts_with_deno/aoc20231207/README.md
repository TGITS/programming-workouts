# README

Défi du Jour 7 du [Advent Of Code 2023](https://adventofcode.com/2023).
Tentative de résolution avec [TypeScript](https://www.typescriptlang.org/) en
utilisant le runtime/outil [Deno](https://deno.com/)

Pour lancer le script : `deno -P --allow-read main.ts` Pour lancer les tests :
`deno test -P`

## Problème posé

### Première partie

On a une liste de main du jeu de cartes "Camel Cards" avec l'enchère associée
(_bid_), il faut calculer le gain total, qui se caclule par la somme de chaque
enchère multipliée par le rang de la main par ordre ascendant (la main la moins
forte a le rang 1). Bien sûr la liste de couple main/enchère n'est pas
initialement ordonnée

## Idées pour la résolution

### Première partie

Ma première idée de stratégie pour résourdre le problème est la suivante :

- Lire le fichier ligne par ligne pour obtenir une liste de couples main
  (_hand_, tableau de nombres) et enchère (un nombre).
  - Transformation chaque carte de la main en une valeur numérique, 13 pour le
    'A' et 1 pour le '2'
- Trier cette liste de couples par ordre ascendant
  - Cela implique de construire une fonction de comparaison dédiée qui sera
    utiliser dans le tri
- Une fois le tableau trié, une simple boucle dessus nous permettra de calculer
  la somme

La partie la plus délicate est la fonction de comparaison de 2 mains. C'est
notamment là où il faudra concentrer nos tests.

En termes de construction du code :

- on définit un type correspondant à notre tuple
- on définit une fonction de parsing qui va lire le fichier et produire une
  liste de tuple
  - on pourra prévoir des fonctions intermédiaires
- on définit une fonction de comparaison de 2 mains
  - on pourra prévoir une fonction qui calcule l'histogramme d'une main
- on définit une fonction qui finalise le calcul à partir des différentes
  fonctions

### Partie 2

Les grands principes sont les mêmes, ce qui changent :

- la manière de définir le type de la main
- la valeur du J, sachant que par contre la manière de départager ou le tri
  reste au final le même, c'est juste le paramétrage qui change

## Ressources et liens utilisés

Ressources et liens utilisés lors de la résolution de ce problème : aussi bien
des liens vers des pages rappelant la syntaxe du langage utilisé pour la
résolution, la manière de réaliser tel ou tel type d'opération dans ce langage,
des rappels algorithmique, etc.

- [Examples](https://docs.deno.com/examples/)
- [Reading files](https://docs.deno.com/examples/reading_files/)
- [Testing](https://docs.deno.com/runtime/fundamentals/testing/)
- [Array.prototype.sort()](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/sort)
- [JavaScript Array Sort](https://www.w3schools.com/js/js_array_sort.asp)
- [Sort an Array of Objects in JavaScript, TypeScript or Node.js](https://futurestud.io/tutorials/sort-an-array-of-objects-in-javascript-typescript-or-node-js)
- [4 Ways to Convert String to Character Array in JavaScript](https://www.samanthaming.com/tidbits/83-4-ways-to-convert-string-to-character-array/)
- [JavaScript - How to Get Character Array from String?](https://www.geeksforgeeks.org/javascript/how-to-get-character-array-from-string-in-javascript/)
- [String.prototype.split()](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String/split)
- [parseInt()](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/parseInt)
- [Deno nuggets: Process file line-by-line](https://medium.com/deno-the-complete-reference/deno-nuggets-process-file-line-by-line-d77a7caa3ff7)
- [Read Line by Line as Data Stream](https://decipher.dev/deno-by-example/advanced-readline/)
- [TypeScript Tuples](https://www.w3schools.com/typescript/typescript_tuples.php)
- [Typescript Set](https://www.geeksforgeeks.org/typescript/typescript-set/)
- [Set](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Set)
- [Sets in TypeScript](https://graphite.com/guides/typescript-sets)
- [Count Occurrences of All Items in an Array in JavaScript](https://www.geeksforgeeks.org/javascript/count-occurrences-of-all-items-in-an-array-in-javascript/)
- [Count Frequency of an Array Item in JavaScript](https://www.geeksforgeeks.org/javascript/count-frequency-of-an-array-item-in-javascript/)
- [How to Count Objects in an Array](https://www.freecodecamp.org/news/how-to-count-objects-in-an-array/)
- [TypeScript Hashmaps](https://www.convex.dev/typescript/core-concepts/data-structures/typescript-hashmap)
- [Map](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Map)
- [deno.json and package.json - Permissions](https://docs.deno.com/runtime/fundamentals/configuration/#permissions)
- [Module metadata - Concepts](https://docs.deno.com/examples/module_metadata_tutorial/#concepts)
