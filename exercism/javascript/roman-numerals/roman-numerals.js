export const toRoman = (num) => {
  let roman = "";

  while (num >= 1000) {
    roman = roman + "M";
    num -= 1000;
  }

  if (num >= 900) {
    roman = roman + "CM";
    num -= 900;
  }

  if (num < 900 && num >= 500) {
    let temp = "";
    while (num > 599) {
      temp += "C";
      num -= 100;
    }
    roman = roman + "D" + temp;
    num -= 500
  }

  if (num >= 400) {
    roman = roman + "CD"
    num -= 400;
  }

  while (num >= 100) {
    roman = roman + "C";
    num -= 100;
  }

  if (num >= 90) {
    roman = roman + "XC";
    num -= 90;
  }

  if (num < 90 && num >= 50) {
    let temp = "";
    while (num > 59) {
      temp += "X";
      num -= 10;
    }
    roman = roman + "L" + temp;
    num -= 50;
  }

  if (num >= 40) {
    roman = roman + "XL";
    num -= 40;
  }

  while (num >= 10) {
    roman = roman + "X";
    num -= 10;
  }

  if (num === 9) {
    roman = roman + "IX";
    num -= 9;
  }

  if (num < 9 && num >= 5) {
    let temp = "";
    while (num > 5) {
      temp += "I";
      num -= 1;
    }
    roman = roman + "V" + temp;
    num -= 5;
  }

  if (num === 4) {
    roman = roman + "IV";
    num -= 4;
  }

  while (num >= 1) {
    roman = roman + "I";
    num -= 1;
  }

  return roman;
};
