def is_valid(isbn):
    return sum([(index + 1) * int(number) for index, number in enumerate(list(isbn.replace('-',''))[::-1])]) % 11 == 0

