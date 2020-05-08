def is_isogram(string):
    """
        Test whether the given string parameter is an isogram or not
    """
    processed_input = string.lower().replace("-","").replace(" ", "")
    return len(set(processed_input)) == len(processed_input)
