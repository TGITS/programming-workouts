class SpaceAge(object):
    def __init__(self, seconds):
        self._earth_orbital_period = 31557600
        self._mercury_orbital_period = 0.2408467 * self._earth_orbital_period
        self._seconds = seconds

    def do_computation(self, orbital_period):
        return round(self._seconds / orbital_period, 2)

    def on_earth(self):
        return self.do_computation(self._earth_orbital_period)

    def on_mercury(self):
        return self.do_computation(self._mercury_orbital_period)
