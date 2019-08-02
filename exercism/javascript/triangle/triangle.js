export class Triangle {
  constructor(side1, side2, side3) {
    this.sides = [];
    this.sides.push(side1);
    this.sides.push(side2);
    this.sides.push(side3);
  }

  kind() {
    if (this.sides.every(s => s === 0)) {
      throw new Error("Triangles with no size are illegal");
    }

    if (this.sides.some(s => s <= 0)) {
      throw new Error("Triangles with negative or nul size are illegal");
    }

    let sumOfAllSides = this.sides.reduce((acc, val) => acc + val);

    if (this.sides.map(s => (sumOfAllSides - s) === s).filter(v => v === true).length === 1) {
      return 'degenerate';
    }

    if (this.sides.map(s => (sumOfAllSides - s) >= s).some(v => v === false)) {
      throw new Error("Triangles violating triangle inequality are illegal");
    }

    if (this.sides.every(s => s === this.sides[0])) {
      return 'equilateral'
    }

    if (this.sides.map(currentSide => this.sides.filter(s => s === currentSide).length).filter(size => size === 2).length === 2) {
      return 'isosceles';
    }

    return 'scalene'
  }
}
