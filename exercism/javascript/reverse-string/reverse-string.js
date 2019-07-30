export const reverseString = (stringToReverse) => {
  let reversedString = '';
  for(var i = stringToReverse.length-1; i >=0; i--) {
    reversedString += stringToReverse.charAt(i);
  }
  return reversedString;
};
