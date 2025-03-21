def find_first_digit(s: str) -> str:
    for c in s:
        if c.isdigit():
            return c
    return ""
        
def find_last_digit(s: str) -> str:
    for c in s[::-1]:
        if c.isdigit():
            return c
    return ""

if __name__ == "__main__":
    file = open("input.txt", "r")
    lines = file.readlines()
    print(sum([int(find_first_digit(line) + find_last_digit(line)) for line in lines]))
