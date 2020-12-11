import re


def is_valid(isbn):
    clean_isbn = isbn.replace('-', '')
    if re.fullmatch(r"\d{9}(\d|X)", clean_isbn, re.IGNORECASE):
        return sum([(index + 1) * to_int(digit_or_x)
                    for index, digit_or_x in enumerate(clean_isbn[::-1])]) % 11 == 0
    return False


def to_int(digit_as_string):
    if 'X' == digit_as_string.upper():
        return 10
    else:
        return int(digit_as_string)
