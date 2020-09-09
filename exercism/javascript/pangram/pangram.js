const alphabet = new Set("abcdefghijklmnopqrstuvwxyz".split(''));

export const isPangram = (s) => {
  return new Set(s.toLowerCase().split('').filter(x => alphabet.has(x))).size == alphabet.size;
};
