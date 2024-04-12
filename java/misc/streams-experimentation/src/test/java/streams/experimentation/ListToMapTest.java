package streams.experimentation;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ListToMapTest {

    private ListToMap listToMap = new ListToMap();

    @Test
    void givenAListAndABaseKeyShouldHaveAMapWithImperativeSolution() {
        List<String> values = List.of("value_0", "value_1", "value_2");
        String baseKey = "base_key_";
        Map<String, String> expectedMap = Map.of(baseKey + "0", values.get(0), baseKey + "1", values.get(1), baseKey + "2", values.get(2));


        Map<String, String> computedMap = listToMap.imperativeSolution(baseKey, values);

        assertThat(computedMap.keySet()).containsExactlyInAnyOrder(baseKey + "0", baseKey + "1", baseKey + "2");
        assertThat(computedMap.values()).containsExactlyInAnyOrder("value_0", "value_1", "value_2");

        assertThat(computedMap).containsEntry(baseKey + "0", "value_0").containsEntry(baseKey + "1", "value_1").containsEntry(baseKey + "2", "value_2");
    }

    @Test
    void givenAListAndABaseKeyShouldHaveAMapWithFirstFunctionalSolution() {
        List<String> values = List.of("value_0", "value_1", "value_2");
        String baseKey = "base_key_";
        Map<String, String> expectedMap = Map.of(baseKey + "0", values.get(0), baseKey + "1", values.get(1), baseKey + "2", values.get(2));


        Map<String, String> computedMap = listToMap.firstFunctionalSolution(baseKey, values);

        assertThat(computedMap.keySet()).containsExactlyInAnyOrder(baseKey + "0", baseKey + "1", baseKey + "2");
        assertThat(computedMap.values()).containsExactlyInAnyOrder("value_0", "value_1", "value_2");

        assertThat(computedMap).containsEntry(baseKey + "0", "value_0").containsEntry(baseKey + "1", "value_1").containsEntry(baseKey + "2", "value_2");
    }

    @Test
    void givenAListAndABaseKeyShouldHaveAMapWithSecondFunctionalSolution() {
        List<String> values = List.of("value_0", "value_1", "value_2");
        String baseKey = "base_key_";
        Map<String, String> expectedMap = Map.of(baseKey + "0", values.get(0), baseKey + "1", values.get(1), baseKey + "2", values.get(2));


        Map<String, String> computedMap = listToMap.secondFunctionalSolution(baseKey, values);

        assertThat(computedMap.keySet()).containsExactlyInAnyOrder(baseKey + "0", baseKey + "1", baseKey + "2");
        assertThat(computedMap.values()).containsExactlyInAnyOrder("value_0", "value_1", "value_2");

        assertThat(computedMap).containsEntry(baseKey + "0", "value_0").containsEntry(baseKey + "1", "value_1").containsEntry(baseKey + "2", "value_2");
    }
}
