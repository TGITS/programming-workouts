# Java Enum Extraction Report

**Source:** `C:\Users\cvaudry\Documents\Perso\Workspace\programming-workouts\java\misc\enum-extractor\examples`  
**Java version:** 11 *(detected from: built-in default (Java 11))*  
**Enums found:** 6

---

## `com.example.chess.Color`

- **File:** `com/example/chess/ChessPiece.java`
- **Description:** Couleur d'une pièce.

| Constant | description |
|----------|-------------|
| `WHITE` | Pièces blanches — jouent en premier. |
| `BLACK` | Pièces noires. |

```csv
constant,description
WHITE,Pièces blanches — jouent en premier.
BLACK,Pièces noires.
```

## `com.example.chess.Type`

- **File:** `com/example/chess/ChessPiece.java`
- **Description:** Type de pièce avec sa valeur centipawn (100 cp = 1 pion) et ses symboles Unicode blanc et noir.
- **Fields:** `int centipawnValue`, `String whiteSymbol`, `String blackSymbol`

| Constant | centipawnValue | whiteSymbol | blackSymbol | description |
|----------|---------|---------|---------|-------------|
| `PAWN` | `100` | `"♙"` | `"♟"` | Le pion — pièce de base. |
| `KNIGHT` | `320` | `"♘"` | `"♞"` | Le cavalier — se déplace en L. |
| `BISHOP` | `330` | `"♗"` | `"♝"` | Le fou — se déplace en diagonale. |
| `ROOK` | `500` | `"♖"` | `"♜"` | La tour — se déplace horizontalement et verticalement. |
| `QUEEN` | `900` | `"♕"` | `"♛"` | La dame — pièce la plus puissante. |
| `KING` | `20000` | `"♔"` | `"♚"` | Le roi — pièce à protéger à tout prix. |

```csv
constant,centipawnValue,whiteSymbol,blackSymbol,description
PAWN,100,"""♙""","""♟""",Le pion — pièce de base.
KNIGHT,320,"""♘""","""♞""",Le cavalier — se déplace en L.
BISHOP,330,"""♗""","""♝""",Le fou — se déplace en diagonale.
ROOK,500,"""♖""","""♜""",La tour — se déplace horizontalement et verticalement.
QUEEN,900,"""♕""","""♛""",La dame — pièce la plus puissante.
KING,20000,"""♔""","""♚""",Le roi — pièce à protéger à tout prix.
```

## `com.example.config.LogLevel`

- **File:** `com/example/config/LogLevel.java`
- **Description:** Niveaux de journalisation, du plus verbeux au plus silencieux. Compatible avec les conventions SLF4J / Log4j.
- **Fields:** `int severity`, `String ansiCode`

| Constant | severity | ansiCode | description |
|----------|---------|---------|-------------|
| `TRACE` | `0` | `"\u001B[37m"` | Traces très détaillées — usage développement uniquement. |
| `DEBUG` | `1` | `"\u001B[36m"` | Informations de débogage. |
| `INFO` | `2` | `"\u001B[32m"` | Informations générales sur le déroulement nominal. |
| `WARN` | `3` | `"\u001B[33m"` | Avertissement : situation anormale mais non bloquante. |
| `ERROR` | `4` | `"\u001B[31m"` | Erreur : une opération a échoué. |
| `FATAL` | `5` | `"\u001B[35m"` | Erreur fatale : l'application ne peut pas continuer. |

```csv
constant,severity,ansiCode,description
TRACE,0,"""\u001B[37m""",Traces très détaillées — usage développement uniquement.
DEBUG,1,"""\u001B[36m""",Informations de débogage.
INFO,2,"""\u001B[32m""",Informations générales sur le déroulement nominal.
WARN,3,"""\u001B[33m""",Avertissement : situation anormale mais non bloquante.
ERROR,4,"""\u001B[31m""",Erreur : une opération a échoué.
FATAL,5,"""\u001B[35m""",Erreur fatale : l'application ne peut pas continuer.
```

## `com.example.Direction`

- **File:** `com/example/Direction.java`
- **Description:** Points cardinaux et intercardinaux. Exemple d'enum simple sans champs ni constructeur.

| Constant | description |
|----------|-------------|
| `NORTH` | Nord — 0° / 360°. |
| `NORTH_EAST` | Nord-Est — 45°. |
| `EAST` | Est — 90°. |
| `SOUTH_EAST` | Sud-Est — 135°. |
| `SOUTH` | Sud — 180°. |
| `SOUTH_WEST` | Sud-Ouest — 225°. |
| `WEST` | Ouest — 270°. |
| `NORTH_WEST` | Nord-Ouest — 315°. |

```csv
constant,description
NORTH,Nord — 0° / 360°.
NORTH_EAST,Nord-Est — 45°.
EAST,Est — 90°.
SOUTH_EAST,Sud-Est — 135°.
SOUTH,Sud — 180°.
SOUTH_WEST,Sud-Ouest — 225°.
WEST,Ouest — 270°.
NORTH_WEST,Nord-Ouest — 315°.
```

## `com.example.HttpStatus`

- **File:** `com/example/HttpStatus.java`
- **Description:** Codes de statut HTTP les plus courants. Chaque constante porte le code numérique et le message associé.
- **Fields:** `int code`, `String reason`

