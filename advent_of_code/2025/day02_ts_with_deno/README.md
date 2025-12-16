# README

Défi du Jour 2 du [Advent Of Code 2025](https://adventofcode.com/2025).
Tentative de résolution avec [TypeScript](https://www.typescriptlang.org/) en
utilisant le runtime/outil [Deno](https://deno.com/)

Pour lancer le script : `deno -P --allow-read main.ts` Pour lancer les tests :
`deno test -P`

Jour 2 : https://adventofcode.com/2025/day/2

## Problème posé

Il s'agit de trouver des identifiants (numériques) incorrects dans des plages de
valeurs. Entre les 2 parties, ce qui change, c'est la manière de caractériser
ces identifiants incorrects.

## Idées pour la résolution

### Première partie

Tel que formulé dans l'énoncé, l'identifiant est constitué de 2 parties qui se
repètent. Du coup c'est assez direct, les identifiants sont forcément d'une
taille multiple de 2 et il faut que les 2 moitiés de l'identifiant soit
identiques.

### Deuxième partie

La règle est plus complexe, il faut retrouver un motif qui se répète au moins 2
fois dans l'identifiant. Le plus facile en oeuvre c'est de passer par une
expression régulière. Au départ j'avaic penser à `(?:\d+){2,}` ou `\d+(?:\d+)+`
mais ce n'était pas la bonne regexp, cela ne correspondait pas à ce que je
voulais réellement faire, cela _matchait_ trop de valeurs. Il faut pouvoir
vérifier qu'on a bien une répétition d'un certain _pattern_. En découpant en 2
RegExp pour trouver les différents cas où on en a 2 ou cas où on a en plus de 2
: `/^(\d+)\1$/` et `/^(\d+)\1+$/`. Après vérification, on peut le faire en 1
seule RegExp avec `/^(\d+)\1{1,}$/`.

## Ressources et liens utilisés

Ressources et liens utilisés lors de la résolution de ce problème : aussi bien
des liens vers des pages rappelant la syntaxe du langage utilisé pour la
résolution, la manière de réaliser tel ou tel type d'opération dans ce langage,
des rappels algorithmique, etc.

- https://stackoverflow.com/questions/4739759/how-to-match-repeated-patterns
- https://regex101.com/
- https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Regular_expressions
- https://developer.mozilla.org/fr/docs/Web/JavaScript/Reference/Global_Objects/RegExp
- https://stackoverflow.com/questions/7068751/regex-to-find-two-or-more-consecutive-chars
- https://regexone.com/lesson/repeating_characters
- https://javascript.info/regexp-quantifiers
- https://www.regular-expressions.info/charclass.html
- https://upskilld.com/learn/matching-a-certain-number-of-repetitions-with-regex/
