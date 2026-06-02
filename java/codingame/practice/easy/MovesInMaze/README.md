# README

Proposition de solution au puzzle CodinGame ["Moves in Maze"](https://www.codingame.com/ide/puzzle/moves-in-maze) en Java

## Enoncé du problème

L'énoncé du problème est en anglais. Elle est reproduit ci-après à partir de [l'énoncé sur le site de CodinGame](https://www.codingame.com/ide/puzzle/moves-in-maze).

### Objectif

You are in a maze. You have to find the minimum number of moves to reach each cell from the starting point, and output those numbers in the initial maze.

The number of moves is represented using a character: `0-9` then `A-Z` (`A=10`, `B=11`, ... `Z=35`).

You may move from a cell to a neighbouring cell which is not a wall in any one of the four directions: left, right, up or down. The maze is periodic: if you go left you appear on the right if there is no wall, and vice versa, similarly with up/down.

There may be unreachable points.


The input maze is made of `#` for walls, `.` for free spaces and `S` for the starting position.
The output must be made of `#` for walls, `.` for unreachable points, and numbers `0-9`, `A-Z`.

### Entrée

- First line: two space-separated integers `w` and `h`, the width and height of the maze.
- `h` following lines: the maze.

### Sortie

- `h` lines: the maze with numbers for the reachable points.

### Contraintes

- 3 ≤ w
- h ≤ 30
- There are no more than 35 moves needed to reach a point.

### Exemple

#### Entrée

10 5
##########
#S.......#
##.#####.#
##.#.....#
##########

#### Sortie

##########
#01234567#
##2#####8#
##3#DCBA9#
##########

## Contraintes sur l'implémentation

L'implémentation sera réalisée en Java en utilisant pour le développement local [JBang](https://www.jbang.dev/).
En plus de la solution au problème en lui-même, il faut au préalable développer des _scripts_ en Java (toujours avec JBang) pour simuler en local la fourniture des données d'entrée et la validation des données de sorties. Des scripts en shell unix et en powershell pourront également être à développer pour le lancement des différents programme java avec JBang.
A noter que l'on part du principe qu'une version de JBang est disponible dans la ligne de commande du système.

## Structure du projet local

- `MovesInMaze.java` : solution CodinGame (lecture stdin, écriture stdout).
- `CodingameHarness.java` : harness local JBang avec 3 commandes:
	- `run <inputFile> [solutionFile]`
	- `judge <inputFile> <expectedFile> [solutionFile]`
	- `judge-all [testsDir] [solutionFile]`
- `tests/*.in` : entrées de test.
- `tests/*.out` : sorties attendues associées.
- `scripts/*.sh` et `scripts/*.ps1` : lanceurs Unix/PowerShell.

## Exécution locale

Depuis la racine du projet.

### Lancer la solution sur un cas (sans validation)

PowerShell:

```powershell
./scripts/run-case.ps1 sample
```

Unix:

```bash
./scripts/run-case.sh sample
```

### Valider un cas

PowerShell:

```powershell
./scripts/judge-case.ps1 sample
```

Unix:

```bash
./scripts/judge-case.sh sample
```

### Valider tous les cas

PowerShell:

```powershell
./scripts/judge-all.ps1
```

Unix:

```bash
./scripts/judge-all.sh
```

## Ajouter un nouveau test

Créer un couple de fichiers avec le même préfixe:

- `tests/mon-test.in`
- `tests/mon-test.out`

Puis lancer la validation complète.

### Générer automatiquement un squelette

PowerShell:

```powershell
./scripts/new-test.ps1 mon-test
```

Unix:

```bash
./scripts/new-test.sh mon-test
```

Pour écraser un test existant:

PowerShell:

```powershell
./scripts/new-test.ps1 mon-test -Force
```

Unix:

```bash
./scripts/new-test.sh mon-test --force
```
