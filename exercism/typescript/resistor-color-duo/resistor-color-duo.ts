class ValueByColor {
  private mapping: Map<string, string>;

  constructor() {
    this.mapping = new Map();
    this.mapping.set("black", "0");
    this.mapping.set("brown", "1");
    this.mapping.set("red", "2");
    this.mapping.set("orange", "3");
    this.mapping.set("yellow", "4");
    this.mapping.set("green", "5");
    this.mapping.set("blue", "6");
    this.mapping.set("violet", "7");
    this.mapping.set("grey", "8");
    this.mapping.set("white", "9");
  }

  get = (color: string) => this.mapping.get(color);
}

export class ResistorColor {
  private static valueByColors: ValueByColor = new ValueByColor();
  private colors: string[];

  constructor(colors: string[]) {
    if (colors == null || typeof colors === 'undefined' || colors.length < 2) {
      throw new Error("At least two colors need to be present");
    }
    this.colors = colors;
  }

  colors2Values = () => this.colors.slice(0, 2).map(s => ResistorColor.valueByColors.get(s)).join("")
  value = (): number => parseInt(this.colors2Values(), 10)
}