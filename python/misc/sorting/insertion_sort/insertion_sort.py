def sort(to_sort):
    to_sort_size = len(to_sort)
    if to_sort_size >= 1:
        for j in range(1, to_sort_size):
            processed = to_sort[j]
            i = j-1
            while i >= 0 and to_sort[i] > processed:
                to_sort[i+1] = to_sort[i]
                i = i - 1
            to_sort[i+1] = processed


def sort_by_step(to_sort):
    to_sort_size = len(to_sort)
    if to_sort_size >= 1:
        for j in range(1, to_sort_size):
            processed = to_sort[j]
            i = j-1
            while i >= 0 and to_sort[i] > processed:
                to_sort[i+1] = to_sort[i]
                i = i - 1
            to_sort[i+1] = processed


class SortStepReporting:

    def __init__(self, initial_sequence, sequence_traversal_index=None, processed_element=None, search_position_index=None, sequence_during_sorting=None):
        self.initial_sequence = initial_sequence
        self.sequence_traversal_index = sequence_traversal_index
        self.processed_element = processed_element
        self.search_position_index = search_position_index
        self.sequence_during_sorting = sequence_during_sorting

    @property
    def initial_sequence(self):
        return self._initial_sequence

    @initial_sequence.setter
    def initial_sequence(self, value):
        self._initial_sequence = value

    @property
    def sequence_traversal_index(self):
        return self._sequence_traversal_index

    @sequence_traversal_index.setter
    def sequence_traversal_index(self, value):
        self._sequence_traversal_index = value

    @property
    def processed_element(self):
        return self._processed_element

    @processed_element.setter
    def processed_element(self, value):
        self._processed_element = value

    @property
    def search_position_index(self):
        return self._search_position_index

    @processed_element.setter
    def search_position_index(self, value):
        self._search_position_index = value

    @property
    def sequence_during_sorting(self):
        return self._sequence_during_sorting

    @processed_element.setter
    def sequence_during_sorting(self, value):
        self._sequence_during_sorting = value
