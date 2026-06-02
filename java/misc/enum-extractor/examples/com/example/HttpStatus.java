package com.example;

/**
 * Codes de statut HTTP les plus courants.
 * Chaque constante porte le code numérique et le message associé.
 */
public enum HttpStatus {

    // 2xx — Succès
    /** La requête a réussi. */
    OK(200, "OK"),
    /** Une ressource a été créée avec succès. */
    CREATED(201, "Created"),
    /** Requête acceptée, traitement en cours. */
    ACCEPTED(202, "Accepted"),
    /** Pas de contenu à retourner. */
    NO_CONTENT(204, "No Content"),

    // 3xx — Redirections
    /** Redirection permanente. */
    MOVED_PERMANENTLY(301, "Moved Permanently"),
    /** Redirection temporaire. */
    FOUND(302, "Found"),
    /** La ressource n'a pas été modifiée depuis le dernier accès. */
    NOT_MODIFIED(304, "Not Modified"),

    // 4xx — Erreurs client
    /** La requête est malformée. */
    BAD_REQUEST(400, "Bad Request"),
    /** Authentification requise. */
    UNAUTHORIZED(401, "Unauthorized"),
    /** Accès interdit même avec authentification. */
    FORBIDDEN(403, "Forbidden"),
    /** La ressource demandée n'existe pas. */
    NOT_FOUND(404, "Not Found"),
    /** La méthode HTTP n'est pas autorisée sur cette ressource. */
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    /** Conflit avec l'état actuel de la ressource. */
    CONFLICT(409, "Conflict"),
    /** Trop de requêtes en peu de temps (rate limiting). */
    TOO_MANY_REQUESTS(429, "Too Many Requests"),

    // 5xx — Erreurs serveur
    /** Erreur interne du serveur. */
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    /** Fonctionnalité non implémentée. */
    NOT_IMPLEMENTED(501, "Not Implemented"),
    /** Service temporairement indisponible. */
    SERVICE_UNAVAILABLE(503, "Service Unavailable");

    private final int    code;
    private final String reason;

    HttpStatus(int code, String reason) {
        this.code   = code;
        this.reason = reason;
    }

    public int    code()   { return code; }
    public String reason() { return reason; }

    public boolean isSuccess()     { return code >= 200 && code < 300; }
    public boolean isRedirection() { return code >= 300 && code < 400; }
    public boolean isClientError() { return code >= 400 && code < 500; }
    public boolean isServerError() { return code >= 500 && code < 600; }

    @Override
    public String toString() { return code + " " + reason; }
}
