import unittest

from frequencies_map import frequencies_map_with_comprehension_from, frequencies_map_with_for_from, frequencies_map_with_map_from, frequencies_map_with_counter_from


class FrequenciesMapWithForTest(unittest.TestCase):
    def test_empty_list(self):
        self.assertEqual(frequencies_map_with_for_from([]), {})

    def test_single_element_list(self):
        self.assertEqual(frequencies_map_with_for_from(["a"]), {"a": 1})

    def test_several_identical_elements_list(self):
        self.assertEqual(frequencies_map_with_for_from(
            ["a", "a", "a"]), {"a": 3})

    def test_several_different_elements_list_sorted(self):
        self.assertEqual(frequencies_map_with_for_from(
            ["a", "a", "a", "b", "b", "c", "d", "d", "d", "d"]), {"a": 3, "b": 2, "c": 1, "d": 4})

    def test_several_different_elements_list_unsorted(self):
        self.assertEqual(frequencies_map_with_for_from(
            ["a", "b", "c", "a", "d", "a", "d", "b", "d", "d"]), {"a": 3, "b": 2, "c": 1, "d": 4})


class FrequenciesMapWithMapTest(unittest.TestCase):
    def test_empty_list(self):
        self.assertEqual(frequencies_map_with_map_from([]), {})

    def test_single_element_list(self):
        self.assertEqual(frequencies_map_with_map_from(["a"]), {"a": 1})

    def test_several_identical_elements_list(self):
        self.assertEqual(frequencies_map_with_map_from(
            ["a", "a", "a"]), {"a": 3})

    def test_several_different_elements_list_sorted(self):
        self.assertEqual(frequencies_map_with_map_from(
            ["a", "a", "a", "b", "b", "c", "d", "d", "d", "d"]), {"a": 3, "b": 2, "c": 1, "d": 4})

    def test_several_different_elements_list_unsorted(self):
        self.assertEqual(frequencies_map_with_map_from(
            ["a", "b", "c", "a", "d", "a", "d", "b", "d", "d"]), {"a": 3, "b": 2, "c": 1, "d": 4})


class FrequenciesMapWithComprehensionTest(unittest.TestCase):
    def test_empty_list(self):
        self.assertEqual(frequencies_map_with_comprehension_from([]), {})

    def test_single_element_list(self):
        self.assertEqual(
            frequencies_map_with_comprehension_from(["a"]), {"a": 1})

    def test_several_identical_elements_list(self):
        self.assertEqual(frequencies_map_with_comprehension_from(
            ["a", "a", "a"]), {"a": 3})

    def test_several_different_elements_list_sorted(self):
        self.assertEqual(frequencies_map_with_comprehension_from(
            ["a", "a", "a", "b", "b", "c", "d", "d", "d", "d"]), {"a": 3, "b": 2, "c": 1, "d": 4})

    def test_several_different_elements_list_unsorted(self):
        self.assertEqual(frequencies_map_with_comprehension_from(
            ["a", "b", "c", "a", "d", "a", "d", "b", "d", "d"]), {"a": 3, "b": 2, "c": 1, "d": 4})


class FrequenciesMapWithCounterTest(unittest.TestCase):
    def test_empty_list(self):
        self.assertEqual(frequencies_map_with_counter_from([]), {})

    def test_single_element_list(self):
        self.assertEqual(
            frequencies_map_with_counter_from(["a"]), {"a": 1})

    def test_several_identical_elements_list(self):
        self.assertEqual(frequencies_map_with_counter_from(
            ["a", "a", "a"]), {"a": 3})

    def test_several_different_elements_list_sorted(self):
        self.assertEqual(frequencies_map_with_counter_from(
            ["a", "a", "a", "b", "b", "c", "d", "d", "d", "d"]), {"a": 3, "b": 2, "c": 1, "d": 4})

    def test_several_different_elements_list_unsorted(self):
        self.assertEqual(frequencies_map_with_counter_from(
            ["a", "b", "c", "a", "d", "a", "d", "b", "d", "d"]), {"a": 3, "b": 2, "c": 1, "d": 4})


if __name__ == "__main__":
    unittest.main()
