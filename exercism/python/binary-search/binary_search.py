def find(search_list, value):
    min_index = 0
    max_index = len(search_list) - 1

    while min_index < max_index:
        middle_index = ((max_index - min_index) // 2) + min_index
        if value == search_list[middle_index]:
            return middle_index
        elif value > search_list[middle_index]:
            min_index = middle_index + 1
        else:
            max_index = middle_index - 1

    if min_index == max_index:
        if value == search_list[min_index]:
            return min_index
        else:
            raise ValueError(f"Value {value} not found")
    raise ValueError(f"Value {value} not found")
