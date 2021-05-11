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

        sum_for_odd_index = sum(
            [luhn_double(int(c)) for c in self.card_num[-2::-2]]
        )
        sum_for_even_index = sum([int(c) for c in self.card_num[::-2]])

        return (sum_for_odd_index + sum_for_even_index) % 10 == 0
