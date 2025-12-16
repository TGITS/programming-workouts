# README

Défi du Jour 8 du [Advent Of Code 2025](https://adventofcode.com/2025).
Tentative de résolution avec [TypeScript](https://www.typescriptlang.org/) en
utilisant le runtime/outil [Deno](https://deno.com/)

Pour lancer le script : `deno -P --allow-read main.ts`
Pour lancer les tests : `deno test -P`

## Problème posé

### Première partie

Il faut relier des [_junction boxes_](https://en.wikipedia.org/wiki/Junction_box) repérer par des coordonnées dans l'espace (_x,y,z_).
Il faut déterminer les **X** (10 dans l'exemple, 1000 avec les données du problème) connections les plus courtes (selon la distance [euclidienne classique](https://en.wikipedia.org/wiki/Euclidean_distance)). Cela va créer des circuits plus ou moins grands.
Il faut extraire les 3 circuits les plus grands après ce travail de trouver les **X** connections les plus courtes induisant la création de circuit.

### Deuxième partie

On part de là où on s'est arreté à la fin de la partie 1, avec notre liste de circuits créés à partir des connections les plus courtes entre _junction boxes_.
Il faut continuer à connecter les _junction boxes_ jusqu'à produire un seul grand circuit.
Le résultat attendu est le produit des abscisses des 2 dernières _junction boxes_ qu'il a fallu connecté pour avoir le grand circuit unique.

## Idées pour la résolution

J'ai commencé la résolution sans trop me poser de question en codant la fonction pour récupérer les données d'entrée.
Quand je me suis aperçu que c'était un problème de graphes, je me suis dit que cela allait me prendre trop de temps à faire en TypeScript.
Oui, j'ai manqué de courage et pour me donner bonne conscience je me suis dit que je reviendrai dessus plus tard au calme pour le résoudre proprement en TypeScript. Là il faut avouer que Python et [Networkx](https://networkx.org/en/) permettent de sacrément simplifier l'ensemble du code.

Au final je n'ai fait ni la première ni la seconde partie en TypeScript.

## Ressources et liens utilisés

Ressources et liens utilisés lors de la résolution de ce problème : aussi bien
des liens vers des pages rappelant la syntaxe du langage utilisé pour la
résolution, la manière de réaliser tel ou tel type d'opération dans ce langage,
des rappels algorithmique, etc.

- [Kruskal's algorithm](https://en.wikipedia.org/wiki/Kruskal%27s_algorithm)
- [Disjoint-set data structure](https://en.wikipedia.org/wiki/Disjoint-set_data_structure)