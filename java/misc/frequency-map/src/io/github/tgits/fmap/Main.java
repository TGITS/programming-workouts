package io.github.tgits.fmap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.System.out;
import static java.util.stream.Collectors.summingInt;

public class Main {

	public static void main(String[] args) {
		List<String> characters = List.of("A", "B", "C", "A", "D", "A", "C");
		out.println("Séquence de caractères pour lequel il faut établir le tableau de fréquences : " + characters);
		out.println("Tableau de fréquences créé en mode impératif avec des longs : " + createFrequencyMapOldSchool(characters));
		out.println("Tableau de fréquences déterminé avec l'API Streams avec des longs: " + createFrequencyMapWithStreams(characters));
		out.println("Tableau de fréquences créé en mode impératif avec des ints : " + createFrequencyMapOldSchoolWithInts(characters));
		out.println("Tableau de fréquences déterminé avec l'API Streams avec des longs: " + createFrequencyMapWithStreamsAndInts(characters));
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

	private static <T> Map<T, Integer> createFrequencyMapOldSchoolWithInts(List<T> elements) {
		Map<T, Integer> frequency = new HashMap<>();

		for (T element : elements) {
			int previousCount = 0;
			if (frequency.get(element) != null)
				previousCount = frequency.get(element);

			frequency.put(element, previousCount + 1);
		}

		return frequency;
	}

	private static <T> Map<T, Integer> createFrequencyMapWithStreamsAndInts(List<T> elements) {
		return elements.stream().collect(Collectors.groupingBy(Function.identity(), summingInt(element -> 1)));
	}
}
