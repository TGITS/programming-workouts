import re
import functools
import operator

if __name__ == "__main__":
    general_pattern = r'Game\s(\d+):(.+)'
    red_pattern = r'\s+(\d+)\s+red'
    blue_pattern = r'\s+(\d+)\s+blue'
    green_pattern = r'\s+(\d+)\s+green'
    sum_of_powers_of_set = 0
    with open ('input.txt', 'r') as input:
        for line in input:
            matches = re.fullmatch(general_pattern, line.strip())
            game_number = matches.group(1)
            results = matches.group(2)
            red_matches = re.findall(red_pattern, results)
            power_of_set = []
            if red_matches is not None:
                power_of_set.append(max([int(number) for number in red_matches]))

            blue_matches = re.findall(blue_pattern, results)
            if blue_matches is not None:
                power_of_set.append(max([int(number) for number in blue_matches]))
                    
            green_matches = re.findall(green_pattern, results)
            if green_matches is not None and len(green_matches) > 0:
                power_of_set.append(max([int(number) for number in green_matches]))
            
            sum_of_powers_of_set += functools.reduce(operator.mul,  power_of_set)  

    print(sum_of_powers_of_set)
        