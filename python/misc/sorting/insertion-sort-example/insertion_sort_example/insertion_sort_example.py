def insertion_sort(array):
    array_len = len(array)
    for position in range(1, array_len):
        insert(array, position, array[position])


def insert(array, position, value_to_insert):
    i = position - 1
    while i >= 0 and array[i] > value_to_insert:
        array[i+1] = array[i]
        i = i - 1
    array[i+1] = value_to_insert
