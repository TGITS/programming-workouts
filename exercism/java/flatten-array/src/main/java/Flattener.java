import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Flattener {

	public List<Object> flatten(List<Object> list) {
		return flattenToStream(list).collect(Collectors.toList());
	}

	@SuppressWarnings("unchecked")
	private Stream<Object> flattenToStream(Object currentElem) {
		if (currentElem instanceof List) {
			return ((List<Object>) currentElem).stream().flatMap(this::flattenToStream);
		} else {
			return Stream.ofNullable(currentElem);
		}
	}

}