| Constant | code | reason | description |
|----------|---------|---------|-------------|
| `OK` | `200` | `"OK"` | La requête a réussi. |
| `CREATED` | `201` | `"Created"` | Une ressource a été créée avec succès. |
| `ACCEPTED` | `202` | `"Accepted"` | Requête acceptée, traitement en cours. |
| `NO_CONTENT` | `204` | `"No Content"` | Pas de contenu à retourner. |
| `MOVED_PERMANENTLY` | `301` | `"Moved Permanently"` | Redirection permanente. |
| `FOUND` | `302` | `"Found"` | Redirection temporaire. |
| `NOT_MODIFIED` | `304` | `"Not Modified"` | La ressource n'a pas été modifiée depuis le dernier accès. |
| `BAD_REQUEST` | `400` | `"Bad Request"` | La requête est malformée. |
| `UNAUTHORIZED` | `401` | `"Unauthorized"` | Authentification requise. |
| `FORBIDDEN` | `403` | `"Forbidden"` | Accès interdit même avec authentification. |
| `NOT_FOUND` | `404` | `"Not Found"` | La ressource demandée n'existe pas. |
| `METHOD_NOT_ALLOWED` | `405` | `"Method Not Allowed"` | La méthode HTTP n'est pas autorisée sur cette ressource. |
| `CONFLICT` | `409` | `"Conflict"` | Conflit avec l'état actuel de la ressource. |
| `TOO_MANY_REQUESTS` | `429` | `"Too Many Requests"` | Trop de requêtes en peu de temps (rate limiting). |
| `INTERNAL_SERVER_ERROR` | `500` | `"Internal Server Error"` | Erreur interne du serveur. |
| `NOT_IMPLEMENTED` | `501` | `"Not Implemented"` | Fonctionnalité non implémentée. |
| `SERVICE_UNAVAILABLE` | `503` | `"Service Unavailable"` | Service temporairement indisponible. |

```csv
constant,code,reason,description
OK,200,"""OK""",La requête a réussi.
CREATED,201,"""Created""",Une ressource a été créée avec succès.
ACCEPTED,202,"""Accepted""","Requête acceptée, traitement en cours."
NO_CONTENT,204,"""No Content""",Pas de contenu à retourner.
MOVED_PERMANENTLY,301,"""Moved Permanently""",Redirection permanente.
FOUND,302,"""Found""",Redirection temporaire.
NOT_MODIFIED,304,"""Not Modified""",La ressource n'a pas été modifiée depuis le dernier accès.
BAD_REQUEST,400,"""Bad Request""",La requête est malformée.
UNAUTHORIZED,401,"""Unauthorized""",Authentification requise.
FORBIDDEN,403,"""Forbidden""",Accès interdit même avec authentification.
NOT_FOUND,404,"""Not Found""",La ressource demandée n'existe pas.
METHOD_NOT_ALLOWED,405,"""Method Not Allowed""",La méthode HTTP n'est pas autorisée sur cette ressource.
CONFLICT,409,"""Conflict""",Conflit avec l'état actuel de la ressource.
TOO_MANY_REQUESTS,429,"""Too Many Requests""",Trop de requêtes en peu de temps (rate limiting).
INTERNAL_SERVER_ERROR,500,"""Internal Server Error""",Erreur interne du serveur.
NOT_IMPLEMENTED,501,"""Not Implemented""",Fonctionnalité non implémentée.
SERVICE_UNAVAILABLE,503,"""Service Unavailable""",Service temporairement indisponible.
```

## `com.example.Planet`

- **File:** `com/example/Planet.java`
- **Description:** Enumération des planètes du système solaire. Chaque planète connaît sa masse (kg) et son rayon (m), ce qui permet de calculer la gravité de surface.
- **Fields:** `double mass`, `double radius`

| Constant | mass | radius | description |
|----------|---------|---------|-------------|
| `MERCURY` | `3.303e+23` | `2.4397e6` | Planète la plus proche du Soleil. |
| `VENUS` | `4.869e+24` | `6.0518e6` | Souvent appelée la "jumelle" de la Terre. |
| `EARTH` | `5.976e+24` | `6.37814e6` | Notre planète d'origine. |
| `MARS` | `6.421e+23` | `3.3972e6` |  |
| `JUPITER` | `1.9e+27` | `7.1492e7` |  |
| `SATURN` | `5.688e+26` | `6.0268e7` |  |
| `URANUS` | `8.686e+25` | `2.5559e7` |  |
| `NEPTUNE` | `1.024e+26` | `2.4746e7` |  |

```csv
constant,mass,radius,description
MERCURY,3.303e+23,2.4397e6,Planète la plus proche du Soleil.
VENUS,4.869e+24,6.0518e6,"Souvent appelée la ""jumelle"" de la Terre."
EARTH,5.976e+24,6.37814e6,Notre planète d'origine.
MARS,6.421e+23,3.3972e6,
JUPITER,1.9e+27,7.1492e7,
SATURN,5.688e+26,6.0268e7,
URANUS,8.686e+25,2.5559e7,
NEPTUNE,1.024e+26,2.4746e7,
```

