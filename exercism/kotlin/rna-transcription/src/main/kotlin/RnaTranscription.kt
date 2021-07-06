fun transcribeToRna(dna: String): String {
    return dna.asSequence().map { c ->
        when (c) {
            'G' -> 'C'
            'C' -> 'G'
            'T' -> 'A'
            'A' -> 'U'
            else -> throw IllegalArgumentException("Invalid nucleotid")
        }
    }.joinToString("")
}
