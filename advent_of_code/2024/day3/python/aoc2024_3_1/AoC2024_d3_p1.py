import re

def extract_data(input_name: str) -> str:
    lines = []
    with open(input_name, "r") as input:
        lines = input.readlines()

    return "".join(lines)


if __name__ == "__main__":
    data = extract_data("input.txt")
    print(data)
    # regex : mul\((\d+)\,(\d+)\)
    match = re.search(r'mul\((\d+)\,(\d+)\)', data)
    if match:
        print('found', match.group())
    else:
        print('did not find')
    to_compute = re.findall(r'mul\((\d+)\,(\d+)\)', data)
    print(to_compute)
    print(sum([int(t[0]) * int(t[1]) for t in to_compute])) #167090022
