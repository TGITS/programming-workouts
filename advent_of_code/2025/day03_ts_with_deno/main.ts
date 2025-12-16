export type Bank = number[];

export function parseInput(filename: string): Bank[] {
  const data = Deno.readTextFileSync(filename);
  const lines = data.split("\n").map((line) => line.trim()).filter((line) =>
    line.length > 0
  );

  const banks: Bank[] = lines.map((line) =>
    line.split("").map((element) => parseInt(element))
  );
  return banks;
}

function findNextBiggestDigit(
  bank: Bank,
  nextIndex: number,
  numbersLeftToFind: number,
): string {
  let index = -1;
  let digit = 0;
  // Notre espace de recherche pour le prochain chiffre est entre nextIndex (indice de départ) et la fin du tableau moins le nombre de chiffres qu'il nous reste à trouver.
  // Comme notre condition de boucle est avec une comparaison stricte (<), on fait - (numbersLeftToFind - 1)
  for (let i = nextIndex; i < bank.length - (numbersLeftToFind - 1); i++) {
    // classique recherche du max dans un tableau
    // on cherche le premier max et son indice dans l'espace de recherche.
    // par contre on parcours tout l'espace de recherche pour être sûr de trouver le max.
    // On pourrait optimiser un peu en s'arrêtant dès qu'on trouve un 9, mais cela semble avoir peut d'intêret.
    // Sinon il faudrait précalculer les max dans chaque espace de recherche mais cela complexifierait le code pour un gain minime.
    const currentDigit = bank[i];
    if (currentDigit > digit) {
      index = i;
      digit = currentDigit;
    }
  }
  // Condition d'arrêt de la récursion : on a trouvé le dernier chiffre à ajouter au nombre final.
  if (numbersLeftToFind === 1) {
    return `${digit}`;
  }

  // Appel récursif pour trouver le chiffre suivant.
  // On passe l'indice suivant celui du chiffre trouvé pour ne pas réutiliser le même chiffre.
  // On décrémente le nombre de chiffres restant à trouver et on redéfinit les limites de l'espace de recherche.
  return `${digit}${
    findNextBiggestDigit(bank, index + 1, numbersLeftToFind - 1)
  }`;
}

// Fonction pour initier notre récursion tout en convertissant le résultat en nombre.
function findLargestPossibleVoltage(bank: Bank, numbersLeft: number): number {
  return parseInt(findNextBiggestDigit(bank, 0, numbersLeft));
}

export function findLargestPossibleVoltagePart1(bank: Bank): number {
  return findLargestPossibleVoltage(bank, 2);
}

export function findLargestPossibleVoltagePart2(bank: Bank): number {
  return findLargestPossibleVoltage(bank, 12);
}

// La fonction pour résourdre le problème était largement factorisable entre les 2 parties
// Je la garde privée au module suite au refactoring
// Dans l'absolue les fonctions solvePart1 et solvePart2 ne sont pas indispensables
// Mais je trouve que cela rend le code plus lisible et cela m'a évité une refacto d'une partie des tests.
function solve(filename: string, numbersLeft: number): number {
  const batteries: number[] = [];
  const banks = parseInput(filename);
  for (const bank of banks) {
    batteries.push(findLargestPossibleVoltage(bank, numbersLeft));
  }
  return batteries.reduce((a, b) => a + b, 0);
}

export function solvePart1(filename: string): number {
  return solve(filename, 2);
}

export function solvePart2(filename: string): number {
  return solve(filename, 12);
}

if (import.meta.main) {
  console.log("Solve Part 1 =", solvePart1("./data/input.txt")); // 17087 with my input data
  console.log("Solve Part 2 =", solvePart2("./data/input.txt")); // 169019504359949 with my input data
}
