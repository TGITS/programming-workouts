import re
from math import pow

if __name__ == "__main__":
    general_pattern = r'Card\s+(\d+):\s+(.+)'
    number_of_instances_by_card_number = {}
    sum_of_gains = 0
    with open ('input.txt', 'r') as input:
        for line in input:
            matches = re.fullmatch(general_pattern, line.strip())
            print("matches", matches)
            print("groupe 1:", matches.group(1))
            card_number = int(matches.group(1))
            print("card number:", card_number)
            if number_of_instances_by_card_number.get(card_number) is None:
                number_of_instances_by_card_number[card_number] = 0
            number_of_instances_by_card_number[card_number] += 1

            numbers = re.split("\s+\|\s+", matches.group(2))
            #print("numbers:", numbers)
            winning_numbers = set(re.split("\s+",numbers[0]))
            numbers_we_have = set(re.split("\s+",numbers[1]))
            #print("winning numbers:", winning_numbers)
            #print("numbers we have", numbers_we_have)
            winning_numbers_we_have = numbers_we_have.intersection(winning_numbers)
            #print("winning numbers_we have:", winning_numbers_we_have)
            number_of_winning_numbers_we_have = len(winning_numbers_we_have)
            print("Number of winning numbers", number_of_winning_numbers_we_have)
            if(number_of_winning_numbers_we_have > 0):
                for j in range(0,number_of_instances_by_card_number[card_number]):
                    for i in range(1, number_of_winning_numbers_we_have+1):
                        if number_of_instances_by_card_number.get(card_number+i) is None:
                            number_of_instances_by_card_number[card_number+i] = 0
                        number_of_instances_by_card_number[card_number+i] += 1
        
        print(sum(number_of_instances_by_card_number.values()))
               
            

        