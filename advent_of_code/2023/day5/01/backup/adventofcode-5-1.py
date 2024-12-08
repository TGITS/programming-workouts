## Code du challenge de la veille - Pas le code pour le 5ème jour
import re
from math import pow

if __name__ == "__main__":
    general_pattern = r"Card\s+\d+:\s+(.+)"
    sum_of_gains = 0
    with open("input.txt", "r") as input:
        for line in input:
            matches = re.fullmatch(general_pattern, line.strip())
            numbers = re.split("\s+\|\s+", matches.group(1))
            print("numbers:", numbers)
            winning_numbers = set(re.split("\s+", numbers[0]))
            numbers_we_have = set(re.split("\s+", numbers[1]))
            print("winning numbers:", winning_numbers)
            print("numbers we have", numbers_we_have)
            winning_numbers_we_have = numbers_we_have.intersection(winning_numbers)
            print("winning numbers_we have:", winning_numbers_we_have)
            number_of_winning_numbers_we_have = len(winning_numbers_we_have)
            if number_of_winning_numbers_we_have > 0:
                sum_of_gains += pow(2, (number_of_winning_numbers_we_have - 1))

    print(int(sum_of_gains))
