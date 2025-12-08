# README

Défi du Jour 8 du [Advent Of Code 2025](https://adventofcode.com/2025).
Tentative de résolution avec Python (sous forme de projet géré avec [uv](https://docs.astral.sh/uv/))
plutôt que d'utiliser [TypeScript](https://www.typescriptlang.org/) avec [Deno](https://deno.com/) comme pour les autres jours,
car la nature du problème (algorithme sur les graphes) se prêter à une résolution plus rapide (tout est relatif ! :-) que de faire en TypeScript.

- Pour lancer le script : `uv run main.py`
- Pour lancer les tests : `uv run pytest .`
  - Il faudrait qu'il y ait des tests... je n'ai pas eu une approche TDD pour ce puzzle et je n'ai pas pris le temps d'écrire des tests lors de la première itération.

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

### Première partie

Python et [Networkx](https://networkx.org/en/) permettent de sacrément simplifier l'ensemble du code par rapport à TypeScript.
A la lecture des données d'entrée, j'ai ma liste de _junction boxes_ et j'ai initialisé un graphe `Networkx` dont les noeuds sont les boites.

Je calcule les distances pour une combinaison de boites (on utilise à cette fin l'excellent [itertools](https://docs.python.org/3/library/itertools.html) avec ici la fonction [combinations](https://docs.python.org/3/library/itertools.html#itertools.combinations)). Cela permet de sortir une liste de tuples (distance, boite1, boite2), que l'on trie par ordre croissant.

Il ne reste plus qu'à prendre les **X** premiers (10 pour l'exemple, 1000 avec les données du puzzle) de cette liste, et de créer les arcs entre les noeuds correspondants dans notre graphe créé avec Networkx et initialisé avec nos boites.

On n'a plus qu'à récupérer la liste des circuits, en extraire la taille de ce circuit, trier par ordre descendant la liste obtenue, prendre les 3 premiers éléments de cette liste et en calculer le produit.

### Deuxième partie

A la fin de la première partie on a un graphe avec un certain nombre de circuit, et l'ensemble des distances triées par ordre croissant pour les combinaisons de boites.
On a déjà fait les **X** premiers, on repart de là et on ajoute de nouveaux arcs dans le graphe, jusqu'à ce que le graphe soit entièrement connecté (fonction [is_connected](https://networkx.org/documentation/stable/reference/algorithms/generated/networkx.algorithms.components.is_connected.html)).
En sortie de boucle on n'a plus qu'à récupérer les abscisses des 2 dernières boites connectées et caculer le produit.

## Ressources et liens utilisés

Ressources et liens utilisés lors de la résolution de ce problème : aussi bien
des liens vers des pages rappelant la syntaxe du langage utilisé pour la
résolution, la manière de réaliser tel ou tel type d'opération dans ce langage,
des rappels algorithmique, etc.

- [Networkx](https://networkx.org/en/)
- [Kruskal's algorithm](https://en.wikipedia.org/wiki/Kruskal%27s_algorithm)
- [Disjoint-set data structure](https://en.wikipedia.org/wiki/Disjoint-set_data_structure)
- [Pytest](https://docs.pytest.org/en/stable/getting-started.html)
