module rna_transcription;

import std.algorithm;
import std.conv;

string dnaComplement(string dna_strand) {
    char[char] dnaToRna;
    dnaToRna['G'] = 'C';
    dnaToRna['C'] = 'G';
    dnaToRna['T'] = 'A';
    dnaToRna['A'] = 'U';
    char[] dna_strand_to_dchar = dna_strand.dup;
    char[] rna_strand;
    foreach(letter;dna_strand_to_dchar) {
        rna_strand ~= dnaToRna[letter];
    }

    return rna_strand.idup;
}

unittest
{
    import std.exception : assertThrown;

    const int allTestsEnabled = 0;

    assert(dnaComplement("C") == "G");
    assert(dnaComplement("G") == "C");
    static if (allTestsEnabled)
    {
        assert(dnaComplement("T") == "A");
        assert(dnaComplement("A") == "U");

        assert(dnaComplement("ACGTGGTCTTAA") == "UGCACCAGAAUU");

        assertThrown(dnaComplement("U"));
        assertThrown(dnaComplement("XXX"));
        assertThrown(dnaComplement("ACGTXXXCTTAA"));
    }

}
