# correct value : 56017

import re

# get digit as str
def extract_digit(num:str) -> str:
    num_value_by_name = {
    'one' : '1',
    'two' : '2',
    'three': '3',
    'four': '4',
    'five': '5',
    'six': '6',
    'seven': '7',
    'eight': '8',
    'nine': '9',
    }

    if num.isdigit():
        return num
    else:
        return num_value_by_name[num]

if __name__ == "__main__":
    pattern = r'(?=(one|two|three|four|five|six|seven|eight|nine|\d))'

    with open ('input.txt', 'r') as input:
        sum = 0
        for line in input:
            matches = re.findall(pattern, line.strip())

            left_index = 0
            right_index = len(matches) - 1

            sum += (int(extract_digit(matches[left_index]) + extract_digit(matches[right_index])))
        
        print(sum)
