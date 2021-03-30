import re
import itertools


class Luhn:
    def __init__(self, card_num):
        self.card_num = re.sub(r"\s+", "", card_num)

    def valid(self):
        if len(self.card_num) <= 1:
            return False

        if re.search(r"[^0-9]", self.card_num):
            return False

        def luhn_double(num):
            product_by_2 = num * 2
            return (product_by_2 - 9) if product_by_2 > 9 else product_by_2

        number_of_digits = len(self.card_num)
        sum_for_odd_index = sum([luhn_double(int(c)) for c in itertools.islice(
            reversed(self.card_num), 1, number_of_digits, 2)])
        sum_for_even_index = sum([int(c) for c in itertools.islice(
            reversed(self.card_num), 0, number_of_digits, 2)])

        return (sum_for_odd_index + sum_for_even_index) % 10 == 0
