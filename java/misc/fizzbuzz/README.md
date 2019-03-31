# Kata FizzBuzz

Tout en mettant en œuvre une approche TDD, proposer une implémentation suivant les règles édictées dans les paragraphes ci-après.

## Règles 1

Afficher les nombres de 1 à 100
Pour les multiples de 3, afficher FIZZ à la place du nombre
Pour les multiples de 5, afficher BUZZ à la place du nombre
Pour les multiples de 3 et 5, afficher FIZZBUZZ

### Compléments

Vous pouvez générer un projet Java avec Maven en utilisant un archétype comme ci-après :

```
mvn archetype:generate -B \
-DarchetypeGroupId=org.apache.maven.archetypes \
-DarchetypeArtifactId=maven-archetype-quickstart \
-DgroupId=programming.workout \
-DartifactId=fizzbuzz -Dversion=1.0.0
```

Il ne vous reste plus qu'à importer ce projet dans votre IDE ou éditeur préféré. Vous pouvez bien sûr créer directement le projet dans votre IDE ou avec un autre outil de *build* comme Gradle par exemple.

## Règle 2

Il faut que cela marche pour une liste quelconque de nombres

- Une liste de nombres en entrée
- Une liste de chaines de caractères en sortie avec
  - Pour les multiples de 3, afficher FIZZ à la place du nombre
  - Pour les multiples de 5, afficher BUZZ à la place du nombre
  - Pour les multiples de 3 et 5, afficher FIZZBUZZ
  - Pour les autres nombres, l'afficher sous forme de chaîne de caractère sans transformation

## Règle 3 : Paramétrisation

Il faut que 3, 5, FIZZ et BUZZ soient des paramètres modifiables de l’application

## Règle 4 : Paramétrer le paramétrage

On souhaite pouvoir uniquement paramétrer le couple (3,FIZZ) ou juste le couple (5,BUZZ) ou les 2 couples (3,FIZZ) et (5,BUZZ) (ce qui correspond au cas de la Règle 3).
Au sein d’un couple on veut pouvoir faire varier la valeur du nombre en entrée ou de la chaine en sortie

## Règle 5 : Changement de Transformation

On souhaite avoir différents type de règles, pas uniquement les multiples
Par exemple, on veut pouvoir ajouter une règle de transformation d’un nombre en une chaine s’il comporte le chiffre « N ».

## Règle 6 : une solution originale

L’algorithme classique de FizzBuzz relève d’un copyright et il faut trouver un nouvel algorithme
Par exemple une solution qui n’utilise pas de if/else pour une liste de nombre entre 1 et une valeur donnée

## Pour aller plus loin

Implémenter une solution récursive

Implémenter une solution fonctionnelle avec un *fold* (utiliser Vavr ?)

