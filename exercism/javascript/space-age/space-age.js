const EARTH_ORBITAL_PERIOD_IN_SECONDS = 31557600;
const MERCURY_ORBITAL_PERIOD_IN_EARTH_YEAR = 0.2408467;
const VENUS_ORBITAL_PERIOD_IN_EARTH_YEAR = 0.61519726;
const MARS_ORBITAL_PERIOD_IN_EARTH_YEAR = 1.8808158;
const JUPITER_ORBITAL_PERIOD_IN_EARTH_YEAR = 11.862615;
const SATURN_ORBITAL_PERIOD_IN_EARTH_YEAR = 29.447498;
const URANUS_ORBITAL_PERIOD_IN_EARTH_YEAR = 84.016846;
const NEPTUNE_ORBITAL_PERIOD_IN_EARTH_YEAR = 164.79132;

const round_with_2_decimals = (val) => Math.round(val * 100) / 100;

const compute_age_function_by_planet = {
  'mercury': (age_in_seconds) => round_with_2_decimals(age_in_seconds / (EARTH_ORBITAL_PERIOD_IN_SECONDS * MERCURY_ORBITAL_PERIOD_IN_EARTH_YEAR)),
  'venus': (age_in_seconds) => round_with_2_decimals(age_in_seconds / (EARTH_ORBITAL_PERIOD_IN_SECONDS * VENUS_ORBITAL_PERIOD_IN_EARTH_YEAR)),
  'earth': (age_in_seconds) => round_with_2_decimals(age_in_seconds / EARTH_ORBITAL_PERIOD_IN_SECONDS),
  'mars': (age_in_seconds) => round_with_2_decimals(age_in_seconds / (EARTH_ORBITAL_PERIOD_IN_SECONDS * MARS_ORBITAL_PERIOD_IN_EARTH_YEAR)),
  'jupiter': (age_in_seconds) => round_with_2_decimals(age_in_seconds / (EARTH_ORBITAL_PERIOD_IN_SECONDS * JUPITER_ORBITAL_PERIOD_IN_EARTH_YEAR)),
  'saturn': (age_in_seconds) => round_with_2_decimals(age_in_seconds / (EARTH_ORBITAL_PERIOD_IN_SECONDS * SATURN_ORBITAL_PERIOD_IN_EARTH_YEAR)),
  'uranus': (age_in_seconds) => round_with_2_decimals(age_in_seconds / (EARTH_ORBITAL_PERIOD_IN_SECONDS * URANUS_ORBITAL_PERIOD_IN_EARTH_YEAR)),
  'neptune': (age_in_seconds) => round_with_2_decimals(age_in_seconds / (EARTH_ORBITAL_PERIOD_IN_SECONDS * NEPTUNE_ORBITAL_PERIOD_IN_EARTH_YEAR))
}

export const age = (planet, age_in_seconds) => compute_age_function_by_planet[planet](age_in_seconds);
