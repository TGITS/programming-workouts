# As you observe them for a while, you find that the stones have a consistent behavior. Every time you blink, the stones each simultaneously change 
# according to the first applicable rule in this list:

# - If the stone is engraved with the number 0, it is replaced by a stone engraved with the number 1.
# - If the stone is engraved with a number that has an even number of digits, it is replaced by two stones. 
# The left half of the digits are engraved on the new left stone, and the right half of the digits are engraved on the new right stone. 
# (The new numbers don't keep extra leading zeroes: 1000 would become stones 10 and 0.)
# - If none of the other rules apply, the stone is replaced by a new stone; the old stone's number multiplied by 2024 is engraved on the new stone.


def extract_line_of_numbers(input_name: str) -> list[str]:
    lines = None
    with open(input_name, "r") as input:
        lines = [line.strip() for line in input.readlines()]

    return lines[0].split(' ')

def transform_number(number: str) -> str:
    if number == '0':
        return '1'
    elif len(number) % 2 == 0:
        half = len(number) // 2
        left = number[0:half].lstrip('0')
        if len(left) == 0:
            left = '0'
            right = number[half:].lstrip('0')
        if len(right) == 0:
            right = '0'
        return left + "," + right
    else:
        return str(int(number)*2024)


def transform_line(line: list[str]) -> list[str]:
    transform_line = []
    for number in line:
        if number == '0':
            transform_line.append('1')
        elif len(number) % 2 == 0:
            half = len(number) // 2
            left = number[0:half].lstrip('0')
            if len(left) == 0:
                left = '0'
            right = number[half:].lstrip('0')
            if len(right) == 0:
                right = '0'
            transform_line.append(left)
            transform_line.append(right)
        else:
            transform_line.append(str(int(number)*2024))
    return transform_line


def apply_transformation(line: list[str]): 
    transformed_line = line
    while True:
        transformed_line = transform_line(transformed_line)
        yield transformed_line

def apply_transformation_n_times(line: list[str], n:int) -> list[str]: 
    i = 0
    gen = apply_transformation(line)
    while i < n-1:
        next(gen)
        i += 1

    return gen


if __name__ == "__main__":
    numbers = extract_line_of_numbers("input_test.txt")
    #numbers = extract_line_of_numbers("input.txt")
    print(numbers)
    #transformed_line = apply_transformation_n_times(numbers, 25)
    #print(transformed_line)
    gen = apply_transformation_n_times(numbers, 25)
    print(len(list(gen)))


