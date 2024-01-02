def append(list1, list2):
    for e in list2:
        list1.append(e)
    return list1


def concat(lists):
    concatenated_list = []
    for e in lists:
        if isinstance(e, list):
            append(concatenated_list, e)
        else:
            concatenated_list.append(e)
    return concatenated_list


def filter(function, list):
    filtered_list = []
    for e in list:
        if function(e):
            filtered_list.append(e)
    return filtered_list


def length(list):
    # without using built-in len function
    # size = 0
    # for _ in list:
    #     size += 1
    # return size
    # using built-in len function
    return len(list)


def map(function, list):
    mapped_list = []
    for e in list:
        mapped_list.append(function(e))
    return mapped_list


def foldl(function, list, initial):
    accumulator = initial
    for e in list:
        accumulator = function(accumulator, e)
    return accumulator


def foldr(function, list, initial):
    accumulator = initial
    for e in reverse(list):
        # Difference in the test locally and submitted
        # For submission version
        accumulator = function(accumulator, e)
        # Local tests versions
        # accumulator = function(e, accumulator)
    return accumulator


def reverse(list):
    # Complex version without using the slice notation
    # i = length(list) - 1
    # reversed_list = []
    # while i >= 0:
    #     reversed_list.append(list[i])
    #     i -= 1
    # return reversed_list
    # Simpler version using the slice notation
    return list[::-1]
