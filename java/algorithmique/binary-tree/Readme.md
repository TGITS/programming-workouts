# README

## Procédure

* La branche `master` contient les versions complètes des exercices avec les solutions/corrections (plus précisément, 
une solution/correction).
* Créer une branche spécifique au candidat ou à la personne voulant s'entrainer 
et supprimer le code des méthodes
  * on laisse juste les tests unitiaires qui sont la spécification de ce qu'il y a à faire
  * le code initial peut ne pas compiler, l'idée est de voir la démarche du candidat.
* Eventuellement penser à modifier les tests pour les ignorer tous sauf un et ainsi faire 
progresser le candidat en activant les tests un par un.

## Suggestions

* L'exercice est très orienté algorithmique. Il est trivial pour qui connait les notions de parcours infixe, postfixe et préfixe d'un arbre binaire. Il faut connaitre la récursivité. Selon le candidat, le temps disponible et ce que l'on veut tester, on peut faire implémenter la structure de données et les parcours,  tous les parcours (la structure de données étant fournies), ou l'un des parcours. 

* Côté récursivité, cela permet de poser des questions sur la récursivité, son intérêts (pas besoin de variable de boucle, solution plus déclarative) et ses écueils (StackOverFlow), la TCO (Tail Call Optimisation), ce qu'il en ait en Java avec la TCO (le compilateur ne l'implémente pas) et les implications pour la performance.
  - Cela permet de tester les connaissances générales du candidat
    - Quelles langages implémentent la TCO (par exemple Lua ou Haskell, et les langages fonctionnelles) ?
    - Comment font les langages fonctionnelles sur la JVM pour contourner les problématiques de TCO (par exemple Scala ou Clojure)
      - Annotation ou structure spéciale qui permet au compilateur de transformer la récursion sous forme itérative de manière transparente pour l'utilisateur.
* Il peut être intéressant de discuter avec le candidat sur les améliorations possibles, les limites de l'implémentation actuelle, etc.
  * L'implémentation est un peu réductrice (pourquoi une chaine plutôt qu'une liste par exemple ?), à quoi sert le contenu ? Pourquoi est-ce que l'Id n'est pas générique lui aussi, 
  quitte à faire en sorte qu'il implémente comparable ? 
## Tags

* Tags

* Java
* Algorithmique
* Generics
* Arbre binaire
* Récursivité