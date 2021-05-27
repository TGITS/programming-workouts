import re


class PhoneNumber:

    def __init__(self, raw_number):
        number = re.sub(r'[\+\(\)\.\-\s]', '', raw_number)
        match = re.fullmatch(r'1?([2-9]\d{2})([2-9]\d{2})(\d{4})', number)

        if not match:
            raise ValueError("Incorrect Phone Number")

        self.area_code = match.group(1)
        self.part_1 = match.group(2)
        self.part_2 = match.group(3)
        self.number = self.area_code + self.part_1 + self.part_2

    def pretty(self):
        return f"({self.area_code})-{self.part_1}-{self.part_2}"
