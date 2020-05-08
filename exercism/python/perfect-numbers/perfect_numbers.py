def classify(number):
    if (number <= 0):
        raise ValueError("Numbers less or equal to zero are not allowed")
    elif is_abundant(number):
        return "abundant"
    elif is_deficient(number):
        return "deficient"
    elif is_perfect(number):
        return "perfect"
    else:
       raise AssertionError("Value not allowed")

def is_perfect(number):
    return compute_divisors_sum(number) == number

def is_abundant(number):
    return compute_divisors_sum(number) > number

def is_deficient(number):
    return compute_divisors_sum(number) < number

def compute_divisors_sum(number):
    return sum([i for i in range(1,number) if number % i == 0])