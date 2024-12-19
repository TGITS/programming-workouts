import sys

sys.setrecursionlimit(1000000)

def get_data(input_name: str) -> tuple[set[str], list[str]]:
    available_towel_patterns, desired_designs = None, []
    with open(input_name, "r") as input:
        lines = input.readlines()
        i = 0
        available_towel_patterns = lines[i].strip().split(", ")
        i += 2
        while i < len(lines):
            desired_designs.append(lines[i].strip())
            i += 1

    return set(available_towel_patterns),desired_designs

def is_pattern_possible(head: str, tail: str,available_towel_patterns:set[str]) -> bool:
    # print("head:", head)
    # print("tail:", tail)
    if len(tail) == 0:
        return head in available_towel_patterns
    
    if head in available_towel_patterns:
        if is_pattern_possible(tail[0:1], tail[1:], available_towel_patterns):
            return True
        else: #Backtrack
            return is_pattern_possible(head + tail[0:1], tail[1:], available_towel_patterns)
    else:
        return is_pattern_possible(head + tail[0:1], tail[1:], available_towel_patterns)
    

if __name__ == "__main__":
    # available_towel_patterns, desired_designs = get_data("input_test.txt")
    available_towel_patterns, desired_designs = get_data("input.txt")
    print("Available towel patterns:", available_towel_patterns)
    print("Desired designs:", desired_designs)
    possible_designs = [design for design in desired_designs if is_pattern_possible( design[0:1], design[1:], available_towel_patterns)]
    print(possible_designs)
    print(len(possible_designs))

