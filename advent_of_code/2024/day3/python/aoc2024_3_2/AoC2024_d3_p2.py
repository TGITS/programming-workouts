import re


def extract_data(input_name: str) -> str:
    data = None
    with open(input_name, "r") as input:
        data = input.read().replace('\n','').strip()

    return "do()" + data + "don't()"


if __name__ == "__main__":
    data = extract_data("input.txt")
    filtered_data = "".join(re.findall(r"do\(\)(.*?)don't\(\)", data))
    # First we suppress from the data the pattern don't\(\).*do\(\))*
    # filtered_data = re.sub(r"don't\(\).*?do\(\)", '', data)
    to_compute = re.findall(r'mul\((\d+)\,(\d+)\)', filtered_data)
    print()
    print(to_compute)
    print()
    print(sum([int(t[0]) * int(t[1]) for t in to_compute])) #89823704
