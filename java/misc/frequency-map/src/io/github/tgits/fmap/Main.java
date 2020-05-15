package io.github.tgits.fmap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		List<String> characters = List.of("A", "B", "C", "A", "D", "A", "C");
		System.out.println("Séquence de caractères pour lequel il faut établir le tableau de fréquences : " + characters);
		System.out.println("Tableau de fréquences déterminé à l'ancienne : " + createFrequencyMapOldSchool(characters));
		System.out.println("Tableau de fréquences déterminé avec l'API Streams : " + createFrequencyMapWithStreams(characters));
	}

	private static <T> Map<T, Long> createFrequencyMapOldSchool(List<T> elements) {
		Map<T, Long> frequency = new HashMap<>();

		for (T element : elements) {
			long previousCount = 0L;
			if (frequency.get(element) != null)
				previousCount = frequency.get(element);

			frequency.put(element, previousCount + 1);
		}

		return frequency;
	}

	private static <T> Map<T, Long> createFrequencyMapWithStreams(List<T> elements) {
		return elements.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	}
}
