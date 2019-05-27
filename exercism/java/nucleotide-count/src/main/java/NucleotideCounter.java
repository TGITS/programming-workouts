import java.util.HashMap;
import java.util.Map;

public class NucleotideCounter {

    private final Map<Character, Integer> countByNucleotide;
    private final String nucleotide;

    public NucleotideCounter(String s) {
        countByNucleotide = new HashMap<>();
        countByNucleotide.put('A',0);
        countByNucleotide.put('C',0);
        countByNucleotide.put('G',0);
        countByNucleotide.put('T',0);
        if(!s.matches("[ACGT]*")) {
            throw new IllegalArgumentException("Invalid DNA Letters");
        }
        this.nucleotide = s;
    }

    public Map<Character, Integer> nucleotideCounts() {
        this.nucleotide.chars().mapToObj(c -> (char)c).forEach(c -> countByNucleotide.computeIfPresent(c,(k,v) -> v + 1));
        return countByNucleotide;
    }
}
