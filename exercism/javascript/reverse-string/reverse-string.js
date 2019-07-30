export const reverseString = (stringToReverse) => {
  let reversedString = '';
  for(let i = 0; i < stringToReverse.length; i++) {
    reversedString = stringToReverse.charAt(i) + reversedString;
  }
  return reversedString;
};
