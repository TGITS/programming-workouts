def is_valid(isbn):
    sum([index * int(number) for index, number in enumerate(isbn.replace('-',
                                                                         '').split().reverse())]) % 11 == 0
