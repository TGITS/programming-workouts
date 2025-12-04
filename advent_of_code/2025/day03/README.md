# README

Défi du Jour 3 du [Advent Of Code 2025](https://adventofcode.com/2025).
Tentative de résolution avec [TypeScript](https://www.typescriptlang.org/) en
utilisant le runtime/outil [Deno](https://deno.com/)

Pour lancer le script : `deno -P --allow-read main.ts` Pour lancer les tests :
`deno test -P`

## Problème posé

### Première partie

Il faut trouver un nombre à 2 chiffres le plus élevé possible dans une liste de
nombres entre 1 et 9, sachant qu'on ne peut pas réordonner les nombres, il faut
les prendre dans l'ordre de droite à gauche.

### Deuxième partie

C'est la même chose que pour la première partie mais il faut un nombre à 12
chiffres cette fois-ci avec les mêmes contraintes.

## Idées pour la résolution

### Première partie

Pour la première partie, j'étais partie sur une solution assez brute et
inélégante pour trouver le voltage

```typescript
function findLargestPossibleVoltage(bank: Bank): number {
  let firstDigit: number = 0;
  let firstDigitFound = false;
  let secondDigit: number = 0;
  let secondDigitFound = false;
  let currentIndex = 0;

  let num = 9;
  while (num >= 0) {
    const index = bank.indexOf(num, currentIndex);
    if (index != -1) {
      if (index == bank.length - 1) {
        if (!firstDigitFound && !secondDigitFound) {
          secondDigit = num;
          secondDigitFound = true;
          currentIndex = 0;
          num--;
        } else if (firstDigitFound && !secondDigitFound) {
          secondDigit = num;
          secondDigitFound = true;
          break;
        } else if (!firstDigitFound && secondDigitFound) {
          firstDigit = num;
          firstDigitFound = true;
          break;
        }
      } else {
        if (!firstDigitFound) {
          firstDigit = num;
          firstDigitFound = true;
          currentIndex = index + 1;
        } else if (firstDigitFound && !secondDigitFound) {
          secondDigit = num;
          secondDigitFound = true;
          break;
        }
      }
    } else {
      num--;
    }
    if (firstDigitFound && secondDigitFound) {
      break;
    }
  }
  return parseInt(`${firstDigit}${secondDigit}`);
}
```

La solution est inélégante mais fonctionne... Par contre elle ne passe pas à
l'échelle arrivée la deuxième partie.

### Deuxième partie

La solution initiale ne peut pas être adaptée (enfin peut-être mais il ne s'agit
pas de faire une bouse indescriptible non plus) pour la deuxième partie du
challenge, il faut trouver autre chose.

Si on pose la problématique différemment :

- si j'ai 2 chiffres à trouver :
  - il faut que je trouve le plus grand chiffre entre l'indice de début de la
    liste et l'indice du pénultième élément (avant-dernier élément)
    - je dois garder le dernier nombre pour le 2ème chiffre dans le _pire cas_.
    - Mon espace de recherche pour trouver le premier plus grand chiffre se
      limite aux indices entre 0 et la taille de la liste moins deux sachant que
      le dernier indice est à la taille de la liste moins 1.
  - une fois que je l'ai, il faut que je cherche l'élément entre l'indice où je
    me suis arrêté précédemment et le dernier élément.
- si j'ai 3 chiffres à trouver
  - il faut que je trouve le plus grand chiffre entre l'indice de début de la
    liste et l'indice de l'anté-pénultième élément (avant-avant-dernier élément)
    - je dois garder le dernier nombre pour le 2ème et 3ème chiffre dans le
      _pire cas_.
    - Mon espace de recherche pour trouver le premier plus grand chiffre se
      limite aux indices entre 0 et la taille de la liste moins 3 sachant que le
      dernier indice est à la taille de la liste moins 1.
  - une fois que je l'ai, c'est le même problème que pour la recherche de 2
    chiffres, c'est juste que l'indice de départ n'est pas le début de la liste
    mais l'indice suivant celui auquel on a arreté la recherche précédente.

Damned ! formulé ainsi on sent qu'il y a une solution récursive qui va être
paramétrée par la liste/tableau de chiffres, l'indice auquel on démarre la
recherche du chiffre courant et le nombre de chiffre encore à rechercher.

J'aurai dû partir directement sur cette approche plutôt que de partir sur la
première solution. Dans tous les cas, même avec une approche utilisant
directement des itérations plutôt que de la récursivité, ce qu'il faut c'est
bien gérer l'espace de recherche du plus grand chiffre.

## Ressources et liens utilisés

Ressources et liens utilisés lors de la résolution de ce problème : aussi bien
des liens vers des pages rappelant la syntaxe du langage utilisé pour la
résolution, la manière de réaliser tel ou tel type d'opération dans ce langage,
des rappels algorithmique, etc.
