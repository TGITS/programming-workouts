enum Color {
  black = "0",
  brown = "1",
  red = "2",
  orange = "3",
  yellow = "4",
  green = "5",
  blue = "6",
  violet = "7",
  grey = "8",
  white = "9"
}

type ColorKey = keyof typeof Color;

export class ResistorColor {
  private firstColor: Color;
  private secondColor: Color;

  constructor(colors: ColorKey[]) {
    if (colors.length < 2) {
      throw new Error("At least two colors need to be present");
    }
    this.firstColor = Color[colors[0]];
    this.secondColor = Color[colors[1]];
  }

  value = (): number => Number(`${this.firstColor}${this.secondColor}`)
}