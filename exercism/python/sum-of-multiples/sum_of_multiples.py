def sum_of_multiples(limit, multiples):
    return sum({i for multiple in filter(lambda x: x > 0, multiples) for i in range(1, limit) if i % multiple == 0})
