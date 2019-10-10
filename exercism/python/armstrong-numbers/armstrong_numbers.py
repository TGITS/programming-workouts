def is_armstrong_number(number):
    number_as_string = str(number)
    power = len(number_as_string)
    return number == sum([int(digit)**power for digit in number_as_string])
