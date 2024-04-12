package streams.experimentation;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * We have a list of strings  (values), a string which represent the base for a key.
 * For each value in values we want to put it as a value in a map associated with a key constructed
 * from the concatenation of the base for the key and the index of the value in the list.
 */
public class ListToMap {

    public Map<String, String> imperativeSolution(final String baseKey, final List<String> values) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < values.size(); i++) {
            map.put(baseKey + i, values.get(i));
        }
        return map;
    }

    public Map<String, String> firstFunctionalSolution(final String baseKey, final List<String> values) {
        Map<String, String> map = new HashMap<>();
        IntStream.range(0, values.size()).forEach(i -> map.put(baseKey + i, values.get(i)));
        return map;
    }

    public Map<String, String> secondFunctionalSolution(final String baseKey, final List<String> values) {
        return IntStream
                .range(0, values.size())
                .mapToObj(i -> new AbstractMap.SimpleEntry<>(baseKey + i, values.get(i)))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
    }
}
