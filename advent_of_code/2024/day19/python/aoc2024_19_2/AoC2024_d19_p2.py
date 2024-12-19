from functools import cache
import sys


sys.setrecursionlimit(1000000)

def get_data(input_name: str) -> tuple[set[str], set[str]]:
    available_towel_patterns, desired_designs = None, []
    with open(input_name, "r") as input:
        lines = input.readlines()
        i = 0
        available_towel_patterns = lines[i].strip().split(", ")
        i += 2
        while i < len(lines):
            desired_designs.append(lines[i].strip())
            i += 1

    return frozenset(available_towel_patterns),frozenset(desired_designs)

@cache
def count_possible_patterns(desired_design:str, available_patterns:set[str]) -> int :
    if len(desired_design) == 0:
        return 1
    
    count = 0
    for pattern in available_patterns:
        if desired_design.startswith(pattern):
            count += count_possible_patterns(desired_design.removeprefix(pattern), available_patterns)

    return count
    

if __name__ == "__main__":
    # available_towel_patterns, desired_designs = get_data("input_test.txt")
    available_towel_patterns, desired_designs = get_data("input.txt")
    print("Available towel patterns:", available_towel_patterns)
    print("Desired designs:", desired_designs)
    sum_of_designs = 0
    for design in desired_designs:
        sum_of_designs += count_possible_patterns(design, available_towel_patterns)
    print(sum_of_designs)

