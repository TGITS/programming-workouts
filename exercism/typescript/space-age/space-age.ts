const EARTH_ORBITAL_PERIOD_IN_SECONDS = 31557600;

const orbital_periods_in_earth_year: Record<string, number> = {
  'mercury': 0.2408467,
  'venus': 0.61519726,
  'earth': 1,
  'mars': 1.8808158,
  'jupiter': 11.862615,
  'saturn': 29.447498,
  'uranus': 84.016846,
  'neptune': 164.79132
}

const round_with_2_decimals = (val:number) => Math.round(val * 100) / 100;

export const age = (planet: string, age_in_seconds: number) => round_with_2_decimals(age_in_seconds / (EARTH_ORBITAL_PERIOD_IN_SECONDS * orbital_periods_in_earth_year[planet]));