from enum import Enum


class SpaceAge(object):

    earth_orbital_period = 31557600
    Planet = Enum(
        'Planets', 'MERCURY  VENUS EARTH MARS JUPITER SATURN URANUS NEPTUNE')
    ratios_by_planet = {
        Planet.MERCURY: 0.2408467,
        Planet.VENUS: 0.61519726,
        Planet.EARTH: 1,
        Planet.MARS: 1.8808158,
        Planet.JUPITER: 11.862615,
        Planet.SATURN: 29.447498,
        Planet.URANUS: 84.016846,
        Planet.NEPTUNE: 164.79132}

    def __init__(self, seconds):
        self._seconds = seconds

    def do_computation(self, planet):
        return round(self._seconds / (SpaceAge.ratios_by_planet[planet] * SpaceAge.earth_orbital_period), 2)

    def on_earth(self):
        return self.do_computation(SpaceAge.Planet.EARTH)

    def on_mercury(self):
        return self.do_computation(SpaceAge.Planet.MERCURY)

    def on_venus(self):
        return self.do_computation(SpaceAge.Planet.VENUS)

    def on_mars(self):
        return self.do_computation(SpaceAge.Planet.MARS)

    def on_jupiter(self):
        return self.do_computation(SpaceAge.Planet.JUPITER)

    def on_saturn(self):
        return self.do_computation(SpaceAge.Planet.SATURN)

    def on_uranus(self):
        return self.do_computation(SpaceAge.Planet.URANUS)

    def on_neptune(self):
        return self.do_computation(SpaceAge.Planet.NEPTUNE)
