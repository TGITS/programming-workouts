export const validate = input => {
  const inputAsString = input.toString();
  const power = inputAsString.length;
  return input === Array.from(inputAsString).map(Number).reduce((accumulator, currentValue) => accumulator + currentValue ** power, 0);
};