const colorAndAssociatedValue: { [index: string]: string } = {
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
}

export class ResistorColor {
  private colors: string[];

  constructor(colors: string[]) {
    if (colors.length < 2) {
      throw new Error("At least two colors need to be present");
    }
    this.colors = colors;
  }

  colors2Values = (): string => this.colors.slice(0, 2).map(s => colorAndAssociatedValue[s]).join("")
  value = (): number => Number(this.colors2Values())
}