package com.example.config;

/**
 * Niveaux de journalisation, du plus verbeux au plus silencieux.
 * Compatible avec les conventions SLF4J / Log4j.
 */
public enum LogLevel {

    /** Traces très détaillées — usage développement uniquement. */
    TRACE(0, "\u001B[37m"),

    /** Informations de débogage. */
    DEBUG(1, "\u001B[36m"),

    /** Informations générales sur le déroulement nominal. */
    INFO (2, "\u001B[32m"),

    /** Avertissement : situation anormale mais non bloquante. */
    WARN (3, "\u001B[33m"),

    /** Erreur : une opération a échoué. */
    ERROR(4, "\u001B[31m"),

    /** Erreur fatale : l'application ne peut pas continuer. */
    FATAL(5, "\u001B[35m");

    private static final String RESET = "\u001B[0m";

    private final int    severity;
    private final String ansiCode;

    LogLevel(int severity, String ansiCode) {
        this.severity = severity;
        this.ansiCode = ansiCode;
    }

    public int    severity() { return severity; }
    public String ansiCode() { return ansiCode; }

    /** Retourne {@code true} si ce niveau est au moins aussi sévère que {@code other}. */
    public boolean isAtLeast(LogLevel other) {
        return this.severity >= other.severity;
    }

    /** Formate un message avec la couleur ANSI correspondante. */
    public String format(String message) {
        return ansiCode + "[" + name() + "] " + message + RESET;
    }
}
