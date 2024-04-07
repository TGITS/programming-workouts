export class Squares {
  private count: number;

  constructor(count: number) {
    this.count = count;
  }

  get sumOfSquares(): number {
    return Array.from(range(1, this.count + 1))
      .map((i) => i ** 2)
      .reduce((total, currentValue) => total + currentValue);
  }

  get squareOfSum(): number {
    return (
      Array.from(range(1, this.count + 1)).reduce(
        (total, currentValue) => total + currentValue
      ) ** 2
    );
  }

  get difference(): number {
    return this.squareOfSum - this.sumOfSquares;
  }
}

// eslint-disable-next-line @typescript-eslint/explicit-function-return-type
function* range(start = 0, end = 0, step = 1) {
  for (let n = start; n < end; n += step) yield n;
}
