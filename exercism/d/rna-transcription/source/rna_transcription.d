module rna_transcription;

import std.algorithm;
import std.conv;

string dnaComplement(string rna_strand) {
    string[string] dnaToRna;
    dnaToRna["G"] = "C";
    dnaToRna["C"] = "G";
    dnaToRna["T"] = "A";
    dnaToRna["A"] = "U";


    return rna_strand.map!(letter => dnaToRna[letter.to!string]).to!string;
}

unittest
{
    import std.exception : assertThrown;

    const int allTestsEnabled = 0;

    assert(dnaComplement("C") == "G");
    static if (allTestsEnabled)
    {
        assert(dnaComplement("G") == "C");
        assert(dnaComplement("T") == "A");
        assert(dnaComplement("A") == "U");

        assert(dnaComplement("ACGTGGTCTTAA") == "UGCACCAGAAUU");

        assertThrown(dnaComplement("U"));
        assertThrown(dnaComplement("XXX"));
        assertThrown(dnaComplement("ACGTXXXCTTAA"));
    }

}
