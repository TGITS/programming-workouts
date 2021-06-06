import divisibility


def test_divisible_numbers_by_5_and_7_between_1_10():
    numbers = divisibility.numbers_divisible_by_5_and_7_between_values(0, 10)
    print(numbers)
    assert 1 == len(numbers)
    assert [0] == numbers


def test_divisible_numbers_by_5_and_7_between_1_35():
    numbers = divisibility.numbers_divisible_by_5_and_7_between_values(0, 35)
    print(numbers)
    assert 2 == len(numbers)
    assert [0, 35] == numbers


def test_divisible_numbers_by_5_and_7_between_300_450():
    numbers = divisibility.numbers_divisible_by_5_and_7_between_values(300, 450)
    assert 4 == len(numbers)
    assert [315, 350, 385, 420] == numbers


def test_divisible_numbers_by_5_and_13_between_1_10():
    numbers = divisibility.numbers_divisible_by_5_and_13_between_values(0, 10)
    assert 1 == len(numbers)
    assert [0] == numbers


def test_divisible_numbers_by_5_and_13_between_1_85():
    numbers = divisibility.numbers_divisible_by_5_and_13_between_values(0, 85)
    assert 2 == len(numbers)
    assert [0, 65] == numbers


def test_divisible_numbers_by_5_and_13_between_300_450():
    numbers = divisibility.numbers_divisible_by_5_and_13_between_values(300, 450)
    assert 2 == len(numbers)
    assert [325, 390] == numbers
