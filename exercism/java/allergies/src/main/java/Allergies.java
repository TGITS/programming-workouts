import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Allergies {

    List<Allergen> allergens = new ArrayList<>();

    public Allergies(final int score) {
        int workingScore = score;

        List<Integer> notListedAllergenScores = IntStream
                .iterate(1, i -> i * 2)
                .skip(8)
                .takeWhile(i -> i <= score)
                .boxed()
                .collect(Collectors.toList());

        for (int notListedAllergenScore : notListedAllergenScores) {
            if (workingScore >= notListedAllergenScore) {
                workingScore = workingScore - notListedAllergenScore;
            }
        }

        List<Allergen> allergensList = Arrays.stream(Allergen.values())
                .sorted(Comparator.comparingInt(Allergen::getScore).reversed())
                .collect(Collectors.toList());

        for (Allergen allergen : allergensList) {
            if (workingScore >= allergen.getScore()) {
                workingScore = workingScore - allergen.getScore();
                allergens.add(allergen);
            }
        }

        allergens.sort(Comparator.comparingInt(Allergen::getScore));
    }

    public boolean isAllergicTo(final Allergen allergen) {
        return allergens.contains(allergen);
    }

    public List<Allergen> getList() {
        return allergens;
    }

}
