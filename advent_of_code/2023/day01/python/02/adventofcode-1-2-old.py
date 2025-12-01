# correct value : 56017

def convert_to_number(s:str) -> str:
    conversion_list = [
        ("one","1"),
        ("two","2"),
        ("three","3"),
        ("four","4"),
        ("five","5"),
        ("six","6"),
        ("seven","7"),
        ("eight","8"),
        ("nine","9")
    ]
    temp = s[:]
    for couple in conversion_list:
        temp = temp.replace(couple[0],couple[1])
    return temp

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
    with open ('input.txt', 'r') as input:
        with open ('output.txt', 'w') as output:
            sum = 0
            for num_line,line in enumerate(input):
                current_line = line.strip()
                output.write(str(num_line) + " - " + current_line + "\n")
                converted_line = convert_to_number(current_line)
                output.write(str(num_line) + " - " + converted_line+ "\n")
                found_number = find_first_digit(converted_line) + find_last_digit(converted_line)
                output.write(str(num_line) + " - " + str(found_number) + "\n")
                sum += (int(found_number))
        
            output.write(str(sum))
            print(sum)
