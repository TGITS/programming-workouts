const colorAndAssociatedValue = [
  ["black", "0"],
  ["brown", "1"],
  ["red", "2"],
  ["orange", "3"],
  ["yellow", "4"],
  ["green", "5"],
  ["blue", "6"],
  ["violet", "7"],
  ["grey", "8"],
  ["white", "9"]
];

const createValueByColor = (): Map<string, string> => {
  const mapping = new Map();
  colorAndAssociatedValue.forEach(element => {
    mapping.set(element[0], element[1]);
  });
  return mapping;
}

export class ResistorColor {
  private static valueByColors = createValueByColor();
  private colors: string[];

  constructor(colors: string[]) {
    if (colors.length < 2) {
      throw new Error("At least two colors need to be present");
    }
    this.colors = colors;
  }

  colors2Values = (): string => this.colors.slice(0, 2).map(s => ResistorColor.valueByColors.get(s)).join("")
  value = (): number => Number(this.colors2Values())
}