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

    def __init__(self, initial_sequence):
        self.initial_sequence = initial_sequence
        self.sequence_traversal_index = None
        self.processed_element = None
        self.search_position_index = None
        self.sequence_during_sorting = None

    def update(self, sequence_traversal_index, processed_element, search_position_index, sequence_during_sorting):
        self.sequence_traversal_index = sequence_traversal_index
        self.processed_element = processed_element
        self.search_position_index = search_position_index
        self.sequence_during_sorting = sequence_during_sorting
