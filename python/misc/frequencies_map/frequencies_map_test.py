import unittest

from frequencies_map import create_frequencies_map


class FrequenciesMapTest(unittest.TestCase):
    def test_empty_list(self):
        self.assertEqual(create_frequencies_map([]), {})

    def test_single_element_list(self):
        self.assertEqual(create_frequencies_map(["a"]), {"a": 1})

    def test_several_identical_elements_list(self):
        self.assertEqual(create_frequencies_map(["a", "a", "a"]), {"a": 3})

    def test_several_different_elements_list_sorted(self):
        self.assertEqual(create_frequencies_map(
            ["a", "a", "a", "b", "b", "c", "d", "d", "d", "d"]), {"a": 3, "b": 2, "c": 1, "d": 4})

    def test_several_different_elements_list_unsorted(self):
        self.assertEqual(create_frequencies_map(
            ["a", "b", "c", "a", "d", "a",   "d", "b",   "d", "d"]), {"a": 3, "b": 2, "c": 1, "d": 4})


if __name__ == "__main__":
    unittest.main()
