def square(number):
    if number <= 0 or number > 64:
        raise ValueError("The provided number must be in the range 1 to 64")
    return 2 ** (number - 1)


def total():
    return sum([2**number for number in range(0,64)])
