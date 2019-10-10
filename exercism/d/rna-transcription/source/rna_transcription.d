module rna_transcription;

import std.algorithm;
import std.conv;

string dnaComplement(string dna_strand)
{
    immutable char[char] dnaToRna = ['G' :'C', 'C': 'G', 'T':'A', 'A' : 'U'];
    char[] dna_strand_to_dchar = dna_strand.dup;
    char[] rna_strand;
    foreach (letter; dna_strand_to_dchar)
    {
        if (letter in dnaToRna)
        {
            rna_strand ~= dnaToRna[letter];
        }
        else
        {
            throw new Exception("Incorrect value !");
        }
    }

    return rna_strand.idup;
}

unittest
{
    import std.exception : assertThrown;

    const bool allTestsEnabled = true;

    static if (allTestsEnabled)
    {
        assert(dnaComplement("C") == "G");
        assert(dnaComplement("G") == "C");
        assert(dnaComplement("T") == "A");
        assert(dnaComplement("A") == "U");

        assert(dnaComplement("ACGTGGTCTTAA") == "UGCACCAGAAUU");

        assertThrown(dnaComplement("U"));
        assertThrown(dnaComplement("XXX"));
        assertThrown(dnaComplement("ACGTXXXCTTAA"));
    }

}
