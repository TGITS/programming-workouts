const colorsMapping = {
  "black": "0",
  "brown": "1",
  "red": "2",
  "orange": "3",
  "yellow": "4",
  "green": "5",
  "blue": "6",
  "violet": "7",
  "grey": "8",
  "white": "9"
};

export const value = ([color_1, color_2]) => {
  return Number(colorsMapping[color_1.toLowerCase()] + colorsMapping[color_2.toLowerCase()]);
};
