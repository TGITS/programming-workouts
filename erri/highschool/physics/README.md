# README

## Création d'un environnement virtuel avec Python

Quand on utilise Python et des dépendances (c'est-à-dire d'autre bibliothèques) comme par exemple numpy ou matplotlib, il est préférable d'utiliser un environnement virtuel.
En effet, cela permet d'installer les bibliothèques _localement_ à l'environnement virtuel correspondant à votre projet, et non de manière générale à l'échelle de votre système.
Cela permet de pouvoir utiliser des versions différentes d'une même bibliothèque pour des projets différents et évite de potentiel collision de noms.
Cela demande un peu plus d'effort mais c'est une bonne pratque qui peut faire gagner beaucoup de temps et s'éviter quelques migraines à essayer de comprendre pourquoi un programme ne fonctionne pas.

Il y a plusieurs manières de créer et de gérer des environnements virtuels en Python, mais si après je pars sur l'utilisation de `venv` qui est fourni en standard avec Python quand on l'installe.

* Créer un environnement virtuel avec `venv` :
  * A la racine de votre répertoire de travail dans une invite de commandes (ouverte pour l'occasion ou depuis votre éditeur ou IDE):
    * `python -m venv venv`
      * Le deuxième `venv` n'est pas une erreur, c'est le nom du répertoire qui contiendra notre environnement virtuel, vous pouvez choisir le cas échéant une autre valeur.
      * On peu ajouter le paramètre `--prompt` pour avoir un prompt plus spécifique dans la ligne de commande.
      * `python -m venv --prompt "physics" venv`
  * Un répertoire `venv` doit être créé : ajouter ce répertoire dans le `.gitignore` si vous gérer le versionning de vos sources avec Git
* Activer l'environnement virtuel : `venv\Scripts\activate`
* Installer les bibliothèques dont vous avez besoin avec `pip`. Ci-après on donne l'exemple avec [NumPy](https://numpy.org/install/) et [matplotlib](https://matplotlib.org/stable/users/installing/index.html)
  * `pip install numpy`
  * `pip install matplotlib`
* A la fin de votre session de travail, désactiver l'environnement virtuel : `venv\Scripts\deactivate`

Penser à activer l'environnement virtuel avant de lancer les commandes : `venv\Scripts\activate`
A la fin de votre session de travail, penser à désactiver l'environnement virtuel : `venv\Scripts\deactivate` (ce n'est pas dramatique si ce n'est pas fait, si vous fermez l'invite de commande).
