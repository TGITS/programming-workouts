from functools import cache

def get_data(input_name: str) -> list[int]:
    data = []
    with open(input_name, "r") as input:
        for line in input:
            data.append(int(line.strip()))
    return data

@cache
def mix(value: int, secret_number:int) -> int:
    return value ^ secret_number

@cache
def prune(secret_number:int) -> int :
    return secret_number % 16777216

@cache
def compute_new_secret_number(secret_number:int) -> int:
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
def compute_nth_secret_number(secret_number:int, n:int) -> int:
    new_secret_number = secret_number
    for _ in range(n):
        new_secret_number = compute_new_secret_number(new_secret_number)
    return new_secret_number

def compute_sum_of_secret_number(data:list[int]) -> int:
    return sum([compute_nth_secret_number(num, 2000) for num in data])

if __name__ == "__main__":
    # data = get_data("input_test.txt")
    data = get_data("input.txt")
    #print("data:", data)

    print(compute_sum_of_secret_number(data))