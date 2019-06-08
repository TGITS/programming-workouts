export function twoFer(name = 'you') {
  return `One for ${name.trim() ? name : 'you'}, one for me.`;
}
