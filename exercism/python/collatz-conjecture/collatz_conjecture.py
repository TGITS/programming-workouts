def steps(number):

    if number < 1:
        raise ValueError("The given value must be a positive integer")

    step = 0
    while True:
        if number == 1:
            return step

        if number % 2 == 0:
            number = number / 2
        else:
            number = 3 * number + 1

        step += 1
