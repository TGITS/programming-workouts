def is_paired(input_string):

    if len(input_string) == 0:
        return True

    pairing_char = {'}': '{', ')': '(', ']': '['}
    stack = []

    for c in input_string:
        if c in pairing_char.values():
            stack.append(c)
        elif c in pairing_char.keys() and (len(stack) == 0 or stack.pop() != pairing_char[c]):
            return False

    return len(stack) == 0
