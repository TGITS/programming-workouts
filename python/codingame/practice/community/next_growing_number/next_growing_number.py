# Next Growing Number : https://www.codingame.com/ide/puzzle/next-growing-number
def compute_next_growing_number(number):
    if number.isdigit():
        next_number = str(int(number) + 1)
        if len(next_number) == 1:
            return next_number

        digits = list(next_number)
        index = 0
        no_modification = True
        while no_modification and index < len(digits)-1:
            if digits[index] > digits[index+1]:
                digits[index+1] = digits[index]
                no_modification = False
            index += 1

        while index < len(digits)-1:
            digits[index+1] = digits[index]
            index += 1

        return "".join(digits)

    raise ValueError("The provided value should be a number")


if __name__ == "__main__":
    n = input()
    print(compute_next_growing_number(n))
