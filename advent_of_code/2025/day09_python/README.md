# README

Défi du Jour 9 du [Advent Of Code 2025](https://adventofcode.com/2025).
Tentative de résolution avec Python (sous forme de projet géré avec [uv](https://docs.astral.sh/uv/))
plutôt que d'utiliser [TypeScript](https://www.typescriptlang.org/) avec [Deno](https://deno.com/) comme pour les autres jours,
car la nature du problème (algorithme sur les graphes) se prêter à une résolution plus rapide (tout est relatif ! :-) que de faire en TypeScript.

- Pour lancer le script : `uv run main.py`
- Pour lancer les tests : `uv run pytest .`

## Problème posé

### Première partie

On a une liste de coordonnées de tuile rouge pavant une salle.
Il faut trouver le rectangle ayant une tuile rouge dans 2 coins opposés du rectangle 
ayant la plus grande surface

### Deuxième partie

## Idées pour la résolution

### Première partie

A partir d'une combinaison de toutes les coordonnées, il faut calculer la surface et prendre la plus grande.
En python, c'est quasiment aussi directe que cela à traduire en code.

### Deuxième partie

Pour la deuxième partie, il y a une condition sur les rectangles qu'on peut faire. 
Il faut que le rectangle ne soit constitué que de tuile rouge ou verte.
Chaque tuile rouge est connectée à une tuile rouge avant et une tuile rouge après par une ligne droite de tuile verte.
La liste de coordonnées de tuiles boucle, de sorte que la première tuile rouge est connectée à la dernière tuile rouge.
Les tuiles qui sont adjacentes dans la liste de tuiles seront toujours sur la même ligne ou la même colonne.

Idée de solution :

- Génération de la liste des bords externes, divisés par vertical et horizontal
- Determiner si chaque bord peut être étendu dans chaque direction tout en restant dans le "polygone" en utilisant la liste des bords perpendiculaires. Les étendre jusqu'à ce qu'il rencontre un autre bord.
- Pour chaque rectangle, regarder si ses bords sont dans le polygone global, et si c'est le cas conserver la valeur s'il est plus grande que la version précédemment conserver.

## Ressources et liens utilisés

Ressources et liens utilisés lors de la résolution de ce problème : aussi bien
des liens vers des pages rappelant la syntaxe du langage utilisé pour la
résolution, la manière de réaliser tel ou tel type d'opération dans ce langage,
des rappels algorithmique, etc.

- https://docs.python.org/3/library/itertools.html#itertools.pairwise
- [Find if two rectangles overlap | GeeksforGeeks](https://www.youtube.com/watch?v=wx0nyomRS_U)
- [Find if two rectangles overlap](https://www.geeksforgeeks.org/dsa/find-two-rectangles-overlap/)
- [How to check if a given point lies inside or outside a polygon?](https://www.geeksforgeeks.org/dsa/how-to-check-if-a-given-point-lies-inside-a-polygon/)