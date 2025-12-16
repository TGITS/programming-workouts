# README

Défi du Jour 12 du [Advent Of Code 2025](https://adventofcode.com/2025).
Tentative de résolution avec [TypeScript](https://www.typescriptlang.org/) en
utilisant le runtime/outil [Deno](https://deno.com/)

Pour lancer le script : `deno -P --allow-read main.ts` Pour lancer les tests :
`deno test -P`

## Problème posé

Le problème semble quasiment insurmontable présenter comme il l'est. Bon, déjà
il y a 2 heuristiques qui permettent de déterminer à grosses mailles si les
cadeaux tiennent.

- Si la surface occupée par les cadeaux est plus grande que la surface de la
  zone concernée, cela ne pourra jamais tenir
- Si la zone pour poser les cadeaux peut être divisée en carré de 3 par 3 de
  sorte que tous les cadeaux puissent tenir, cela rentre.

En fait, rien qu'en fasaint la première heuristique, on a le résultat attendu.
Contrairement à ce que laisse entendre l'énoncé, il n'y a pas besoin de faire
des rotations de figure et essayer de voir comment on peut les assembler. Ouf !
Cela ne semblait pas trivial. Le truc c'était que pour une fois on avait un jeu
de données favorable mais l'énoncé laisser présager le pire.

## Idées pour la résolution

## Ressources et liens utilisés

Ressources et liens utilisés lors de la résolution de ce problème : aussi bien
des liens vers des pages rappelant la syntaxe du langage utilisé pour la
résolution, la manière de réaliser tel ou tel type d'opération dans ce langage,
des rappels algorithmique, etc.
