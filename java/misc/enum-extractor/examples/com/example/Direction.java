package com.example;

/**
 * Points cardinaux et intercardinaux.
 * Exemple d'enum simple sans champs ni constructeur.
 */
public enum Direction {

    /** Nord — 0° / 360°. */
    NORTH,
    /** Nord-Est — 45°. */
    NORTH_EAST,
    /** Est — 90°. */
    EAST,
    /** Sud-Est — 135°. */
    SOUTH_EAST,
    /** Sud — 180°. */
    SOUTH,
    /** Sud-Ouest — 225°. */
    SOUTH_WEST,
    /** Ouest — 270°. */
    WEST,
    /** Nord-Ouest — 315°. */
    NORTH_WEST;

    /** Retourne la direction opposée. */
    public Direction opposite() {
        Direction[] values = values();
        return values[(ordinal() + 4) % values.length];
    }
}
