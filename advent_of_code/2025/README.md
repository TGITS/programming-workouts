# TheGeekInTheShell

## Membres

- Christophe Vaudry (cvaudry@norsys.fr)

## 🎄 À propos

Ce dépôt contient des solutions pour l'[Advent of Code 2025](https://adventofcode.com/2025).

L'Advent of Code est un calendrier de l'Avent de petits défis de programmation qui peuvent être résolus dans n'importe quel langage de programmation. 
Ce projet me permet de collecter et versionner les solutions que je pourrais produire. 
Cette année il n'y aura que 12 jours de défi (les années précédentes, il y en avait 25 jours).

Cette année mon calendrier professionnel et personnel chargé sur la première quinzaine de décembre ne me permettra pas de participer pour essayer de viser une place sur le podium dans les 12 jours prévus.
Néanmoins, je compte profiter de cet "Advent of Code 2025" pour approfondir [TypeScript](https://www.typescriptlang.org/) et essayer le runtime JavaScript [Deno](https://deno.com/).
A défaut d'avoir le temps de réaliser le calendrier en 12 jours, je me fixe de le faire sur le mois de décembre, donc en 31 jours maximum.
Mon objectif premier reste l'apprentissage de TypeScript et de Deno.

## 📁 Structure du projet

Le dépôt est organisé par jour de challenge.
La structure typique dun projet solution devrait être similaire à ce qui suit

```
advent-of-code-2025/
├── day01/
│    ├── deno.json       # Fichier pour la gestion du projet Deno
│    ├── deno.lock       # Fichier pour la gestion du projet Deno
│    ├── input.txt       # Fichier du challenge
│    ├── input_test.txt  # Fichier de test récupéré à partir des informations sur le site
│    ├── main.ts         # Programme TypeScript avec la (tentative) de solution
│    └── main_test.ts    # Test du code TypeScript
├── day02/
├── day03/           
├── ...
└── day12/    
```

Ainsi chaque dossier `dayXX` contient :
- Le  code pour le du défi du jour
- Les fichiers de configuration spécifique à Deno
- Les fichiers d'entrée (input)
- Les tests éventuels

## 🎯 Objectif

Ce projet a pour but à mon niveau :
- Pratiquer la programmation avec TypeScript
- Me familiariser avec Deno
- Conserver une trace de ma progression tout au long du mois de décembre
- Partager mes solutions avec la communauté
- En profiter pour me prendre la tête sur des problèmes en tout genre

## 🚀 Utilisation

J'ajouterais les solutions dans le dossier correspondant et dès que possible.

## 📝 Note

J'essaierai d'en tirer un bilan sur mon apprentissage/approfondissement de TypeScript et sur l'utilisation de Deno.

---

**Bon code et joyeux Advent of Code ! 🎄✨**
