import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class ProteinTranslator {

    private static final Map<String, String> AMINO_ACID_BY_CODON = new HashMap<>();
    private static final String STOP = "STOP";

    static {
        AMINO_ACID_BY_CODON.put("AUG", "Methionine");
        AMINO_ACID_BY_CODON.put("UUU", "Phenylalanine");
        AMINO_ACID_BY_CODON.put("UUC", "Phenylalanine");
        AMINO_ACID_BY_CODON.put("UUA", "Leucine");
        AMINO_ACID_BY_CODON.put("UUG", "Leucine");
        AMINO_ACID_BY_CODON.put("UCU", "Serine");
        AMINO_ACID_BY_CODON.put("UCC", "Serine");
        AMINO_ACID_BY_CODON.put("UCA", "Serine");
        AMINO_ACID_BY_CODON.put("UCG", "Serine");
        AMINO_ACID_BY_CODON.put("UAU", "Tyrosine");
        AMINO_ACID_BY_CODON.put("UAC", "Tyrosine");
        AMINO_ACID_BY_CODON.put("UGU", "Cysteine");
        AMINO_ACID_BY_CODON.put("UGC", "Cysteine");
        AMINO_ACID_BY_CODON.put("UGG", "Tryptophan");
        AMINO_ACID_BY_CODON.put("UAA", STOP);
        AMINO_ACID_BY_CODON.put("UAG", STOP);
        AMINO_ACID_BY_CODON.put("UGA", STOP);
    }


    List<String> translate(String rnaSequence) {
        List<String> listOfAminoAcids = splitListOfCodons(rnaSequence).stream().map(s -> AMINO_ACID_BY_CODON.get(s)).collect(Collectors.toList());
        int index = listOfAminoAcids.indexOf(STOP);
        return index < 0 ? listOfAminoAcids : listOfAminoAcids.subList(0, index);
    }

    private List<String> splitListOfCodons(String rnaSequence) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < rnaSequence.length(); i = i + 3) {
            list.add(rnaSequence.substring(i, i + 3));
        }
        return list;
    }
}