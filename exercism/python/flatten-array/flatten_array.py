def flatten(iterable):
    flatten_iterable = []
    for elem in iterable:
        if elem is not None :
            if type(elem) is list:
                flatten_iterable.extend(flatten(elem))
            else:
                flatten_iterable.append(elem)
    
    return flatten_iterable
