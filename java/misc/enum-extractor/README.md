# enum-extractor

Un script [JBang](https://www.jbang.dev/) qui parcourt une base de code Java et extrait toutes les **énumérations** (`enum`) avec leurs constantes et leurs valeurs, dans un format lisible par un humain.

## Fonctionnalités

- Parcourt récursivement un répertoire source Java
- Détecte les enums de premier niveau **et** les enums imbriquées dans des classes
- Extrait pour chaque enum :
  - Le nom complet (package + nom)
  - Le chemin du fichier source
  - La Javadoc de l'enum (si présente)
  - Les champs non-statiques (paramètres du constructeur)
  - Les constantes avec leurs arguments de constructeur et leur Javadoc
- Colonnes dynamiques : chaque argument du constructeur obtient sa propre colonne nommée d'après le paramètre ; la colonne `description` n'apparaît que si au moins une constante a une Javadoc
- Deux formats de sortie : **texte** (défaut) et **Markdown**
- Le format Markdown inclut un tableau lisible **et** un bloc de code CSV pour chaque enum
- Sortie vers la console (stdout) ou vers un fichier

## Prérequis

- [JBang](https://www.jbang.dev/documentation/guide/latest/installation.html) installé — voir l'[Annexe : Installation de JBang](#annexe--installation-de-jbang)
- Java 17 ou supérieur

Les dépendances Maven sont téléchargées automatiquement par JBang au premier lancement :

| Bibliothèque | Version | Rôle |
|---|---|---|
| [picocli](https://picocli.info/) | 4.7.5 | Interface en ligne de commande |
| [JavaParser](https://github.com/javaparser/javaparser) | 3.26.0 | Parsing du code source Java |

## Installation

Aucune installation supplémentaire n'est nécessaire. Clonez ou copiez le fichier `EnumExtractor.java` puis rendez-le exécutable (Linux/macOS) :

```bash
chmod +x EnumExtractor.java
```

## Utilisation

### Syntaxe générale

```
jbang EnumExtractor.java [OPTIONS] SOURCE_DIR
```

### Options

| Option | Description |
|--------|-------------|
| `SOURCE_DIR` | **(Requis)** Répertoire racine du code source Java à analyser |
| `-j, --java-version VERSION` | Version Java à utiliser pour le parsing (ex: `8`, `11`, `17`, `21`). Auto-détectée si absente. |
| `-o, --output FILE` | Écrit le résultat dans `FILE` au lieu de la console |
| `-f, --format FORMAT` | Format de sortie : `text` (défaut) ou `markdown` |
| `-h, --help` | Affiche l'aide |
| `-V, --version` | Affiche la version |

### Exemples

**Afficher les enums d'un projet sur la console :**

```bash
jbang EnumExtractor.java /path/to/my-project/src
```

**Forcer une version Java spécifique (utile si l'auto-détection échoue) :**

```bash
jbang EnumExtractor.java /path/to/my-project/src -j 21
```

**Exporter en fichier texte :**

```bash
jbang EnumExtractor.java /path/to/my-project/src -o enums.txt
```

**Exporter en Markdown :**

```bash
jbang EnumExtractor.java /path/to/my-project/src -f markdown -o enums.md
```

**Rendre le script directement exécutable (Linux/macOS) :**

```bash
./EnumExtractor.java /path/to/my-project/src -f markdown -o enums.md
```

## Exemple de sortie

### Format texte (défaut)

```
JAVA ENUM EXTRACTION REPORT
===========================
Source       : /home/user/my-project/src
Java version : 17  (detected from: pom.xml)
Enums        : 2

┌─────────────────────────────────────────────────────
│ Enum    : com.example.Planet
│ File    : com/example/Planet.java
│ Comment : Enumération des planètes du système solaire.
│ Fields  : double mass, double radius
│ Values (8):
│   • MERCURY(mass=3.303e+23, radius=2.4397e6)  // Planète la plus proche du Soleil.
│   • VENUS(mass=4.869e+24, radius=6.0518e6)
│   • EARTH(mass=5.976e+24, radius=6.37814e6)  // Notre planète d'origine.
│   ...
└─────────────────────────────────────────────────────

┌─────────────────────────────────────────────────────
│ Enum    : com.example.Direction
│ File    : com/example/Direction.java
│ Comment : Points cardinaux et intercardinaux.
│ Values (8):
│   • NORTH  // Nord — 0° / 360°.
│   • EAST   // Est — 90°.
│   ...
└─────────────────────────────────────────────────────
```

### Format Markdown

Le format Markdown génère pour chaque enum un **tableau** à colonnes dynamiques suivi d'un **bloc CSV**.

#### Règles d'affichage des colonnes

| Colonne | Présente si… |
|---------|-------------|
| `constant` | Toujours |
| Une colonne par paramètre du constructeur (ex: `mass`, `radius`) | L'enum possède un constructeur avec des paramètres |
| `description` | Au moins une constante de l'enum a une Javadoc |

#### Exemple — enum avec arguments et descriptions (`Planet`)

````markdown
## `com.example.Planet`

- **File:** `com/example/Planet.java`
- **Description:** Enumération des planètes du système solaire.
- **Fields:** `double mass`, `double radius`

| Constant | mass | radius | description |
|----------|------|--------|-------------|
| `MERCURY` | `3.303e+23` | `2.4397e6` | Planète la plus proche du Soleil. |
| `VENUS` | `4.869e+24` | `6.0518e6` | |
| `EARTH` | `5.976e+24` | `6.37814e6` | Notre planète d'origine. |

```csv
constant,mass,radius,description
MERCURY,3.303e+23,2.4397e6,Planète la plus proche du Soleil.
VENUS,4.869e+24,6.0518e6,
EARTH,5.976e+24,6.37814e6,Notre planète d'origine.
```
````

#### Exemple — enum sans arguments ni descriptions (`Direction`)

````markdown
## `com.example.Direction`

- **File:** `com/example/Direction.java`
- **Description:** Points cardinaux et intercardinaux.

| Constant |
|----------|
| `NORTH` |
| `EAST` |
| `SOUTH` |
| `WEST` |

```csv
constant
NORTH
EAST
SOUTH
WEST
```
````

## Détection de la version Java

Le script détermine la version Java à utiliser dans l'ordre de priorité suivant :

| Priorité | Source | Exemple |
|----------|--------|---------|
| 1 | Option CLI `--java-version` | `-j 21` |
| 2 | Fichier `.java-version` (remonte depuis `SOURCE_DIR`) | `21` ou `openjdk-21` |
| 3 | `pom.xml` (remonte depuis `SOURCE_DIR`) | `<maven.compiler.release>17</maven.compiler.release>` |
| 4 | `build.gradle` ou `build.gradle.kts` (remonte depuis `SOURCE_DIR`) | `sourceCompatibility = 17` |
| 5 | Défaut intégré : **Java 11** | (aucune source détectée) |

La version utilisée est toujours affichée dans le rapport (champ **Java version**).

## Architecture

Le script tient dans un seul fichier `EnumExtractor.java`, découpé en :

- **`EnumExtractor`** – commande picocli principale, orchestre le scan et la sortie
- **`resolveLanguageLevel()`** – détermine la version Java (CLI > `.java-version` > `pom.xml` > `build.gradle` > défaut)
- **`toLanguageLevel()`** – mappe un entier (8, 17, 21…) vers le `LanguageLevel` JavaParser correspondant
- **`extractEnums()`** – parcourt les fichiers `.java` avec `Files.walk()`
- **`parseFile()`** – parse chaque fichier avec JavaParser, extrait les `EnumDeclaration` et les noms de paramètres du constructeur
- **`renderText()` / `renderMarkdown()`** – formatage de la sortie avec colonnes dynamiques
- **`EnumInfo` / `ConstantInfo`** – modèle de données interne

---

## Annexe : Installation de JBang

JBang est un outil qui permet d'exécuter des fichiers `.java` directement, comme des scripts, sans projet Maven ou Gradle.

### Linux / macOS

**Via SDKMAN (recommandé) :**
```bash
sdk install jbang
```

**Via Homebrew :**
```bash
brew install jbangdev/tap/jbang
```

**Via le script d'installation officiel :**
```bash
curl -Ls https://sh.jbang.dev | bash -s - app setup
```

### Windows

**Via Scoop :**
```powershell
scoop bucket add jbangdev https://github.com/jbangdev/scoop-bucket
scoop install jbang
```

**Via Chocolatey :**
```powershell
choco install jbang
```

**Via WinGet :**
```powershell
winget install jbangdev.jbang
```

**Via le script PowerShell officiel :**
```powershell
iex "& { $(iwr https://ps.jbang.dev) } app setup"
```

### Vérification de l'installation

```bash
jbang --version
```

### Note sur le premier lancement

Lors du premier lancement du script, JBang télécharge automatiquement les dépendances (`picocli` et `javaparser-core`) dans son cache local (`~/.jbang/cache`). Les lancements suivants sont instantanés.

