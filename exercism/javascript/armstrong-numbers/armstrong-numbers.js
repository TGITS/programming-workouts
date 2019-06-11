export const validate = input => {
  let inputAsString = input.toString();
  let power = inputAsString.length;
  let sum = Array.from(inputAsString).map(s => parseInt(s)).map(num => Math.pow(num,power)).reduce((accumulator, currentValue) => accumulator + currentValue);
  return sum === input;
};