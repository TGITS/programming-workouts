from functools import cache


def get_data(input_name: str) -> list[int]:
    data = []
    with open(input_name, "r") as input:
        for line in input:
            data.append(int(line.strip()))
    return data


@cache
def mix(value: int, secret_number: int) -> int:
    return value ^ secret_number


@cache
def prune(secret_number: int) -> int:
    return secret_number % 16777216


@cache
def compute_new_secret_number(secret_number: int) -> int:
    new_secret_number = secret_number * 64
    new_secret_number = mix(new_secret_number, secret_number)
    new_secret_number = prune(new_secret_number)

    temp = new_secret_number // 32
    new_secret_number = mix(temp, new_secret_number)
    new_secret_number = prune(new_secret_number)

    temp = new_secret_number * 2048
    new_secret_number = mix(temp, new_secret_number)
    new_secret_number = prune(new_secret_number)

    return new_secret_number


@cache
def compute_nth_secret_number(secret_number: int, n: int) -> int:
    new_secret_number = secret_number
    for _ in range(n):
        new_secret_number = compute_new_secret_number(new_secret_number)
    return new_secret_number


def compute_sum_of_secret_number(data: list[int]) -> int:
    return sum([compute_nth_secret_number(num, 2000) for num in data])


def compute_all_prices_lists(data: list[int]) -> list[list[int]]:
    return [compute_prices_list(secret_number, 2000) for secret_number in data]


def compute_prices_list(secret_number: int, n: int) -> list[int]:
    # tuple representing the price and the variation relatively to the previous price
    prices_list = [(int(str(secret_number)[-1]), None)]
    new_secret_number = secret_number
    for _ in range(n):
        new_secret_number = compute_new_secret_number(new_secret_number)
        previous_tuple = prices_list[-1]
        price = int(str(new_secret_number)[-1])
        prices_list.append((price, price - previous_tuple[0]))
    return prices_list


def find_optimal_sequence(prices_lists):
    sum_by_sequence = {}
    for prices_list in prices_lists:
        seen_sequences = set()
        for s1, s2, s3, s4 in zip(
            prices_list[1:], prices_list[2:], prices_list[3:], prices_list[4:]
        ):
            sequence = (s1[1], s2[1], s3[1], s4[1])
            if not sequence in seen_sequences:
                associated_price = s4[0]
                current_sum = sum_by_sequence.get(sequence, 0)
                current_sum += associated_price
                sum_by_sequence[sequence] = current_sum
                seen_sequences.add(sequence)

    return max(sum_by_sequence.values())


if __name__ == "__main__":
    data = get_data("input_test.txt")
    # data = get_data("input.txt")
    # print("data:", data)

    # print(compute_prices_list(123, 10))
    print(find_optimal_sequence(compute_all_prices_lists(data)))
    # 1614
