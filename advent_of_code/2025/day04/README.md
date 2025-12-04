# README

Défi du Jour 4 du [Advent Of Code 2025](https://adventofcode.com/2025).
Tentative de résolution avec [TypeScript](https://www.typescriptlang.org/) en
utilisant le runtime/outil [Deno](https://deno.com/)

Pour lancer le script : `deno -P --allow-read main.ts` Pour lancer les tests :
`deno test -P`

## Problème posé

### Première partie

Il faut trouver les positions dans une grille des rouleaux de papier qui sont
entourés par moins de 4 autres rouleaux de papiers. C'est une inégalité stricte.

### Deuxième partie

Pour la deuxième partie on itère le processus de rechercher les rouleaux
accessibles : On recherche les rouleaux accessibles, on les retire de la grille
et on relance le processus de trouver les rouleaux accessibles.

## Idées pour la résolution

### Première partie

Je transforme le tableau de chaine de caractères par un tableau de chiffre, 1
pour un rouleau et 0 si c'est vide. En faisant la somme des contenus des voisins
je sais si c'est inférieur à 4 ou peux. Il faut une fonction qui donne la liste
indices des voisins, on peut faire un calcul bourrin et filtrer ceux qui ont une
coordonnées négatives ou plus grandes que la taille de la grille. Il faut
calculer la longueur et la largeur de la grille.

### Deuxième partie

On fait une boucle supplémentaire, en conservant la liste des rouleaux à retirer
de la grille et on recalcule pour trouver les nouveaux rouleaux à retirer. On
boucle tant que l'on a des rouleux à retirer. La solution de la partie 2 a été
faite rapidement, avec un copier-coller et une adaptation de la partie 1. Cela
mériterait un petit refactoring pour factoriser un peu le code et éviter de la
duplication. A mettre en place quand un peu de temps.

## Ressources et liens utilisés

Ressources et liens utilisés lors de la résolution de ce problème : aussi bien
des liens vers des pages rappelant la syntaxe du langage utilisé pour la
résolution, la manière de réaliser tel ou tel type d'opération dans ce langage,
des rappels algorithmique, etc.
