# As you observe them for a while, you find that the stones have a consistent behavior. Every time you blink, the stones each simultaneously change 
# according to the first applicable rule in this list:

# - If the stone is engraved with the number 0, it is replaced by a stone engraved with the number 1.
# - If the stone is engraved with a number that has an even number of digits, it is replaced by two stones. 
# The left half of the digits are engraved on the new left stone, and the right half of the digits are engraved on the new right stone. 
# (The new numbers don't keep extra leading zeroes: 1000 would become stones 10 and 0.)
# - If none of the other rules apply, the stone is replaced by a new stone; the old stone's number multiplied by 2024 is engraved on the new stone.
import functools

def extract_line(input_name: str) -> list[str]:
    lines = None
    with open(input_name, "r") as input:
        lines = [line.strip() for line in input.readlines()]

    return lines[0].split(' ')

def iterate(numbers: tuple[int, ...], times: int) -> int:
  return sum(count_split(number, times) for number in numbers)

@functools.cache
def count_split(number:str, times:int) -> int:
    if times == 0:
        return 1
    if number == '0':
        return count_split('1', times - 1)
    if len(number) % 2 == 0:
        half = len(number) // 2
        left = number[0:half].lstrip('0')
        if len(left) == 0:
            left = '0'
        right = number[half:].lstrip('0')
        if len(right) == 0:
            right = '0'
        return iterate((left,right), times-1)
    return count_split(str(int(number)*2024), times-1)


if __name__ == "__main__":
    #numbers = extract_line_of_numbers("input_test.txt")
    numbers = extract_line("input.txt")
    print(numbers)
    #print(transformed_line)
    print(iterate(numbers, 75))
