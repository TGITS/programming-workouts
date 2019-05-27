import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class NucleotideCounter {

    private final String nucleotide;

    public NucleotideCounter(String s) {
        if(!s.matches("[ACGT]*")) {
            throw new IllegalArgumentException("Invalid DNA Letters");
        }
        this.nucleotide = s;
    }

    public Map<Character, Integer> nucleotideCounts() {
        Map<Character, Integer> countByNucleotide = this.nucleotide.chars().mapToObj(c-> (char)c).collect(groupingBy(c -> c,reducing(0, e -> 1, Integer::sum)));
        for(char c:new char[]{'A','C','G','T'}) {
            countByNucleotide.putIfAbsent(c,0);
        }
        return countByNucleotide;
    }
}
