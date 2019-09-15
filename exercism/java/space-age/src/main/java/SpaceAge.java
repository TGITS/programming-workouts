class SpaceAge {

    private static final double EARTH_ORBITAL_PERIOD_IN_SECONDS = 31557600.0;
    private static final double MERCURY_ORBITAL_PERIOD_IN_SECONDS = EARTH_ORBITAL_PERIOD_IN_SECONDS * 0.2408467;
    private static final double VENUS_ORBITAL_PERIOD_IN_SECONDS = EARTH_ORBITAL_PERIOD_IN_SECONDS * 0.61519726;
    private static final double MARS_ORBITAL_PERIOD_IN_SECONDS = EARTH_ORBITAL_PERIOD_IN_SECONDS * 1.8808158;
    private static final double JUPITER_ORBITAL_PERIOD_IN_SECONDS = EARTH_ORBITAL_PERIOD_IN_SECONDS * 11.862615;
    private static final double SATURN_ORBITAL_PERIOD_IN_SECONDS = EARTH_ORBITAL_PERIOD_IN_SECONDS * 29.447498;
    private static final double URANUS_ORBITAL_PERIOD_IN_SECONDS = EARTH_ORBITAL_PERIOD_IN_SECONDS * 84.016846;
    private static final double NEPTUNE_ORBITAL_PERIOD_IN_SECONDS = EARTH_ORBITAL_PERIOD_IN_SECONDS * 164.79132;

    private double seconds;

    SpaceAge(double seconds) {
        this.seconds = seconds;
    }

    double getSeconds() {
        return seconds;
    }

    double onEarth() {
        return this.seconds / EARTH_ORBITAL_PERIOD_IN_SECONDS;
    }

    double onMercury() {
        return this.seconds / MERCURY_ORBITAL_PERIOD_IN_SECONDS;
    }

    double onVenus() {
        return this.seconds / VENUS_ORBITAL_PERIOD_IN_SECONDS;
    }

    double onMars() {
        return this.seconds / MARS_ORBITAL_PERIOD_IN_SECONDS;
    }

    double onJupiter() {
        return this.seconds / JUPITER_ORBITAL_PERIOD_IN_SECONDS;
    }

    double onSaturn() {
        return this.seconds / SATURN_ORBITAL_PERIOD_IN_SECONDS;
    }

    double onUranus() {
       return this.seconds / URANUS_ORBITAL_PERIOD_IN_SECONDS;
    }

    double onNeptune() {
        return this.seconds / NEPTUNE_ORBITAL_PERIOD_IN_SECONDS;
    }

}
