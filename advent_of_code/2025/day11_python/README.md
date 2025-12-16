# README

Défi du Jour 11 du [Advent Of Code 2025](https://adventofcode.com/2025).
Tentative de résolution avec Python (sous forme de projet géré avec [uv](https://docs.astral.sh/uv/))
plutôt que d'utiliser [TypeScript](https://www.typescriptlang.org/) avec [Deno](https://deno.com/) comme pour les autres jours,
car la nature du problème (algorithme sur les graphes) se prêter à une résolution plus rapide (tout est relatif ! :-) que de faire en TypeScript.

- Pour lancer le script : `uv run main.py`
- Pour lancer les tests : `uv run pytest .`

## Problème posé

Pour les 2 parties, il s'agit de compter les chemins possibles.

## Idées pour la résolution

### Première partie

En utilisant Networkx c'est direct, il faut compter les chemins !

### Deuxième partie

Hélas, Networkx n'est pas d'un grand secours.
En effet, si on commence par une approche naïve on peut faire :

```python
def solve_part2(filename):
    graph = extract_data(filename)
    paths = nx.all_simple_paths(graph, "svr", "out")
    fft = "fft"
    dac = "dac"
    count = 0
    for path in paths:
        if fft in path and dac in path:
            count += 1

    return count
```

Hélas, cela prend vraiment, vraiment trop de temps.
Sachant que c'est un graphe dirigé, il faut faire par segment de graphe :

- svr => fft => dac => out
- svr => dac => fft => out

Comme c'est un graphe dirigé, on aura soit `svr => fft => dac => out` soit `svr => dac => fft => out` qui sera valable.

Mais même en adaptant, cela prend trop de temps.

Il a fallu trouver autre chose en comptant les chemins valides de manière récursive en exploitant la mémoïzation pour ne pas revenir sur les parties de chemin déjà explorer.

## Ressources et liens utilisés

Ressources et liens utilisés lors de la résolution de ce problème : aussi bien
des liens vers des pages rappelant la syntaxe du langage utilisé pour la
résolution, la manière de réaliser tel ou tel type d'opération dans ce langage,
des rappels algorithmique, etc.

* https://networkx.org/documentation/stable/reference/algorithms/simple_paths.html
* https://networkx.org/documentation/stable/auto_examples/drawing/plot_directed.html
* https://networkx.org/documentation/stable/tutorial.html
* https://www.geeksforgeeks.org/data-visualization/directed-graphs-multigraphs-and-visualization-in-networkx/
* https://www.geeksforgeeks.org/python/convert-generator-object-to-list-in-python/