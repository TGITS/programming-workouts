import { value } from './resistor-color-duo.js';

describe('Resistor Colors', () => {
  test('Brown and black', () => {
    expect(value(['brown', 'black'])).toEqual(10);
  });

  test('Brown and black', () => {
    expect(value(['BROWN', 'Black'])).toEqual(10);
  });

  test('Blue and grey', () => {
    expect(value(['blue', 'grey'])).toEqual(68);
  });

  test('Blue and grey', () => {
    expect(value(['BLUE', 'GreY'])).toEqual(68);
  });

  test('Yellow and violet', () => {
    expect(value(['yellow', 'violet'])).toEqual(47);
  });

  test('Yellow and violet', () => {
    expect(value(['Yellow', 'Violet'])).toEqual(47);
  });

  test('Orange and orange', () => {
    expect(value(['orange', 'orange'])).toEqual(33);
  });

  test('Orange and orange', () => {
    expect(value(['Orange', 'orAnge'])).toEqual(33);
  });
});
