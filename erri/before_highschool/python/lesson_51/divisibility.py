def numbers_divisible_by_5_and_7_between_values(start_value, end_value):
    result = []
    for e in range(start_value, end_value + 1, 5):
        if e % 7 == 0:
            result.append(e)
    return result


def numbers_divisible_by_5_and_13_between_values(start_value, end_value):
    result = []
    for e in range(start_value, end_value + 1, 5):
        if e % 13 == 0:
            result.append(e)
    return result


if __name__ == "__main__":
    print(numbers_divisible_by_5_and_7_between_values(300, 450))
    print(numbers_divisible_by_5_and_13_between_values(300, 450))
