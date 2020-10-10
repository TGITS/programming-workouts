const alphabet = new Set("abcdefghijklmnopqrstuvwxyz".split(''));

export const isPangram = (s) => {
  return new Set(s.toLowerCase().split('').filter(x => alphabet.has(x))).size == alphabet.size;
  /* The Exercism mentor suggested the use of String.match (https://developer.mozilla.org/fr/docs/Web/JavaScript/Reference/Objets_globaux/String/match). 
    We can use it as follow : return new Set(s.toLowerCase().match(/[a-z]/g)).size == alphabet.size; 
    La méthode match() retourne le tableau des correspondances entre la chaîne courante et une expression rationnelle.
    Ainsi ici cela retournera un tableau de toutes les corespondances et nous créerons un ensemble à parti de ce tableau. 
    Si pour chaine mise en minuscule "match" toutes les lettres entre 'a' et 'z' sont retournées, on a bien un pangram. */
};
