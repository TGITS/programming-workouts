import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Flattener {

    @SuppressWarnings("unchecked")
    public List<Object> flatten(List<Object> list) {
        return (List<Object>) list.stream()
                .filter(Objects::nonNull)
                .reduce(new ArrayList<>(), (previousAccumulatedElem, currentElem) -> accumulate((List<Object>) previousAccumulatedElem, currentElem));
    }

    @SuppressWarnings("unchecked")
    private List<Object> accumulate(List<Object> previousAccumulatedElem, Object currentElem) {
        if (currentElem instanceof List) {
            previousAccumulatedElem.addAll(flatten((List<Object>) currentElem));
        } else {
            previousAccumulatedElem.add(currentElem);
        }
        return previousAccumulatedElem;
    }

}