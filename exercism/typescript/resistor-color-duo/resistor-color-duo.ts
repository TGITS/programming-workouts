export class ResistorColor {
  private valueByColors: Map<string, string>;
  private colors: string[];

  constructor(colors: string[]) {
    if (colors == null || typeof colors === 'undefined' || colors.length < 2) {
      throw new Error("At least two colors need to be present");
    }
    this.colors = colors;
    this.valueByColors = new Map();
    this.valueByColors.set("black", "0");
    this.valueByColors.set("brown", "1");
    this.valueByColors.set("red", "2");
    this.valueByColors.set("orange", "3");
    this.valueByColors.set("yellow", "4");
    this.valueByColors.set("green", "5");
    this.valueByColors.set("blue", "6");
    this.valueByColors.set("violet", "7");
    this.valueByColors.set("grey", "8");
    this.valueByColors.set("white", "9");
  }

  value = (): number => parseInt(this.colors.slice(0, 2).map(s => this.valueByColors.get(s)).reduce((accumulator, currentValue) => accumulator?.concat(currentValue || ""), "") || "", 10)
}

