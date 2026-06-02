package com.example;

/**
 * Enumération des planètes du système solaire.
 * Chaque planète connaît sa masse (kg) et son rayon (m),
 * ce qui permet de calculer la gravité de surface.
 */
public enum Planet {

    /** Planète la plus proche du Soleil. */
    MERCURY(3.303e+23, 2.4397e6),

    /** Souvent appelée la "jumelle" de la Terre. */
    VENUS  (4.869e+24, 6.0518e6),

    /** Notre planète d'origine. */
    EARTH  (5.976e+24, 6.37814e6),

    MARS   (6.421e+23, 3.3972e6),
    JUPITER(1.9e+27,   7.1492e7),
    SATURN (5.688e+26, 6.0268e7),
    URANUS (8.686e+25, 2.5559e7),
    NEPTUNE(1.024e+26, 2.4746e7);

    /** Constante gravitationnelle universelle (m³ kg⁻¹ s⁻²). */
    static final double G = 6.67300E-11;

    private final double mass;   // in kilograms
    private final double radius; // in meters

    Planet(double mass, double radius) {
        this.mass   = mass;
        this.radius = radius;
    }

    public double mass()   { return mass; }
    public double radius() { return radius; }

    public double surfaceGravity() {
        return G * mass / (radius * radius);
    }

    public double surfaceWeight(double otherMass) {
        return otherMass * surfaceGravity();
    }
}
