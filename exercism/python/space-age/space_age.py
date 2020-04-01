from enum import Enum

class SpaceAge(object):

    def __init__(self, seconds):
        self._earth_orbital_period = 31557600
        self._mercury_orbital_period = 0.2408467 * self._earth_orbital_period
        self._venus_orbital_period = 0.61519726 * self._earth_orbital_period
        self._mars_orbital_period = 1.8808158 * self._earth_orbital_period
        self._jupiter_orbital_period = 11.862615 * self._earth_orbital_period
        self._saturn_orbital_period = 29.447498 * self._earth_orbital_period
        self._uranus_orbital_period = 84.016846 * self._earth_orbital_period
        self._neptune_orbital_period = 164.79132 * self._earth_orbital_period
        self._seconds = seconds
        self.Planet = Enum('Planets', 'MERCURY  VENUS EARTH MARS JUPITER SATURN URANUS NEPTUNE')
        self._ratios_by_planet = {
            self.Planet.MERCURY: 0.2408467,
            self.Planet.VENUS: 0.61519726,
            self.Planet.EARTH: 1,
            self.Planet.MARS: 1.8808158,
            self.Planet.JUPITER: 11.862615,
            self.Planet.SATURN: 29.447498,
            self.Planet.URANUS: 84.016846,
            self.Planet.NEPTUNE: 164.79132}

    def do_computation(self, planet):
        return round(self._seconds / (self._ratios_by_planet[planet] * self._earth_orbital_period), 2)

    def on_earth(self):
        return self.do_computation(self.Planet.EARTH)

    def on_mercury(self):
        return self.do_computation(self.Planet.MERCURY)

    def on_venus(self):
        return self.do_computation(self.Planet.VENUS)

    def on_mars(self):
        return self.do_computation(self.Planet.MARS)

    def on_jupiter(self):
        return self.do_computation(self.Planet.JUPITER)

    def on_saturn(self):
        return self.do_computation(self.Planet.SATURN)

    def on_uranus(self):
        return self.do_computation(self.Planet.URANUS)

    def on_neptune(self):
        return self.do_computation(self.Planet.NEPTUNE)
