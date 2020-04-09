enum CodeByColor {
  black = 0,
  brown = 1,
  red = 2,
  orange = 3,
  yellow = 4,
  green = 5,
  blue = 6,
  violet = 7,
  grey = 8,
  white = 9
}

type Color = keyof typeof CodeByColor;

export class ResistorColor {
  private colors: string[]

  constructor(colors: string[]) {
    if (colors.length < 2) {
      throw new Error("At least two colors need to be present");
    }
    this.colors = colors;
  }
  value = (): number => CodeByColor[this.colors[0] as Color] * 10 + CodeByColor[this.colors[1] as Color];
}